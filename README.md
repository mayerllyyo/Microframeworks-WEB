# Web Framework

This project upgrades a basic web server into a lightweight framework for building RESTful services and managing static files. It allows developers to define routes with lambda expressions, extract query parameters easily, and configure static file locations. Built with Maven and Git, the framework demonstrates core concepts of HTTP, web architecture, and scalable application development.

## Overview

This project provides a minimal HTTP/1.1 server and a small framework-style API to:

- Register GET endpoints using lambdas
- Parse query parameters through a request wrapper
- Serve static assets (HTML/CSS/JS) from the classpath (`src/main/resources`)
- Keep the runtime lightweight (raw sockets + thread pool)

## Key Features

- Lambda-based routing via a functional `Route` interface
- Query string parsing with `HttpRequest.getValues(name)`
- Static file serving from `resources/webroot`
- No external runtime dependencies
- Unit tests validating request parsing and framework behavior


## Author

- **Mayerlly Suárez Correa** [mayerllyyo](https://github.com/mayerllyyo)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
