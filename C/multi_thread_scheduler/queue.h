/*
 * Sandeep Heera
 * queue.h
 * Header file for queue.c.
 */

#ifndef QUEUE_H
#define QUEUE_H

#include "job.h"

struct node{
    Job *data;
	struct node *next;
};  typedef struct node Node;

struct queue{
	Node *front;
	int size;
};  typedef struct queue Queue;

void enqueue(Queue*, Job*);
Job *dequeue(Queue*);
Job *peek(Queue*);
bool is_empty(Queue*);
void display_queue(Queue*);
Queue *create_queue();
void destroy_queue(Queue*);

#endif
