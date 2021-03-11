#include <pthread.h>
#include <stdlib.h>
#include <stdio.h>

pthread_mutex_t mutex;

static int miners = 1;
static int traders = 1;
static int storage = 0;

void* mine(void* arg) {
    if(pthread_mutex_lock(&mutex) != 0) {
        perror("pthread_mutex_lock");
        return NULL;
    }

    if(pthread_mutex_unlock(&mutex) != 0) {
        perror("pthread_mutex_lock");
        return NULL;
    }

    return NULL;
}

void* trade(void* arg) {
    if(pthread_mutex_lock(&mutex) != 0) {
        perror("pthread_mutex_lock");
        return NULL;
    }

    if(pthread_mutex_unlock(&mutex) != 0) {
        perror("pthread_mutex_lock");
        return NULL;
    }

    return NULL;
}

int main(void) {
    if(pthread_mutex_init(&mutex, NULL) != 0) {
        perror("pthread_mutex_init");
        return -1;
    }

    pthread_t *miners_group = malloc(sizeof(pthread_t) * miners);
    pthread_t *traders_group = malloc(sizeof(pthread_t) * traders);

    for(int i = 0; i < miners; i++) {
        if(pthread_create(&miners_group[i], NULL, mine, NULL) != 0) {
            perror("pthread_create");
            return -1;
        }
    }

    for(int i = 0; i < traders; i++) {
        if(pthread_create(&traders_group[i], NULL, trade, NULL) != 0) {
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

    return EXIT_SUCCESS;
}