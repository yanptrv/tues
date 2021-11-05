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

#define TEST_DRIVERS 20
#define CARS 5

pthread_mutex_t mutex[CARS];
static int times_driven[CARS] = { 0, 0, 0, 0, 0 };

void* test_drive(void* arg) {
    long driver = (long)arg;
    for (int i = 0; i < CARS; i++) {
        if (pthread_mutex_trylock(&mutex[i]) == 0) {

            printf("Buyer %ld takes car %d.\n", driver, i+1);
            times_driven[i]++;
            printf("Buyer %ld returns car %d.\n", driver, i+1);

            if (pthread_mutex_unlock(&mutex[i]) != 0) {
                perror("pthread_mutex_unlock");
                return NULL;
            }
        }
        else {
            i = i-1;
        }
    }
    return NULL;
}

int main(void) {
    for (int i = 0; i < CARS; i++) {
        if (pthread_mutex_init(&mutex[i], NULL) != 0) {
            perror("pthread_mutex_init");
            return -1;
        }
    }
    pthread_t *drivers_group = malloc(sizeof(pthread_t) * TEST_DRIVERS);

    for (long i = 0; i < TEST_DRIVERS; i++) {
        if (pthread_create(&drivers_group[i], NULL, test_drive, (void*)i) != 0) {
            perror("pthread_create");
            return -1;
        }
    }

    for (int i = 0; i < TEST_DRIVERS; i++) {
        if (pthread_join(drivers_group[i], NULL) != 0) {
            perror("pthread_join");
            return -1;
        }
    }
    for (int i = 0; i < CARS; i++) {
        if (pthread_mutex_destroy(&mutex[i]) != 0) {
            perror("pthread_mutex_destroy");
            return -1;
        }
    }
    free(drivers_group);

    return 0;
}
