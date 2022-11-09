# Libaries
import RPi.GPIO as gpio
import time

# Set warnings off
GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)

# Set pins to amplifier weight sensor
RS = 18  # RS = on LCD screen connected to RPI
EN = 23  # EN = on LCD screen connected to RPI
D4 = 24  # D4 = on LCD screen connected to RPI
D5 = 25  # D5 = on LCD screen connected to RPI
D6 = 8  # D6 = on LCD screen connected to RPI
D7 = 7  # D7 = on LCD screen connected to RPI
DT = 27  # DT = on amplifier connected to RPI
SCK = 17  # SCK = on amplifier connected to RPI

# Setup state from components
gpio.setmode(gpio.BCM)
gpio.setup(RS, gpio.OUT)
gpio.setup(EN, gpio.OUT)
gpio.setup(D4, gpio.OUT)
gpio.setup(D5, gpio.OUT)
gpio.setup(D6, gpio.OUT)
gpio.setup(D7, gpio.OUT)
gpio.setup(SCK, gpio.OUT)


# Fucntion to initilize LCD screen
def begin():
    lcdcmd(0x33)
    lcdcmd(0x32)
    lcdcmd(0x06)
    lcdcmd(0x0C)
    lcdcmd(0x28)
    lcdcmd(0x01)
    time.sleep(0.0005)

# Function for sending commands to LCD
def lcdcmd(ch):
    gpio.output(RS, 0)
    gpio.output(D4, 0)
    gpio.output(D5, 0)
    gpio.output(D6, 0)
    gpio.output(D7, 0)

    if ch & 0x10 == 0x10:
        gpio.output(D4, 1)

    if ch & 0x20 == 0x20:
        gpio.output(D5, 1)

    if ch & 0x40 == 0x40:
        gpio.output(D6, 1)

    if ch & 0x80 == 0x80:
        gpio.output(D7, 1)

    gpio.output(EN, 1)
    time.sleep(0.005)
    gpio.output(EN, 0)
    # Low bits
    gpio.output(D4, 0)
    gpio.output(D5, 0)
    gpio.output(D6, 0)
    gpio.output(D7, 0)

    if ch & 0x01 == 0x01:
        gpio.output(D4, 1)

    if ch & 0x02 == 0x02:
        gpio.output(D5, 1)

    if ch & 0x04 == 0x04:
        gpio.output(D6, 1)

    if ch & 0x08 == 0x08:
        gpio.output(D7, 1)

    gpio.output(EN, 1)
    time.sleep(0.005)
    gpio.output(EN, 0)


# Function for reading data from HX711 (amplifier) and return output
def readCount():
    i = 0
    Count = 0
    gpio.setup(DT, gpio.OUT)
    gpio.output(DT, 1)
    gpio.output(SCK, 0)
    gpio.setup(DT, gpio.IN)

    while gpio.input(DT) == 1:
        i = 0
    for i in range(24):
        gpio.output(SCK, 1)
        Count = Count << 1
        gpio.output(SCK, 0)
        if gpio.input(DT) == 0:
            Count = Count + 1

    gpio.output(SCK, 1)
    Count = Count ^ 0x800000  # uitzoeken waarom
    gpio.output(SCK, 0)
    return Count
