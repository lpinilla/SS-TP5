import matplotlib.pyplot as plt
import pandas as pd
import numpy as np

filename1="../resources/veloVsDensiL10H3.tsv"
my_csv = pd.read_csv(filename1,sep='    ',header=None,usecols=[0,1], names=['densi1','velo1'])
velo1 = my_csv.velo1
densi1 = my_csv.densi1

filename2="../resources/veloVsDensiL8H5.tsv"
my_csv = pd.read_csv(filename2,sep='    ',header=None,usecols=[0,1], names=['densi2','velo2'])
velo2 = my_csv.velo2
densi2 = my_csv.densi2

plt.ylabel(r'Promedio de Velocidad de las particulas $[\frac{m}{s}]$')
plt.xlabel(r'Densidad $[\frac{1}{m^{2}}]$')
plt.plot(densi1,velo1,'ro-')
plt.plot(densi2,velo2,'bo-')
plt.legend(['Largo:10 Ancho:3','Largo:8 Ancho:5'])
plt.show()