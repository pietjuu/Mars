from flask import Flask

app = Flask(__name__)

from .data.mock_positions_of_scan import mock_molecules_scan

from .exception import errorhandler
from .domain.transporter import Transporter

trans = Transporter()

from .controller import status_controller, scan_controller, send_controller