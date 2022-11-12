#Libraries
import RPi.GPIO as GPIO
from time import sleep
from hx711 import HX711

#Set warnings off (optional)
GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)

#Set Sensor pins
hx = HX711(dout_pin=5, pd_sck_pin=6)

#Reset HX711
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
print (reading)
if reading:
	print('Data subtracted by offset but still not converted to units:', reading)
else:
	print('invalid data', reading)

