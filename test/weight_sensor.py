#Libaries
import RPi.GPIO as gpio
import time

#Set pins to amplifier weight sensor
RS = 18 #RS = on LCD screen connected to RPI
EN = 23 #EN = on LCD screen connected to RPI
D4 = 24 #D4 = on LCD screen connected to RPI
D5 = 25 #D5 = on LCD screen connected to RPI
D6 = 8  #D6 = on LCD screen connected to RPI
D7 = 7  #D7 = on LCD screen connected to RPI
DT = 27 #DT = on amplifier connected to RPI
SCK = 17 #SCK = on amplifier connected to RPI

#Function for reading data from HX711 (amplifier) and return output
def readCount():
  i=0
  Count=0
  gpio.setup(DT, gpio.OUT)
  gpio.output(DT,1)
  gpio.output(SCK,0)
  gpio.setup(DT, gpio.IN)

  while gpio.input(DT) == 1:
	 i=0
  for i in range(24):
	gpio.output(SCK,1)
	Count=Count<<1
	gpio.output(SCK,0)
	if gpio.input(DT) == 0: 
		Count=Count+1

  gpio.output(SCK,1)
  Count=Count^0x800000
  gpio.output(SCK,0)
  return Count

