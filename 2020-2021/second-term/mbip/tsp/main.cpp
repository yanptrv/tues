#include <bits/stdc++.h>

#define N 50

int squareMatrix[N][N];

int loadGraph(char** argv);
void tsp(int size, int source);
void dijkstraMethod(int size, int source);

int main(int argc, char** argv) {
    if (argc != 2) {
        std::cout << "Use ./tsp <filename.txt>" << std::endl;
        return 1;
    }
    std::cout << "Въведете стартов връх: ";
    int input;
    std::cin >> input;
    tsp(loadGraph(argv), input);
    dijkstraMethod(loadGraph(argv), input);

    return 0;
}

int loadGraph(char** argv) {
    std::ifstream fd(argv[1]);
    int rows = std::count(std::istreambuf_iterator<char>(fd), std::istreambuf_iterator<char>(), '\n');
    fd.seekg(0);

    for (int rowNum = 0; rowNum < rows; ++rowNum) {
        std::string fileVal;
        for (int colNum = 0; colNum < rows; ++colNum) {
            if (colNum != rows-1) {
                getline(fd, fileVal, ' ');
            } else {
                getline(fd, fileVal, '\n');
            }
            squareMatrix[rowNum][colNum] = std::stoi(fileVal);
        }
    }

    fd.close();
    return rows;
}

void tsp(int size, int source) {
    int path[size], minLength = INT_MAX;

    std::vector<int> points;
    for (int i = 0; i < size; ++i) {
        if (i != source) {
            points.push_back(i);
        }
    }
    do {
        int currLength = 0, temp = source, flag = 0;
        for (int i : points) {
            if (squareMatrix[temp][i] == -1) {
                flag = 1;
                break;
            }
            currLength += squareMatrix[temp][i];
            temp = i;
        }
        if (squareMatrix[temp][source] == -1 or flag) continue;
        currLength += squareMatrix[temp][source];
        if (minLength > currLength) {
            minLength = std::min(minLength, currLength);
            for (int i = 1; i < size; ++i) {
                path[i] = points[i - 1];
                path[0] = source;
            }
        }

    }while(next_permutation(points.begin(), points.end()));
    std::cout << "Път:";
    for (int i = 0; i < size; ++i) {
        std::cout << " " << path[i];
    }
    std::cout << std::endl << "Време: " << minLength << "мин" << std::endl;
}

void dijkstraMethod(int size, int source) {
    int distance[size];
    bool ifUsed[size];
    distance[source] = 0;
    ifUsed[0] = false;
    for (int i = 1; i < size; ++i) {
        distance[i] = INT_MAX;
        ifUsed[i] = false;
    }

    for (int i = 0; i < size-1; ++i) {

        int minVal = INT_MAX, minNum;
        for (int j = 0; j < size; ++j) {
            if (!ifUsed[j] && distance[j] <= minVal) {
                minNum = j;
                minVal = distance[j];
            }
        }
        int inUse = minNum;
        ifUsed[inUse] = true;

        for (int j = 0; j < size; ++j) {
            if (!ifUsed[j] && squareMatrix[inUse][j] && distance[inUse] != INT_MAX and squareMatrix[inUse][j] + distance[inUse] < distance[j] and squareMatrix[inUse][j] != -1) {
                distance[j] = distance[inUse] + squareMatrix[inUse][j];
            }
        }
    }

    for (int i = 0; i < size; i++)
        printf("Връх -> %d <- oтдалечен на %dмин от стартов връх -> %d <- \n", i, distance[i], source);
}
