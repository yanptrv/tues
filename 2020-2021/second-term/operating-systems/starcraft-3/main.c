#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

int scv = 5;
int minerals = 0;
int soldiers = 0;

pthread_mutex_t mutex;

void* get_minerals(void* arg) {
    for(;;) {
        if (pthread_mutex_trylock(&mutex) == 0) {



            if (pthread_mutex_unlock(&mutex) != 0) {
                perror("pthread_mutex_unlock");
                return NULL;
            }
        } else { ;
        }
    }

    return NULL;
}

int main(int argc, char* argv[]) {
    if(pthread_mutex_init(&mutex, NULL) != 0) {
        perror("pthread_mutex_init");
        return -1;
    }

    pthread_t *scv_group = malloc(sizeof(pthread_t) * scv);

    for(int i = 0; i < scv; i++) {
        if(pthread_create(&scv_group[i], NULL, get_minerals, NULL) != 0) {
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

    if(pthread_mutex_destroy(&mutex) != 0) {
        perror("pthread_mutex_destroy");
        return -1;
    }

    return 0;
}
