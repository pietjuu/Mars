from flask import abort, jsonify
from .scan import Scan


# status message codes:
# UNKNOWN
# DOOR OPEN
# READY TO SCAN AND SEND

class Transporter:

    def __init__(self):
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
        closed = True  # CODE TO READ SENSOR

        self._ready = closed
        self._msg = "READY TO SCAN AND SEND" if closed else "DOOR OPEN"

    def get_status(self):
        self.check_status()
        return jsonify(
            ready=self._ready,
            message=self._msg
        )
