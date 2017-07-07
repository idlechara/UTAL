#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <semaphore.h>
#include <errno.h>

#define MAX_DELAY 3
#define NUM_SMOKERS 3
#define MESSAGE_INIT char messages[3][20] = {"TOBACCO and MATCHES", "PAPER and MATCHES", "PAPER and TOBACCO"};

sem_t mutex[NUM_SMOKERS+1];
int count[NUM_SMOKERS];

void *smoker(void *id) {
	MESSAGE_INIT;
   long tid = (long)id;
   printf("Smoker (%d) came to the store!\n", tid+1);
   while(--count[tid]){
      printf("Smoker %ld wants to smoke, but it lacks materials %s\n", tid+1, messages[tid]);
	  int error =0;
	  error |= sem_wait(&mutex[tid]) | sem_wait(&mutex[3]);
	  printf("Smoker %ld grabs %s from the table and smokes.\n", tid+1, messages[tid]);
	  error |= sem_post(&mutex[3]);
	  if(error)
		  printf("ERROR ON SMOKER THREAD %d\n" , tid+1);
      sleep(1+rand()%MAX_DELAY);
   }
   printf("Smoker %d left.\n", tid+1);
   pthread_exit(NULL);
}

void *dealer(void *id) {
	MESSAGE_INIT;
   long tid = (long)id;
   printf("Dealer opened the store!\n");
   while(true){
	  int error = 0;
	  error |= sem_wait(&mutex[3]);
		int material = 0, i=0;
		while(i+1)material += count[i--];
		if (!material) break;
	  material = rand()%NUM_SMOKERS;
	  printf("testing smoker %d\n", material);
      if(count[material]==0){
      	sem_post(&mutex[3]);
      	continue;
      }
      printf("Dealer puts items %s on the table.\n", messages[material]);
	  if(sem_post(&mutex[material]) | sem_post(&mutex[3])) 
	  	printf("ERROR ON DEALER THREAD.\n");
      sleep(1+rand()%MAX_DELAY);
   }

   printf("Dealer closed the store! BYEE!\n");
   pthread_exit(NULL);
}

int main (int argc, char const *argv[]){
	MESSAGE_INIT;
	srand(time(NULL));
	pthread_t smokers[NUM_SMOKERS];
	long i, j = i = NUM_SMOKERS;
	while(count[i] = atoi(argv[1])+1,-1 != sem_init(&mutex[i],0, (i==3)? 1 : 0) && i >= 0){i--;}
	while(i == -1 && j != -1 && 0 == pthread_create(&smokers[j], NULL, (j==3)? dealer : smoker, (void *)j)){j--;}
	pthread_exit(NULL);
}
