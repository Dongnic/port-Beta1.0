"use strict";
exports.__esModule = true;
var express_1 = require("express");
var http_1 = require("http");
var app = (0, express_1["default"])();
var server = http_1["default"].createServer(app);
var io = require('socket.io')(server, {
    cors: {
        origin: 'http://localhost:8080'
    }
});
// All the messages will be stored in this array.
var messagesArray = ['Hello... Welcome'];
io.on('connection', function (socket) {
    io.emit('getInitialMessages', messagesArray);
    socket.on('sendMessage', function (message) {
        messagesArray.push(message);
        if (message === 'clear')
            messagesArray = [];
        io.emit('returnMessage', messagesArray);
    });
});
var port = 3001;
server.listen(port, function () { return console.log("Server running at http://localhost:".concat(port)); });
