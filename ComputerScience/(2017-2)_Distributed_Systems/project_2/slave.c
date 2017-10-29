#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <sys/shm.h>
#include <time.h>

#define MAX_RETRIES 100

#define SHARED_MEMORY_LENGHT 4096
#define SHARED_MEMORY_SIZE sizeof(size_t)*SHARED_MEMORY_LENGHT // up to 1024 buckets
#define STATUS_TRUE 1
#define STATUS_FALSE 0
#define STATUS_BIZANTINE -1

typedef struct {
    int pid;
    char kind;
} process_description_t;

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
        printf("press return\n");
        getchar();
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

    srand(time(NULL));   // should only be called once


    int general_id = atoi(argv[1]);
    //printf("*****CONFIGURATION******\n");
    //printf("Which general I'm I? ");
    //scanf("%d", &general_id);
    //printf("What's my status? (0: false, 1: true, -1: bizantine) ");
    int general_status = atoi(argv[2]);
    //scanf("%d", &general_status);


    // Allocate shared memory space!
    key_t mem_key;
    int shmid;
    size_t *data;
    int mode;

    if ((mem_key = ftok("main.c", 'M')) == -1) {
        perror("ftok");
        exit(1);
    }

    if ((shmid = shmget(mem_key, SHARED_MEMORY_SIZE, 0644)) == -1) {
        perror("shmget");
        exit(1);
    }

    /* attach to the segment to get a pointer to it: */
    data = shmat(shmid, (void *) 0, 0);
    if (data == (size_t *) (-1)) {
        perror("shmat");
        exit(1);
    }

    size_t expected_processes = data[0];

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


    printf("Total generals to expect %zu\n", expected_processes);
    for (int i = 0; i < stages; i++) {
        // Lock all semaphores
        sb.sem_num = general_id;
        if (semop(semid, &sb, 1) == -1) {
            perror("semop");
            exit(1);
        }

        switch (i) {
            case 0:
                printf("Stage 1 Sending status to others: \n");
                int status = 0;
                if (general_status == STATUS_TRUE) {
                    status = 1;
                }
                if (general_status == STATUS_FALSE) {
                    status = 0;
                }
                if (general_status == STATUS_BIZANTINE) {
                    status = (rand() % 2);      // returns a pseudo-random integer between 0 and RAND_MAX
                }

                for (size_t g_idx = 0; g_idx < expected_processes; g_idx++) {
                    printf("%p ",(void*)&data[(g_idx * expected_processes) + 1 + general_id]);
                    printf("General %d sends status %d to general %zu\n", general_id, status, g_idx);
                    data[(g_idx * expected_processes) + 1 + general_id] = (general_status==STATUS_BIZANTINE)? (rand() % 2) : status;
                }
                break;
            case 1: {
                printf("Stage 2: Constructing vectors...\n");
                size_t *vector = data + 1 + (general_id * expected_processes);
                size_t vector_length = expected_processes;

                size_t *recv_offset = data + 1 + (expected_processes * expected_processes);

                printf("Stage 3.1: Sending vector data...\n");
                for (size_t vector_idx=0; vector_idx < expected_processes; vector_idx++) {
                    for (size_t idx=0; idx < expected_processes; idx++) {
                        printf("W%p ",(void*)&recv_offset[idx + (expected_processes * expected_processes * vector_idx) + (expected_processes*general_id)]);
                        printf("R%p ",(void*)&vector[idx]);
                        recv_offset[idx + (expected_processes * expected_processes * vector_idx) + (expected_processes*general_id)] = (general_status==STATUS_BIZANTINE)? (rand() % 2) : vector[idx];
                        printf("General %zu recieved status %zu from general %zu\n", vector_idx, vector[idx], idx);
                    }
                }
            }
                break;
            case 2: {
                printf("Stage 3.2: Reading vector data...\n");
                size_t *recv_offset = data + 1 + (expected_processes * expected_processes) +
                                      (general_id * expected_processes * expected_processes);

                size_t *final_vector = malloc(sizeof(size_t) * expected_processes);
                for (size_t idx=0; idx < expected_processes; idx++) {
                    printf("[");
                    size_t count = 0;
                    for (size_t vector_idx=0; vector_idx < expected_processes; vector_idx++) {

                        printf("%p ",(void*)&recv_offset[idx + (expected_processes * vector_idx)]);
                        printf(" %zu ", recv_offset[idx + (expected_processes * vector_idx)]);
                        if(recv_offset[idx + (expected_processes * vector_idx)] == STATUS_TRUE){
                            count ++;
                        }
                    }
                    printf(" ]\n");
                    if(count > (expected_processes / 2)){
                        final_vector[idx] = STATUS_TRUE;
                    } else{
                        final_vector[idx] = STATUS_FALSE;
                    }
                }
                size_t final_countdown = 0;
                printf("Results are: [");
                for(int idx = 0; idx < expected_processes; idx ++){
                    if(final_vector[idx] == STATUS_TRUE) final_countdown++;
                    printf(" %zu ", final_vector[idx]);
                }
                printf(" ] \n");
                if(final_countdown > expected_processes/2){
                    printf("General %d will go to war!\n", general_id);
                }
                else{
                    printf("General %d will not go to war!\n", general_id);
                }
            }
                break;

        }


        sb.sem_op = 1; /* free resource */
        if (semop(semid, &sb, 1) == -1) {
            perror("semop");
            exit(1);
        }
    }

    /* detach from the segment: */
    if (shmdt(data) == -1) {
        perror("shmdt");
        exit(1);
    }


    return 0;
}
