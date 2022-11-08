# Transporter API
Every Transporter device runs a small API.  
With this API the Shippert API can control the device or request data from it.  

## Deploy
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