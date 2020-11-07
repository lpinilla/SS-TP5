import matplotlib.pyplot as plt
import pandas as pd
import numpy as np

filename1="../resources/veloVsDensiL10H3.tsv"
my_csv = pd.read_csv(filename1,sep=' ',header=None,usecols=[0,1,2], names=['densi1','velo1','error1'])
velo1 = my_csv.velo1
densi1 = my_csv.densi1
error1=my_csv.error1

# filename2="../resources/veloVsDensiL8H5.tsv"
# my_csv = pd.read_csv(filename2,sep=' ',header=None,usecols=[0,1,2], names=['densi2','velo2','error2'])
# velo2 = my_csv.velo2
# densi2 = my_csv.densi2
# error2= my_csv.error2

plt.ylabel(r'Velocidad  $[\frac{m}{s}]$')
plt.xlabel(r'Densidad $[\frac{1}{m^{2}}]$')
plt.errorbar(densi1, velo1, error1, linestyle='-', marker='.', color='red')
# plt.errorbar(densi2, velo2, error2, linestyle='None', marker='.', color='blue')
plt.legend(['Largo:10 Ancho:3'])
plt.show()