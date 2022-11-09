#Libaries 
import RPi.GPIO as GPIO
from time import sleep

#set warnings off
GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)

#Set pins to button
Button = 23

#Setup button

GPIO.setup(Button, GPIO.IN)

#Logic program
while True:
	button_state = GPIO.input(Button)
	print(button_state)

