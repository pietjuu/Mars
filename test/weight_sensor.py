# Libaries
import RPi.GPIO as GPIO
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
GPIO.setmode(GPIO.BCM)
GPIO.setup(RS, GPIO.OUT)
GPIO.setup(EN, GPIO.OUT)
GPIO.setup(D4, GPIO.OUT)
GPIO.setup(D5, GPIO.OUT)
GPIO.setup(D6, GPIO.OUT)
GPIO.setup(D7, GPIO.OUT)
GPIO.setup(SCK, GPIO.OUT)


# Fucntion to initilize LCD screen
def begin():
    lcdcmd(0x33)
    lcdcmd(0x32)
    lcdcmd(0x06)
    lcdcmd(0x0C)
    lcdcmd(0x28)
    lcdcmd(0x01)
    time.sleep(0.0005)


print("geen errors check 1")


# Function for sending commands to LCD
def lcdcmd(ch):
    GPIO.output(RS, 0)
    GPIO.output(D4, 0)
    GPIO.output(D5, 0)
    GPIO.output(D6, 0)
    GPIO.output(D7, 0)

    if ch & 0x10 == 0x10:
        GPIO.output(D4, 1)

    if ch & 0x20 == 0x20:
        GPIO.output(D5, 1)

    if ch & 0x40 == 0x40:
        GPIO.output(D6, 1)

    if ch & 0x80 == 0x80:
        GPIO.output(D7, 1)

    GPIO.output(EN, 1)
    time.sleep(0.005)
    GPIO.output(EN, 0)
    # Low bits
    GPIO.output(D4, 0)
    GPIO.output(D5, 0)
    GPIO.output(D6, 0)
    GPIO.output(D7, 0)

    if ch & 0x01 == 0x01:
        GPIO.output(D4, 1)

    if ch & 0x02 == 0x02:
        GPIO.output(D5, 1)

    if ch & 0x04 == 0x04:
        GPIO.output(D6, 1)

    if ch & 0x08 == 0x08:
        GPIO.output(D7, 1)

    GPIO.output(EN, 1)
    time.sleep(0.005)
    GPIO.output(EN, 0)


print("geen errors check 2")


# Write things to LCD screen
def lcdwrite(ch):
    GPIO.output(RS, 1)
    GPIO.output(D4, 0)
    GPIO.output(D5, 0)
    GPIO.output(D6, 0)
    GPIO.output(D7, 0)

    if ch & 0x10 == 0x10:
        GPIO.output(D4, 1)

    if ch & 0x20 == 0x20:
        GPIO.output(D5, 1)

    if ch & 0x40 == 0x40:
        GPIO.output(D6, 1)

    if ch & 0x80 == 0x80:
        GPIO.output(D7, 1)

    GPIO.output(EN, 1)
    time.sleep(0.005)
    GPIO.output(EN, 0)

    # Low bits
    GPIO.output(D4, 0)
    GPIO.output(D5, 0)
    GPIO.output(D6, 0)
    GPIO.output(D7, 0)

    if ch & 0x01 == 0x01:
        GPIO.output(D4, 1)

    if ch & 0x02 == 0x02:
        GPIO.output(D5, 1)

    if ch & 0x04 == 0x04:
        GPIO.output(D6, 1)

    if ch & 0x08 == 0x08:
        GPIO.output(D7, 1)

    GPIO.output(EN, 1)
    time.sleep(0.005)
    GPIO.output(EN, 0)


print("geen errors check 3")


# Clear LCDscreen
def lcdclear():
    lcdcmd(0x01)


# LCD screen cursor
def setCursor(x, y):
    if y == 0:
        n = 128 + x
    elif y == 1:
        n = 192 + x
    lcdcmd(n)


print("geen errors check 4")


# Print LCD screen
def lcdprint(Str):
    l = 0;
    l = len(Str)
    for i in range(l):
        lcdwrite(ord(Str[i]))


print("geen errors check 5")


# Function for reading data from HX711 (amplifier) and return output
def readCount():
    i = 0
    Count = 0
    GPIO.setup(DT, GPIO.OUT)
    GPIO.output(DT, 1)
    GPIO.output(SCK, 0)
    GPIO.setup(DT, GPIO.IN)

    while GPIO.input(DT) == 1:
        i = 0
    for i in range(24):
        GPIO.output(SCK, 1)
        Count = Count << 1
        GPIO.output(SCK, 0)
        if GPIO.input(DT) == 0:
            Count = Count + 1

    GPIO.output(SCK, 1)
    Count = Count ^ 0x800000  # uitzoeken waarom
    GPIO.output(SCK, 0)
    print("Function read count werkt (tot voor de return)")
    return Count


print("geen errors check 6")
