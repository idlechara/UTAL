#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <sys/shm.h>

#define MAX_RETRIES 100
#define SHARED_MEMORY_LENGHT 4096
#define SHARED_MEMORY_SIZE sizeof(size_t)*SHARED_MEMORY_LENGHT // up to 1024 buckets

typedef struct {
    int pid;
    char kind;
} process_description_t;


// I took this from my favourite IPC guide on all internet.
// And since it's pretty neat, I haven't found another way to
// make this work in a better way. ww
/*
** initsem() -- more-than-inspired by W. Richard Stevens' UNIX Network
** Programming 2nd edition, volume 2, lockvsem.c, page 295.
*/
int initsem(key_t key, int nsems)  /* key from ftok() */
{
    int i;
    union semun arg;
    struct semid_ds buf;
    struct sembuf sb;
    int semid;
    semid = semget(key, nsems, IPC_CREAT | IPC_EXCL | 0666);
    if (semid >= 0) { /* we got it first */
        sb.sem_op = 1;
        sb.sem_flg = 0;
        arg.val = 1;
        //printf("press return\n");
        //getchar();
        for (sb.sem_num = 0; sb.sem_num < nsems; sb.sem_num++) {
            /* do a semop() to "free" the semaphores. */
            /* this sets the sem_otime field, as needed below. */
            if (semop(semid, &sb, 1) == -1) {
                int e = errno;
                semctl(semid, 0, IPC_RMID); /* clean up */
                errno = e;
                return -1; /* error, check errno */
            }
        }
    } else if (errno == EEXIST) { /* someone else got it first */
        int ready = 0;
        semid = semget(key, nsems, 0); /* get the id */
        if (semid < 0) return semid; /* error, check errno */
        /* wait for other process to initialize the semaphore: */
        arg.buf = &buf;
        for (i = 0; i < MAX_RETRIES && !ready; i++) {
            semctl(semid, nsems - 1, IPC_STAT, arg);
            if (arg.buf->sem_otime != 0) {
                ready = 1;
            } else {
                sleep(1);
            }
        }
        if (!ready) {
            errno = ETIME;
            return -1;
        }
    } else {
        return semid; /* error, check errno */
    }
    return semid;
}

int main(int argc, char *argv[]) {
    int expected_processes=atoi(argv[1]);
    //printf("*****CONFIGURATION******\n");
    //printf("How many processes I should expect? ");
    //scanf("%d", &expected_processes);



    // Allocate shared memory space!
    key_t mem_key;
    int shmid;
    size_t *data;
    int mode;

    if ((mem_key = ftok("main.c", 'M')) == -1) {
        perror("ftok");
        exit(1);
    }

    if ((shmid = shmget(mem_key, SHARED_MEMORY_SIZE, 0644 | IPC_CREAT)) == -1) {
        perror("shmget");
        exit(1);
    }

    /* attach to the segment to get a pointer to it: */
    data = shmat(shmid, (void *) 0, 0);
    if (data == (size_t *) (-1)) {
        perror("shmat");
        exit(1);
    }

    // Zero mem
    for(int i=0; i < SHARED_MEMORY_LENGHT; i++){
        data[i] = 0;
    }
    data[0] = expected_processes;


    key_t sem_key;
    int semid;
    struct sembuf sb;
    sb.sem_num = 0;
    sb.sem_op = -1;  /* set to allocate resource */
    sb.sem_flg = SEM_UNDO;
    if ((sem_key = ftok("main.c", 'J')) == -1) {
        perror("ftok");
        exit(1);
    }
    const int stages = 3;
    // Grab a semaphore. It's assumed that you cannot grab any semaphore as long as you aren't in control of it.
    if ((semid = initsem(sem_key, expected_processes)) == -1) {
        perror("initsem");
        exit(1);
    }


    //for (int i = 0; i < stages; i++) {
        // Lock all semaphores
        for (int j = 0; j < expected_processes; j++) {
            sb.sem_num = j;
            if (semop(semid, &sb, 1) == -1) {
                perror("semop");
                exit(1);
            }
        }
        printf("Press [ENTER] to continue: OK");
        getchar();

        // Unlock all semaphores
        for (int j = 0; j < expected_processes; j++) {
            sb.sem_num = j;
            sb.sem_op = 1; /* free resource */
            if (semop(semid, &sb, 1) == -1) {
                perror("semop");
                exit(1);
            }
        }

    //}
    printf("Press [ENTER] to make the world burn to ashes: OK?");
    getchar();

    /* freeing resources*/
    union semun arg;
    if (semctl(semid, 0, IPC_RMID, arg) == -1) {
        perror("semctl");
        return 0;
    }
    /* detach from the segment: */
    if (shmdt(data) == -1) {
        perror("shmdt");
        exit(1);
    }

    return 0;
}
