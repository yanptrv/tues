#include <iostream>
#include <fstream>
#include <algorithm>
#include <climits>
#include <vector>

using namespace std;

int** graph;
int size;
int* printa;

void getMatrix(int argc, char* argv[]) {
    if (argc != 2) {
        cout << "Use ./a.out <filename.txt>";
        return;
    }

    ifstream fd(argv[1]);
    size =  count(istreambuf_iterator<char>(fd), istreambuf_iterator<char>(), '\n');

    graph = new int*[size];
    for (int i = 0; i < size; ++i) {
        graph[i] = new int[size];
    }

    fd.seekg(0);
    string line;
    for (int i = 0; i < size; ++i)
    {
        for (int j = 0; j < size; ++j)
        {
            if (j != size - 1) {
                getline(fd, line, ' ');
            } else {
                getline(fd, line, '\n');
            }
            graph[i][j] = stoi(line);
        }
    }

    fd.close();
}

int minDistance(const int dist[], const bool sptSet[])
{
    // Initialize min value
    int min = INT_MAX, min_index;

    for (int v = 0; v < size; ++v)
        if (!sptSet[v] && dist[v] <= min)
            min = dist[v], min_index = v;

    return min_index;
}

void printSolution(int dist[])
{
    printf("Vertex   Distance from Source\n");
    for (int i = 0; i < size; i++)
        printf("%d \t\t %d\n", i, dist[i]);
}

void dijkstra(int** grapha, int src)
{
    int dist[size]; // The output array.  dist[i] will hold the shortest
    // distance from src to i

    bool sptSet[size]; // sptSet[i] will be true if vertex i is included in shortest
    // path tree or shortest distance from src to i is finalized

    // Initialize all distances as INFINITE and stpSet[] as false
    for (int i = 0; i < size; i++)
        dist[i] = INT_MAX, sptSet[i] = false;

    // Distance of source vertex from itself is always 0
    dist[src] = 0;

    // Find shortest path for all vertices
    for (int count = 0; count < size - 1; count++) {
        // Pick the minimum distance vertex from the set of vertices not
        // yet processed. u is always equal to src in the first iteration.
        int u = minDistance(dist, sptSet);

        // Mark the picked vertex as processed
        sptSet[u] = true;

        // Update dist value of the adjacent vertices of the picked vertex.
        for (int v = 0; v < size; v++)

            // Update dist[v] only if is not in sptSet, there is an edge from
            // u to v, and total weight of path from src to  v through u is
            // smaller than current value of dist[v]
            if (!sptSet[v] && grapha[u][v] && dist[u] != INT_MAX
                && dist[u] + grapha[u][v] < dist[v] && grapha[u][v] != -1)
                dist[v] = dist[u] + grapha[u][v];
    }

    // print the constructed distance array
    printSolution(dist);
}

int TSP(int** grph, int p) {
    vector<int> ver; //
    for (int i = 0; i < size; i++) {
        if (i != p)
            ver.push_back(i);
    }
    int m_p = INT_MAX; // store minimum weight of a graph
    do {
        int cur_pth = 0;
        int k = p;
        for (int i : ver) {
            cur_pth += grph[k][i];
            k = i;
        }
        cur_pth += grph[k][p];
        if (m_p > cur_pth) {
            m_p = cur_pth;
            printa = new int[size];
            printa[0] = p;
            for (int i = 1; i < size; ++i) {
                printa[i] = ver[i-1];
            }
        }
    }

    while (next_permutation(ver.begin(), ver.end()));
    return m_p;
}

int main(int argc, char* argv[]) {
    getMatrix(argc, argv);
    dijkstra(graph, 0);
    cout << TSP(graph, 0) << endl;
    for (int i = 0; i < size; ++i) {
        cout << printa[i];
    }
    return 0;
}
