'use strict';
var useLocalStorage = false;

var InitLS = function () {};
var localStorageProvider = new InitLS();

InitLS.prototype.addObj = function (key, value) {
    localStorage.setItem(key, JSON.stringify(value));
};

InitLS.prototype.getObj = function (key) {
    let item = localStorage.getItem(key);
    item = JSON.parse(item);
    return item;
};

InitLS.prototype.deleteObj = function (key) {
    localStorage.removeItem(key);
};


function InitIDB() {}
var indexedDBProvider = new InitIDB();

function openIndexedDB() {
    var openRequest = indexedDB.open('myDB', 4);
    openRequest.onupgradeneeded = function (event) {
        console.log("Upgrading...");
        var db = event.target.result;
        var objectStore = db.createObjectStore('data', {keyPath: 'id'});
    };
    // openRequest.createObjectStore('fans', {keyPath: 'fans'});
    return openRequest;


}


InitIDB.prototype.addObj = function (key, value) {

    var openDB = openIndexedDB();
    openDB.onsuccess = function() {
        var db = {};
        db.result = openDB.result;
        db.tx = db.result.transaction('data', "readwrite");
        db.store = db.tx.objectStore('data');

        db.store.put({id: key, data: value});
    };

    return true;

};

InitIDB.prototype.getObj = function (key, callback) {

    var openDB = openIndexedDB();
    var db = {};
    openDB.onsuccess = function () {
        db.result = openDB.result;
        db.tx = db.result.transaction("data", "readonly");
        db.store = db.tx.objectStore("data");

        var getData = db.store.get(key);
        getData.onsuccess = function () {
            // console.log(getData.result.data);
            callback(getData.result.data);
        }
    };
};

InitIDB.prototype.deleteObj = function (key) {
    var openDB = openIndexedDB();
    var db = {};
    openDB.onsuccess = function () {
        db.result = openDB.result;
        db.tx = db.result.transaction("data", "readwrite");
        db.store = db.tx.objectStore("data");

        var delData = db.store.delete(key);
        delData.onsuccess = function () {
            console.log("data deleted");
        }
    };
};


function handleConnectionChange(event) {
    if (event.type == "offline") {
        console.log("- connection.");
    }
    if (event.type == "online") {
        console.log("+ connection.");
    }
}

window.addEventListener('online', handleConnectionChange);
window.addEventListener('offline', handleConnectionChange);


function addItem(key, value) {
    if (useLocalStorage) {
        localStorageProvider.addObj(key, value);
    } else {
        indexedDBProvider.addObj(key, value);
    }
}

function getItem(key, callback) {
    if (useLocalStorage) {
        return localStorageProvider.getObj(key);
    } else {
        return indexedDBProvider.getObj(key, callback);
    }
}

function deleteItem(key) {

    if (useLocalStorage) {
        localStorageProvider.deleteObj(key);
    } else {
        return indexedDBProvider.deleteObj(key);
    }
}


// function deleteAllItems() {
//     for (var i = 0; i < InitLS.length; i++) {
//         var key = InitLS.key(i);
//         InitLS.removeItem(key);
//     }
// }
//
// function getAllItems() {
//     var array = [];
//     var obj = {};
//     for (var i = 0; i < InitLS.length; i++) {
//         var key = InitLS.key(i);
//         var item = JSON.parse(InitLS[key]);
//         obj.key = key;
//         obj.item = item;
//         array.push(obj);
//     }
//     return array;
//
// }