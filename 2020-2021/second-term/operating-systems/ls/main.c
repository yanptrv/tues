//-----------------------------------------
// NAME: Kristiyan Petrov
// CLASS: XIa
// NUMBER: 13
// PROBLEM: #1
// FILE NAME: main.c
// Имплементация на unix функцията ls
// с подръжка на 3 аргумента
// -A; -l; -R
// -----------------------------------------

#include <stdio.h>
#include <dirent.h>
#include <sys/stat.h>
#include <unistd.h>
#include <string.h>
#include <grp.h>
#include <pwd.h>
#include <time.h>
#include <stdlib.h>

int list = 0, hidden = 0, recursive = 0;

// ---------------------------------------
// FUNCTION: printType
// Проверява типа на дадения аргумент
// PARAMETERS: truct stat st
// структура от тип stat
// благодарение на коята взимаме st_mode
// с който намираме типа на аргумента
// ---------------------------------------

void printType(struct stat st);

// ---------------------------------------
// FUNCTION: printListInfo
// функция, която принтира информацията
// наподобяваща тази в unix командата
// ls -l
// PARAMETERS: struct stat st
// структура от тип stat
// която съдържа необходимата информация
// за даден файл, която извличаме
// ---------------------------------------

void printListInfo(struct stat st);

// ---------------------------------------
// FUNCTION: basicLS
// основната функция, коята имплементира
// метода за ls и съответните флагове, които
// използва
// PARAMETERS:  char* string, int num, int index
// приема стринг, който представлява входния аргумент
// номер, който представлява общия брой входни аргументи
// без допълнителните аргументи, като: "-l -R -A"
// и номера на самия аргумент (кой подрет е)
// ---------------------------------------

void basicLS(char* string, int num, int index);

// ---------------------------------------
// FUNCTION: checkForArguments
// функция, която проверява за аргументи, като:
// "-l" "-R" "-A", и вика главната функция с
// останалите аргументи (файловете подадени от командния ред)
// PARAMETERS: int argc, char** argv
// това са параметрите на main-a, представляват
// бройка и масив от стрингове от стандартния вход
// ---------------------------------------

void checkForArguments(int argc, char** argv);

// ---------------------------------------
// FUNCTION: getTotalBlocks
// обикаля дадената директория и намира
// "total" стойността от "ls -l", която е
// сбор от блоковете на всички файлове / 2
// PARAMETERS: char* string
// името на директорията, от командния ред
// ---------------------------------------

void getTotalBlocks(char* string);

// ---------------------------------------
// FUNCTION: getTotalDirs
// обикаля дадената директория и намира
// колко под директории има в нея, връща
// тяхната бройка
// PARAMETERS: char* string
// името на директорията, подадена от командния ред
// ---------------------------------------

int getTotalDirs(char* string);

int main(int argc, char* argv[]) {
    checkForArguments(argc, argv);

    return 0;
}

void printType(struct stat st) {
    switch (st.st_mode & S_IFMT) {
        case S_IFBLK: printf("b"); break;
        case S_IFCHR: printf("c"); break;
        case S_IFDIR: printf("d"); break;
        case S_IFLNK: printf("l"); break;
        case S_IFSOCK: printf("s"); break;
        case S_IFREG: printf("-"); break;
        case S_IFIFO: printf("l"); break;
        default: printf("?"); break;
    }
}

int getTotalDirs(char* string) {
    DIR* fd;
    struct dirent* dirent;
    struct stat st;
    int totalDirs = 0;

    if ((fd = opendir(string)) == NULL) {
        char* error = malloc(strlen("ls: cannot open directory ") + strlen(string) +1);
        strcat(error, "ls: cannot open directory ");
        strcat(error, string);
        perror(error);
        free(error);
        return 1;
    }

    while ((dirent = readdir(fd)) != NULL) {
        if (hidden) {
            if (strcmp(dirent->d_name, ".") != 0 && strcmp(dirent->d_name, "..") != 0) {
                char* fullPath = malloc(strlen(string) + strlen("/") + strlen(dirent->d_name) +1);
                strcpy(fullPath, string);
                strcat(fullPath, "/");
                strcat(fullPath, dirent->d_name);
                if (stat(fullPath, &st)  != 0) {
                    perror("stat");
                    free(fullPath);
                    return 1;
                }
                if (recursive) {
                    if (S_ISDIR(st.st_mode)) {
                        ++totalDirs;
                    }
                }
                free(fullPath);
            }
        } else if (!hidden) {
            if (dirent->d_name[0] != '.') {
                char* fullPath = malloc(strlen(string) + strlen("/") + strlen(dirent->d_name) +1);
                strcpy(fullPath, string);
                strcat(fullPath, "/");
                strcat(fullPath, dirent->d_name);
                if (stat(fullPath, &st)  != 0) {
                    perror("stat");
                    free(fullPath);
                    return 1;
                }
                if (recursive) {
                    if (S_ISDIR(st.st_mode)) {
                        ++totalDirs;
                    }
                }
                free(fullPath);
            }
        }
    }
    if (closedir(fd) != 0) {
        perror("closedir");
        return 1;
    }
        return totalDirs;
}

void getTotalBlocks(char* string) {
    DIR* fd;
    struct dirent* dirent;
    struct stat st;
    int total = 0;

    if ((fd = opendir(string)) == NULL) {
        char* error = malloc(strlen("ls: cannot open directory ") + strlen(string) + 1);
        strcat(error, "ls: cannot open directory ");
        strcat(error, string);
        perror(error);
        free(error);
        return;
    }

    while ((dirent = readdir(fd)) != NULL) {
        if (hidden) {
            if (strcmp(dirent->d_name, ".") != 0 && strcmp(dirent->d_name, "..") != 0) {
                char* fullPath = malloc(strlen(string) + strlen("/") + strlen(dirent->d_name) +1);
                strcpy(fullPath, string);
                strcat(fullPath, "/");
                strcat(fullPath, dirent->d_name);
                if (stat(fullPath, &st)  != 0) {
                    perror("stat");
                    free(fullPath);
                    return;
                }
                total += st.st_blocks;
                free(fullPath);
            }
        } else if (!hidden) {
            if (dirent->d_name[0] != '.') {
                char* fullPath = malloc(strlen(string) + strlen("/") + strlen(dirent->d_name) +1);
                strcpy(fullPath, string);
                strcat(fullPath, "/");
                strcat(fullPath, dirent->d_name);
                if (stat(fullPath, &st)  != 0) {
                    perror("stat");
                    free(fullPath);
                    return;
                }
                total += st.st_blocks;
                free(fullPath);
            }
        }
    }
    if (closedir(fd) != 0) {
        perror("closedir");
        return;
    }
    printf("total %d\n", total/2);
}

void printListInfo(struct stat st) {
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

    printf(" %ld", st.st_nlink);

    struct passwd* user = getpwuid(st.st_uid);
    if (user == NULL) {
        perror("passwd");
        return;
    }
    printf(" %s", user->pw_name);
    struct group* group = getgrgid(st.st_gid);
    if (group == NULL) {
        perror("group");
        return;
    }
    printf(" %s", group->gr_name);

    printf(" %ld",st.st_size);

    struct tm *tm = localtime(&st.st_mtime);
    if (tm == NULL) {
        perror("time");
        return;
    }
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
}

void checkForArguments(int argc, char** argv) {
    int argument;
    while ((argument = getopt(argc, argv, "lRA")) != -1) {
        if (argument == 'l') {
            list = 1;
        }
        if (argument == 'A') {
            hidden = 1;
        }
        if (argument == 'R') {
            recursive = 1;
        }
    }
    if (optind == argc) {
        basicLS(".", 0, 0);
    } else {
        int numOfFiles = 0, fileIndex = 0;
        for (int counter = optind; counter < argc; ++counter) {
            ++numOfFiles;
        }
        for (; optind < argc; ++optind) {
            ++fileIndex;
            basicLS(argv[optind], numOfFiles, fileIndex);
        }
    }
}

void basicLS(char* string, int num, int index) {
    DIR* fd;
    struct dirent* dirent;
    struct stat st, check;
    char* err;
    int arrayNum = 0;
    if (stat(string, &check) != 0) {
        perror("stat");
        return;
    }
    switch (check.st_mode & S_IFMT) {
        case S_IFBLK:
        case S_IFCHR:
        case S_IFLNK:
        case S_IFSOCK:
        case S_IFREG:
        case S_IFIFO:
            if (index > 1) {
                printf("\n");
            }

            printType(check);

            if (stat(string, &st) != 0) {
                perror("stat");
                return;
            }

            if (list) {
                printListInfo(st);
            }

            printf(" %s\n", string);
            break;
        case S_IFDIR:
            if ((fd = opendir(string)) == NULL) {
                char* err2  = malloc(strlen("ls: cannot open directory ") + strlen(string) +1);
                strcat(err2, "ls: cannot open directory ");
                strcat(err2, string);
                perror(err2);
                free(err2);
                return;
            }
            if (index > 1) {
                printf("\n");
            }
            if (num > 1 || recursive) {
                printf("%s:\n", string);
            }
            if (list) {
                getTotalBlocks(string);
            }
            char** allDirs = malloc(getTotalDirs(string) * sizeof(char*));
            while ((dirent = readdir(fd)) != NULL) {
                if (hidden) {
                    if (strcmp(dirent->d_name, ".") != 0 && strcmp(dirent->d_name, "..") != 0) {
                        char* fullPath = malloc(strlen(string) + strlen("/") + strlen(dirent->d_name) +1);
                        strcpy(fullPath, string);
                        strcat(fullPath, "/");
                        strcat(fullPath, dirent->d_name);
                        if (stat(fullPath, &st)  != 0) {
                            perror("stat");
                            free(fullPath);
                            for (int i = 0; i < getTotalDirs(string); ++i) {
                                free(allDirs[i]);
                            }
                            free(allDirs);
                            return;
                        }
                        printType(st);
                        if (list) {
                            printListInfo(st);
                        }
                        printf(" %s\n", dirent->d_name);
                        if (recursive) {
                            if (S_ISDIR(st.st_mode)) {
                                allDirs[arrayNum] = malloc(strlen(fullPath) + 1);
                                strcpy(allDirs[arrayNum], fullPath);
                                ++arrayNum;
                            }
                        }
                        free(fullPath);
                    }
                } else if (!hidden) {
                    if (dirent->d_name[0] != '.') {
                        char* fullPath = malloc(strlen(string) + strlen("/") + strlen(dirent->d_name) +1);
                        strcpy(fullPath, string);
                        strcat(fullPath, "/");
                        strcat(fullPath, dirent->d_name);
                        if (stat(fullPath, &st)  != 0) {
                            perror("stat");
                            free(fullPath);
                            for (int i = 0; i < getTotalDirs(string); ++i) {
                                free(allDirs[i]);
                            }
                            free(allDirs);
                            return;
                        }
                        printType(st);
                        if (list) {
                            printListInfo(st);
                        }
                        printf(" %s\n", dirent->d_name);
                        if (recursive) {
                            if (S_ISDIR(st.st_mode)) {
                                allDirs[arrayNum] = malloc(strlen(fullPath) + 1);
                                strcpy(allDirs[arrayNum], fullPath);
                                ++arrayNum;
                            }
                        }
                        free(fullPath);
                    }
                }
            }
            if (closedir(fd) != 0) {
                perror("closedir");
                for (int i = 0; i < getTotalDirs(string); ++i) {
                    free(allDirs[i]);
                }
                free(allDirs);
                return;
            }
            if (recursive) {
                for (int i = 0; i < getTotalDirs(string); ++i) {
                    basicLS(allDirs[i], getTotalDirs(string), i+2);
                    free(allDirs[i]);
                }
            }
            free(allDirs);
            break;
        default:
            err = malloc(strlen("ls: cannot access ") + strlen(string) + 1);
            strcpy(err, "ls: cannot access ");
            strcat(err, string);
            perror(err);
            free(err);
            break;
    }
}