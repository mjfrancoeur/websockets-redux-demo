# base image
FROM phusion/baseimage:0.9.22

# create app directory
WORKDIR opt/app

# install node
RUN apt-get install curl -y
RUN curl -sL https://deb.nodesource.com/setup_10.x | bash
RUN apt-get install nodejs -y

# install java
RUN apt-get update
RUN apt-get install default-jdk -y
# install maven
RUN apt-get install maven -y

# set vi bash bindings
RUN echo "set -o vi" >> ~/.bashrc

# expose port 8000 and 80
EXPOSE 8080
