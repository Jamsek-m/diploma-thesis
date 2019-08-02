const express = require("express");
const server = express();

server.use((req, res, next) => {
    res.setHeader("Timing-Allow-Origin", "*");
    next();
});

server.use(express.static(__dirname + "/dist/angular-sample"));

server.use((req, res) => {
   res.sendFile(__dirname + "/dist/angular-sample/index.html");
});

server.listen(3000, () => {
   console.log("Server started");
});
