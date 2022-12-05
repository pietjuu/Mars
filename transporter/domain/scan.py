from random import randint

from flask import abort, jsonify
from . import mock_molecules_scan


class Scan:

    def execute(self, ready):
        if not ready:
            abort(405, description="Transporter isn't ready to scan.")

        weight = randint(1, 9000)  # Code to get sensor value

        # DUMMY DATA = SIMULATES A REAL LIFE MOLECULE SCANNER RESPONSE
        data = {
            "summary": [
                {
                    "molecule": "H20",
                    "count": round(weight * (5/7))
                },
                {
                    "molecule": "C02",
                    "count": round(weight * (2/7))
                }
            ],
            "molecules": mock_molecules_scan,
            "size": {
                "length": randint(10, 40),
                "width": randint(5, 30),
                "depth": randint(10, 20)
            }
        }

        return data
