import { API, TOKEN } from '@/main.js';
import store from '@/store/index.js';

function get(uri, successHandler = logJson, failureHandler = errorNotification) {
    const options = constructOptions('get');
    const request = new Request(API + uri, options);

    return call(request, successHandler, failureHandler);
}

function post(uri, body, successHandler = logJson, failureHandler = errorNotification) {
    const options = constructOptions('POST', body);
    const request = new Request(API + uri, options);

    return call(request, successHandler, failureHandler);
}

function put(uri, body, successHandler = logJson, failureHandler = errorNotification) {
    const options = constructOptions('PUT', body);
    const request = new Request(API + uri, options);

    return call(request, successHandler, failureHandler);
}

function remove(uri, successHandler = logJson, failureHandler = errorNotification) {
    const options = constructOptions('DELETE');
    const request = new Request(API + uri, options);

    return call(request, successHandler, failureHandler);
}

function constructOptions(httpVerb, requestBody){
    const options= {};
    options.method = httpVerb;

    options.headers = {};
    options.headers["Content-Type"] = "application/json";

    if(TOKEN !== null) {
        options.headers["Authorization"] = "Bearer " + TOKEN;
    }

    options.body = JSON.stringify(requestBody);
    return options;
}


function logJson(json) {
    console.log(json);
}

function errorNotification(response) {
    response.json().then(error => {
        store.dispatch('createNotification', {content: error.cause, type: `error`});
    });
}

function call(request, successHandler, errorHandler) {
    return fetch(request)
        .then(response => {
            const type =response.headers.get('content-type');
            if (!response.ok) {
                throw response;
            }
            if(type?.includes('application/json')) {
                return response.json();
            }
        })
        .then(successHandler)
        .catch(errorHandler);
}

export { get, post, put, remove };
