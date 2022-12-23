"use strict";

export function beautifyId(id){
    return capitalizeFirstLetter(id.replace(/_/g, ' '));
}

export function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

export function convertSpacesToUnderscores(string){
    return string.replace(/ /g, "_");
}

export function convertUnderscoresToSpaces(string){
    return string.replace(/_/g, " ");
}

export function addEventListenerToElements(type, handler, selector){
    const $elements = document.querySelectorAll(selector);
    $elements.forEach(($element) => $element.addEventListener(type, handler));
}

export function addClassToElements(selector, clss){
    document.querySelectorAll(selector).forEach($element => {
        $element.classList.add(clss);
    });
}

export function removeClassFromElements(selector, clss){
    document.querySelectorAll(selector).forEach($element => {
        $element.classList.remove(clss);
    });
}

export function containsQuery(array, query) {
    return array.indexOf(query) !== -1;
}

export function itemsToUserReadable(items, transporters, users) {
    return items.map((item) => {
        const result = {...item};
        users.forEach(usr => {
          const name = `${usr.firstname} ${usr.lastname}`;
          if(item.receiver === usr.id) {
            result.receiver = name;
          }
          else if(item.sender === usr.id) {
            result.sender = name;
          }
        });
        transporters.forEach(trans => {
          const name = trans.name;
          if(item.origin === trans.id) {
            result.origin = name;
          }
          else if(item.destination === trans.id) {
            result.destination = name;
          }
        });
        return result;
    });
}
