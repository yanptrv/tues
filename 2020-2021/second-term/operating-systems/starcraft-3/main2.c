//--------------------------------------------
// NAME: Kristiyan Petrov
// CLASS: XI A
// NUMBER: 14
// PROBLEM: #11
// FILE NAME: main2.c
// FILE PURPOSE:
// Implementing the backend of StarCraft III
//---------------------------------------------

#include <pthread.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>

pthread_mutex_t *blocksMutex;
int scvCount = 5, *blocksMinerals, storage = 0, marinesCount = 19, flag = 0;
long blocksCount = 2;

void* mineMinerals(void* arg) {
    long scvNum = (long)arg;
    while (storage != blocksCount*500) {
        for (long i = 0; i < blocksCount; i++) {
            sleep(3);
            if (blocksMinerals[i] == 0) {
                continue;
            }
            if (pthread_mutex_trylock(&blocksMutex[i]) == 0) {
                perror("pthread_mutex_lock");
            }

            if (blocksMinerals[i] >= 8) {
                printf("SCV %ld is mining from mineral block %ld\n", scvNum+1, i+1);
                blocksMinerals[i] -= 8;
                printf("SCV %ld is transporting minerals\n", scvNum+1);
                sleep(2);
                storage += 8;
                printf("SCV %ld delivered minerals to the Command center\n", scvNum+1);
            }
            else if (blocksMinerals[i] < 8 && blocksMinerals[i] != 0){
                printf("SCV %ld is mining from mineral block %ld\n", scvNum+1, i+1);
                printf("SCV %ld is transporting minerals\n", scvNum+1);
                sleep(2);
                storage += blocksMinerals[i];
                printf("SCV %ld delivered minerals to the Command center\n", scvNum+1);
                blocksMinerals[i] = 0;
            }

            if (pthread_mutex_unlock(&blocksMutex[i]) != 0) {
                perror("pthread_mutex_unlock");
                return NULL;
            }
        }
    }
    flag = 1;

    return NULL;
}

void* commandCenter() {
    char command[2];
    while (1) {
        if (marinesCount == 20 && flag == 1) {
            return NULL;
        }
        printf("%d\n", marinesCount);
        scanf("%s", command);
        if (strcmp(command, "m") == 0) {
            if (storage >= 50) {
                storage-=50;
                sleep(1);
                marinesCount++;
                printf("You wanna piece of me, boy?\n");
            }
            else {
                printf("Not enough minerals.\n");
            }
        }
        else {
            printf("Invalid command.\n");
        }
    }

}

int main(int argc, char* argv[]) {
    if (argc == 2) {
        blocksCount = strtol(argv[1], (char**)NULL, 10);
    }

    blocksMinerals = malloc(sizeof(long) * blocksCount);
    blocksMutex = malloc(sizeof(pthread_mutex_t) * blocksCount);
    pthread_t *scvGroup = malloc(sizeof(pthread_t) * scvCount);
    pthread_t cmdCntr;

    for (long i = 0; i < blocksCount; i++) {
        blocksMinerals[i] = 500;
        if (pthread_mutex_init(&blocksMutex[i], NULL) != 0) {
            perror("pthread_mutex_init");
            return -1;
        }
    }

    for (long i = 0; i < scvCount; i++) {
        if (pthread_create(&scvGroup[i], NULL, mineMinerals, (void*)i) != 0) {
            perror("pthread_create");
            return -1;
        }
    }

    if (pthread_create(&cmdCntr, NULL, &commandCenter, NULL) != 0) {
        perror("pthread_create");
        return -1;
    }

    for (int i = 0; i < scvCount; i++) {
        if (pthread_join(scvGroup[i], NULL) != 0) {
            perror("pthread_join");
            return -1;
        }
    }

    if (pthread_join(cmdCntr, NULL) != 0) {
        perror("pthread_join");
        return -1;
    }

    for (long i = 0; i < blocksCount; i ++) {
        if (pthread_mutex_destroy(&blocksMutex[i]) != 0) {
            perror("pthread_mutex_destroy");
            return -1;
        }
    }

//    for (int i = 0; i < blocksCount; ++i) {
//        printf("%d\n", blocksMinerals[i]);
//    }
    printf("Map minerals %ld, player minerals %d, SCVs %d, Marines %d", blocksCount*500, storage, scvCount, marinesCount);
    free(blocksMinerals);
    free(blocksMutex);
    free(scvGroup);
    return 0;
}
