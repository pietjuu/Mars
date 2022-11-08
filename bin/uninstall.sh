#! /bin/sh

set -e

echo "[!] Stopping Server of Transporter Server..."
sudo update-rc.d transporter_server.sh remove
sudo /etc/init.d/transporter_server.sh stop

echo "[!] Remove Auto Start of Transporter Server"
sudo rm -rf /etc/init.d/transporter_server.sh

echo "[!] Removed Auto Start of Transporter Server"
