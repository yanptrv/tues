#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int scv = 5;
int minerals_in_storage = 0;
int soldiers = 0;
long minerals_count;

pthread_mutex_t *mutex;

void* get_minerals(void* arg) {
    long scv_number = (long)arg;
    for(int i = 0; i < minerals_count; i++) {
        sleep(3);
        if (pthread_mutex_trylock(&mutex[i]) == 0) {

            printf("SCV %ld is mining from mineral block %d\n", scv_number+1, i+1);
            printf("SCV %ld is transporting minerals\n", scv_number+1);
            sleep(2);
            printf("SCV %ld delivered minerals to the Command center\n", scv_number+1);
            minerals_in_storage+=8;

            if (pthread_mutex_unlock(&mutex[i]) != 0) {
                perror("pthread_mutex_unlock");
                return NULL;
            }
            break;
        }
        else {
            if (i == minerals_count-1) {
                sleep(2);
                i = 0;
            }
        }
    }

    return NULL;
}

int main(int argc, char* argv[]) {

    if(argc == 2) {
        minerals_count = (long) strtol(argv[1], (char**)NULL, 10);
    }
    else if(argc == 1){
        minerals_count = 2;
    }

    mutex  = malloc(sizeof(pthread_mutex_t)*minerals_count);

    for(int i = 0; i < minerals_count; i++) {
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

    for(int i = 0; i < minerals_count; i++) {
        if(pthread_mutex_destroy(&mutex[i]) != 0) {
            perror("pthread_mutex_destroy");
            return -1;
        }
    }
    free(scv_group);
    free(mutex);

    return 0;
}
