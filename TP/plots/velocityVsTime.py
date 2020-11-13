import matplotlib.pyplot as plt
import pandas as pd
import numpy as np

# tener en cuenta, fijarse donde se "estabiliza" el grafico para graficar
estabiliza=11
filename="../resources/velocity_144.tsv"
my_csv = pd.read_csv(filename,sep='    ',header=None,usecols=[0], names=['velo'])
velo = my_csv.velo

# filename="../resources/L10W3/velocity_30.tsv"
# my_csv30 = pd.read_csv(filename,sep='    ',header=None,usecols=[0], names=['velo'])
# velo30 = my_csv30.velo
#
# filename="../resources/L10W3/velocity_90.tsv"
# my_csv150 = pd.read_csv(filename,sep='    ',header=None,usecols=[0], names=['velo'])
# velo150 = my_csv150.velo
#
# filename="../resources/L10W3/velocity_210.tsv"
# my_csv210 = pd.read_csv(filename,sep='    ',header=None,usecols=[0], names=['velo'])
# velo210 = my_csv210.velo

#imprimo cada 1 segundos osea 20 tiempos
printT=0.5
printStep=10
deltaT=0.05
eje_x= np.arange(0,(len(velo))*deltaT,printT)
eje_y= velo[0:len(velo):printStep]
estabilizaProm=int(estabiliza/deltaT)


# eje_x30= np.arange(0,(len(velo30))*deltaT,printT)
# eje_y30= velo30[0:len(velo30):printStep]
#
# eje_x150= np.arange(0,(len(velo150))*deltaT,printT)
# eje_y150= velo150[0:len(velo150):printStep]
#
# eje_x210= np.arange(0,(len(velo210))*deltaT,printT)
# eje_y210= velo210[0:len(velo210):printStep]

print("Promedio: ",np.mean(velo[estabilizaProm:len(velo)]))
print("Error:" ,np.std(velo[estabilizaProm:len(velo)]))
# print("Mean square error:", mean_squared_error(eje_x,eje_y))
x_c = [estabiliza, estabiliza]
y_c = [np.amin(velo), np.max(velo)]
plt.plot(x_c, y_c, linestyle='--',linewidth=0.3)

plt.ylabel(r'Promedio de Velocidad de las particulas $[\frac{m}{s}]$')
plt.xlabel('Tiempo [s]')
plt.plot(eje_x,eje_y,'-ob',linewidth=1)
# plt.plot(eje_x30,eje_y30,'-or',linewidth=1)
# plt.plot(eje_x150,eje_y150,'-og',linewidth=1)
# plt.plot(eje_x150,eje_y210,'-om',linewidth=1)



# plt.errorbar(eje_x, eje_y, error, linestyle='-', marker='.', color='red')
# plt.legend(['Estable','N: 10','N: 30','N: 120','N: 210'])
# plt.savefig('veloVsTimeN150.png')
plt.show()


