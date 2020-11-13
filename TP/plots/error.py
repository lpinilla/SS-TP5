import matplotlib.pyplot as plt
import matplotlib.pyplot as error_curve
import numpy as np
import pandas as pd
import seaborn as sns;
from sklearn.metrics import mean_squared_error


filename="../resources/Compare(Punto c)/compare(Paper).tsv"
my_csv = pd.read_csv(filename,sep=' ',header=None,usecols=[1], names=['velo'])
veloPaper = my_csv.velo

filename1="../resources/Compare(Punto c)/compare(nuesto).tsv"
my_csv = pd.read_csv(filename1,sep=' ',header=None,usecols=[1], names=['velo1'])
veloVariacion = my_csv.velo1

filename7="../resources/Compare(Punto c)/compare(Original).tsv"
my_csv = pd.read_csv(filename7,sep=' ',header=None,usecols=[1], names=['velo7'])
veloOriginal=my_csv.velo7

filename8="../resources/Compare(Punto c)/compare0.1.tsv"
my_csv = pd.read_csv(filename8,sep=' ',header=None,usecols=[1], names=['velo8'])
velo8=my_csv.velo8

filename2="../resources/Compare(Punto c)/compare.13.tsv"
my_csv = pd.read_csv(filename2,sep=' ',header=None,usecols=[1], names=['velo2'])
velo2 = my_csv.velo2


errorVariacion=mean_squared_error(veloPaper,veloVariacion)
errorOriginal=mean_squared_error(veloPaper,veloOriginal)
error8=mean_squared_error(veloPaper,velo8)
error2=mean_squared_error(veloPaper,velo2)


print ("Variacion:",errorVariacion)

print ("Original:",errorOriginal)
print ("Original:",error8)
print ("Original:",error2)



plt.ylabel('Error cuadratico medio [$m^{2}$]')
plt.xlabel('Valor Radios Minimos [m] ')
plt.yscale('log')
# plt.xscale('log')

plt.plot(0.18,errorVariacion,'ob')
plt.plot(0.15,errorOriginal,'or')
plt.plot(0.2,0)
# plt.plot(0.2,0,'ow')
# plt.plot(0.1,error8,'og')
# plt.plot(0.13,error2,'oc')

# plt.legend(['R:0.18','R:',Error Rmin])

plt.show()