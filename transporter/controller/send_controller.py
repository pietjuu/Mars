from . import app
from . import trans


# Scend item
@app.route('/api/send', methods=['POST'])
def send():
    result = trans.send()
    return result
