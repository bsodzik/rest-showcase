
{"isbn":"54321","title":"Dance with Dragons","description":"Awesome!","authors":["Georgie"]}


curl -D - -H 'Accept: application/json' -H 'Content-Type: application/json' -X POST -d '{"isbn":"54321","title":"Dance with Dragons","description":"Awesome!","authors":["Georgie"]}' http://localhost:8080/books

curl -D - -H 'Accept: application/xml' -H 'Content-Type: application/xml' -X POST -d '<?xml version="1.0" encoding="UTF-8" standalone="yes"?><book><authors>Test</authors><description>Test</description><isbn>123</isbn><title>Test</title></book>'