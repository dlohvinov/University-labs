'use strict';

var mongoose = require('mongoose');
var Schema = mongoose.Schema;


var FansSchema = new Schema({
    body: {
        type: String
    },
    date: {
        type: String
    },
    time: {
        type: String
    },
    author: {
        type: String
    }
});
var NewsSchema = new Schema({
    title: {
        type: String
    },
    description: {
        type: String
    },
    body: {
        type: String
    }
});

module.exports = mongoose.model('Fans', FansSchema);
module.exports = mongoose.model('News', NewsSchema);
