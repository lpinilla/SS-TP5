import matplotlib.pyplot as plt
import pandas as pd
import numpy as np
import matplotlib.ticker as mticker

# tener en cuenta, fijarse donde se "estabiliza" el grafico para graficar
estabiliza=1

filename="../resources/velocity_50.tsv"
my_csv = pd.read_csv(filename,sep='    ',header=None,usecols=[0], names=['velo'])
velo = my_csv.velo

eje_x= np.arange(1,len(velo)+1,1)

print("Promedio: ",np.mean(velo[estabiliza:len(velo)]))
x_c = [estabiliza, estabiliza]
y_c = [np.amin(velo), np.max(velo)]
plt.plot(x_c, y_c, linestyle='--',linewidth=0.3)

plt.ylabel(r'Promedio de Velocidad de las particulas $[\frac{m}{s}]$')
plt.xlabel('Tiempo [s]')
plt.plot(eje_x,velo)
plt.show()

