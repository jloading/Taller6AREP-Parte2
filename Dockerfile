FROM openjdk:17

LABEL authors="Juanca"

WORKDIR /usrapp/bin

ENV PORT 4568

COPY /target/classes /usrapp/bin/classes
COPY /target/dependency /usrapp/bin/dependency

CMD ["java","-cp","./classes:./dependency/*","org.example.SparkServer"]