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
Button_sensor = 23
Button_start = 16

# Set pins to LED
Led_door_closed = 24
Led_door_open = 12
Led_package_send = 17

# Setup button and start state LED
GPIO.setup(Button_sensor, GPIO.IN)
GPIO.setup(Button_start, GPIO.IN)
GPIO.setup(Led_door_closed, GPIO.OUT)
GPIO.setup(Led_door_open, GPIO.OUT)
GPIO.setup(Led_package_send, GPIO.OUT)


def door_closed():
    button_state = GPIO.input(Button_sensor)
    if button_state == 1:
        GPIO.output(Led_door_closed, GPIO.HIGH)
        GPIO.output(Led_door_open, GPIO.LOW)
        return True
    else:
        return False


print(door_closed())


def door_open():
    button_state = GPIO.input(Button_sensor)
    if button_state == 0:
        GPIO.output(Led_door_closed, GPIO.LOW)
        GPIO.output(Led_door_open, GPIO.HIGH)
        return True
    else:
        return False


def start_1():
    button_state = GPIO.input(Button_start)
    if button_state == 1:
        GPIO.output(Led_package_send, GPIO.HIGH)
        return True
    else:
        return False


def write_ready():
    lcd = i2c.CharLCD(i2c_expander, address, port=port, charmap=charmap, cols=cols, rows=rows)
    lcd.write_string("Package is ready")
    return True


def write_not_ready():
    lcd = i2c.CharLCD(i2c_expander, address, port=port, charmap=charmap, cols=cols, rows=rows)
    lcd.write_string("Package isn't ready")
    return True


def write_package_send_with_led():
    lcd = i2c.CharLCD(i2c_expander, address, port=port, charmap=charmap, cols=cols, rows=rows)
    lcd.write_string("Package is send")
    return True


def cleanAndExit():
    print("Cleaning...")
    print("Bye!")
    sys.exit()


while True:
    try:
        if door_closed() == True & start_1() == False:
            write_ready()
            print("eerste if statement")
        elif door_open():
            write_not_ready()
            print("eerste elf statement")
        else:
            print("fout in programma")

        if door_closed() == True & start_1() == True:
            write_package_send_with_led()


    except (KeyboardInterrupt, SystemExit):
        cleanAndExit()
