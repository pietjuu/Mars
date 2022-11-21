from . import app
from . import trans


@app.route('/api/location', methods=['GET'])
def location():
    result = trans.get_location()
    return result
