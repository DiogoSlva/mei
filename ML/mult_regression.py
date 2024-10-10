import numpy as np
import pandas as pd

#X12, X22, X1y, X2y and X1X2

def calculate_variables(x1, x2, y, debug = False):
    x1_squared_list = list()
    x2_squared_list = list()
    sum_x1y_list = list()
    sum_x2y_list = list()
    sum_x1x2_list = list()

    for x in x1:
        x = 0
        x = x * x
        x1_squared_list.append(x)
    
    for x in x2:
        x = 0
        x = x * x
        x2_squared_list.append(x)
    
    for value_x1, value_y in zip(x1, y):
        temp = 0
        temp = value_x1 * value_y
        sum_x1y_list.append(temp)
        
    for value_x2, value_y in zip(x2, y):
        temp = 0
        temp = value_x2 * value_y
        sum_x2y_list.append(temp)    
        
    for value_x1, value_x2 in zip(x1, x2):
        temp = 0
        temp = value_x1 * value_x2
        sum_x1x2_list.append(temp) 
        
    x1_squared = sum(x1_squared_list)
    x2_squared = sum(x1_squared_list)
    sum_x1y = sum(sum_x1y_list)
    sum_x2y = sum(sum_x2y_list)
    sum_x1x2 = sum(sum_x1x2_list)

    
    b1 = ((x1_squared * sum_x1y) - (sum_x1x2 * sum_x2y)) / ((x1_squared * x2_squared) - (sum_x1x2))
    b2 = 

if __name__ == "__main__":
    
    path = 'MultivariateRegression.csv'
    data = pd.read_csv(path)
    
    x1 = data.iloc[:,0].tolist()
    x2 = data.iloc[:,1].tolist()
    y  = data.iloc[:,2].tolist()
    