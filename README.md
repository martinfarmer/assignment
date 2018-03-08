# assignment
Highlight of great OOA and software for potential job - upload a file with metadata and store to mongo. Include unit tests.

## The challenge

Implement a Restful API in spring-boot application. API to upload a file with a few meta-data fields. Persist meta-data in persistent store (In memory DB or file system and store the content on a file system). Upon finishing, please upload the assignment to GitHub. The project also should include unit tests and readme.txt file.

## design

The Handler package deal with writing files and metadata. There is an abstract implementation that writes files to disk. There is a database implemention, using H2, for writing metadata to database.

The FilePersistenceService is a command design pattern that has a DI FileHandler, and simply know what to call to save a file and metadata.

The FileUploadController is the main Spring RestController and handles unmarchalling JSON metadata from the incoming http post into a Java POJO, and the ofloading to the FilePersistenceService

### Different metadata storage options

The MetaDataRepository handles JPA based database storage, using H2 initially as a quick in-memory database solution. An update to the pom.xml dependancy would allow for a different database solution to be used, while using JPA.

A mongodb solution would require a new Spring Repository interface that would handle the CRUD for Mongo.

## Getting Started

For ease in running the software, H2 (an in-memory database is being used). No additional dependecies are required.

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
