#! /bin/sh

### BEGIN INIT INFO
# Provides:          run.py
# Required-Start:    $remote_fs $syslog
# Required-Stop:     $remote_fs $syslog
# Default-Start:     2 3 4 5
# Default-Stop:      0 1 6
### END INIT INFO

# If you want a command to always run, put it here

# Carry out specific functions when asked to by the system

case "${1}" in
	start)
		cd /home/pi/shippert/transporter/
		python3 run.py & # run program continuously  ('&')
		echo "[i] Transporter Server is Running"
		;;

	stop)
		cd /home/pi/shippert/transporter/
		pkill -f run.py
		echo "[i] Transporter Server has Stopped"
		;;

	*)
		echo "[!] error - Unkown Parameter"
		echo "Usage: /etc/init.d/transporter_server.sh {start|stop}"
		exit 1 # returns the script as false (=error) , not necessary
		;;
esac

exit 0
