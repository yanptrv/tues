//--------------------------------------------
// NAME: Kristiyan Petrov
// CLASS: XI A
// NUMBER: 13
// PROBLEM: #1
//---------------------------------------------

#include <pthread.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

pthread_mutex_t mutex;

static int miners = 1;
static int traders = 1;
static int storage = 0;

void* mine(void* arg) {
    long number = (long)(arg) + 1;
    for(int i = 0; i < 20; i++) {
        if (pthread_mutex_lock(&mutex) != 0) {
            perror("pthread_mutex_lock");
            return NULL;
        }

        storage+=10;
        printf("Miner %ld gathered 10 gold\n", number);

        if (pthread_mutex_unlock(&mutex) != 0) {
            perror("pthread_mutex_lock");
            return NULL;
        }
        sleep(2);
    }

    return NULL;
}

void* trade(void* arg) {
    long number = (long)(arg) + 1;
    for(int i = 0; i < 20; i++) {
        if (pthread_mutex_lock(&mutex) != 0) {
            perror("pthread_mutex_lock");
            return NULL;
        }

        if(storage == 0) {
            printf("The warehouse is empty, cannot sell!\n");
        }
        else {
            storage-=10;
            printf("Trader %ld sold 10 gold\n", number);
        }

        if (pthread_mutex_unlock(&mutex) != 0) {
            perror("pthread_mutex_lock");
            return NULL;
        }
        sleep(2);
    }

    return NULL;
}

int main(int argc, char* argv[]) {
    if(pthread_mutex_init(&mutex, NULL) != 0) {
        perror("pthread_mutex_init");
        return -1;
    }

    if (argc >= 3) {
        miners = (int) strtol(argv[1], (char**)NULL, 10);
        traders = (int) strtol(argv[2], (char**)NULL, 10);
    }

    pthread_t *miners_group = malloc(sizeof(pthread_t) * miners);
    pthread_t *traders_group = malloc(sizeof(pthread_t) * traders);

    for(long i = 0; i < miners; i++) {
        if(pthread_create(&miners_group[i], NULL, mine, (void*)i) != 0) {
            perror("pthread_create");
            return -1;
        }
    }

    for(long i = 0; i < traders; i++) {
        if(pthread_create(&traders_group[i], NULL, trade, (void*)i) != 0) {
            perror("pthread_create");
            return -1;
        }
    }

    for(int i = 0; i < miners; i++) {
        if(pthread_join(miners_group[i], NULL) != 0) {
            perror("pthread_join");
            return -1;
        }
    }

    for(int i = 0; i < traders; i++) {
        if(pthread_join(traders_group[i], NULL) != 0) {
            perror("pthread_join");
            return -1;
        }
    }

    if(pthread_mutex_destroy(&mutex) != 0) {
        perror ("pthread_mutex_destroy");
    }
    printf("Gold: %d\n", storage);
    free(miners_group);
    free(traders_group);

    return 0;
}