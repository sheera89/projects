/** Sandeep Heera
    s_t_matrix_mult.c
    This program takes two 2000x2000 matrices
    and multiplies them using a single thread.
    The total time taken is recorded.
 */

#include <stdio.h>
#include <time.h>
#include <stdlib.h>

//constants
const int MAT_SIZE = 2000;

void create_matrices();
void fill_mat(int**, int**);
void mult_mat(int**, int**, int**);
void print_mat(int**);
void free_arr_ptr(int**);

int **a, **b, **c;

int main(){
	clock_t start, end;
	double cpu_time;

	create_matrices();
	fill_mat(a, b);					    	//fill matrix a and matrix b
	start = clock();					//start the clock
	mult_mat(a, b, c);
	end = clock();						//stop the clock
	cpu_time = ((double)(end-start)/ CLOCKS_PER_SEC);
/*
	printf("\nFirst Matrix:\n");
	print_mat(a);

	printf("\nSecond Matrix:\n");
	print_mat(b);

	printf("\nProduct of a and b:\n");
	print_mat(c);
*/
	printf("\nTotal time for multiplication %f\n", cpu_time);

	free_arr_ptr(a);
	free_arr_ptr(b);
	free_arr_ptr(c);

	return 0;
}

/** Creates 2-D array of integers by using a pointer for each
    row. The array will represent a matrix. A, b, and c are
    global **int pointers.
*/
void create_matrices(){
	int i;

	a = malloc(MAT_SIZE * sizeof(int *));
	b = malloc(MAT_SIZE * sizeof(int *));
	c = malloc(MAT_SIZE * sizeof(int *));

	for(i=0;i<MAT_SIZE;i++){
		a[i] = malloc(MAT_SIZE * sizeof(int));
		b[i] = malloc(MAT_SIZE * sizeof(int));
		c[i] = malloc(MAT_SIZE * sizeof(int));
	}
}

/** Takes the two 2-D integer matrices arr1 and arr2 and assigns random
    values between 0 and 50 to them via the rand() function.

    arr1: 2-D integer array of size MAT_SIZE x MAT_SIZE
    arr2: 2-D integer array of size MAT_SIZE x MAT_SIZE
*/
void fill_mat(int **a, int **b){
	srand(time(NULL));	//seed time

	int i, j;
	for(i=0;i<MAT_SIZE;i++){
		for(j=0;j<MAT_SIZE;j++){
			a[i][j] = rand()%51;
			b[i][j] = rand()%51;
		}
	}
}

/** Takes three 2-D integer matrices and performs matrix multiplication
    on a and b and stores the result in c.

    a: 2-D integer matrix of size MAT_SIZE x MAT_SIZE that will be multiplied
       by b.
    b: 2-D integer matrix of size MAT_SIZE x MAT_SIZE that will be multiplied
       by a.
    c: 2-D integer matrix of size MAT_SIZE x MAT_SIZE that will be the result
       of the matrix multiplication of a and b.
*/
void mult_mat(int **a, int **b, int **c){
	int i, j, k, sum;
	for(i=0;i<MAT_SIZE;i++){
		for(j=0;j<MAT_SIZE;j++){
			sum = 0;
			for(k=0;k<MAT_SIZE;k++){
				sum += a[i][k]*b[k][j];
			}
			c[i][j] = sum;
		}
	}
}

/** Display the 2-D integer matrix arr. Used for debugging purposes.

    arr: 2-D integer matrix of size MAT_SIZE x MAT_SIZE
 */
void print_mat(int **arr){
	int i, j;
	for(i=0;i<MAT_SIZE;i++){
		for(j=0;j<MAT_SIZE;j++){
			if(j == MAT_SIZE - 1){
				printf("%d \n", arr[i][j]);
			}
			else{
				printf("%d ", arr[i][j]);
			}
		}
	}
}

/** Frees the entire array of integer  pointers including the elements.

	a: array of integer pointers to be freed.
*/
void free_arr_ptr(int **a){
	int i;
	for(i=0;i<MAT_SIZE;i++){
		free(a[i]);
	}
	free(a);
}
