#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int scv = 5;
int minerals_in_storage = 0;
int soldiers = 0;

pthread_mutex_t *mutex;

void* get_minerals(void* arg) {
    long minerals_counter = (long)arg;
    for(int i = 0; i < minerals_counter; i++) {
        if (pthread_mutex_trylock(&mutex[i]) == 0) {

            printf("Moving to mineral block");
            sleep(3);

            if (pthread_mutex_unlock(&mutex[i]) != 0) {
                perror("pthread_mutex_unlock");
                return NULL;
            }
        }
        else {
            if (i == minerals_counter-1) {
                i = 0;
            }
        }
    }

    return NULL;
}

int main(int argc, char* argv[]) {

    long minerals_count = (long) strtol(argv[1], (char**)NULL, 10);
    if(pthread_mutex_init(mutex = malloc(sizeof(pthread_mutex_t)*minerals_count), NULL) != 0) {
        perror("pthread_mutex_init");
        return -1;
    }

    pthread_t *scv_group = malloc(sizeof(pthread_t) * scv);

    for(int i = 0; i < scv; i++) {
        if(pthread_create(&scv_group[i], NULL, get_minerals, (void*)minerals_count) != 0) {
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

    for(int i = 0; i < minerals_count; i++) {
        if(pthread_mutex_destroy(&mutex[i]) != 0) {
            perror("pthread_mutex_destroy");
            return -1;
        }
    }

    return 0;
}
