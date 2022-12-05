# Transporter API 
Every Transporter device runs a small API.  
With this API the Shippert API can control the device or request data from it.  

## Deploy on RPI
> Note: python3 and pip must already be installed on the RPI  

Create the following directory on the RPI: 
```bash
mkdir /home/pi/shippert/transporter/
```

To install the all the necessary python packages run the following cmd inside the created directory:
```bash
sudo pip install -r requirements.txt
```
To run the server on startup execute the following command:
```bash
sudo ./bin/install.sh
```

To stop running the server on startup execute the following command:
```bash
sudo ./bin/uninstall.sh
```


## Transporter APIs on Docker Containers
To simulate real Transporter devices on Mars, we are running 10 Transporter APIs on docker containers on a server of Thibo Verbeerst.

There are now 3 type of Transporter APIs configured.
1. Always Ready Transporter (aka door closed)
2. Always Not Ready Transporter (aka door open)
3. Status of Transporter is random

> Note: location of transporter is always random, number of scanned molecules is always random and size of item is always random

### Transporter API addresses:
1. Always Ready Transporter (aka door closed)
    - https://transporter1.thibo.cloud
    - https://transporter2.thibo.cloud
    - https://transporter3.thibo.cloud
    - https://transporter4.thibo.cloud

2. Always Not Ready Transporter (aka door open)
    - https://transporter5.thibo.cloud
    - https://transporter6.thibo.cloud

3. Status of Transporter is random
    - https://transporter7.thibo.cloud
    - https://transporter8.thibo.cloud
    - https://transporter9.thibo.cloud
    - https://transporter10.thibo.cloud

> Quick test: https://transporter7.thibo.cloud/api/scan

### Thibo's Server Schedule
These addresses / the server is online on:
- Monday: 19u00 - 23u00
- Tuesday: 19u00 - 23u00
- Wednesday: 19u00 - 23u00
- Thursday: 19u00 - 23u00
- Friday: offline
- Weekend: 12u00 - 21u00

Want the server to be online at a specific time?  
contact: thibo.verbeerst@student.howest.be

Note: the Transporter servers will be destroyed on **16 February 2023**
