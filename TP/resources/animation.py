import sys
import matplotlib.animation as ani
import matplotlib.pyplot as plt
from matplotlib.ticker import (AutoMinorLocator, MultipleLocator)

#follow = 15
size = 10
w = 3

axis_left = -3
axis_right = 6
axis_down = -0.5
axis_up = size + 0.5

times = [
    {
    't' : 0,
    'x' : [],
    'y' : [],
    'r' : []
    }
]

aux = {'t': 0, 'x' : [], 'y' : [], 'r' : []}

with open(str(sys.argv[1])) as f:
    next(f)
    for line in f:
        #-1 para ignorar el \n
        data = line[:-1].split('   ')
        if(len(data) == 1):
            if(len(aux['x']) != 0):
                times.append(aux)
                aux = {'t': 0, 'x' : [], 'y' : [], 'r' : []}
            aux['t'] = data[0]
        else:
            aux['x'].append(float(data[0]))
            aux['y'].append(float(data[1]))
            aux['r'].append(float(data[2]))
f.close()

times = times[1:]

#fig, ax = plt.subplots()
#Writer = ani.writers['ffmpeg']
#writer = Writer(fps=300, metadata=dict(artist='me'), bitrate=1080)
#frame = '0'

#ax.xaxis.set_major_locator(MultipleLocator(10))
#ax.yaxis.set_major_locator(MultipleLocator(10))

def init():
    ax.set_ylim(axis_down, axis_up)
    ax.set_xlim(axis_left, axis_right)
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

left_rect = plt.Rectangle((-0.5,0), 0.5, size, color='yellow', fill=False)
right_rect = plt.Rectangle((w,0), 0.5, size, color='yellow', fill=False)
plt.gcf().gca().add_artist(left_rect)
plt.gcf().gca().add_artist(right_rect)

def create_circles(x, y, r):
    circles = []
    for i in range(len(x)):
        circles.append(plt.Circle((x[i], y[i]), r[i], color='blue', fill=False))
        #plt.gcf().gca().add_artist(cir)
    return circles

def animate(i):
    x = times[i]['x'][0:]
    y = times[i]['y'][0:]
    r = times[i]['r'][0:]
    line.set_data(x,y)
    ax.set_title(str(i))
    ax.patches = []
    circles = create_circles(x,y,r)
    for i in range(len(circles)):
        ax.add_patch(circles[i])
    return line,
    #frame = str(i)

animation = ani.FuncAnimation(fig, animate, frames=len(times), interval=1, repeat=True, init_func=init)
plt.show()
#animation.save('poc.gif', writer='imagemagick')
