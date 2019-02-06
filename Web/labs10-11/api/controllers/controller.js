'use strict';

var mongoose = require('mongoose'),
    Fans = mongoose.model('Fans'),
    News = mongoose.model('News');

exports.getAllFans = function(req, res) {
    Fans.find({}, function(err, task) {
        if (err)
            res.send(err);
        res.json(task);
    });
};

exports.addFans = function(req, res) {
    var new_task = new Fans(req.body);
    new_task.save(function(err, task) {
        if (err)
            res.send(err);
        res.json(task);
    });
};


exports.getAllNews = function(req, res) {
    News.find({}, function(err, task) {
        if (err)
            res.send(err);
        res.json(task);
    });
};

exports.addNews = function(req, res) {
    var new_task = new News(req.body);
    new_task.save(function(err, task) {
        if (err)
            res.send(err);
        res.json(task);
    });
};

exports.deleteAll = function(req, res) {
    Fans.remove({
    }, function(err, task) {
        if (err)
            res.send(err);
        res.json({ message: 'Task successfully deleted' });
    });

    News.remove({
    }, function(err, task) {
        if (err)
            res.send(err);
        res.json({ message: 'News successfully deleted' });
    });
};




// exports.read_a_task = function(req, res) {
//     Fans.findById(req.params.taskId, function(err, task) {
//         if (err)
//             res.send(err);
//         res.json(task);
//     });
// };


// exports.update_a_task = function(req, res) {
//     Fans.findOneAndUpdate({_id: req.params.taskId}, req.body, {new: true}, function(err, task) {
//         if (err)
//             res.send(err);
//         res.json(task);
//     });
// };


// exports.delete_a_task = function(req, res) {
//
//
//     Fans.remove({
//         _id: req.params.fansId
//     }, function(err, task) {
//         if (err)
//             res.send(err);
//         res.json({ message: 'Task successfully deleted' });
//     });
// };
//
