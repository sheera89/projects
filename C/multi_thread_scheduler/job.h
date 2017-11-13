/*
 * Sandeep Heera
 * job.c
 * Header file for job.c.
 */

#ifndef JOB_H
#define JOB_H

#define CPU_PHASE 1
#define IO_PHASE 2
#define NR_PHASES 2
#define MAX_PHASES 10
#define MAX_PHASE_TIME 12

typedef int bool;
#define true 1
#define false 0

struct job {
	int job_id;
	int creator_thread_id;
	int nr_phases;
	int current_phase;
	int current_phase_type; //phase types: 1 = CPU phase; 2 = IO phase
	int phase_type_and_duration[NR_PHASES];
    bool is_completed;
};  typedef struct job Job;

Job *create_job(int,int);
void display_job(Job*);

#endif

