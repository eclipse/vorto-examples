FROM java:8
# Add folder to hold jar file
RUN mkdir /gen
WORKDIR /gen
ARG JAR_FILE
ADD target/${JAR_FILE} /gen/generators.jar
RUN chmod +x /gen/wait-for-it.sh
RUN chmod +x /gen/run_generators.sh
ADD ./docker/wait-for-it.sh /gen
ADD ./docker/run_generators.sh /gen/run_generators.sh

# Wait for repository to become avaliable
# Read mounted config file, remove newlines from file and write to variable, run spring boot after that
CMD ["/bin/bash", "/gen/wait-for-it.sh", "-t", "20", "localhost:8080",  "--", "/bin/bash", "/gen/run_generators.sh"]

