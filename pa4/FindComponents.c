#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"List.h"
#include"Graph.h"

int main(int argc, char* argv[])
{
    // first check if there are 3 arguments exactly
    if (argc < 3 || argc > 3)
    {
        fprintf(stderr, "Usage: %s <input file> <output file>\n", argv[0]);
        exit(1);
    }

    FILE *in, *out;
    in = fopen(argv[1], "r");
    out = fopen(argv[2], "w");

    if (in == NULL)
    {
        fprintf(stderr, "Unable to open file %s for reading\n", argv[1]);
        exit(1);
    }
    if (out == NULL)
    {
        fprintf(stderr, "Unable to open file %s for writingn\n", argv[2]);
        exit(1);
    }

    int n = 0; // number of vertices
    fscanf(in, "%d", &n); // number of vertices
    Graph G = newGraph(n);
    int v1;
    int v2;

    // Make graph
    while (fgetc(in) != EOF)
    {
        fscanf(in, "%d", &v1);
        fscanf(in, "%d", &v2);
        if (v1 == 0 && v2 == 0)
        {
            break;
        }
        addArc(G, v1, v2);
    }

    // The following code is written by using the hints
    // Professor Tantalo presented:

    // put all vertices into a List
    List L = newList();
    for (int i = 1; i <= n; i++)
    {
        append(L, i);
    }

    // print adjacency list to stdout
    fprintf(out, "Adjacency list representation of G:\n");
    printGraph(out, G);
    fprintf(out, "\n");

    DFS(G, L);

    Graph T = transpose(G);
    DFS(T, L);

    // count number of Strongly-Connected-Components (scc)
    int countScc1 = 0;
    for (int i = 1; i <= getOrder(T); i++)
    {
        if(getParent(T, i) == NIL)
        {
            countScc1++;
        }
    }
    fprintf(out, "G contains %d strongly connected components:\n", countScc1);

    // array of Lists of scc
    List *scc = calloc(countScc1 + 1, sizeof(List));
    for (int i = 1; i <= countScc1; i++)
    {
        scc[i] = newList();
    }

    // topologically sort scc and print them out
    // use stack method shown in class
    int countScc2 = 1; // number of scc
    int listLength = length(L);
    int last = front(L); // back of the list
    for (int i = 1; i <= listLength - 1; i++)
    {
        int top = back(L); // "top" of stack
        deleteBack(L); // pop
        prepend(scc[countScc2], top); // push in front
        if (getParent(T, top) == NIL)
        {
            countScc2++;
        }
    }
    prepend(scc[countScc2], last); // last element missing otherwise

    // print scc of Graph, which will be topilogically sorted
    for (countScc2 = 1; countScc2 <= countScc1; countScc2++)
    {
        fprintf(out, "Component %d: ", countScc2);
        printList(out, scc[countScc2]);
        fprintf(out, "\n");
    }

    // after program is finished, free memory
    freeGraph(&G);
    freeGraph(&T);
    for (countScc2 = 0; countScc2 <= countScc1; countScc2++)
    {
        freeList(&scc[countScc2]);
    }
    freeList(&L);
    free(scc);
    fclose(in);
    fclose(out);
    return(0);
}
