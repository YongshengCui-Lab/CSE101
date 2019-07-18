#include<stdio.h>
#include<stdlib.h>
#include "List.h"

// private NodeObj type
typedef struct NodeObj
{
    int data;
    struct NodeObj* prev;
    struct NodeObj* next;
}NodeObj;

typedef NodeObj* Node; // private Node type

// private ListObj type
typedef struct ListObj
{
    Node front;
    Node back;
    Node cursor;
    int index;
    int length;
}
ListObj;

// newNode()
// same as Node class constructor
Node newNode(int data)
{
    Node N = malloc(sizeof(NodeObj));
    N->data = data;
    N->prev = NULL;
    N->next = NULL;
    return (N);
}

// freeNode()
// frees heap memory pointed to by *pN, sets *pN to NULL
void freeNode(Node* pN)
{
    if (pN != NULL && *pN != NULL)
    {
        free(*pN);
        *pN = NULL;
    }
}

// newList()
// equivalent to List class constructor
List newList(void)
{
    List L = malloc(sizeof(ListObj));
    L->front = L->back = NULL;
    L->cursor = NULL;
    L->index = -1;
    L->length = 0;
    return (L);
}

// freeList()
void freeList(List* pL) {
   if(pL != NULL && *pL != NULL) {
      Node temp = (*pL)->front;
      while(temp != NULL) {
         Node current = temp;
         temp = temp->next;
         free(current);
      }

      free(*pL);
      *pL = NULL;
   }
}

// Access functions ---------------------------------------

// length()
// returns the number of elements in this List
int length(List L)
{
    if (L == NULL)
    {
        printf("Invalid: calling length() on NULL List reference\n");
        exit(1);
    }
    return L->length;
}

// index()
// returns the index of the cursor of the list
int index(List L)
{
    if (L == NULL)
    {
        printf("Invalid: calling index() on NULL List reference\n");
        exit(1);
    }
    return L->index;
}

// front()
// returns the front element. pre: length() > 0
int front(List L)
{
    if (L == NULL)
    {
        printf("Invalid: calling front() on NULL List reference\n");
        exit(1);
    }
    if (length(L) <= 0)
    {
        printf("Invalid: front() called on empty List\n");
        exit(1);
    }
    return L->front->data;
}

// back()
// returns the back element. pre: length() > 0
int back(List L)
{
    if (L == NULL)
    {
        printf("Invalid: calling back() on NULL List reference\n");
        exit(1);
    }
    if (length(L) < 1)
    {
        printf("Invalid: back() called on empty List reference\n");
        exit(1);
    }
    return L->back->data;
}

// get()
// returns the cursor element. pre: length() > 0 and index() > 0
int get(List L)
{
    if (L == NULL)
    {
        printf("Invalid: calling get() on NULL List reference\n");
        exit(1);
    }
    if (length(L) < 1)
    {
        printf("Invalid: calling get() called on empty List reference\n");
        exit(1);
    }
   if(L->index < 0)
   {
      printf("Invalid: calling get() with an undefined index on List\n");
      exit(1);
   }
    return L->cursor->data;
}

// equals
int equals(List A, List B)
{
    int eq = 0;
    Node M = NULL;
    Node N = NULL;
    if (A == NULL || B == NULL)
    {
        printf("Invalid: calling equals() on NULL List reference\n");
        exit(1);
    }
    eq = (A->length == B->length);
    M = A->front;
    N = B->front;
    while (eq && M != NULL)
    {
        eq = (M->data == N->data);
        M = M->next;
        N = N->next;
    }
    return eq;
}

// Manipulation procedures ------------------------------------------------

// clear()
// resets this List to its original empty state
void clear(List L)
{
    if (L == NULL)
    {
        printf("Invalid: calling clear() on a NULL List reference\n");
        exit(1);
    }
    while (length(L) > 0)
    {
        deleteFront(L);
    }
    L->front = L->back = NULL;
    L->cursor = NULL;
    L->index = -1;
    L->length = 0;
}

// moveFront()
// places the cursor to the front of the List
void moveFront(List L)
{
    if (L == NULL)
    {
        printf("Invalid: calling moveFront() on a NULL List reference\n");
        exit(1);
    }
    if (length(L) > 0)
    {
        L->cursor = L->front;
        L->index = 0;
    }
}

// moveBack()
// places the cursor at the back of the List
void moveBack(List L)
{
    if (L == NULL)
    {
        printf("Invalid: calling moveBack() on a NULL List reference\n");
        exit(1);
    }
    if (length(L) > 0)
    {
        L->cursor = L->back;
        L->index = L->length - 1;
    }
}

// movePrev()
// moves cursor one step toward the front of the List
void movePrev(List L)
{
    if (L == NULL)
    {
        printf("Invalid: calling movePrev() on a NULL List reference\n");
        exit(1);
    }
    if (L->cursor != NULL && L->index == 0)
    {
        L->cursor = NULL;
        L->index = -1;
    }
    else
    {
        L->cursor = L->cursor->prev;
        L->index--;
    }
}

// moveNext()
// moves the cursor one step toward the back of the List
void moveNext(List L)
{
    if (L == NULL)
    {
        printf("Invalid: calling moveNext() on a NULL List reference\n");
        exit(1);
    }
    if (L->cursor != NULL && L->index == L->length - 1)
    {
        L->cursor = NULL;
        L->index = -1;
    }
    else
    {
        L->cursor = L->cursor->next;
        L->index++;
    }
}

// prepend()
void prepend(List L, int data)
{
    if (L == NULL)
    {
        printf("Invalid: calling prepend() on a NULL List reference\n");
        exit(1);
    }
    Node N = newNode(data);
    if (L->front == NULL)
    {
        L->front = N;
        L->back = N;
        L->cursor = L->front;
    }
    else
    {
        L->front->prev = N;
        N->next = L->front;
        L->front = N;
        L->index++;
    }
    L->length++;
}

// append()
void append(List L, int data)
{
    if (L == NULL)
    {
        printf("Invalid: calling apend() on a NULL List reference\n");
        exit(1);
    }
    Node N = newNode(data);
    if (L->back == NULL)
    {
        L->front = N;
        L->back = N;
        L->cursor = L->back;
    }
    else
    {
        L->back->next = N;
        N->prev = L->back;
        L->back = N;
        N->next = NULL;
    }
    L->length++;
}

// insertBefore()
// inserts new element before cursor
void insertBefore(List L, int data)
{
    if (L == NULL)
    {
        printf("Invalid: calling insertBefore() on a NULL List reference\n");
        exit(1);
    }
    if (L->cursor == NULL)
    {
        printf("Invalid: cannot insertBefore()  if cursor is undefined\n");
        exit(1);
    }
    if (L->cursor == L->front)
    {
        prepend(L, data);
    }
    else
    {
        Node N = newNode(data);
        N->prev = L->cursor->prev;
        N->next = L->cursor;
        L->cursor->prev->next = N;
        L->cursor->prev = N;
        L->index++;
        L->length++;
    }
}

// insertAfter()
void insertAfter(List L, int data)
{
    if (L == NULL)
    {
        printf("Invalid: calling insertAfter() on a NULL List reference\n");
        exit(1);
    }
    if (length(L) < 1)
    {
        printf("Invalid: insertAfter() called on empty List\n");
        exit(1);
    }
    if (L->cursor == L->back)
    {
        append(L, data);
    }
    else
    {
        Node N = newNode(data);
        L->cursor->next->prev = N;
        N->next = L->cursor->next;
        N->prev = L->cursor;
        L->cursor->next = N;
        L->length++;
    }
}

// deleteFront()
void deleteFront(List L)
{
    if (L == NULL)
    {
        printf("Invalid: calling deleteFront() on a NULL List reference\n");
        exit(1);
    }
    if (length(L) < 1)
    {
        printf("Invalid: cannot deleteFront() empty List\n");
        exit(1);
    }
    else
    {
      if (L->length == 1)
      {
          Node N = L->front;
          freeNode(&N);
          L->cursor = NULL;
          L->front = L->back = NULL;
          L->index = -1;
      }
      else
      {
          Node N = L->front;
          L->front = L->front->next;
          L->front->prev = NULL;
          if (L->cursor != NULL)
          {
              L->index--;
          }
          freeNode(&N);
      }
      L->length--;
  }
}

// deleteBack()
// deletes back element
void deleteBack(List L)
{
    if (L == NULL)
    {
        printf("Invalid: cannot deleteBack() on NULL List reference\n");
        exit(1);
    }
    if (length(L) <= 0)
    {
        printf("Invalid: cannot deleteBack() empty List\n");
        exit(1);
    }
    else
    {
        if (L->length == 1)
        {
            Node N = L->back;
            freeNode(&N);
            L->cursor = NULL;
            L->front = L->back = NULL;
            L->index = -1;
        }
        else
        {
            Node N = L->back;
            L->back = L->back->prev;
            L->back->next = NULL;
            if (L->index == L->length - 1)
            {
                L->index = -1;
            }
            freeNode(&N);
        }
        L->length--;
    }
}

// delete()
void delete(List L)
{
    if (L == NULL)
    {
        printf("Invalid: delete() called on NULL List reference\n");
        exit(1);
    }
    if (length(L) <= 0)
    {
        printf("Invalid: delete() called on empty List");
        exit(1);
    }
    if (L->cursor == NULL)
    {
        printf("Invalid:delete() called on undefined cursor");
        exit(1);
    }
    if (L->cursor == L->front)
    {
        deleteFront(L);
    }
    else if (L->cursor == L->back)
    {
        deleteBack(L);
    }
    else
    {
        Node N = L->cursor;
        L->cursor->prev->next = L->cursor->next;
        L->cursor->next->prev = L->cursor->prev;
        freeNode(&N);
        L->cursor = NULL;
        L->length--;
    }
    L->index = -1;
}

// printList()
void printList(FILE* out, List L) {
   Node N = L->front;
   int i = 0;
   while(N != NULL) {
      if(i != 0)
         fprintf(out, " %d", N->data);
      else
         fprintf(out, "%d", N->data);
      N = N->next;
      i++;
   }
}

// copyList()
List copyList(List L)
{
    List C = newList();
        Node N = L->front;
        while (N != NULL)
        {
            append(C, N->data);
            N = N->next;
        }
    C->cursor = NULL;
    C->index = -1;
    return C;
}
