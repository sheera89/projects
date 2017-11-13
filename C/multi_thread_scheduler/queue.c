/*
 * Sandeep Heera
 * queue.c
 * A FIFO queue which will be used to store job structures.
 */

#include <stdlib.h>
#include <stdio.h>
#include "queue.h"

/*
 * Enqueues a job to the given queue.
 */
void enqueue(Queue *q, Job *j){
	Node* new_node = malloc(sizeof(Node));
	new_node->data = j;
	q->size++;
	if(is_empty(q)){
		new_node->next = NULL;
		q->front = new_node;
	}
	else{
        Node* temp = q->front;
        while(temp->next != NULL){
            temp = temp->next;
        }
		temp->next = new_node;
		new_node->next = NULL;
	}
}

/*
 * Returns first element of the queue and NULL if the queue is empty.
 * Removes the first element of the queue.
 */
Job *dequeue(Queue *q){
	if(is_empty(q)){
		return NULL;
	}
	else{
		Node* temp = q->front;
		q->front = q->front->next;
		q->size--;

		return temp->data;
	}
}

/*
 * Returns the first element of the queue and NULL if the queue is empty.
 * Does not remove the first element of the queue.
 */
Job *peek(Queue *q){
    if(is_empty(q)){
        return NULL;
    }
    else{
        return q->front->data;
    }
}

/*
 * Returns true(1) if queue is empty and false(0) otherwise.
 */
bool is_empty(Queue *q){
	if(q->front == NULL){
		return true;
	}
	else{
        return false;
	}
}

/*
 * Displays all jobs in the queue.
 */
void display_queue(Queue *q){
	printf("Displaying queue:\n");
	Node *temp = q->front;
	int node_number = 0;
	if(temp == NULL){
		printf("Queue is empty.\n");
	}
	else{
		while(temp != NULL){
			printf("Node %d: \n", node_number);
			display_job(temp->data);
			temp = temp->next;
			node_number++;
		}
	}
}

/*
 * Returns a pointer to a new queue.
 */
Queue *create_queue(){
	Queue *new_queue = malloc(sizeof(Queue));
	new_queue->front = NULL;
	new_queue->size = 0;

	return new_queue;
}

/*
 * Frees memory of all nodes, along with the queue. Does not free memory
 * associated with the job of a node.
 */
void destroy_queue(Queue *q){
	while(q->front != NULL){
		Node *temp = q->front->next;
		free(q->front->data);
		free(q->front);
		q->front = temp;
	}
	free(q);
}
