
# License TL;DR database

## Installation

### Requirements

The following programs are required to build the program:
 * Java SE and JDK, version 1.7 or above
 * Wildfly
 * Any relational database with a JDBC driver

### Setup

First, the JDBC driver must be deployed on Wildfly. This is simplest done by putting the jar file inside /standalone/deployments within Wildfly.

For the program to be able to communicate to the database, a datasource must be added. The datasource must have `java:/LicenseDatabase` as a JNDI-name for the database to be able to properly communicate with the program.

### Build

To build the program, enter the following commands in the specified order:
```
cd license-tldr
mvn compile
mvn war:war
```

A .war file will be generated in /license-tldr/target directory of the project. This war file should be deployed to Wildfly, in the same manner as above.

