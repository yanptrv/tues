import numpy as np
import matplotlib.pyplot as plt
from PIL import Image

img = Image.open('lung.jpeg')

plt.imshow(img, cmap='gray')
img = np.asarray(img)

flat = img.flatten()

plt.hist(flat, bins=50)
plt.show()


def get_histogram(image, bins):
    histogram = np.zeros(bins)
    for pixel in image:
        histogram[pixel] += 1

    return histogram


hist = get_histogram(flat, 256)


def cumsum(a):
    a = iter(a)
    b = [next(a)]
    for i in a:
        b.append(b[-1] + i)
    return np.array(b)


cs = cumsum(hist)
plt.plot(cs)
nj = (cs - cs.min()) * 255
N = cs.max() - cs.min()
cs = nj / N
cs = cs.astype('uint8')

plt.plot(cs)
img_new = cs[flat]

img_new = np.reshape(img_new, img.shape)

fig = plt.figure()
fig.set_figheight(15)
fig.set_figwidth(15)

fig.add_subplot(1, 2, 1)
plt.imshow(img, cmap='gray')

fig.add_subplot(1, 2, 2)
plt.imshow(img_new, cmap='gray')
plt.show()
