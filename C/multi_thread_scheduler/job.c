/*
 * Sandeep Heera
 * job.c
 * A structure which will be used to store the contents of
 * each job including unique ID and phases.
 */

#include <stdlib.h>
#include <stdio.h>
#include "job.h"

/*
 * Creates a new job with the given id number, thread id, random number of phases, and random
 * phase times for each CPU/IO phase.
 */
Job *create_job(int id, int thread_id){
	Job *new_job = malloc(sizeof(Job));
	new_job->job_id = id;
	new_job->creator_thread_id = thread_id;
	new_job->nr_phases = rand() % MAX_PHASES + 1;
	new_job->current_phase = 0;
	new_job->current_phase_type = CPU_PHASE;    //starting phase
	*(new_job->phase_type_and_duration) = rand() % MAX_PHASE_TIME + 1;		//CPU phase time
	*(new_job->phase_type_and_duration + 1) = rand() % MAX_PHASE_TIME + 1;	//IO phase time
	new_job->is_completed = false;

	return new_job;
}

/*
 * Prints all members of a given job structure.
 */
void display_job(Job *j){
	printf("\tJob ID: %d\n", j->job_id);
	printf("\tJob was created by thread: %d\n", j->creator_thread_id);
	printf("\tNumber of Phases: %d\n", j->nr_phases);
	printf("\tCurrent Phase: %d\n", j->current_phase);
	printf("\tCurrent Phase Type: %d (1 = CPU, 2 = IO)\n", j->current_phase_type);
	printf("\tCPU phase length: %d\n", *(j->phase_type_and_duration));
	printf("\tIO phase length: %d\n", *(j->phase_type_and_duration + 1));
	printf("\tIs completed: %d\n\n", j->is_completed);
}
