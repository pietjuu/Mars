#!/usr/bin/env python3

# TODO: cleanup code
# TODO: delete print and input statements
# TODO: find out how calculation is done

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
    lcd = i2c.CharLCD(i2c_expander, address, port=port, charmap=charmap, cols=cols, rows=rows)
    lcd.write_string(text)


def clear_lcd():
    lcd = i2c.CharLCD(i2c_expander, address, port=port, charmap=charmap, cols=cols, rows=rows)
    lcd.clear()


def set_led_state(led1_state, led2_state, led3_state):
    GPIO.output(led_door_closed, led1_state)
    GPIO.output(led_door_open, led2_state)
    GPIO.output(led_package_send, led3_state)


def clean_and_exit():
    while True:
        lcd = i2c.CharLCD(i2c_expander, address, port=port, charmap=charmap, cols=cols, rows=rows)
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
        reading = hx.get_raw_data_mean(30)
        if reading:
            return reading
        else:
            raise ValueError('Cannot calculate mean value. Try debug mode. Variable reading:', reading)
    except (KeyboardInterrupt, SystemExit):
        clean_and_exit()


def calculate_weight():
    global value
    offset_zero_kg = check_start_value_in_bits()  # TODO: check if this is necessary
    offset_one_kg = 0  # check in weegschaal hoeveel bits het is
    one_kg = 1000
    try:
        reading = offset_one_kg
        if reading:
            knownWeightGrams = one_kg
            try:
                value = float(knownWeightGrams)
            except ValueError:
                print("Expected integer or float and I have got:", knownWeightGrams)

            ratio = reading / value  # offsetOneKg // 1000
            hx.set_scale_ratio(ratio)
        else:
            raise ValueError('Cannot calculate mean value. Try debug mode. Variable reading:', reading)
    except (KeyboardInterrupt, SystemExit):
        clean_and_exit()


def get_weight():
    weight = hx.get_weight_mean(30, "grams")
    rounded_weight = round(weight)
    string_weight = str(rounded_weight)
    print(rounded_weight, "grams")
    return string_weight


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
            sleep(5)  # this is for testing purposes TODO: remove sleep if necessary
            clear_lcd()
            write_LCD(get_weight() + " grams")

        # button_state_start == 1 and button_state_sensor == 0:
        elif button_start_pressed() == True and door_open() == True:
            set_led_state(GPIO.LOW, GPIO.HIGH, GPIO.LOW)
            write_LCD("Door is open")

        # button_state_sensor == 1 and button_state_start == 1:
        elif button_start_pressed() == True and door_closed() == True:
            set_led_state(GPIO.HIGH, GPIO.LOW, GPIO.HIGH)
            write_LCD("Package is send")

        # else statement
        else:
            raise ValueError('Something went wrong')
    except (KeyboardInterrupt, SystemExit):
        clean_and_exit()
