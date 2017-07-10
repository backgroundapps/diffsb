# Diffsb
## Endpoints to compare two JSON Files


---


### Infrastructure
This is a SpringBoot App build using gradle

* To use this app just run `gradle bootRun` in the project directory.
* To execute all tests just run `gradle clean test` in the project directory.


---


### Use

To see this application working you will need to call both endpoints following the order.

* 1 - Access the url: [LOCALHOST:PORT]/v1/diff/[ID]/left/[ENCODED JSON]
* 2 - Access the url: [LOCALHOST:PORT]/v1/diff/[ID]/right/[ENCODED JSON]
* 3 - Access the url: [LOCALHOST:PORT]/v1/diff/[ID] [This url shows the result of the comparison]

#### LEGENDS:
* LOCALHOST    - Address of the server
* PORT         - Port of your application
* ID           - ID of the file included
* ENCODED JSON - JSON encoded in base64

#### SAMPLES:
* 1 - http://localhost:8080/v1/diff/42/left/eyJhIjoiYSJ9
* 2 - http://localhost:8080/v1/diff/42/right/eyJhIjoiYSJ9
* 3 - http://localhost:8080/v1/diff/42


---
### Check list of improvements:
[Improvements](https://github.com/jonatasemidio/diffsb/issues)


---
### Check Coverage:
[Coverage](https://jonatasemidio.github.io/diffsb/)


---
### Check Build Log on Travis:
[Build](https://travis-ci.org/jonatasemidio/diffsb)



