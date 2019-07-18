#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include "Graph.h"

#define WHITE 0
#define GRAY 1
#define BLACK 2

typedef struct GraphObj
{
    List* adj;//An array of Lists whose ith contain the neighbors vertex
    int* color;//An array of ints£¨or char, or strings) whose ith element
    int* parent;//An array of ints whose ith element is the parents
    int* discover;
    int* finish;
    int order; // the number of vertices
    int size; // the number of edges
}GraphObj;

typedef struct GraphObj* Graph; // private Graph type
//returns a Graph pointing to a newly created GraphObj representing a graph
//having n vertices and no edge;
// all arrays are length n+1
// where n is the # of the vertices in the Graph

/*** Constructors-Destructors ***/

// newGraph()
Graph newGraph(int n)
{
    Graph G = malloc(sizeof(GraphObj));
    G->adj = calloc(n + 1, sizeof(List));
    G->color = calloc(n + 1, sizeof(int));
    G->parent = calloc(n + 1, sizeof(int));
    G->discover = calloc(n + 1, sizeof(int));
    G->finish = calloc(n + 1, sizeof(int));
    G->order = n;
    G->size = 0;
    for (int i = 1; i <= n; i++)
    {
        G->adj[i] = newList();
        G->color[i] = WHITE;
        G->parent[i] = NIL;
        G->discover[i] = UNDEF;
        G->finish[i] = UNDEF;
    }
    return G;
}

// freeGraph()
// frees all the memory allocated
// for the graph
void freeGraph(Graph* pG)
{
    Graph temp = *pG;
    // free list first
    for(int i = 1; i <= getOrder(temp); i++)
    {
        freeList(&(temp->adj[i]));
    }

    free(temp->adj);
    free(temp->color);
    free(temp->parent);
    free(temp->discover);
    free(temp->finish);

    // finally free *pG
    free(*pG);
    *pG = NULL;
}

/*** Access functions ***/

// getOrder()
// gets the number of vertices
// in the graph
int getOrder(Graph G)
{
    if (G == NULL)
    {
        printf("Invalid: cannot getOrder() on NULL Graph reference\n");
        exit(1);
    }
    return G->order;
}

// getSize()
// returns the number of vertices
// in the graph
int getSize(Graph G)
{
    if (G == NULL)
    {
        printf("Invalid: cannot getSize() on NULL graph reference\n");
        exit(1);
    }
    return G->size;
}

// getParent()
// returns the parent of the graph
// pre: 1<=u<=getOrder(G)
int getParent(Graph G, int u)
{
    if (G == NULL)
    {
        printf("Invalid: cannot getParent() on NULL Graph reference\n");
        exit(1);
    }
    if (u < 1 || u > getOrder(G))
    {
        printf("Invalid: cannot getParent() if u is out of bounds\n");
        exit(1);
    }
    return G->parent[u];
}

int getDiscover(Graph G, int u)
{
    if (G == NULL)
    {
        printf("Invalid: cannot getDiscover() on NULL Graph reference\n");
        exit(1);
    }
    if (u < 1 || u > getOrder(G))
    {
        printf("Invalid: cannot getParent() if u is out of bounds\n");
        exit(1);
    }
    return G->discover[u];
}

// getFinish()
// gets finish time of vertex
int getFinish(Graph G, int u)
{
    if (G == NULL)
    {
        printf("Invalid: cannot getFinish() on NULL Graph reference\n");
        exit(1);
    }
    if (u < 1 || u > getOrder(G))
    {
        printf("Invalid: cannot getParent() if u is out of bounds\n");
        exit(1);
    }
    return G->finish[u];
}

/*** Manipulation procedures ***/

void addEdge(Graph G, int u, int v){
    if(G == NULL)
    {
        printf("Invalid: cannot call addEdge(Graph G, int u, int v)");
        exit(1);
    }
    if(u < 1 || u > getOrder(G))
    {
        printf("Invalid: cannot call addEdge(Graph G, int u, int v), out of bounds");
        exit(1);
    }
    if(v < 1 || v > getOrder(G))
    {
        printf("Invalid: cannot call addEdge(Graph G, int u, int v), out of bounds");
        exit(1);
    }
    addArc(G, u, v);
    addArc(G, v, u);
    G->size--;
}

void addArc(Graph G, int u, int v){
    if(G == NULL)
    {
        printf("Invalid: cannot call addArc(Graph G, int u, int v)");
        exit(1);
    }
    if(u < 1 || u > getOrder(G))
    {
        printf("Invalid:addArc(Graph G, int u, int v) called out of bounds");
        exit(1);
    }
    if(v < 1 || v > getOrder(G))
    {
        printf("Invalid:addArc(Graph G, int u, int v) called out of bounds");
        exit(1);
    }

    moveFront(G->adj[u]);
    while(index(G->adj[u]) > -1 && v > get(G->adj[u]))
    {
        moveNext(G->adj[u]);
    }
    if(index(G->adj[u]) ==  -1)
    {
        append(G->adj[u], v);
    }
    else
        insertBefore(G->adj[u], v);

    G->size++;
}

// DFS()
// implements the DFS algorithm presented in class
// pre: length(S)==getOrder(G)
void DFS(Graph G, List S)
{
    if (G == NULL)
    {
        printf("Invalid: cannot DFS() on NULL Graph reference\n");
        exit(1);
    }
    if (S == NULL)
    {
        printf("Invalid: cannot DFS on NULL List reference\n");
        exit(1);
    }
    if (length(S) != getOrder(G))
    {
        printf("Invalid: cannot DFS() without length(S) = getOrder(G)\n");
        exit(1);
    }
    for (int i = 1; i <= getOrder(G); i++)
    {
        G->color[i] = WHITE;
        G->parent[i] = NIL;
    }
    int time = 0;
    moveFront(S);
    while (index(S) >= 0)
    {
        int u = get(S);
        if (G->color[u] == WHITE)
        {
            visit(G, S, u, &time);
        }
        moveNext(S);
    }
    for (int i = 0; i < getOrder(G); i++)
    {
        deleteBack(S);
    }
}

// visit()
// helper function for DFS()
void visit(Graph G, List S, int u, int *time)
{
    if (G == NULL)
    {
        printf("Invalid: cannot DFS() on NULL Graph reference\n");
        exit(1);
    }
    if (S == NULL)
    {
        printf("Invalid: cannot DFS on NULL List reference\n");
        exit(1);
    }
    G->discover[u] = ++*time;
    G->color[u] = GRAY;
    moveFront(G->adj[u]);
    while (index(G->adj[u]) >= 0)
    {
        int y = get(G->adj[u]);
        if (G->color[y] == WHITE)
        {
            G->parent[y] = u;
            visit(G, S, y, time);
        }
        moveNext(G->adj[u]);
    }
    G->color[u] = BLACK;
    G->finish[u] = ++*time;
    prepend(S, u);
}


/*** Other operations ***/

// transpose()
// transposes a graph by switching directions
Graph transpose(Graph G)
{
    Graph T = newGraph(getOrder(G));
    for (int i = 1; i <= getOrder(G); i++)
    {
        moveFront(G->adj[i]);
        if (length(G->adj[i]) == 0)
        {
            continue;
        }
        else
        {
            while (index(G->adj[i]) != -1)
            {
                addArc(T, get(G->adj[i]), i);
                moveNext(G->adj[i]);
            }
        }
    }
    return T;
}

// copyGraph()
// returns a copy of the current graph
Graph copyGraph(Graph G)
{
    Graph C = newGraph(getOrder(G));
    for (int i = 1; i <= getOrder(G); i++)
    {
        moveFront(G->adj[i]);
        if (length(G->adj[i]) == 0)
        {
            continue;
        }
        else
        {
            while (index(G->adj[i]) >= 0)
            {
                addArc(C, i, get(G->adj[i]));
                moveNext(G->adj[i]);
            }
        }
    }
    return C;
}
// printGraph()
void printGraph(FILE* out, Graph G)
{
    if (G == NULL)
    {
        printf("Invalid: printGraph() called on NULL Graph reference\n");
        exit(1);
    }
    int i;
    for (i = 1; i <= getOrder(G); i++)
    {
        List list = G->adj[i];
        fprintf(out, "%d:", i);
        moveFront(list);
        while (index(list) != -1)
        {
            fprintf(out, " %d", get(list));
            moveNext(list);
        }
        fprintf(out, "\n");
    }
}
