from . import app
from . import trans


# Status of domain
@app.route('/api/status', methods=['GET'])
def status():
    result = trans.get_status()
    return result
