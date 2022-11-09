#! /bin/sh

# Tell the kernel the name of the interpreter that should be used to execute the script that follows

# This script executes commands like a terminal 

# Program to run the transporter server automatically on startup

CURRENT_TIME="$(date +"%x %r %Z")"
TIMESTAMP="[i] Installed on ${CURRENT_TIME}, by ${USER}\n"

set -e # stop script if there is an command error


echo '[!] Installing Transporter Server...\n'
cd "$(dirname "$0")"
sudo cp transporter_server.sh /etc/init.d/ # copy the script to run the server to /etc/init.d/ --> the scripts in this dir is always read by the pi automatically
sudo chmod +x /etc/init.d/transporter_server.sh # set permisson to execute the file

# With update-rc.d, you can schedule tasks to be started when you boot your computer, for example the ssh service.
# if defaults is used, update-rc.d will make links to start the service in runlevels 2345, and stop the service in runlevels 016.
sudo update-rc.d transporter_server.sh defaults # install init script links

# starting the service:
sudo /etc/init.d/transporter_server.sh start

echo "[!] Transporter Server Installed\n"


echo ${TIMESTAMP}
echo "[i] Author of The Script: Shippert Team\n"
