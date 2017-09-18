#include <mpi.h>
#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <unistd.h>

typedef struct {
    bool *data;
    int length;
    int start;
    int end;
} sieve_t;

void print_sieve(sieve_t *sieve, int rank){
    printf("sieve@%p[%d](%d-%d]: \t", sieve->data, rank, sieve->start, sieve->end);
    for(int i=0; i<sieve->length; i++){
        if(sieve->data[i] == false){
            printf("0");
        }
        else{
            printf("1");
        }
        if(i%10 == 0 && i>1 && i<sieve->length-1){
            printf(" ");
        }
    }
    printf("\n");
}
/**
*   bool *sieve: actual sieve, partitioned to calculate the range between [rank*length,(rank*length)+length]
*   int index: actual index on the sieve to be tested
*   returns: value of the lesser prime detected. -1 if none.
*/
int eratosthenes(sieve_t *sieve, int index){
    int start_index = (sieve->start / index);
    start_index = start_index * index;
    for(int i=start_index; i < sieve->end; i+= index){
        //if it's in range
        if(i < sieve->end && i >= sieve->start && i != index){
            int idx = i - sieve->start;
            // printf("sieve: %d, start_index=%d, end_index=%d, i=%d, idx=%d\n", index, start_index,sieve->end, i, idx);
            sieve->data[idx] = false;            
        }
    }

    if(index >= sieve->end) return -1;
    else{
        //patch starting line
        start_index = index < sieve->start ? sieve->start : index;
        start_index++;

        // printf("index=%d, start_index=%d\n", index, start_index);
        for(int i=start_index; i < sieve->end; i++){
            int idx = i - sieve->start;
            if(sieve->data[idx] == true && i > 2){
                return i;
            }
        }
    }
    return -1;
}

int get_minimal_prime(sieve_t *sieve, int index){
    int start_index = (sieve->start / index);
    start_index = start_index * index;

    for(int i=start_index; i < sieve->end; i++){
        //if it's in range
        if(i < sieve->end && i >= sieve->start){
            int idx = i - sieve->start;
            if(sieve->data[idx] == true)
                return i;       
        }
    }
    return -1;
}

void init_sieve(sieve_t *sieve, int length, int rank){
    sieve->data = malloc(sizeof(bool) * length);
    sieve->length = length;
    sieve->start = rank * length;
    sieve->end = sieve->start + length;
    for(int i=0; i<length; i++){
        sieve->data[i] = true;
    }
    return;
}


int main(int argc, char** argv) {
    // Initialize the MPI environment
    MPI_Init(&argc, &argv);

    if(argc < 2){
        printf("You need to pass the max integer as an argument in order to run this program.");
        exit(1);
    }

    int limit = atoi(argv[1]);
    // printf("limit=%d\n", limit);
    // Get the number of processes
    int world_size;
    MPI_Comm_size(MPI_COMM_WORLD, &world_size);

    // Get the rank of the process
    int world_rank;
    MPI_Comm_rank(MPI_COMM_WORLD, &world_rank);

    // Get the name of the processor
    char processor_name[MPI_MAX_PROCESSOR_NAME];
    int name_len;
    MPI_Get_processor_name(processor_name, &name_len);

    if(world_size < 2){
        printf("This program cannot be run with less than two workers.");
        exit(1);
    }

    int sieve_length = (limit / (world_size - 1)) + 1;

    if(world_rank == 0){
        // printf("[Main processor] Started.\n");
        for(int sieve_stage = 2; sieve_stage < 10; ){
            // printf("SIEVE STAGE %d\n", sieve_stage);
            for(int i=1; i<world_size; i++){
                // printf("[Main processor] Sending message %d to processor %d.\n", sieve_stage, i);
                MPI_Send(&sieve_stage, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
            }

            int next_stage = -1;
            for(int i=1; i<world_size; i++){
                // printf("[Main processor] Waiting for processor %d to reply.", i);
                int response = 0;
                MPI_Recv(&response, 1, MPI_INT, i, 0, MPI_COMM_WORLD,MPI_STATUS_IGNORE);
                if(response > 0 && next_stage == -1){
                    next_stage = response;
                }
                sieve_stage = next_stage;
                if(next_stage == -1){
                    break;
                }
                // printf("[Main processor] Processor %d to replied: %d\n",i, response );
                // sleep(1);
            }
        }
        
        for(int i=1; i<world_size; i++){
            int exit_signal = -1;
            // printf("[Main processor] Triggering exit signal %d.\n", i);
            MPI_Send(&exit_signal, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
            print_primes:{
                int count = 0;
                MPI_Recv(&count, 1, MPI_INT, i, 0, MPI_COMM_WORLD,MPI_STATUS_IGNORE);
                for(int j=0; j<count; j++){
                    int recieved = 0;
                    MPI_Recv(&recieved, 1, MPI_INT, i, 0, MPI_COMM_WORLD,MPI_STATUS_IGNORE);
                    printf(" %d", recieved);
                }

            }
        }
    }

    else if (world_rank > 0){
        int index_to_perform = 0;
        sieve_t sieve;
        init_sieve(&sieve, sieve_length, world_rank-1);

        MPI_Recv(&index_to_perform, 1, MPI_INT, 0, 0, MPI_COMM_WORLD,MPI_STATUS_IGNORE);
        while(index_to_perform >= 0){
            //perform erastothenes
            int lesser_prime = eratosthenes(&sieve, index_to_perform);
            // printf("Processor %s, rank %d, performed %d sieve\n", processor_name, world_rank, index_to_perform);
            // print_sieve(&sieve, 0);
            
            // return lesser_prime
            MPI_Send(&lesser_prime, 1, MPI_INT, 0, 0, MPI_COMM_WORLD);
            MPI_Recv(&index_to_perform, 1, MPI_INT, 0, 0, MPI_COMM_WORLD,MPI_STATUS_IGNORE);
        }

        //exit signal recieved, sending primes!

        deliver: {
            int count = 0;

            //count primes available
            for(int i=0; i < sieve.length; i++){
                if(sieve.data[i] == true) count ++;
            }
            MPI_Send(&count, 1, MPI_INT, 0, 0, MPI_COMM_WORLD);

            // send one by one, not going to bother sending arrays or dynamic sending since
            // it will probably confusing for revision.
            for(int i=0; i < sieve.length; i++){
                if(sieve.data[i] == true){
                    int idx = sieve.start + i;
                    MPI_Send(&idx, 1, MPI_INT, 0, 0, MPI_COMM_WORLD);
                };
            }

        }
        // terminate worker
        free(sieve.data);
    }

    // Finalize the MPI environment.
    MPI_Finalize();
}