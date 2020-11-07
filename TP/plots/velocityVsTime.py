import matplotlib.pyplot as plt
import pandas as pd
import numpy as np

# tener en cuenta, fijarse donde se "estabiliza" el grafico para graficar
estabiliza=0

filename="../resources/velocity_120.tsv"
my_csv = pd.read_csv(filename,sep='    ',header=None,usecols=[0], names=['velo'])
velo = my_csv.velo

#imprimo cada 1 segundos osea 20 tiempos
printT=0.5
printStep=10
deltaT=0.05
eje_x= np.arange(0,(len(velo))*deltaT,printT)
eje_y= velo[0:len(velo):printStep]

print("Promedio: ",np.mean(velo[estabiliza:len(velo)]))
print("Error:" ,np.std(velo[estabiliza:len(velo)]))
# print("Mean square error:", mean_squared_error(eje_x,eje_y))
x_c = [estabiliza, estabiliza]
y_c = [np.amin(velo), np.max(velo)]
plt.plot(x_c, y_c, linestyle='--',linewidth=0.3)

plt.ylabel(r'Promedio de Velocidad de las particulas $[\frac{m}{s}]$')
plt.xlabel('Tiempo [s]')
plt.plot(eje_x,eje_y,'-o',linewidth=1)
# plt.errorbar(eje_x, eje_y, error, linestyle='-', marker='.', color='red')
plt.legend(['Estable'])
plt.savefig('veloVsTimeN121.png')
plt.show()


