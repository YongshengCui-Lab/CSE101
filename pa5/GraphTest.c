//#include<stdio.h>
//#include<stdlib.h>
//#include "List.h"
//#include "Graph.h"
//
//int main(int argc, char* argv[])
//{
//    int n = 8;
//    Graph G = newGraph(n);
//    List S = newList();
//    Graph T = NULL;
//    Graph C = NULL;
//
//    for (int i = 1; i <= n; i++)
//    {
//        append(S, i);
//    }
//
//    addEdge(G, 1, 5);
//    addEdge(G, 1, 2);
//    addEdge(G, 2, 3);
//    addEdge(G, 2, 4);
//    addEdge(G, 4, 2);
//    addEdge(G, 5, 5);
//    addEdge(G, 6, 6);
//    printGraph(stdout, G);
//    printf("\n");
//
//    printf("Graph order is: %d\n", getOrder(G));
//    printf("Graph size is: %d\n", getSize(G));
//
//    printf("\n");
//
//    printf("DFS on regular is: \n");
//    DFS(G, S);
//    printf("\n");
//    printf("x:  d  f  p\n");
//    for (int i = 1; i <= n; i++)
//    {
//        printf("%d: %2d %2d %2d\n", i, getDiscover(G, i), getFinish(G, i),
//               getParent(G, i));
//    }
//    printf("\n");
//    printList(stdout, S);
//    printf("\n\n");
//
//    addArc(G, 1, 5);
//    addArc(G, 2, 5);
//    addArc(G, 3, 7);
//    addArc(G, 3, 8);
//    addArc(G, 6, 5);
//    printf("Graph order is: %d\n", getOrder(G));
//    printf("Graph size is: %d\n", getSize(G));
//
//    T = transpose(G);
//    C = copyGraph(G);
//    printf("\n");
//    printGraph(stdout, C);
//    printf("\n");
//    printGraph(stdout, T);
//    printf("\n");
//
//    printf("DFS on transpose is:\n");
//    DFS(T, S);
//    printf("\n");
//    printf("x:  d  f  p\n");
//    for (int i = 1; i <= 6; i++)
//    {
//        printf("%d: %2d %2d %2d\n", i, getDiscover(T, i), getFinish(T, i),
//               getParent(T, i));
//    }
//    printf("\n");
//    printf("----Stack----\n");
//    printList(stdout, S);
//    printf("\n");
//
//    freeList(&S);
//    freeGraph(&G);
//    freeGraph(&T);
//    freeGraph(&C);
//
//    return (0);
//}
