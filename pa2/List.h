/*
 * List.h
 *
 *  Created on: Oct 17, 2018
 *      Author: yongsheng cui
 */

#ifndef LIST_H_INCLUDED
#define LIST_H_INCLUDED

typedef struct ListObj* List;

/*****CONSTRUCTORS - DESTRUCTORS*****/
// Returns reference to new empty List object.
List newList(void);

// Frees all heap memory associated with List *pL, and sets *pL to NULL.
void freeList(List* pL);
/*****CONSTRUCTORS - DESTRUCTORS END*****/

/*****ACCESS FUNCTIONS*****/
int length(List L);

int index(List L);

int front(List L);

int back(List L);

int get(List L);

int equals(List A, List B);
/*****ACCESS FUNCTIONS END*****/


/*****MANIPULATION FUNCTIONS*****/
void clear(List L);

void moveFront(List L);

void moveBack(List L);

void movePrev(List L);

void moveNext(List L);

void prepend(List L, int data);

void append(List L, int data);

void insertBefore(List L, int data);

void insertAfter(List L, int data);

void deleteFront(List L);

void deleteBack(List L);

void delete(List L);
/*****MANIPULATIONS FUNCTIONS END*****/


void printList(FILE* out, List L);

List copyList(List L);




#endif // LIST_H_INCLUDED
