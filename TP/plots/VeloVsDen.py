import matplotlib.pyplot as plt
import pandas as pd
import numpy as np

filename1="../resources/veloVsDensiL10H2.5.tsv"
my_csv = pd.read_csv(filename1,sep=' ',header=None,usecols=[0,1,2], names=['densi1','velo1','error1'])
velo1 = my_csv.velo1
densi1 = my_csv.densi1
error1=my_csv.error1

filename2="../resources/veloVsDensiH3.tsv"
my_csv = pd.read_csv(filename2,sep=' ',header=None,usecols=[0,1,2], names=['densi2','velo2','error2'])
velo2 = my_csv.velo2
densi2 = my_csv.densi2
error2= my_csv.error2

filename3="../resources/veloVsDensiL10H5.tsv"
my_csv = pd.read_csv(filename3,sep=' ',header=None,usecols=[0,1,2], names=['densi3','velo3','error3'])
velo3 = my_csv.velo3
densi3 = my_csv.densi3
error3= my_csv.error3

filename4="../resources/veloVsDensiL10H8.tsv"
my_csv = pd.read_csv(filename4,sep=' ',header=None,usecols=[0,1,2], names=['densi4','velo4','error4'])
velo4 = my_csv.velo4
densi4 = my_csv.densi4
error4= my_csv.error4

# filename5="../resources/Compare(Punto c)/compare(Paper).tsv"
# my_csv = pd.read_csv(filename5,sep=' ',header=None,usecols=[0,1], names=['densi5','velo5'])
# velo5 = my_csv.velo5
# densi5 = my_csv.densi5
# #
# filename6="../resources/Compare(Punto c)/compare(nuesto).tsv"
# my_csv = pd.read_csv(filename6,sep=' ',header=None,usecols=[0,1,2], names=['densi6','velo6','error6'])
# velo6 = my_csv.velo6
# densi6 = my_csv.densi6
# error6= my_csv.error6

plt.ylabel(r'Velocidad  $[\frac{m}{s}]$')
plt.xlabel(r'Densidad $[\frac{1}{m^{2}}]$')
plt.errorbar(densi1, velo1, error1, linestyle='-', marker='.', color='red')
plt.errorbar(densi2, velo2, error2, linestyle='-', marker='.', color='blue')
plt.errorbar(densi3, velo3, error3, linestyle='-', marker='.', color='green')
plt.errorbar(densi4, velo4, error4, linestyle='-', marker='.', color='black')
plt.legend(['Ancho:2.5','Ancho:3','Ancho:5','Ancho:8'])
# plt.legend(['Ancho:3 Largo:10'])

# plt.plot(densi5, velo5, linestyle='None', marker='.', color='black')
# plt.errorbar(densi6, velo6, error6, linestyle='-', marker='.', color='red')
# plt.legend(['Paper','Nuestro'])
# plt.grid()
plt.savefig('densivsVelo.png')
plt.show()