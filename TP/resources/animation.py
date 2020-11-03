import sys
import matplotlib.animation as ani
import matplotlib.pyplot as plt
from matplotlib.ticker import (AutoMinorLocator, MultipleLocator)

#follow = 15
size = 10
h = 2.5

times = [
    {
    't' : 0,
    'x' : [],
    'y' : []
    }
]

aux = {'t': 0, 'x' : [], 'y' : [], 'v' : []}

with open(str(sys.argv[1])) as f:
    next(f)
    for line in f:
        #-1 para ignorar el \n
        data = line[:-1].split('   ')
        if(len(data) == 1):
            if(len(aux['x']) != 0):
                times.append(aux)
                aux = {'t': 0, 'x' : [], 'y' : []}#, 'v' : []}
            aux['t'] = data[0]
        else:
            aux['x'].append(float(data[0]))
            aux['y'].append(float(data[1]))
            #aux['v'].append(float(data[2]))
f.close()

times = times[1:]

#fig, ax = plt.subplots()
#Writer = ani.writers['ffmpeg']
#writer = Writer(fps=300, metadata=dict(artist='me'), bitrate=1080)
#frame = '0'

#ax.xaxis.set_major_locator(MultipleLocator(10))
#ax.yaxis.set_major_locator(MultipleLocator(10))

def init():
    ax.set_ylim(0, size)
    ax.set_xlim(-size, size)
    ax.grid(linestyle='-', linewidth='0.5')
    del xdata[:]
    del ydata[:]
    line.set_data(xdata, ydata)
    return line,

fig, ax = plt.subplots()
line, = ax.plot([], [], '.', markersize=6)
part, = ax.plot([], [], '.', color="red", markersize=6)
ax.grid()
xdata, ydata = [], []

rect = plt.Rectangle((-size,size - h), 2 * size, h, color='yellow', fill=False)
plt.gcf().gca().add_artist(rect)

def animate(i):
    x = times[i]['x'][0:]
    y = times[i]['y'][0:]
    #part.set_data(times[i]['x'][follow], times[i]['y'][follow])
    line.set_data(x,y)
    ax.set_title(str(i))
    return line,
    #frame = str(i)


animation = ani.FuncAnimation(fig, animate, frames= len(times), interval=1, repeat=True, init_func=init)
plt.show()
#animation.save('poc.gif', writer='imagemagick')
