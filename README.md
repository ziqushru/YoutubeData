![alt tag](https://raw.githubusercontent.com/ziqushru/YoutubeData/master/res/logo.png)

YoutubeData is a YouTube aggregator.
It uses the YouTube Data API to get the information.
It retrieves data and displays them on a Java FX interface.

To replicate the project in EclipseEE :
* Create a new Maven Project named YoutubeData using the maven-archetype-quickstart
* Delete /src/main/com/App.java and /test/*
* Copy and paste the res, src folders and the pom.xml
* Right Click project -> Properties -> Java Build Path -> Source -> Select YoutubeData/src/test/java -> Remove
* Right Click project -> Properties -> Java Build Path -> Source -> Add Folder -> Select YoutubeData/src -> Ok
* Right Click project -> Properties -> Java Build Path -> Libraries -> Add External JARs -> add google-api-services-youtube-v3-rev183-1.22.0.jar and mysql-connector-java-5.1.42-bin.jar
* Right Click project -> Maven -> Update Project
* Right Click project -> Properties -> Java Build Path -> Libraries -> JRE System Library -> Access rules -> Edit -> Add (Resolution: Accessible, Rule Pattern: javafx/**)
* Change project compliance and JRE to 1.8

For the jar/project to run it needs a database.
It uses :
* MySQL Server 5.7.18
* Connector/J 5.1.42
All needed information about the setup is in the MySQL.txt

Directories:

_Name_ | _Description_
------ | -------------
res | resources
src | source code
MySQL | setup information
pom | dependencies
google-api-services-youtube-v3-rev183-1.22.0.jar | YouTube library
mysql-connector-java-5.1.42-bin.jar | MySQL Library
YoutubeData | runnable jar (Windows compiled)