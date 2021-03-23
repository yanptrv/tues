#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int scv = 5;
int mineralsInStorage = 0, *mineralBlocks;
int soldiers = 0;
long mineralsCount = 2;

pthread_mutex_t *mutex;

void* get_minerals(void* arg) {
    long scvNumber = (long)arg;
    int minedMinerals;
    int flag = 1;
    while(flag) {
        for (int i = 0; i < mineralsCount; i++) {
            sleep(3);
            if (pthread_mutex_trylock(&mutex[i]) == 0) {
                int emptyMinerals = 0;
                for (int j = 0; j < mineralsCount; j++) {
                    if (mineralBlocks[j] == 0) {
                        emptyMinerals++;
                        if (emptyMinerals == mineralsCount) {
                            flag = 0;
                        }
                    }
                }
                printf("%d \n", mineralBlocks[i]);
                printf("SCV %ld is mining from mineral block %d\n", scvNumber + 1, i + 1);
                if (mineralBlocks[i] >= 8){
                    minedMinerals = 8;
                    mineralBlocks[i] -= 8;
                }
                else if (mineralBlocks[i] < 8 && mineralBlocks[i] != 0) {
                    minedMinerals = mineralBlocks[i];
                    mineralBlocks[i] = 0;
                }
                printf("SCV %ld is transporting minerals\n", scvNumber + 1);
                sleep(2);
                mineralsInStorage += minedMinerals;
                printf("SCV %ld delivered minerals to the Command center\n", scvNumber + 1);


                if (pthread_mutex_unlock(&mutex[i]) != 0) {
                    perror("pthread_mutex_unlock");
                    return NULL;
                }
                minedMinerals = 0;
            } else {
                if (i == mineralsCount - 1) {
                    i = 0;
                }
            }
        }
    }
    return NULL;
}
int main(int argc, char* argv[]) {

    if(argc == 2) {
        mineralsCount = strtol(argv[1], (char**)NULL, 10);
    }

    mineralBlocks = malloc(sizeof(long) * mineralsCount);
    for(int i = 0; i < mineralsCount; i++) {
        mineralBlocks[i] = 500;
    }

    mutex = malloc(sizeof(pthread_mutex_t) * mineralsCount);
    for(int i = 0; i < mineralsCount; i++) {
        if(pthread_mutex_init(&mutex[i], NULL) != 0) {
            perror("pthread_mutex_init");
            return -1;
        }
    }

    pthread_t *scv_group = malloc(sizeof(pthread_t) * scv);

    for(long i = 0; i < scv; i++) {
        if(pthread_create(&scv_group[i], NULL, get_minerals, (void*)i) != 0) {
            perror("pthread_create");
            return -1;
        }
    }

    for(int i = 0; i < scv; i++) {
        if(pthread_join(scv_group[i], NULL) != 0) {
            perror("pthread_create");
            return -1;
        }
    }

    for(int i = 0; i < mineralsCount; i++) {
        if(pthread_mutex_destroy(&mutex[i]) != 0) {
            perror("pthread_mutex_destroy");
            return -1;
        }
    }
    printf("%d\n", mineralsInStorage);
    free(mineralBlocks);
    free(scv_group);
    free(mutex);

    return 0;
}
