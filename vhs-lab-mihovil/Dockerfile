#Use OpenJDK as base image
FROM openjdk

#Create a directory for the application
RUN mkdir "/app"

#Copy jar file from host to the container /app directory
COPY "/target/vhs-lab-mihovil-0.0.1-SNAPSHOT.jar" "/app"

#Declare app directory as working directory
WORKDIR "/app"

#Expose Tomcat server port
EXPOSE 2222

#Run the jar file
CMD java -jar "vhs-lab-mihovil-0.0.1-SNAPSHOT.jar" --spring.profiles.active=prod