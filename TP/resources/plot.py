import sys
import matplotlib.pyplot as plt
from matplotlib.ticker import (AutoMinorLocator, MultipleLocator)

x = []
y = []
vx = []
vy = []
#particle_id = 15
#radius = 1.0
M = 10
L = 10
H = 3

with open(str(sys.argv[1])) as f:
    next(f)
    for line in f:
        data = line[0:-1].split('   ')
        x.append(float(data[0]))
        y.append(float(data[1]))
f.close()

fig, ax = plt.subplots()
plt.plot(x[0:], y[0:], '.', color='red', markersize=5, label='test')
plt.plot(x[1], y[1], '.', color='blue', markersize=5)
#plt.plot(x[5], y[5], '.', color='yellow', markersize=7, label='Selected')

#grilla de m's
rects = []
positions = [x * (L/M) for x in range(0,M)]
for i in positions:
    for j in positions:
        rects.append(plt.Rectangle((i, j), L/M, L/M, color='yellow', fill=False))
for r in rects:
    plt.gcf().gca().add_artist(r)


#Paredes
right_rect = plt.Rectangle((L, 0), 0.5, L, color='blue', fill=False)
left_rect = plt.Rectangle((-0.5, 0), 0.5, L, color='blue', fill=False)
rect = plt.Rectangle((0, L - H), L, H, color='green', fill=False)
plt.gcf().gca().add_artist(right_rect)
plt.gcf().gca().add_artist(left_rect)
plt.gcf().gca().add_artist(rect)

#Círculos
for i in range(len(x)):
    rad1 = 0.15
    small_cir = plt.Circle((x[i], y[i]), rad1, color='green', fill=False)
    plt.gcf().gca().add_artist(small_cir)
    rad2 = 0.32
    big_cir = plt.Circle((x[i], y[i]), rad2, color='brown', fill=False)
    plt.gcf().gca().add_artist(big_cir)
    rad3 = 0.90
    alt_cir = plt.Circle((x[i], y[i]), rad3, color='pink', fill=False)
    plt.gcf().gca().add_artist(alt_cir)



#circleCut = 5 - y[particle_id]
#cir = plt.Circle((x[particle_id], -circleCut), radius, color='green', fill=False)
#plt.gcf().gca().add_artist(cir)
plt.xlabel('t')
plt.ylabel('x')
plt.title('testing')
plt.legend()

#cambiando el tamaño de la grilla la grilla
ax.set_xlim(-0.5,L + 0.5)
ax.set_ylim(-0.5,L + 0.5)
#ax.set_ylim(0,L)
ax.grid(linestyle='-', linewidth='0.5')

plt.show()
#plt.savefig('vecinos_de_0.png')
