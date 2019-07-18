#include<stdio.h>
#include<stdlib.h>
#include"Graph.h"

#include "Graph.h"
#include "List.h"

int main(int argc, char* argv[]){
    int i, s, max, min, d, n=35;
   Graph G = newGraph(100);
   int j = 0;
   for(j = 1; j < 100; j++) {
      if(j % 2 == 1)
         addEdge(G, j, 100 - j);
      else if(j % 3 == 1)
         addArc(G, j, 100 - j);
   }

   for(i = 1; i < 100; i++) {
      BFS(G, i);
      for(j = 1; j < 100; j++) {
         d = getDist(G, j);
         if(d != INF)
            printf("Distance: %d-%d: %d\n", i, j, d);
      }
   }

   BFS(G, 20);
   List N = newList();
   getPath(N, G, 100);
   printf("\n");
   printf("Path from 20 to 100: ");
   printList(stdout, N);
   printf("\n");

   BFS(G, 94);
   List A = newList();
   getPath(A, G, 6);
   printf("\n");
   printf("Path from 94 to 6: ");
   printList(stdout, A);
   printf("\n");

   freeList(&N);
   freeList(&A);
   freeGraph(&G);
}
