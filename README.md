# assignment
Highlight of great OOA and software for potential job - upload a file with metadata and store to mongo. Include unit tests.

## Getting Started

To run the software, you will need to have a mongodb instance running on the same host, on port 27017.

### Prerequisites

- There is a bug in the reflection code in earlier versions of java 1.8 that prevent the mapping of JSON representation of Map (as a string) being unmarshalled to a Map - You need to run java 1.8_161 or later to avoid this bug
- mongodb installed and running

### Testing with curl
```
curl -v -H "Content-Type:multipart/form-data" -F "meta={\"metadata\":\"value\",\"key\":\"value\"};type=application/json" -F "file=@foo.txt"  http://localhost:8080/upload
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Martin Farmer** - *Initial work* -

## License

This project is licensed under the GNU General Public License v3.0 - see the [LICENSE.md](LICENSE.md) file for details
