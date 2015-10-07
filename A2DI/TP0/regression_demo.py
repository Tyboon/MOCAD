from sklearn import linear_model

import numpy as np
import matplotlib.pyplot as plt

# Faire la cross_val et le scoring 

x = np.loadtxt('x1.txt')
y = np.loadtxt('y1.txt')

plt.scatter(x,y)

regr = linear_model.LinearRegression()
regr.fit(x[:,np.newaxis], y)

x_test = np.linspace(np.min(x), np.max(x), 100)

plt.plot(x_test, regr.predict(x_test[:,np.newaxis]), color='blue', linewidth=3)

plt.show()
