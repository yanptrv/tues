#include <stdio.h>
#include <unistd.h>

int main(int argc, char* argv[]) {
    int lastc = 1;
    char c = getopt(argc, argv, "abcd");
    if (c == -1) {
        return 0;
    }
    while (c != -1) {
        if (lastc) {
            if(c == 'a') {
                printf("hi");
            }
            lastc = 0;
        }
        c = getopt(argc, argv, "abcd");
        if (c == -1) {
            return 0;
        }
    }
    printf("\n");
    return 0;
}
