#!/usr/bin/env python3
# TODO: cleanup code
# TODO: delete print and input statements
# TODO: find out how calculation is done
# TODO: (maybe make error rate of 1 or 2 grams)
# TODO: offset cant be negative
# TODO: getstatusDoor, getWeight

# Libraries
import RPi.GPIO as GPIO
from hx711 import HX711
from RPLCD import i2c
import sys
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

# Generally 27 is the address. Find yours using: i2cdetect -y 1
address = 0x3f
# 0 on an older Raspberry Pi (older than RPI 4)
port = 1

# Initialise the LCD
lcd = i2c.CharLCD(i2c_expander, address, port=port, charmap=charmap, cols=cols, rows=rows)

# Switch off backlight
lcd.backlight_enabled = False

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


def door_closed():
    button_state = GPIO.input(button_sensor)
    return button_state == 1


def button_start_pressed():
    button_state = GPIO.input(button_start)
    return button_state == 1


def button_start_released():
    button_state = GPIO.input(button_start)
    return button_state == 0


def write_LCD(text):
    lcd.write_string(text)


def set_led_state(led1_state, led2_state, led3_state):
    GPIO.output(led_door_closed, led1_state)
    GPIO.output(led_door_open, led2_state)
    GPIO.output(led_package_send, led3_state)


def clean_and_exit():
    while True:
        GPIO.output(led_door_closed, GPIO.LOW)
        GPIO.output(led_door_open, GPIO.LOW)
        GPIO.output(led_package_send, GPIO.LOW)
        lcd.close(clear=True)
        break
    sys.exit()


# weight sensor
def check_error_weightsensor():
    try:
        err = hx.zero()
        if err:
            ValueError("Tare is unsuccessful.")
        else:
            return True
    except (KeyboardInterrupt, SystemExit):
        clean_and_exit()


def check_start_value_in_bits():
    try:
        reading = hx.get_raw_data_mean(30)  # return mean value of reading without weight on scale
        if reading:
            return reading
        else:
            raise ValueError('Cannot calculate mean value. Try debug mode. Variable reading:', reading)
    except (KeyboardInterrupt, SystemExit):
        clean_and_exit()


def calculate_weight():
    global value
    try:
        err = hx.zero()
        # check if successful
        if err:
            raise ValueError('Tare is unsuccessful.')
        reading = hx.get_raw_data_mean()
        if reading:  # always check if you get correct value or only False
            # now the value is close to 0
            print('Data subtracted by offset but still not converted to units:',
                  reading)
        else:
            print('invalid data', reading)

        input('Put known weight on the scale and then press Enter')
        reading = hx.get_data_mean()
        if reading:
            print('Mean value from HX711 subtracted by offset:', reading)
            known_weight_grams = input(
                'Write how many grams it was and press Enter: ')
            try:
                value = float(known_weight_grams)
                print(value, 'grams')
            except ValueError:
                print('Expected integer or float and I have got:',
                      known_weight_grams)

            ratio = reading / value  # calculate the ratio for channel A and gain 128
            hx.set_scale_ratio(ratio)  # set ratio for current channel
            print('Ratio is set.')
        else:
            raise ValueError('Cannot calculate mean value. Try debug mode. Variable reading:', reading)

    except (KeyboardInterrupt, SystemExit):
        print('Bye :)')


def get_weight():
    return hx.get_weight_mean(30)


def init_weight():
    calculate_weight()


def display_weight():
    weight = int(get_weight())
    lcd.clear()
    if weight < 0:
        write_LCD("0 grams")
    else:
        write_LCD(str(weight) + " grams")


init_weight()

while True:
    display_weight()

"""
    while True:
        try:
            #  button_state_start == 0 and button_state_sensor == 0:
            if button_start_released() and door_open():
                set_led_state(GPIO.LOW, GPIO.HIGH, GPIO.LOW)
                write_LCD("Door is open")
    
            # button_state_start == 0 and button_state_sensor == 1:
            elif button_start_released() == True and door_closed() == True:
                set_led_state(GPIO.HIGH, GPIO.LOW, GPIO.LOW)
                write_LCD("package is ready")
                # sleep(5)  # this is for testing purposes TODO: remove sleep if necessary
                display_weight()
    
            # button_state_start == 1 and button_state_sensor == 0:
            elif button_start_pressed() == True and door_open() == True:
                set_led_state(GPIO.LOW, GPIO.HIGH, GPIO.LOW)
                write_LCD("Door is open")
    
            # button_state_sensor == 1 and button_state_start == 1:
            elif button_start_pressed() == True and door_closed() == True:
                set_led_state(GPIO.HIGH, GPIO.LOW, GPIO.HIGH)
                write_LCD("Package is send")
    
            else:
                raise ValueError('Something went wrong')
        except (KeyboardInterrupt, SystemExit):
            clean_and_exit()
    """
