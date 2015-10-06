import numpy as np
import matplotlib.pyplot as plt

f = np.loadtxt('data/fishes.txt')
s = np.loadtxt('data/sharks.txt')

r = f/s

t = range(101)

plt.plot(t, f, color='blue', linewidth=3)

plt.plot(t, s, color='red', linewidth=3)

# plt.plot(t, r, color='black', linewidth=3)

plt.show()
