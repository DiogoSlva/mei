#%% md
# Regressão Linear
## Importação de packages
#%%
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
#%% md
## Definição de funções auxiliares
#%%
def compute_cost(x, y, theta):
    m = np.shape(y)[0]
    htheta = np.matmul(x, theta)
    dif = htheta - y
    dif = np.power(dif, 2)
    j = 1/(2*m) * np.sum(dif)
    return j

def gradient_descent(x, y, theta, alpha, num_iters):
    m = np.shape(y)[0]
    j_history = np.zeros((num_iters, 1))
    for j in range(num_iters):
        htheta = np.matmul(x, theta)
        dif = htheta - y
        temp = np.empty(theta.shape)
        for i in range(temp.shape[0]):
            temp2 = dif * x[:, [i]]
            temp[i, 0] = theta[i, 0] - alpha / m * np.sum(temp2)
        theta = temp
        j_history[j, 0] = compute_cost(x, y, theta)
    return j_history, theta
#%% md
## Getting Data
#%%
print('getting data')
df = pd.read_csv('RegressaoUnivariada.csv')
print(df.describe())
# gráfico
df.plot(kind='scatter', x='Population',
        y='Profit', title='Pop vs prof')
plt.show()
#%% md
## Extract data
#%%
x = df['Population']
x = np.expand_dims(x, 1)
x = np.hstack((np.ones((np.shape(x)[0], 1)), x))  # add an extra column of ones
y = df['Profit']
y = np.expand_dims(y, 1)
theta = np.zeros((x.shape[1], 1))
#%% md
## Compute the regression
#%%
# Some gradient descent settings
iterations = 15000
alpha = 0.00001
print('\nTesting the cost function ...\n')
# compute and display initial cost
J = compute_cost(x, y, theta)
# run gradient descent
j_history, theta = gradient_descent(x, y, theta, alpha, iterations)
# print theta to screen
print('Theta found by gradient descent:\n')
print(theta)
#%% md
## Plot fitted Line
#%%
y_fitted = np.matmul(x, theta)
plt.scatter(x[:, [1]], y, color='black')
plt.plot(x[:, [1]], y_fitted, color='blue', linewidth=3)
plt.title('Population vs Profit (manual)')
plt.xlabel('Population')
plt.ylabel('Profit')
plt.savefig('univariate_linear.pdf')
plt.show()
# plot cost according to the number of iterations
plt.plot(range(iterations), j_history)
plt.show()


