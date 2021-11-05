#include "gaus_method.h"

void gaus_method()
{
    //checking for the size of the matrix
    int rows, cols;
    std::cout << "Matrix rows: " << std::endl;  std::cin >> rows;
    std::cout << "Matrix cols: " << std::endl; std::cin >> cols;
    if (rows != cols)
    {
        std::cout << "Can't apply Gaus' method to such a matrix";
        return;
    }
    
    int n = rows;
    double **matrix = new double*[n], x[n], safe;//matrix - dynamic two-dimensional array; x - array
   
    //store elements in the matrix
    for (int i = 0; i < n; ++i)
    {
        matrix[i] = new double[n+1];
        for (int j = 0; j < n+1; ++j)
        {
            std::cout << "Enter element [" << i << "][" << j << "]" << std::endl; std::cin >> matrix[i][j];
        }
    }

    //mathematical operations with the elements
    for (int i = n-1; i > 0; --i)
    {
        if (matrix[i-1][0] < matrix[i][0])
        {
            for (int j = 0; j <= n; ++j)
            {
                safe = matrix[i][j];
                matrix[i][j] = matrix[i-1][j];
                matrix[i-1][j] = safe;
            }
        }
    }

    for (int k = 0; k < n-1; ++k)
    {
        for (int i = k; i < n-1; ++i)
        {
            safe = (matrix[i+1][k] / matrix[k][k]);
            for (int j = 0; j <= n; ++j)
            {
                matrix[i+1][j] -= safe*matrix[k][j];
            }
        }
    }

    for (int i = n-1; i >= 0; --i)
    {
        safe = 0;
        for (int j = i; j <= n-1; ++j)
        {
            safe += matrix[i][j]*x[j];
        }
        x[i] = (matrix[i][n] - safe) / matrix[i][i];
    }

    //printing the answers
    for (int i = 0; i < n; ++i)
    {
        std::cout << "X" << i << ": " << x[i] << std::endl;
    }

    //deleting the memory use by the dynamic two-dimensional array
    for (int i = 0; i < n; ++i)
    {
        delete[] matrix[i];
    }
    delete[] matrix;
}