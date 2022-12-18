import { API, TOKEN } from '@/main.js';
import store from '@/store/index.js';

function get(uri, successHandler = logJson, failureHandler = logError) {
    const options = constructOptions('get');
    const request = new Request(API + uri, options);

    return call(request, successHandler, failureHandler);
}

function post(uri, body, successHandler = logJson, failureHandler = logError) {
    const options = constructOptions('POST', body);
    const request = new Request(API + uri, options);

    return call(request, successHandler, failureHandler);
}

function put(uri, body, successHandler = logJson, failureHandler = logError) {
    const options = constructOptions('PUT', body);
    const request = new Request(API + uri, options);

    return call(request, successHandler, failureHandler);
}

function remove(uri, successHandler = logJson, failureHandler = logError) {
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
                store.dispatch('createNotification', {content: response.statusText, type: `error`});
                throw response;
            }
            return response.json();
        })
        .then(successHandler)
        .catch(errorHandler);
}

export { get, post, put, remove };
