#include <vector>
#include <iostream>
#include <stdlib.h>
#include <stdio.h>
#include <pthread.h>
#include <semaphore.h>
#include <unistd.h>
#include <errno.h>

using namespace std;


int car_idx, n, h;
sem_t **car_lock;
pthread_mutex_t loop_mutex;

void update_bridge(){
	cout << "adquiring lock...\n";
    pthread_mutex_lock(&loop_mutex);
	cout << "lock adquired! opening"<<car_idx<<"\n";
	if(car_idx < n){
	    car_idx++;
		sem_post(car_lock[car_idx]);
	}
    pthread_mutex_unlock(&loop_mutex);
	cout << "unlocking!\n";
}

// AKA: a bee
void *road(void *thread_id){
	long id = (long)thread_id;
	cout << "waiting on queue thread_id=" << id <<"\n";
	sem_wait(car_lock[id]);
	cout << "notified!\n";
	sleep(rand()%3);
	cout << "invoking another one.\n";
	update_bridge();
    pthread_exit(NULL);
}

int main (int argn, char **argv){
    n = atoi(argv[1]);
    srand(time(NULL));
    if(errno){
        printf("main: Error detected on semaphore linking.\n");
        exit(5);
    }
    pthread_mutex_init(&loop_mutex, NULL);
    //creates the threads
    pthread_t *cars = (pthread_t *) malloc(sizeof(pthread_t) * (n));
	car_lock = (sem_t **) malloc(sizeof(sem_t*) * (n));
    int i=0;
	car_idx = 0;
    for (i=0; i<n; i++){
        printf("main: Creating car %d... \n", i);
	    char buffer [50];
	    sprintf (buffer, "/car_count_%d", i);
	    sem_unlink(buffer);
		car_lock[i] = sem_open(buffer, O_CREAT|O_EXCL,0,0);
        if (pthread_create(&cars[i], NULL, road, (void *)((long)i))){
            printf("main: Unable to create car %d. Execution terminated.\n", i);
            exit(3);
        }
        sleep(rand()%3);
    }
    //starts the simulation
    printf("main: Releasing locks! \n");
    sem_post(car_lock[0]);
    sem_post(car_lock[1]);
    sem_post(car_lock[2]);
    //since the program runs forever, main thread is detached
    printf("main: Detached\n");
    pthread_exit(NULL);
}