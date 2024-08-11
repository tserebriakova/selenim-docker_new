FROM amazoncorretto:17.0.12

#Install curl jq
RUN apk add curl jq

#workspace
WORKDIR /home/selenium-docker

#add the required files
ADD target/docker-resources ./
ADD runner.sh         runner.sh

# sh runner.sh  # java -cp 'libs/*' \
# -Dselenium.grid.enabled=true \
# -Dselenium.grid.hubHost=${HUB_HOST}\
# =Dbrowser=${BROWSER}\
# org.testng.testng\
# -threadcount ${THREAD_COUNT}\
# test-suites/${TEST_SUITE} #



#Start the runner.sh
ENTRYPOINT sh runner.sh

