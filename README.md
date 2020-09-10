# users-registry
A user registration aplication.

# APIs
This project exposes a set of apis for users management. For the APIs documentation go to <base-app-url>/swagger-ui.html.

# Build
To build this project you must have maven and JDK 11 installed.

Open a terminal, go to the root of the project (where the pom.xml is located) and execute the following command:

```bash
mvn install
```

This will install all dependencies and run the unit and integration tests. Once done you will get a directory named **target**, inside you will find the generated jar.

# Docker
This project has a Dockerfile on the root directory that receives two arguments:
 - TOKEN_SECRET = the string that will sign the JWT tokens
 - JAR_NAME = the name of the generated jar
