FROM ubuntu:latest

#Step 1 : Install the pre-requisite
RUN apt update && apt-get update
RUN apt-get install -y curl zip unzip

ARG CHROMDRIVER_VERSION=107.0.5304.62

#Step 2: Install Chrome
RUN echo "Install Chrome" && \
    curl -O https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb && \
    apt install -y ./google-chrome-stable_current_amd64.deb && \
    rm google-chrome-stable_current_amd64.deb

#Step 3: Install chromedriver for Selenium
RUN echo "Install chromedriver for Selenium" && \
    mkdir -p /app/bin && \
    curl https://chromedriver.storage.googleapis.com/$CHROMDRIVER_VERSION/chromedriver_linux64.zip -o /tmp/chromedriver.zip \
    && unzip /tmp/chromedriver.zip -d /app/bin/ \
    && rm /tmp/chromedriver.zip && \
    chmod +x /app/bin/chromedriver

#Step 4: Install java
ENV PATH=$PATH:/opt/java/jdk-15.0.2/bin

RUN mkdir /opt/java && \
    curl https://download.java.net/java/GA/jdk15.0.2/0d1cfde4252546c6931946de8db48ee2/7/GPL/openjdk-15.0.2_linux-x64_bin.tar.gz -o openjdk-15.0.2_linux-x64_bin.tar.gz &&\
    tar -xzf openjdk-15.0.2_linux-x64_bin.tar.gz && \
    rm -rf openjdk-15.0.2_linux-x64_bin.tar.gz

#Step 5: Install Maven
ARG USER_HOME_DIR="/root"

RUN echo "Downloading maven" \
  && apt install -y maven \
  && ln -sf /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

#Step 6: Copy project
COPY . /SeleniumRestAssuredTests
WORKDIR /SeleniumRestAssuredTests

#Step 7: Run tests
RUN echo "Run tests" && \
    mvn test -Dtest=ApiTests,UiTests
