const express = require('express');
const app = express();

app.use(express.static(__dirname + '/dist/vsm'));

app.get('/*', function(req, res) {
  res.sendFile(__dirname + '/dist/vsm/index.html');
});

app.listen(process.env.PORT || 4200);
