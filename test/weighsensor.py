# Libraries
import RPi.GPIO as GPIO
from time import sleep
from hx711 import HX711

# Set warnings off (optional)
GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)

# Set Sensor pins
hx = HX711(dout_pin=5, pd_sck_pin=6)

# Reset HX711
print("dit is de hx", hx)
err = hx.reset()
print("dit is de reset", err)
if not err == False:
    print("something went wrong")
    print("if statement werkt, tot hiertoe werkt alles")
else:
    print("klaar om te gebruiken")
    print("else statement werkt, tot hiertoe werkt alles")

reading = hx.get_raw_data_mean()
print(reading)
if reading:
    print('Data subtracted by offset but still not converted to units:', reading)
else:
    print('invalid data', reading)

#input('Put known weight on the scale and then press Enter')
reading = hx.get_data_mean()
if reading:
    print('Mean value from HX711 subtracted by offset:', reading)
    known_weight_grams = 100 #input('Write how many grams it was and press Enter: ')
    try:
        value = float(known_weight_grams)
        print(value, 'grams')
    except ValueError:
        print('Expected integer or float and I have got:', known_weight_grams)

print("Now, I will read data in infinite loop. To exit press 'CTRL + C'")
input('Press Enter to begin reading')
print('Current weight on the scale in grams is: ')
while True:
    print(hx.get_weight_mean(20), 'g')
