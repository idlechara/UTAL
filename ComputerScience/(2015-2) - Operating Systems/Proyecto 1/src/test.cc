// Most of the code is "self-explanatory" by using representative names,
// comprensive messages and modularizing methods, so in my opinion no comments
// are needed unless the reader has no knowledge of POSIX pthreads and
// semaphores (which is not the case).
#include <stdlib.h>
#include <stdio.h>
#include <pthread.h>
#include <semaphore.h>
#include <unistd.h>
#include <errno.h>

int pot, n, h;
sem_t *bee_lock, *bear_lock;

// the following two methods are used to make error detection easier to the
// programmer. No magic here.
void wait_for(sem_t *target){
    if(sem_wait(target)){
        printf("Error on semaphore wait detected. Execution terminated.\n");
        exit(1);
    }
}

void notify(sem_t *target){
    if(sem_post(target)){
        printf("Error on semaphore post detected. Execution terminated.\n");
        exit(2);
    }
}

// AKA: a bee
void *bzzz(void *thread_id){
    while(true){
        wait_for(bee_lock);
        sleep(rand()%3);
        pot++;
        if(pot < h){
            printf("bzzz : bee %ld - My life for the hive - pot %d\n",
                   (long)thread_id, pot );
        }
        else{
            printf("bzzz : bee %ld - WE MUST AWAKE TEH BEAR - pot %d \n",
                   (long)thread_id, pot );
            notify(bear_lock);
            wait_for(bee_lock);
        }
        sleep(rand()%3);
        notify(bee_lock);
    }
    pthread_exit(NULL);
}

// AKA: the bear
void *leech(void *thread_id){
    while(true){
        wait_for(bear_lock);
        sleep(rand()%3);
        pot = 0;
        printf("leech : YUMMY! (now the pot is empty again)\n");
        sleep(rand()%3);
        notify(bee_lock);
    }
    pthread_exit(NULL);
}

int main (int argn, char **argv){
    n = atoi(argv[1]);
    h = atoi(argv[2]);
    pot = 0;
    srand(time(NULL));
    //unlinks thread descriptors and creates new ones.
    //sem_unlink("/bee");
    //sem_unlink("/bear");
    sem_init(bee_lock, 0, 0);
    sem_init(bear_lock, 0, 0);
    //bee_lock = sem_open("/bee", O_CREAT|O_EXCL,0,0);
    //bear_lock = sem_open("/bear", O_CREAT|O_EXCL,0,0);
    if(errno){
        printf("main: Error detected on semaphore linking.\n");
        exit(5);
    }
    
    //creates the threads
    pthread_t *bees = (pthread_t *) malloc(sizeof(pthread_t) * n);
    pthread_t bear;
    int i=0;
    for (i=0; i<n; i++){
        printf("main: Creating bee %d... \n", i);
        if (pthread_create(&bees[i], NULL, bzzz, (void *)((long)i))){
            printf("main: Unable to create bee %d. Execution terminated.\n", i);
            exit(3);
        }
    }
    printf("main: Creating bear... \n");
    pthread_create(&bear, NULL, leech, (void *)((long)n));
    
    //starts the simulation
    printf("main: Releasing locks! \n");
    notify(bee_lock);
    
    //since the program runs forever, main thread is detached
    printf("main: Detached\n");
    pthread_exit(NULL);
}
