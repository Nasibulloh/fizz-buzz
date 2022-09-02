# Fizz-Buzz  - set up instruction

## Usage dependencies
1. [Docker][]: We use `Docker` environment to download dependent software and build project.
2. [Git][]: We use Git(BitBucket) for `VCS`


If you dont use `Docker` and want to use your host machine, then you need to install some software.

1. [Java][]: To build and compile the project

2. [PostgreSql][]: Database for using the project

## Working with [Docker][] environment
    1. Before running application, need to prepare build jar file
    2. Run maven command `mvn clean install`
    3. Make sure that you have *.jar file in `target` folder
    4. To start application inside the docker container run `docker-compose up` or `docker-compose up --build`
    5. To stop the running container run `docker-compose down`

[Java]: https://www.java.com/
[Docker]: https://www.docker.com/
[Git]: https://git-scm.com/
[PostgreSql]: https://www.postgresql.org/
