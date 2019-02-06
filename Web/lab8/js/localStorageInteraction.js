
function handleConnectionChange(event){
    if(event.type == "offline"){
        console.log("- connection.");
    }
    if(event.type == "online"){
        console.log("+ connection.");
        // var data = getAllItems();
        //send data to server
        // deleteAllItems();
    }
}

window.addEventListener('online', handleConnectionChange);
window.addEventListener('offline', handleConnectionChange);


function addItem(key, value) {
    localStorage.setItem(key, JSON.stringify(value));
}


function getItem(key) {
    let item = localStorage.getItem(key);
    item = JSON.parse(item);
    return item;
}

function getAllItems() {
    var array = [];
    var obj = {};
        for (var i = 0; i < localStorage.length; i++) {
            var key = localStorage.key(i);
            var item = JSON.parse(localStorage[key]);
            obj.key = key;
            obj.item = item;
            array.push(obj);
        }
    return array;

}
function deleteItem(key) {
    localStorage.removeItem(key);

}


function deleteAllItems() {
    for (var i = 0; i < localStorage.length; i++) {
        var key = localStorage.key(i);
        localStorage.removeItem(key);
    }
}