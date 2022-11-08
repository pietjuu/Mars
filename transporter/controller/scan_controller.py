from . import app
from . import trans


# Scan item and return data
@app.route('/api/scan', methods=['GET'])
def scan():
    result = trans.scan()
    return result
