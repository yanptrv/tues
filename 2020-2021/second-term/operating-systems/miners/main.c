#include <pthread.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

pthread_mutex_t mutex;

static int miners = 3;
static int traders = 3;
static int storage = 0;

void* mine(void* arg) {
    int number = *(int*)(arg) + 1;
    for(int i = 0; i < 20; i++) {
        if (pthread_mutex_lock(&mutex) != 0) {
            perror("pthread_mutex_lock");
            return NULL;
        }

        storage+=10;
        printf("Miner %d gathered 10 gold\n", number);

        if (pthread_mutex_unlock(&mutex) != 0) {
            perror("pthread_mutex_lock");
            return NULL;
        }
        sleep(2);
    }

    return NULL;
}

void* trade(void* arg) {
    int number = *(int*)(arg) + 1;
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
            printf("Trader %d sold 10 gold\n", number);
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

    pthread_t *miners_group = malloc(sizeof(pthread_t) * miners);
    pthread_t *traders_group = malloc(sizeof(pthread_t) * traders);

    for(int i = 0; i < miners; i++) {
        if(pthread_create(&miners_group[i], NULL, mine, &i) != 0) {
            perror("pthread_create");
            return -1;
        }
    }

    for(int i = 0; i < traders; i++) {
        if(pthread_create(&traders_group[i], NULL, trade, &i) != 0) {
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

    return 0;
}