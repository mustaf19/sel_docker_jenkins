FROM openjdk:8u191-jre-alpine3.8

RUN apk add curl jq

# Workspace
WORKDIR /usr/share/udemy

# ADD .jar under target from host
# into this image
ADD target/selenium-docker.jar 			selenium-docker.jar
ADD target/selenium-docker-tests.jar 	selenium-docker-tests.jar
ADD target/libs							libs

# in case of any other dependency like .csv / .json / .xls
# please ADD that as well

# ADD suite files
ADD testng.xml				            testng.xml
ADD search-module.xml					search-module.xml

# ADD health check script
ADD healthcheck.sh                      healthcheck.sh

# BROWSER
# HUB_HOST
# MODULE

ENTRYPOINT sh healthcheck.sh
#ENTRYPOINT java -cp selenium-docker.jar`;`selenium-docker-tests.jar`;`libs/* -DBROWSER=$BROWSER -DHUB_HOST=$HUB_HOST org.testng.TestNG $MODULE


#=============================
#Dear Windows Users,
#
#When we copy the .sh script from windows into the Linux machine, due to some windows specifc characters It might not work properly. Please follow any of these approaches.
#
#Option 1:
#
#You need the shell script only for the container. So we can directly download and keep it in image while building as shown here.
#
#FROM openjdk:8u191-jre-alpine3.8
#
#RUN apk add curl jq
#
## Workspace
#WORKDIR /usr/share/udemy
#
## ADD .jar under target from host
## into this image
#ADD target/selenium-docker.jar selenium-docker.jar
#ADD target/selenium-docker-tests.jar selenium-docker-tests.jar
#ADD target/libs libs
#
## in case of any other dependency like .csv / .json / .xls
## please ADD any files required here
#
## ADD suite files
#ADD book-flight-module.xml book-flight-module.xml
#ADD search-module.xml search-module.xml
#
## ADD health check script
#RUN wget https://s3.amazonaws.com/selenium-docker/healthcheck/healthcheck.sh
#
## BROWSER
## HUB_HOST
## MODULE
#ENTRYPOINT sh healthcheck.sh
#Option 2:
#
#We can convert the file from windows to unix format as shown here.
#
#FROM openjdk:8u191-jre-alpine3.8
#
#RUN apk add curl jq
#
## Workspace
#WORKDIR /usr/share/udemy
#
## ADD .jar under target from host
## into this image
#ADD target/selenium-docker.jar selenium-docker.jar
#ADD target/selenium-docker-tests.jar selenium-docker-tests.jar
#ADD target/libs libs
#
## in case of any other dependency like .csv / .json / .xls
## please ADD any files required here
#
## ADD suite files
#ADD book-flight-module.xml book-flight-module.xml
#ADD search-module.xml search-module.xml
#
## ADD health check script
#ADD healthcheck.sh healthcheck.sh
#RUN dos2unix healthcheck.sh
#
## BROWSER
## HUB_HOST
## MODULE
#ENTRYPOINT sh healthcheck.sh
#Thanks.

