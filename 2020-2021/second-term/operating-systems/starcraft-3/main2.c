//--------------------------------------------
// NAME: Kristiyan Petrov
// CLASS: XI A
// NUMBER: 14
// PROBLEM: #26
// FILE NAME: main2.c
// FILE PURPOSE:
// Implementing the backend of StarCraft III
//---------------------------------------------

#include <pthread.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>

pthread_t *scvGroup;
pthread_mutex_t *blocksMutex, centerMutex;
int *blocksMinerals, storage = 0, marinesCount = 0;
long blocksCount = 2, scvCount = 5;

//--------------------------------------------
// FUNCTION: mineMinerals
// функцията използва mutex-и за синхронизация
// на нишки, в които "копае" от масив с минерални
// блокове и ги добавя с трезор, отново с mutex-и
// приключва своята работа, когата всички минерлани
// блокове (елемнти) на масива имат за стойност нула,
// т.е биват изкопани
// PARAMETERS:
// void* arg е единствения параметър, който приема функцията
// и той представлява подаден от всяква нишка номер (на нишката),
// който бива конвертиран към нормално число
//---------------------------------------------

void* mineMinerals(void* arg) {
    long scvNum = (long)arg;
    while (1) {
        int emptyBlocksCount = 0;
        for (long i = 0; i < blocksCount; i++) {
            sleep(3);
            if (blocksMinerals[i] == 0) {
                emptyBlocksCount++;
                if (emptyBlocksCount == blocksCount) {
                    return NULL;
                }
                else {
                    continue;
                }
            }
            if (pthread_mutex_lock(&blocksMutex[i]) != 0) {
                perror("pthread_mutex_Lock");
                return NULL;
            }

                if (blocksMinerals[i] >= 8) {
                    printf("SCV %ld is mining from mineral block %ld\n", scvNum+1, i+1);
                    blocksMinerals[i] -= 8;
                    printf("SCV %ld is transporting minerals\n", scvNum+1);
                    if (pthread_mutex_lock(&centerMutex) != 0) {
                        perror("pthread_mutex_lock");
                        return NULL;
                    }
                    sleep(2);
                    storage += 8;
                    printf("SCV %ld delivered minerals to the Command center\n", scvNum+1);
                    if (pthread_mutex_unlock(&centerMutex) != 0) {
                        perror("pthread_mutex_unlock");
                        return NULL;
                    }
                }
                else if (blocksMinerals[i] < 8 && blocksMinerals[i] != 0){
                    printf("SCV %ld is mining from mineral block %ld\n", scvNum+1, i+1);
                    printf("SCV %ld is transporting minerals\n", scvNum+1);
                    if (pthread_mutex_lock(&centerMutex) != 0) {
                        perror("pthread_mutex_Lock");
                        return NULL;
                    }
                    sleep(2);
                    storage += blocksMinerals[i];
                    printf("SCV %ld delivered minerals to the Command center\n", scvNum+1);
                    if (pthread_mutex_unlock(&centerMutex) != 0) {
                        perror("pthread_mutex_unlock");
                        return NULL;
                    }
                    blocksMinerals[i] = 0;
                }

            if (pthread_mutex_unlock(&blocksMutex[i]) != 0) {
                perror("pthread_mutex_unlock");
                return NULL;
            }
        }
    }
}

//--------------------------------------------
// FUNCTION: commandCenter
// функцията представлява "команде център", който всъщност
// е цикъл, в който пишеш команда, която съответно добавя
// войник или създава нов работник, работи докато войниците не станат 20
// PARAMETERS:
// функцията не приема никакви параметри
//---------------------------------------------

void* commandCenter() {
    char command;
    while (1) {
        if (marinesCount == 20) {
            return NULL;
        }
        scanf("%s", &command);
        if (strcmp(&command, "m") == 0) {
            if (marinesCount + scvCount == 200) {
                printf("Marines and SCVs limit reached");
            }
            else {
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
        }
        else if (strcmp(&command, "s") == 0) {
            if (marinesCount + scvCount == 200) {
                printf("Marines and SCVs limit reached");
            }
            else {
                if (storage >= 50) {
                    storage-=50;
                    sleep(4);
                    scvCount++;
                    scvGroup = realloc(scvGroup, sizeof(pthread_t) * scvCount);
                    if (pthread_create(&scvGroup[scvCount-1], NULL, mineMinerals, (void*)(scvCount-1)) != 0) {
                        perror("pthread_create");
                        return NULL;
                    }
                    printf("SCV good to go, sir.\n");
                }
                else {
                    printf("Not enough minerals.\n");
                }
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
    scvGroup = malloc(sizeof(pthread_t) * scvCount);
    pthread_t cmdCntr;

    for (long i = 0; i < blocksCount; i++) {
        blocksMinerals[i] = 500;
        if (pthread_mutex_init(&blocksMutex[i], NULL) != 0) {
            free(blocksMinerals);
            free(blocksMutex);
            free(scvGroup);
            perror("pthread_mutex_init");
            return -1;
        }
    }

    if (pthread_mutex_init(&centerMutex, NULL) != 0) {
        free(blocksMinerals);
        free(blocksMutex);
        free(scvGroup);
        perror("pthread_mutex_init");
        return -1;
    }

    if (pthread_create(&cmdCntr, NULL, commandCenter, NULL) != 0) {
        free(blocksMinerals);
        free(blocksMutex);
        free(scvGroup);
        perror("pthread_create");
        return -1;
    }

    for (long i = 0; i < scvCount; i++) {
        if (pthread_create(&scvGroup[i], NULL, mineMinerals, (void*)i) != 0) {
            free(blocksMinerals);
            free(blocksMutex);
            free(scvGroup);
            perror("pthread_create");
            return -1;
        }
    }

    for (int i = 0; i < scvCount; i++) {
        if (pthread_join(scvGroup[i], NULL) != 0) {
            free(blocksMinerals);
            free(blocksMutex);
            free(scvGroup);
            perror("pthread_join");
            return -1;
        }
    }

    if (pthread_join(cmdCntr, NULL) != 0) {
        free(blocksMinerals);
        free(blocksMutex);
        free(scvGroup);
        perror("pthread_join");
        return -1;
    }

    if (pthread_mutex_destroy(&centerMutex) != 0) {
        free(blocksMinerals);
        free(blocksMutex);
        free(scvGroup);
        perror("pthread_mutex_destroy");
        return -1;
    }

    for (long i = 0; i < blocksCount; i++) {
        if (pthread_mutex_destroy(&blocksMutex[i]) != 0) {
            free(blocksMinerals);
            free(blocksMutex);
            free(scvGroup);
            perror("pthread_mutex_destroy");
            return -1;
        }
    }

    printf("Map minerals %ld, player minerals %d, SCVs %ld, Marines %d", blocksCount*500, storage, scvCount, marinesCount);
    free(blocksMinerals);
    free(blocksMutex);
    free(scvGroup);
    return 0;
}
