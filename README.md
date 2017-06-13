# sandwich-api

An API for sandwiches. 🍞🍅🍗🥑🍖

# API Documentation

- GET /sandwich/{string}

Gets a sandwich with name string.

- POST /sandwich

Submits a new sandwich to the database. Sandwich data should be in the POST body, of the form:

```
{ 
	"name": {string}, 
	"chicken-count": {int}, 
	"ham-count": {int}, 
	"lettuce-count": {int}, 
	"mozzarella-count": {int}, 
	"bacon-count": {int}, 
	"avocado-count": {int} 
}
```

Returned body is `{"result": "success"}` if the sandwich was successfully persisted, `{"result": "failure"}` if it was not. Failure is guaranteed if a sandwich with that name has already been POSTed.

# Why?

I wanted to practice and demonstrate my Clojure capabilities. This is a mini-API about sandwiches with certain quantities of chicken, ham, lettuce, mozzarella, bacon, and avocado, but its main purpose is to show that I can use Ring, Compojure, JDBC, and PostgreSQL in Clojure.

# Caveats

This is code mainly for reading, not running. It does run, but you would need to set up a PostgreSQL database at `localhost:5432` with user `postgres` and password `admin`, unless you wanted to modify the hardcoded permissions.

Maybe I'll get around to parsing a config file or some flags on startup one day. We'll see!

**BIG CAVEAT**: nothing has been done to sanitize the strings submitted to the GET /sandwich/{string} route, so this would obviously not be secure!