/*
 * Sandeep Heera
 * multi_thread_scheduler.c
 * This program is a multi-threaded application which
 * simulates a 16 core processor and associated scheduler.
 */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <pthread.h>
#include <unistd.h>
#include "project_3.h"

#define NUM_JOBS 30
#define NUM_THREADS 16
#define CREATE_FREQ 2

//queues
Queue *ready_queue, *io_queue, *completed_queue;

//create locks
pthread_mutex_t ready_lock =       PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t io_queue_lock =    PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t completed_lock =   PTHREAD_MUTEX_INITIALIZER;

//keep track of the number of jobs
int jobs_created = 0;
int jobs_completed = 0;

int main()
{
    srand(time(NULL));

    //array of threads
    pthread_t threads[NUM_THREADS];

    //create the queues
    ready_queue = create_queue();
    io_queue = create_queue();
    completed_queue = create_queue();

    //create the threads
    pthread_create(&threads[0],  NULL, cpu_handler, (void *)(long) 1);
    pthread_create(&threads[1],  NULL, cpu_handler, (void *)(long) 2);
    pthread_create(&threads[2],  NULL, cpu_handler, (void *)(long) 3);
    pthread_create(&threads[3],  NULL, cpu_handler, (void *)(long) 4);
    pthread_create(&threads[4],  NULL, cpu_handler, (void *)(long) 5);
    pthread_create(&threads[5],  NULL, cpu_handler, (void *)(long) 6);
    pthread_create(&threads[6],  NULL, cpu_handler, (void *)(long) 7);
    pthread_create(&threads[7],  NULL, cpu_handler, (void *)(long) 8);
    pthread_create(&threads[8],  NULL, io_handler,  (void *)(long) 9);
    pthread_create(&threads[9],  NULL, io_handler,  (void *)(long) 10);
    pthread_create(&threads[10], NULL, io_handler,  (void *)(long) 11);
    pthread_create(&threads[11], NULL, io_handler,  (void *)(long) 12);
    pthread_create(&threads[12], NULL, jobs_handler,(void *)(long) 13);
    pthread_create(&threads[13], NULL, jobs_handler,(void *)(long) 14);
    pthread_create(&threads[14], NULL, jobs_handler,(void *)(long) 15);
    pthread_create(&threads[15], NULL, jobs_handler,(void *)(long) 16);

    int i;
    for (i = 0; i < NUM_THREADS; i++) {
        pthread_join(threads[i], NULL);
    }

    printf("Jobs completed: %d, jobs created: %d", jobs_completed, jobs_created);

    //free the locks
    pthread_mutex_destroy(&ready_lock);
    pthread_mutex_destroy(&io_queue_lock);
    pthread_mutex_destroy(&completed_lock);

    //free the queues
    free(ready_queue);
    free(io_queue);
    free(completed_queue);

    return 0;
}

/*
 * Runs the job by putting the thread to sleep for the duration of the phase.
 * Also transitions the job to it's next phase or if it's in it's last phase,
 * pushes it to the completed queue.
 */
void run_job(Job *j){
    if(j->current_phase_type == CPU_PHASE){
        sleep(*(j->phase_type_and_duration));

        printf("Job id: %d ran for %d seconds for phase %d. There are %d phases remaining.\n\n",
               j->job_id,*(j->phase_type_and_duration), j->current_phase, j->nr_phases -
               j->current_phase);
    }
    else{
        sleep(*(j->phase_type_and_duration + 1));

        printf("Job id: %d ran for %d seconds for phase %d. There are %d phases remaining.\n\n",
               j->job_id,*(j->phase_type_and_duration + 1), j->current_phase, j->nr_phases -
               j->current_phase);
    }

    //determine where the job goes next
    if(j->current_phase == j->nr_phases){
        //get the completed queue lock
        pthread_mutex_lock(&completed_lock);

        //add to the completed queue
        enqueue(completed_queue, j);
        j->is_completed = true;
        printf("Adding job with id: %d to the completed queue.\n\n", j->job_id);

        //release the lock
        pthread_mutex_unlock(&completed_lock);
    }
    else{
        if(j->current_phase_type == CPU_PHASE){
            //get the IO queue lock to queue to run on IO
            pthread_mutex_lock(&io_queue_lock);

            //queue the job
            enqueue(io_queue, j);
            printf("Adding job with id: %d to the IO queue.\n\n", j->job_id);


            //increment the current phase
            j->current_phase++;

            //change the current phase type
            j->current_phase_type = IO_PHASE;

            //release the lock
            pthread_mutex_unlock(&io_queue_lock);
        }
        else{
            //get the CPU queue lock
            pthread_mutex_lock(&ready_lock);

            //queue the job
            enqueue(ready_queue, j);
            printf("Adding job with id: %d to the CPU queue.\n\n", j->job_id);

            //increment the current phase
            j->current_phase++;

            //change the current phase type
            j->current_phase_type = CPU_PHASE;

            //release the lock
            pthread_mutex_unlock(&ready_lock);
        }
    }
}

/*
 * This function will be passed in to threads that will handle
 * the task of running processes on the CPU.
 */
void *cpu_handler(void *t){
    int thread_id = (int)(long)t;

    while(jobs_completed < NUM_JOBS){
        //get the ready lock and check the queue
        pthread_mutex_lock(&ready_lock);
        Job *to_run = NULL;

        if(!is_empty(ready_queue)){
            to_run = dequeue(ready_queue);  //get the job from the queue
            printf("Thread %d just got job id: %d from the run queue. About to run.\n\n", thread_id,
                   to_run->job_id);
        }
        pthread_mutex_unlock(&ready_lock);  //release lock

        if(to_run != NULL){
            run_job(to_run);                //run the job
        }
    }
    pthread_exit(NULL);
}

/*
 * This function will be passed in to threads that will handle
 * the task of running IO.
 */
void *io_handler(void *t){
    int thread_id = (int)(long)t;

    while(jobs_completed < NUM_JOBS){
        //get the IO lock and check the queue
        pthread_mutex_lock(&io_queue_lock);
        Job *to_run = NULL;

        if(!is_empty(io_queue)){
            to_run = dequeue(io_queue);       //get the job from the queue
            printf("Thread %d just got job id: %d from the run queue.\n\n", thread_id,
                   to_run->job_id);
        }
        pthread_mutex_unlock(&io_queue_lock); //release lock

        if(to_run != NULL){
            run_job(to_run);                  //run the job
        }
    }
    pthread_exit(NULL);
}

/*
 * This function will be passed into threads that will handle
 * the creation and freeing of jobs.
 */
void *jobs_handler(void *t){
    int thread_id = (int)(long)t;

    while(jobs_completed < NUM_JOBS){

        //determine if we need to create any more jobs
        if(jobs_created < NUM_JOBS){
            //get the ready lock and create the job
            pthread_mutex_lock(&ready_lock);

            //in the event that multiple threads evaluated the if condition at the same time
            //before getting the lock, check one more time
            if(jobs_created < NUM_JOBS){
                Job *new_job = create_job(++jobs_created, thread_id);
                new_job->current_phase = 1;
                printf("Thread %d created job:\n", thread_id);
                display_job(new_job);
                enqueue(ready_queue, new_job);  //place in ready queue
                printf("Thread %d put job id: %d on the run queue.\n\n", thread_id, new_job->job_id);
            }
            pthread_mutex_unlock(&ready_lock);  //release lock
            sleep(CREATE_FREQ);
        }

        //get the completed queue lock to check the completed queue
        pthread_mutex_lock(&completed_lock);
        if(!is_empty(completed_queue)){
            Job *check = peek(completed_queue);

            //if this thread is the creator of this job
            if(check->creator_thread_id == thread_id){
                check = dequeue(completed_queue);   //take the job out of the queue
                printf("Thread %d is freeing job id: %d\n\n", thread_id, check->job_id);
                free(check);
                jobs_completed++;
            }
        }
        pthread_mutex_unlock(&completed_lock);
    }
    pthread_exit(NULL);
}
