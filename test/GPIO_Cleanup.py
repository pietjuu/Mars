#Libaries
import RPi.GPIO as GPIO

#set warnings off
GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)

#Set pins to button
Button = 23

#Set pins to LED
Led_door_closed = 24
Led_door_open = 12

#Setup button and start state LED
GPIO.setup(Button, GPIO.IN)
GPIO.setup(Led_door_closed,GPIO.OUT)
GPIO.setup(Led_door_open,GPIO.OUT)

#Set lights off when button is pushed
while True:
	button_state = GPIO.input(Button)
	if button_state == 1:
		GPIO.output(Led_door_closed, GPIO.LOW)
		GPIO.output(Led_door_open, GPIO.LOW)
		break
