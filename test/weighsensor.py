#!/usr/bin/env python3

# TODO: cleanup code
# TODO: delete print and input statements
# TODO: round weight to 2 decimal places
# TODO: nested while True loop for only running when button is pressed
# TODO: find out how calculation is done

# Libraries
import RPi.GPIO as GPIO
from time import sleep
from hx711 import HX711
from RPLCD import i2c
import sys

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
button_sensor = 23
button_start = 16

# Set pins to LED
led_door_closed = 24
led_door_open = 12
led_package_send = 17

# Set pins for weight sensor
hx = HX711(dout_pin=5, pd_sck_pin=6)

# Setup button and start state LED
GPIO.setup(button_sensor, GPIO.IN)
GPIO.setup(button_start, GPIO.IN)
GPIO.setup(led_door_closed, GPIO.OUT)
GPIO.setup(led_door_open, GPIO.OUT)
GPIO.setup(led_package_send, GPIO.OUT)


def door_open():
    button_state = GPIO.input(button_sensor)
    return button_state == 0


def start_1():
    button_state = GPIO.input(button_start)
    return button_state == 1


def start_0():
    button_state = GPIO.input(button_start)
    return button_state == 0


def write_LCD(text):
    lcd = i2c.CharLCD(i2c_expander, address, port=port, charmap=charmap, cols=cols, rows=rows)
    lcd.write_string(text)


def set_led_state(led1_state, led2_state, led3_state):
    GPIO.output(led_door_closed, led1_state)
    GPIO.output(led_door_open, led2_state)
    GPIO.output(led_package_send, led3_state)


def cleanAndExit():
    while True:
        lcd = i2c.CharLCD(i2c_expander, address, port=port, charmap=charmap, cols=cols, rows=rows)
        GPIO.output(led_door_closed, GPIO.LOW)
        GPIO.output(led_door_open, GPIO.LOW)
        GPIO.output(led_package_send, GPIO.LOW)
        lcd.close(clear=True)
        break
    sys.exit()


# weight sensor
def checkErrorWeightsensor():
    try:
        err = hx.zero()
        if err:
            ValueError("Tare is unsuccessful.")
        else:
            return True
    except (KeyboardInterrupt, SystemExit):
        cleanAndExit()


def checkStartValueInBits():
    try:
        reading = hx.get_raw_data_mean()
        if reading:
            return reading
        else:
            raise ValueError('Cannot calculate mean value. Try debug mode. Variable reading:', reading)
    except (KeyboardInterrupt, SystemExit):
        cleanAndExit()


def calculateWeight():
    global value
    offsetZeroKg = checkStartValueInBits()
    offsetOneKg = 0  # check in weegschaal hoeveel bits het is
    oneKg = 1000
    try:
        # TODO: make functions of this if else bits2effectivefloat
        reading = offsetOneKg
        if reading:
            knownWeightGrams = oneKg
            try:
                value = float(knownWeightGrams)
            except ValueError:
                print("Expected integer or float and I have got:", knownWeightGrams)

            ratio = reading / value
            hx.set_scale_ratio(ratio)
        else:
            raise ValueError('Cannot calculate mean value. Try debug mode. Variable reading:', reading)
    except (KeyboardInterrupt, SystemExit):
        cleanAndExit()
