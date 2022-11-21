from random import randint

from flask import abort, jsonify
from .scan import Scan
from . import mock_locations

# status message codes:
# UNKNOWN
# DOOR OPEN
# READY TO SCAN AND SEND


class Transporter:

    def __init__(self):
        self.coordinates = { "longitude": -1, "latitude": -1 }
        self._ready = False
        self._msg = "UNKNOWN"
        self.check_status()

    def scan(self):
        self.check_status()

        scan = Scan()
        response = scan.execute(self._ready)
        return response

    def send(self):
        self.check_status()
        response = self.scan()

        # CODE TO SEND ITEM - Out Of Scope!

        return response

    def build(self):
        # Code to reconstruct molecules into item is out of scope
        pass

    def check_status(self):
        closed = randint(0, 10) % 2 == 0  # CODE TO READ SENSOR

        self._ready = closed
        self._msg = "READY TO SCAN AND SEND" if closed else "DOOR OPEN"

    def check_location(self):
        coordinates = mock_locations[randint(0, len(mock_locations))]  # CODE TO READ LOCATION
        self.coordinates = coordinates

    def get_status(self):
        self.check_status()
        return jsonify(
            ready=self._ready,
            message=self._msg
        )

    def get_location(self):
        self.check_location()
        return jsonify(self.coordinates)
