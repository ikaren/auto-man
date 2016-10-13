Auto-Man
=================
auto man is a tool to generate code. This allows you to build whole DDD-style project and only need to write an xml.

### Generated Project Structure
Auto-Man can generate a Java project built by maven.
the Java project includes 5 modules.

```java
--foo-parent
    |-- foo-core
      |- domain  // data entity
      |- exception
      |- repository // operation of data entity or VO
      |- service // the related operation among entities
      |- vo // is not a domain but is a necessary data object
    |-- foo-infra
      |- repository-impl
      |- sql // orm
    |-- foo-application
      |- application
      |- application-impl 
    |-- foo-facade
      |- dto
      |- service-interface  // expose services by Dubbo
    |-- foo-facade-impl
      |- assembler // used to convert between domain and DTO
      |- interaction // the related operation among Dubbo services
      |- service-interface-impl
```

Quick Start
=================
two ways for Hello World:

### download source and build
run `build.sh` or `build.cmd` if you are windows user, then you can see bin file in `/dist/auto-man-${version}-bin.zip`.

tips: running `build.*` script to build auto-man need to install [Maven](http://maven.apache.org/).
### download bin
download file in `/dist/auto-man-${version}-bin.zip` and unzip.


bin file structure
```
--auto-man-${version}-bin
  |-- bin
    |-- start.sh
  |-- lib
    |-- *.jar
  |-- xml
    |-- *.xml // The project configuration that needs to be generated
  |-- template
    |-- *.ftl
```


* write your project configuration with .xml and put it in /xml. (there is a simple demo)
* run `start.sh` or `start.cmd` if you are windows user.


and then you can see some information and generate process. you'll get your project in the path you specified when everything to be done .

How to write the configuration
===========================
so easy....see simple-demo.xml and you can know what you want to know. hah


Supported data type:


| JDBC Type     | Java Type     |
| ------------- | -------------:|
| CHAR          | String        |
| FLOAT         | Float         |
| TIMESTAMP     | Date          |
| TINYINT       | Integer       |
| VARCHAR       | String        |
| DOUBLE        | Double        |
| INTEGER       | Integer       |
| BIGINT        | Long          |
| DECIMAL       | Double        |

Environment:

| Value         | environment    |
| ------------- | -------------:|
| PROD          | product        |
| BETA         | beta         |
| ALPHA     | alpha          |
| DEV       | development      |
