#Libraries
import RPi.GPIO as GPIO
from time import sleep

#Set warnings off (optional)
GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)

#Set Button and LED pins
Button = 23
LED = 24

#Setup Button and LED
GPIO.setup(Button,GPIO.IN,pull_up_down=GPIO.PUD_UP)
GPIO.setup(LED,GPIO.OUT)

'''
while True:
	button_state = GPIO.input(Button)
	sleep(1)
	print(button_state)
'''

while True:
    button_state = GPIO.input(Button)
    print(button_state)
    if button_state == 0:
        GPIO.output(LED,GPIO.HIGH)
        print("HIGH")
    else:
        GPIO.output(LED,GPIO.LOW)
        print("LOW")
    sleep(1)
