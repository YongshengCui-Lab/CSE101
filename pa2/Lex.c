#include<stdio.h>
#include<stdlib.h>
#include "List.h"
#include <string.h>

#define MAX_LEN 255
int main(int argc, char *argv[])
{
    int count = 0;
    FILE *inputFile;
    FILE *outputFile;

    char line[MAX_LEN];

    if(argc != 3)
    {
        printf("Usage: %s <input file> <output file>\n", argv[0]);
        exit(1);
    }

    inputFile = fopen(argv[1], "r");
    outputFile = fopen(argv[2], "w");

    if(inputFile == NULL)
    {
       printf("Unable to open file %s for reading\n", argv[1]);
        exit(1);
    }

    if(outputFile == NULL)
    {
        printf("Unable to open file %s for writing\n", argv[2]);        exit(1);
    }

    while(fgets(line, MAX_LEN, inputFile) != NULL)
    {
        count++;
    }

    rewind(inputFile);//reset cursor to the start of file

    char line1[count - 1][MAX_LEN];
    int line2 = -1;

    while(fgets(line, MAX_LEN, inputFile) != NULL)
    {
        strcpy(line1[++line2], line);
    }

    List list = newList(); // create a new List

    append(list, 0); // call list function

    //Insertion sort
    for(int i = 1; i < count; i++)
    {
        char *temp = line1[i];
        int j = i - 1;
        //call moveBack function
        moveBack(list);
        //compare the current line to each line in the list
        while(j >= 0 && strcmp(temp, line1[get(list)])<= 0)
              {
                  //move back
                  --j;
                  movePrev(list);
              }
              if(index(list) >= 0) insertAfter(list, i);

              else
              {
                  prepend(list, i);
              }
    }
    //Move the cursor back to the front of the list
    moveFront(list);
    //go thru the list and print out all the lines in proper order
    while(index(list) >= 0)
    {
        fprintf(outputFile, "%s", line1[get(list)]);
        moveNext(list);
    }

    //close both files
    fclose(inputFile);
    fclose(outputFile);

    //free the list
    freeList(&list);

    return 0;

}
