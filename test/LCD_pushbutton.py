# Libraries
import sys
import RPi.GPIO as GPIO
from RPLCD import i2c


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

# Setup button and start state LED
GPIO.setup(button_sensor, GPIO.IN)
GPIO.setup(button_start, GPIO.IN)
GPIO.setup(led_door_closed, GPIO.OUT)
GPIO.setup(led_door_open, GPIO.OUT)
GPIO.setup(led_package_send, GPIO.OUT)


def door_closed():
    button_state = GPIO.input(button_sensor)
    return button_state == 1


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


def cleanAndExit():
    while True:
        lcd = i2c.CharLCD(i2c_expander, address, port=port, charmap=charmap, cols=cols, rows=rows)
        GPIO.output(led_door_closed, GPIO.LOW)
        GPIO.output(led_door_open, GPIO.LOW)
        GPIO.output(led_package_send, GPIO.LOW)
        lcd.close(clear=True)
        break
    sys.exit()


def set_led_state(led1_state, led2_state, led3_state):
    GPIO.output(led_door_closed, led1_state)
    GPIO.output(led_door_open, led2_state)
    GPIO.output(led_package_send, led3_state)


while True:
    try:
        #  button_state_start == 0 and button_state_sensor == 0:
        if start_0() == True and door_open() == True:
            set_led_state(GPIO.LOW, GPIO.HIGH, GPIO.LOW)
            write_LCD("Door is open")
        # button_state_start == 0 and button_state_sensor == 1:
        elif start_0() == True and door_closed() == True:
            set_led_state(GPIO.HIGH, GPIO.LOW, GPIO.LOW)
            write_LCD("package is ready")
        # button_state_start == 1 and button_state_sensor == 0:
        elif start_1() == True and door_open() == True:
            set_led_state(GPIO.LOW, GPIO.HIGH, GPIO.LOW)
            write_LCD("Door is open")
        # button_state_sensor == 1 and button_state_start == 1:
        elif start_1() == True and door_closed() == True:
            set_led_state(GPIO.HIGH, GPIO.LOW, GPIO.HIGH)
            write_LCD("Package is send")
        # else statement
        else:
            print("fout in programma")
    except (KeyboardInterrupt, SystemExit):
        cleanAndExit()
