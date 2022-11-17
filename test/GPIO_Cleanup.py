#Libaries
import RPi.GPIO as GPIO
from RPLCD import i2c

#set warnings off
GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)

#Set pins to button
Button = 23

#Set pins to LED
Led_door_closed = 24
Led_door_open = 12
Led_package_send = 17

#Setup button and start state LED
GPIO.setup(Button, GPIO.IN)
GPIO.setup(Led_door_closed,GPIO.OUT)
GPIO.setup(Led_door_open,GPIO.OUT)

# constants to initialise the LCD
lcdmode = 'i2c'
cols = 15
rows = 2
charmap = 'A00'
i2c_expander = 'PCF8574'

# Generally 27 is the address;Find yours using: i2cdetect -y 1 
address = 0x3f
port = 1 # 0 on an older Raspberry Pi

# Initialise the LCD
lcd = i2c.CharLCD(i2c_expander, address, port=port, charmap=charmap, cols=cols, rows=rows)

#Set lights off when button is pushed
while True:
	button_state = GPIO.input(Button)
	if button_state == 1:
		GPIO.output(Led_door_closed, GPIO.LOW)
		GPIO.output(Led_door_open, GPIO.LOW)
		GPIO.output(Led_package_send, GPIO.LOW)
	if button_state == 1:
		lcd.close(clear=True)
		break
