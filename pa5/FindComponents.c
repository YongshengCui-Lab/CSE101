//#include<stdio.h>
//#include<stdlib.h>
//#include<string.h>
//#include"List.h"
//#include"Graph.h"
//
//int main(int argc, char* argv[])
//{
//    FILE *in;
//    FILE *out;
//    if (argc != 3)
//    {
//        printf("Usage: %s <input file> <output file>\n", argv[0]);
//        exit(1);
//    }
//
//    in = fopen(argv[1], "r");
//    out = fopen(argv[2], "w");
//
//    if (in == NULL)
//    {
//        printf("Cannot open file %s for reading\n", argv[1]);
//        exit(1);
//    }
//    if (out == NULL)
//    {
//        printf("Cannot open file %s for writing\n", argv[2]);
//        exit(1);
//    }
//
//    int n = 0; // number of vertices
//    fscanf(in, "%d", &n); // number of vertices
//    Graph G = newGraph(n);
//    int v1;
//    int v2;
//
//    while (fgetc(in) != EOF)
//    {
//        fscanf(in, "%d", &v1);
//        fscanf(in, "%d", &v2);
//        if (v1 == 0 && v2 == 0)
//        {
//            break;
//        }
//        addArc(G, v1, v2);
//    }
//
//
//    List L = newList();
//    for (int i = 1; i <= n; i++)
//    {
//        append(L, i);
//    }
//
//
//    fprintf(out, "Adjacency list of G:\n");
//    printGraph(out, G);
//    fprintf(out, "\n");
//
//    DFS(G, L);
//    Graph T = transpose(G);
//    DFS(T, L);
//
//
//    int count1 = 0;
//    for (int i = 1; i <= getOrder(T); i++)
//    {
//        if(getParent(T, i) == NIL)
//        {
//            count1++;
//        }
//    }
//    fprintf(out, "G contains %d strongly connected components:\n", count1);
//
//    List *scc = calloc(count1 + 1, sizeof(List));
//    for (int i = 1; i <= count1; i++)
//    {
//        scc[i] = newList();
//    }
//
//    int count2 = 1; // number of scc
//    int listLength = length(L);
//    int last = front(L); // back of the list
//    for (int i = 1; i <= listLength - 1; i++)
//    {
//        int top = back(L); // "top" of stack
//        deleteBack(L); // pop
//        prepend(scc[count2], top); // push in front
//        if (getParent(T, top) == NIL)
//        {
//            count2++;
//        }
//    }
//    prepend(scc[count2], last); // last element missing otherwise
//
//    for (count2 = 1; count2 <= count1; count2++)
//    {
//        fprintf(out, "Component %d: ", count2);
//        printList(out, scc[count2]);
//        fprintf(out, "\n");
//    }
//
//    freeGraph(&G);
//    freeGraph(&T);
//    for (count2 = 0; count2 <= count1; count2++)
//    {
//        freeList(&scc[count2]);
//    }
//    freeList(&L);
//    free(scc);
//    fclose(in);
//    fclose(out);
//    return(0);
//}
