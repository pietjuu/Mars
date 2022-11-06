let api;

document.addEventListener("DOMContentLoaded", loadConfig);

// Entry point for the JS code.
function init() {
    // Very small proof of concept.
    poc();
}

function loadConfig() {
    fetch("config.json")
        .then(resp => resp.json())
        .then(config => {
            api = `${config.host ? config.host + '/': ''}${config.group ? config.group + '/' : ''}api/`;
            init();
        });
}
