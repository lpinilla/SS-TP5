import matplotlib.pyplot as plt
import pandas as pd
import numpy as np
import seaborn as sns;
from sklearn.metrics import mean_squared_error

# filename="../resources/Compare(Punto c)/avg.tsv"
# my_csv = pd.read_csv(filename,sep=' ',header=None,usecols=[0,1], names=['densi','velo'])
# velo = my_csv.velo
# densi=my_csv.densi

# print("velo:",np.mean(velo))
# print("densi:",np.mean(densi))

filename5="../resources/Compare(Punto c)/compare(Paper).tsv"
my_csv = pd.read_csv(filename5,sep=' ',header=None,usecols=[0,1], names=['densi5','velo5'])
velo5 = my_csv.velo5
densi5 = my_csv.densi5

filename6="../resources/Compare(Punto c)/compare(nuesto).tsv"
my_csv = pd.read_csv(filename6,sep=' ',header=None,usecols=[0,1,2], names=['densi6','velo6','error6'])
velo6 = my_csv.velo6
densi6 = my_csv.densi6
error6= my_csv.error6

filename8="../resources/Compare(Punto c)/compare(Original).tsv"
my_csv = pd.read_csv(filename8,sep=' ',header=None,usecols=[0,1,2], names=['densi8','velo8','error8'])
velo8 = my_csv.velo8
densi8 = my_csv.densi8
error8= my_csv.error8

filename9="../resources/Compare(Punto c)/compare0.1.tsv"
my_csv = pd.read_csv(filename9,sep=' ',header=None,usecols=[0,1,2], names=['densi9','velo9','error9'])
velo9 = my_csv.velo9
densi9 = my_csv.densi9
error9= my_csv.error9

# filename2="../resources/Compare(Punto c)/compare.13.tsv"
# my_csv = pd.read_csv(filename2,sep=' ',header=None,usecols=[0,1,2], names=['densi2','velo2','error2'])
# velo2 = my_csv.velo2
# densi2 = my_csv.densi2
# error2= my_csv.error2

plt.ylabel(r'Velocidad Promedio $[\frac{m}{s}]$')
plt.xlabel(r'Densidad $[\frac{1}{m^{2}}]$')
plt.plot(densi5, velo5, linestyle='--', marker='.', color='black')
plt.errorbar(densi6, velo6, error6, linestyle='--', marker='.', color='red')
plt.errorbar(densi8, velo8, error8, linestyle='--', marker='.', color='green')
# plt.errorbar(densi2, velo2, error2, linestyle='--', marker='.', color='cyan')
plt.errorbar(densi9, velo9, error9, linestyle='--', marker='.', color='blue')


errorVariacion=mean_squared_error(velo5,velo6)
errorOriginal=mean_squared_error(velo5,velo8)
print ("Variacion:",errorVariacion)
print ("Original:",errorOriginal)
# plt.grid()
plt.legend([' Mori and Tsukaguchi','Rmin=0.18','Rmin=0.15','Rmin=0.1'])

plt.savefig('error.png')
plt.show()