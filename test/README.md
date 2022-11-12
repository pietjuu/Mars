# What is in this file
1) Which libraries have been used
2) Hardware connection file test_pushbutton.py + explanation
3) Hardware connection file LCD_test.py + explanation
4) Hardware connection file test_weight_sensor.py + explanation
5) Hardware connection file weight_sensor_and_LCD.py + explanation

# 1) Which libraries have been used
Below you find a list with the libraries that we used. 
You can install those packages with the following commands:

```bash
sudo pip3 install [library_name]
```

	- RPi.GPIO (for using GPIO pins o RPI)
	- hx711 (for weight sensor)
	- RPLCD (for LCD screen)

# 2) Hardware conenction file test_pushbutton.py
Everything on the breadboard is connected with the RPI with GPIO pins (input output pins).
Below you find a schema and how everything is connected.
This script makes the Yellow LED glow if the button isn't pushed and makes the green LED glow if the button is pushed.
To execute this script execute the following command:

```python
python3 /home/pi/shippert/transporter/test/test_pushbutton.py
```

To stop the script press ctrl + c and to clear all the lights execute the following command followed by 1 push on the button

```python
python3 /home/pi/shippert/transporter/test/GPIO_Cleanup.py
```


    - The button is connected with pin 16 this is GPIO 23
    - The Green Led is connected with pin 18 this is GPIO 24
    - The Yellow led is connected with pin 32 this is GPIO 12 (PWM0 => this is not important)
    - Pin 6 is the ground pin and is connected with the breadboard (see schema)
    - Pin 2 is the 5 Volt pin and is connected with the breadboard (see schema)

![](schemas/schema-LED-Pushbutton.png)

# 3) Hardware conenction file LCD_test.py
Everything on the breadboard is connected with the RPI with GPIO pins (input output pins).
Below you find a schema and how everything is connected.
This script print for 5 seconds "hello world" on the LCD screen
To execute this script execute the following command:

```python
python3 /home/pi/shippert/transporter/test/LCD_test.py
```

The script stops automatically.

    - Pin 6 is the ground pin and is connected with the LCD screen GND pin (Black) (see schema)
    - Pin 4 is the 5 Volt pin and is connected with the LCD screen VCC pin (Brown) (see schema)
    - Pin 3 is GPIO (SDA) and is connected with the LCD screen SDA pin (Red) (see schema)
    - Pin 5 is GPIO (SDL) and is connected with the LCD screen SDL pin (Orange) (see schema)

![](schemas/schema-LCD-Screen.png)
