#include <stdio.h>
#include <dirent.h>
#include <sys/stat.h>
#include <time.h>
#include <pwd.h>
#include <grp.h>
#include <string.h>

int main(int argc, char* argv[]) {
    int fileCount = 0;
    if (argc != 2) {
        printf("Usage ./listdir <filename>");
        return 1;
    }
    char* filename = argv[1];
    DIR* dir = opendir(filename);
    if (dir == NULL) {
        perror("opendir");
        return 1;
    }

    struct dirent* dirEntry;
    long total = 0;

    while((dirEntry = readdir(dir)) != NULL) {
        struct stat st;
        int statValue = fstatat(dirfd(dir), dirEntry->d_name, &st, 0);
        if (statValue != 0) {
            perror("stat");
            return 1;
        }
        total += st.st_blocks;
        printf("%ld\n", total);
    }
    printf("total %ld\n", total/2);
    closedir(dir);
    DIR* dir1 = opendir(filename);
    dirEntry = readdir(dir1);
    while (dirEntry != NULL) {
        char* filename1 = dirEntry->d_name;
        if (filename1[0] != '.') {
            struct stat st;
            int statValue = fstatat(dirfd(dir1), filename1, &st, 0);
            if (statValue != 0) {
                perror("stat");
                return 1;
            }
            printf(" %ld\t", st.st_blocks);
            switch (st.st_mode & S_IFMT) {
                case S_IFBLK: printf("b"); break;
                case S_IFCHR: printf("c"); break;
                case S_IFDIR: printf("d"); break;
                case S_IFLNK: printf("l"); break;
                case S_IFSOCK: printf("s"); break;
                case S_IFREG: printf("-"); break;
                default: printf("unknown"); break;
            }
            mode_t val=(st.st_mode & ~S_IFMT);
            (val & S_IRUSR) ? printf("r") : printf("-");
            (val & S_IWUSR) ? printf("w") : printf("-");
            (val & S_IXUSR) ? printf("x") : printf("-");
            (val & S_IRGRP) ? printf("r") : printf("-");
            (val & S_IWGRP) ? printf("w") : printf("-");
            (val & S_IXGRP) ? printf("x") : printf("-");
            (val & S_IROTH) ? printf("r") : printf("-");
            (val & S_IWOTH) ? printf("w") : printf("-");
            (val & S_IXOTH) ? printf("x") : printf("-");
//            switch(st.st_mode & ~S_IFMT) {
//                case S_IRUSR: printf("r");
//                case S_IWUSR: printf("w");
//                case S_IXUSR: printf("x");
//                case S_IRGRP: printf("r");
//                case S_IWGRP: printf("w");
//                case S_IXGRP: printf("x");
//                case S_IROTH: printf("r");
//                case S_IWOTH: printf("w");
//                case S_IXOTH: printf("x");
//                default: printf("-");
//            }
            printf(" %ld", st.st_nlink);
            struct passwd *pws = getpwuid(st.st_uid);
            printf(" %s", pws->pw_name);
            struct group *grp = getgrgid(st.st_gid);
            printf(" %s", grp->gr_name);
            printf(" %ld",st.st_size);
            struct tm *tm = localtime(&st.st_mtime);
            switch (tm->tm_mon) {
                case 0: printf(" Jan %d %d:%d", tm->tm_mday, tm->tm_hour, tm->tm_min); break;
                case 1: printf(" Feb %d %d:%d", tm->tm_mday, tm->tm_hour, tm->tm_min); break;
                case 2: printf(" Mar %d %d:%d", tm->tm_mday, tm->tm_hour, tm->tm_min); break;
                case 3: printf(" Apr %d %d:%d", tm->tm_mday, tm->tm_hour, tm->tm_min); break;
                case 4: printf(" May %d %d:%d", tm->tm_mday, tm->tm_hour, tm->tm_min); break;
                case 5: printf(" Jun %d %d:%d", tm->tm_mday, tm->tm_hour, tm->tm_min); break;
                case 6: printf(" Jul %d %d:%d", tm->tm_mday, tm->tm_hour, tm->tm_min); break;
                case 7: printf(" Aug %d %d:%d", tm->tm_mday, tm->tm_hour, tm->tm_min); break;
                case 8: printf(" Sep %d %d:%d", tm->tm_mday, tm->tm_hour, tm->tm_min); break;
                case 9: printf(" Oct %d %d:%d", tm->tm_mday, tm->tm_hour, tm->tm_min); break;
                case 10: printf(" Nov %d %d:%d", tm->tm_mday, tm->tm_hour, tm->tm_min); break;
                case 11: printf(" Dec %d %d:%d", tm->tm_mday, tm->tm_hour, tm->tm_min); break;
            }
            printf(" %s\n", filename1);
        }
        dirEntry = readdir(dir1);
    }
    if (closedir(dir1) != 0) {
        perror("closedir");
        return 1;
    }

    return 0;
}