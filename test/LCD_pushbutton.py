# Libraries
import RPi.GPIO as GPIO
from RPLCD import i2c
from time import sleep

# set warnings off
GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)

# constants to initialise the LCD
lcdmode = 'i2c'
cols = 15
rows = 2
charmap = 'A00'
i2c_expander = 'PCF8574'

# Generally 27 is the address;Find yours using: i2cdetect -y 1
address = 0x3f
# 0 on an older Raspberry Pi
port = 1

# Set pins to button
Button = 23

# Set pins to LED
Led_door_closed = 24
Led_door_open = 12

# Setup button and start state LED
GPIO.setup(Button, GPIO.IN)
GPIO.setup(Led_door_closed, GPIO.OUT)
GPIO.setup(Led_door_open, GPIO.OUT)


def door_closed():
    button_state = GPIO.input(Button)
    if button_state == 1:
        GPIO.output(Led_door_closed, GPIO.HIGH)
        GPIO.output(Led_door_open, GPIO.LOW)
        print("functie door closed werkt")
        sleep(2)


print(door_closed())


def door_open():
    button_state = GPIO.input(Button)
    if button_state == 0:
        GPIO.output(Led_door_closed, GPIO.LOW)
        GPIO.output(Led_door_open, GPIO.HIGH)
        print("functie door open werkt")
        sleep(2)


def write_ready():
    lcd = i2c.CharLCD(i2c_expander, address, port=port, charmap=charmap, cols=cols, rows=rows)
    lcd.write_string("Package is ready")


def write_not_ready():
    lcd = i2c.CharLCD(i2c_expander, address, port=port, charmap=charmap, cols=cols, rows=rows)
    lcd.write_string("Package isn't ready")


while True:
    if door_closed():
        print("test if")
        write_ready()
    elif door_open():
        print("test elif")
        write_not_ready()
    else:
        print("fout in programma")
