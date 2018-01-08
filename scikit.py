# -*- coding: utf-8 -*-
"""
Created on Mon Jan  8 14:18:35 2018

@author: Kuszc
"""
import numpy as np
from sklearn.neural_network import MLPRegressor
import matplotlib.pyplot as plt
#np.random.seed(3)
n = 2000
x = np.random.uniform(1, 100, size = n)
y = np.sqrt(x)
X = np.reshape(x ,[n, 1]) 
y = np.reshape(y ,[n ,])
print (X)
print (y) 
clf = MLPRegressor(alpha=0.1, hidden_layer_sizes = (3,), max_iter = 1000, 
                 activation = 'logistic', verbose = 'True', learning_rate = 'adaptive')
a = clf.fit(X, y)
print (clf.n_layers_)
x_ = np.linspace(0, 100, 160) # define axis

pred_x = np.reshape(x_, [160, 1]) # [160, ] -> [160, 1]
pred_y = clf.predict(pred_x) # predict network output given x_
fig = plt.figure() 
plt.plot(x_, x_**0.5, color = 'b') # plot original function
plt.plot(pred_x, pred_y, '-') # plot network output                     
