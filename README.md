REST showcase
=============

REST API for book store service. Presents how to integrate Spring Boot, Jersey 2 and MongoDB.
REST API supports JSON and XML as well as GZIP encoding and ETag caching.

Project is integrated with Heroku and MongoLab. You can visit it [here](https://bsodzik.herokuapp.com/books).

## Usage examples

* Get all books as JSON

	```
	curl -H 'Accept: application/json' -X GET -D - http://localhost:8080/books
	```

* Get all books as XML

	```
	curl -H 'Accept: application/xml' -X GET -D - http://localhost:8080/books
	```

* Get all books as JSON using GZIP encoding

	```
	curl -H 'Accept: application/json' -H 'Accept-Encoding: gzip' -X GET http://localhost:8080/books > books.zip
	```
	
* Get all books as JSON with ETag caching
	
	```
	curl -H 'Accept: application/json' -H 'If-None-Match: "b92392928ea957208b0d1bfb212d1bc0"' -X GET -D - http://localhost:8080/books
	```
	
* Add book in JSON format
	
	```
	curl -H 'Accept: application/json' -H 'Content-Type: application/json' -X POST -d '{"isbn":"31337","title":"Winds of Winter","description":"How long can we wait?!","authors":["George Martin"]}' -D - http://localhost:8080/books
	```
	
* Add book in XML format
	
	```
	curl -H 'Accept: application/xml' -H 'Content-Type: application/xml' -X POST -d '<?xml version="1.0" encoding="UTF-8" standalone="yes"?><book><authors>Thomas Harris</authors><description>Have the lambs stopped screaming?</description><isbn>0749309423</isbn><title>The silence of the lambs</title></book>' -D - http://localhost:8080/books
```