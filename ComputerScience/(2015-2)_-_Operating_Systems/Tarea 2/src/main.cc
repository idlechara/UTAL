//I suposse that no comments are needed with understandable code.
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <errno.h>
#include <unistd.h>
#include <string.h>

int people_waiting = 0;
int loops = 0;
int max_people_on_roller_coaster = 0;

pthread_mutex_t queue_mutex;
pthread_mutex_t loop_mutex;
pthread_cond_t has_people;

void *put_people_on_queue(void *t){
  int i;
  long id = (long)t;
  pthread_mutex_lock(&loop_mutex);
  int loop = loops+1;
  pthread_mutex_unlock(&loop_mutex);
  while(loop != 0){
    pthread_mutex_lock(&queue_mutex);
    people_waiting++;
    printf("people_on_queue++           people_waiting=%d\tloops_remaining=%d\n", people_waiting, loops);
    if(people_waiting >= max_people_on_roller_coaster)
      pthread_cond_signal(&has_people);
    pthread_mutex_unlock(&queue_mutex);
    sleep(rand()%3);

    pthread_mutex_lock(&loop_mutex);
    loop = loops;
    pthread_mutex_unlock(&loop_mutex);
  }
  pthread_exit(NULL);
}

void *roller_coaster(void *t){
  int i;
  long id = (long)t;
  pthread_mutex_lock(&loop_mutex);
  int loop = loops;
  pthread_mutex_unlock(&loop_mutex);
  while(loop){
    printf("roller_coaster stopped.     people_waiting=%d\tloops_remaining=%d\n", people_waiting, loops);
    pthread_cond_wait(&has_people, &queue_mutex);
    people_waiting -= max_people_on_roller_coaster;
    printf("rolling!!!                  people_waiting=%d\tloops_remaining=%d\n", people_waiting, loops);
    pthread_mutex_unlock(&queue_mutex);
    sleep(rand()%7);
    sleep(1);
    pthread_mutex_lock(&loop_mutex);
    loop = --loops;
    pthread_mutex_unlock(&loop_mutex);
  }
  printf("roller_coaster day end.      people_waiting=%d\tloops_remaining=%d\n", people_waiting, loops);
  pthread_exit(NULL);
}

int main (int argc, char *argv[]){z
  srand(time(NULL));
  pthread_t threads[2];
  pthread_attr_t attr;
  long id1=1, id2=2;

  char arg_1[15], arg_2[15];
  int arg_1_len = strlen(argv[1]), arg_2_len = strlen(argv[2]);
  memcpy( arg_1, &argv[1][2], arg_1_len-2 );
  memcpy( arg_2, &argv[2][2], arg_2_len-2 );
  max_people_on_roller_coaster = atoi(arg_1);
  loops = atoi(arg_2);

  printf("Leido %s;%s\n",argv[1], argv[2]);
  printf("Variables son  Asientos=%d y Vueltas=%d\n", max_people_on_roller_coaster, loops);

  pthread_mutex_init(&queue_mutex, NULL);
  pthread_cond_init (&has_people, NULL);
  pthread_attr_init(&attr);
  pthread_attr_setdetachstate(&attr, PTHREAD_CREATE_JOINABLE);
  pthread_create(&threads[0], &attr, put_people_on_queue, (void *)id1);
  pthread_create(&threads[1], &attr, roller_coaster, (void *)id2);

  int i;
  for (i=0; i<2; i++) {
    pthread_join(threads[i], NULL);
  }
  printf ("%d people didn't managed to board on the roller coaster. Now they're buring the park. GG\n", people_waiting);
  printf ("Joining threads. Done.\n", 2);

  pthread_attr_destroy(&attr);
  pthread_mutex_destroy(&queue_mutex);
  pthread_mutex_destroy(&loop_mutex);
  pthread_cond_destroy(&has_people);
  pthread_exit(NULL);

}
