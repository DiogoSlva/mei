import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

def calculate_b1_b0(x, y, debug = False):
    n = len(x)
    sum_x = sum(x)
    sum_y = sum(y)
    sum_xy_list = list()
    sum_x_squared_list = list()

    for value_x, value_y in zip(x, y):
        temp = 0
        temp = value_x * value_y
        sum_xy_list.append(temp)
    
    sum_xy = sum(sum_xy_list)

    for value_x in x:
        value = 0
        value = value_x * value_x
        sum_x_squared_list.append(value)
    
    sum_x_squared = sum(sum_x_squared_list)

    if debug == True:
        print(f"n: {n}")
        print(f"sum_xy: {sum_xy}")
        print(f"sum_x: {sum_x}")
        print(f"sum_y: {sum_y}")
        print(f"sum_x_squared: {sum_x_squared}")

    b1 = ((n * sum_xy) - (sum_x * sum_y)) / ((n * sum_x_squared) - (sum_x * sum_x))

    b0 = (sum_y - (b1 * sum_x) ) / n 

    return b1, b0

def plot_function(x, y, b1, b0):

    plt.scatter(x, y, color='blue', label='Dados')
    y_pred = [b0 + b1 * xi for xi in x]
    plt.plot(x, y_pred, color='red', label='Linha de Regressão')
    plt.title(f'Linear Regression: y = {b1}x + {b0}')
    plt.xlabel('X')
    plt.ylabel('Y')
    plt.legend()
    
    # Exibir o gráfico
    plt.show()

if __name__ == "__main__":
    path = 'UnivariateRegressionDataset.csv'

    # for testing
    x = [1,2,4,3,5]
    y = [2,3,3,4,5]

    data = pd.read_csv(path)

    population = data.iloc[:,0].tolist()
    profit = data.iloc[:,1].tolist()

    b1, b0 = calculate_b1_b0(population,profit)
    print(f'valor B1: {b1}')
    print(f'valor B0: {b0}')

    plot_function(population, profit, b1, b0)

