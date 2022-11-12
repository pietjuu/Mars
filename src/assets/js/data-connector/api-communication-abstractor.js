import { API } from '@/main.js'

function get(uri, successHandler = logJson, failureHandler = logError) {
    const request = new Request(API + uri, {
        method: 'GET',
    });

    return call(request, successHandler, failureHandler);
}

function post(uri, body, successHandler = logJson, failureHandler = logError) {
    const request = new Request(API + uri, {
        method: 'POST',
        headers: {
            'Content-type': 'application/json;'
        },
        body: JSON.stringify(body)
    });

    return call(request, successHandler, failureHandler);
}

function put(uri, body, successHandler = logJson, failureHandler = logError) {
    const request = new Request(API + uri, {
        method: 'PUT',
        headers: {
            'Content-type': 'application/json;'
        },
        body: JSON.stringify(body)
    });

    return call(request, successHandler, failureHandler);
}

function remove(uri, successHandler = logJson, failureHandler = logError) {
    const request = new Request(API + uri, {
        method: 'DELETE',
    });

    return call(request, successHandler, failureHandler);
}

function logJson(response) {
    response.json().then(console.log);
}

function logError(error) {
    console.log(error);
}

function call(request, successHandler, errorHandler) {
    return fetch(request)
        .then(response => {
            if (!response.ok) {
                throw response;
            }
            return response.json();
        })
        .then(successHandler)
        .catch(errorHandler);
}

export { get, post, put, remove };
