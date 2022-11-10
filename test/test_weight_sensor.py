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
reset = hx.reset()
print(reset)
if not reset:
	print("something went wrong")
	print("if statement werkt, tot hiertoe werkt alles")
else:
	print("klaar om te gebruiken")
	print("else statement werkt, tot hiertoe werkt alles")
