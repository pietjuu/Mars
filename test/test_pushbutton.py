#Libaries
import RPi.GPIO as GPIO
from time import sleep

#set warnings off
GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)

#Set pins to button
Button = 23

#Set pins to LED
Led_door_closed = 24
Led_door_open = 25

#Setup button
GPIO.setup(Button, GPIO.IN)
GPIO.setup(Led_door_closed,GPIO.OUT)
GPIO.setup(Led_door_open,GPIO.OUT)

#Logic program
while True:
	button_state = GPIO.input(Button)
	print(button_state)
	if button_state == 0:
		GPIO.output(Led_door_closed, GPIO.LOW)
		GPIO.output(Led_door_open, GPIO.HIGH)
