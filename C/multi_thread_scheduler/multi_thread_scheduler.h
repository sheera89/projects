/*
 * Sandeep Heera
 * project_3.h
 * Header file for multi_thread_scheduler.c.
 */

#ifndef PROJECT_3_H
#define PROJECT_3_H

#include "queue.h"

void run_job(Job*);
void *cpu_handler(void*);
void *io_handler(void*);
void *jobs_handler(void*);

#endif
