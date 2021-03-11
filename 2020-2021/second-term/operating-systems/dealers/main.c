#include <pthread.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

#define TEST_DRIVERS 20
#define CARS 5

pthread_mutex_t mutex;
static int times_driven = 0;

void* test_drive(void* arg) {
    if(pthread_mutex_lock(&mutex) != 0) {
        perror("pthread_mutex_lock");
    }

    printf("Driving...\n");
    times_driven++;
    sleep(1);

    if (pthread_mutex_unlock(&mutex) != 0) {
        perror("pthread_mutex_unlock");
    }

    return NULL;
}

int main(void) {
    if(pthread_mutex_init(&mutex, NULL) != 0) {
        perror("pthread_mutex_init");
    }
    pthread_t *drivers_group = malloc(sizeof(pthread_t) * TEST_DRIVERS);

    for(int i = 0; i < TEST_DRIVERS; i++) {
        if(pthread_create(&drivers_group[i], NULL, test_drive, NULL) != 0) {
            perror("pthread_create");
        }
    }

    for(int i = 0; i < TEST_DRIVERS; i++) {
        if(pthread_join(drivers_group[i], NULL) != 0) {
            perror("pthread_join");
        }
    }
    if(pthread_mutex_destroy(&mutex) != 0) {
        perror("pthread_mutex_destroy");
    }
    printf("Times driven: %d", times_driven);

    return 0;
}
