# Python code to detect an arrow (seven-sided shape) from an image. 
import numpy as np
import cv2

# Reading image 
img2 = cv2.imread('./imgs/cropimage.jpg', cv2.IMREAD_COLOR)

# Reading same image in another variable and  
# converting to gray scale. 
img = cv2.imread('./imgs/cropimage.jpg', cv2.IMREAD_GRAYSCALE)

# Converting image to a binary image  
# (black and white only image). 
_, threshold = cv2.threshold(img, 110, 255,
                             cv2.THRESH_BINARY)

# Detecting shapes in image by selecting region  
# with same colors or intensity. 
contours, _ = cv2.findContours(threshold, cv2.RETR_TREE,
                               cv2.CHAIN_APPROX_SIMPLE)

# Searching through every region selected to  
# find the required polygon. 
for cnt in contours:
    area = cv2.contourArea(cnt)

    # Shortlisting the regions based on there area. 
    if area > 400:
        approx = cv2.approxPolyDP(cnt,
                                  0.009 * cv2.arcLength(cnt, True), True)

        if (len(approx) >= 4):
            cv2.drawContours(img2, [approx], 0, (0, 0, 255), 5)

        # Showing the image along with outlined arrow.
# cv2.imshow('image2', img2)
perimeter = cv2.arcLength(approx, True)
cv2.imwrite('./imgs/image2.jpg', img2)
# b=max(approx);
b = approx.max()
a = perimeter / 2 - b
f = open('./result/result.txt', 'w')
f.write(str(b) + '\n' + str(a))
f.close()
# print(perimeter,b,a)
# Exiting the window if 'q' is pressed on the keyboard. 
if cv2.waitKey(0) & 0xFF == ord('q'):
    cv2.destroyAllWindows()
