from flask import abort, jsonify
from . import mock_molecules_scan


class Scan:

    def execute(self, ready):
        if not ready:
            abort(405, description="Transporter isn't ready to scan.")

        weight = 1233 # Code to get sensor value

        # DUMMY DATA = SIMULATES A REAL LIFE MOLECULE SCANNER RESPONSE
        data = {
            "summary": [
                {
                    "molecule": "H20",
                    "count": round(weight % 10, 0)
                },
                {
                    "molecule": "C02",
                    "count": round(weight % 5 * 3, 0)
                }
            ],
            "molecules": mock_molecules_scan
        }

        return data



