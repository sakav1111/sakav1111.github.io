---
title: Docker- Summary
permalink: /docker/summary
key: docker-summary
categories:
- Docker
tags:
- Docker
---

#Docker - Summary
========================================
**Docker** is a tool that allows you to easily share, deploy, and run applications consistently in many environments through containers. First, I needed to get access to the Docker command line (CLI). The [installation instructions](https://docs.docker.com/install/) for Docker are not very clear or straightforward, but you need to download **Docker Desktop** (for Mac or Windows).
Additionally, **Docker Hub** is similar to [GitHub](https://github.com/) for git repositories or the [npmjs registry](https://www.npmjs.com/) for JavaScript packages - it's an online repository of Docker images and the login that Docker Desktop will connect to.
-   Install [Docker Desktop](https://www.docker.com/get-started)
-   Create a [Docker Hub](https://hub.docker.com/) account
Once all this is complete, you can ensure Docker CLI is set up and working by checking the version.
```shell
docker -v
```
And log in to Docker Hub, where it will prompt you for your username and password.
```shell
docker login
```
In order to use Docker, you must understand the concept of **images** and **containers**.
### [](www.satyacodes.ml/#images)Images
An **image** is a blueprint that contains the instructions to build a container. It's an immutable snapshot of the file system and configuration of an application. Images can be easily shared between developers.
```shell
docker images # list all images
```
```shell
REPOSITORY     TAG     IMAGE ID     CREATED     SIZE
---
```
### [](www.satyacodes.ml/#containers)Containers
A **container** is a executable package that contains everything needed to run an application. It will always run the same, regardless of infrastructure, in a sandboxed environment. It is a running instance of an image.
```shell
docker ps -a # list all containers
```
```shell
CONTAINER ID     IMAGE     COMMAND     CREATED     STATUS     PORTS     NAMES
---
```
### [](www.satyacodes.ml/#tags)Tags
A **tag** is a reference to a specific image version.

### [](www.satyacodes.ml/#cli-reference)CLI reference

Here's an overview a few commonly used commands.
| Command | Context | Action |
| --- | --- | --- |
| [`docker build`](https://docs.docker.com/engine/reference/commandline/build/) | Image | Builds an image from a Dockerfile |
| [`docker tag`](https://docs.docker.com/engine/reference/commandline/tag/) | Image | Tags an image |
| [`docker images`](https://docs.docker.com/engine/reference/commandline/images/) | Image | Lists images |
| [`docker run`](https://docs.docker.com/engine/reference/run/) | Container | Runs a container based on an image |
| [`docker push`](https://docs.docker.com/engine/reference/commandline/push/) | Image | Pushes an image to a registry |
| [`docker pull`](https://docs.docker.com/engine/reference/commandline/pull/) | Image | Pulls an image from a repository |
| [`docker ps`](https://docs.docker.com/engine/reference/commandline/ps/) | Container | Lists containers |
| [`docker system prune`](https://docs.docker.com/engine/reference/commandline/system_prune/) | Image/Container | Remove unused containers and images |



### [](www.satyacodes.ml/#dockerfile)Dockerfile
I know how to run my app locally for production. I have a Webpack configuration that builds the production React application, then a command to start the Node server on port `5000`. It looks like this.

I don't have a sample application for this article, but any simple Node application should be easy to set up with the following instructions.

```shell
npm i         # install dependencies
```

```shell
npm run build # build React app
```

```shell
npm run start # start Node server
```

To use a container, you'll need to give instructions to Docker via a file called `Dockerfile` in the root of your project. `Dockerfile` seems a little intimidating at first, but it's not that much different from how I set up my local environment. It just requires a few `Dockerfile` specific commands.
-   [FROM](https://docs.docker.com/engine/reference/builder/#from) - Start the Dockerfile and pull from a base image
-   [COPY](https://docs.docker.com/engine/reference/builder/#copy) - Copy files from local source to container target
-   [WORKDIR](https://docs.docker.com/engine/reference/builder/#workdir) - Set working directory for subsequent commands
-   [RUN](https://docs.docker.com/engine/reference/builder/#run) - Run commands
-   [EXPOSE](https://docs.docker.com/engine/reference/builder/#expose) - Set a port
-   [ENTRYPOINT](https://docs.docker.com/engine/reference/builder/#entrypoint) - Set executable command
It looked a little something like this.

Dockerfile
```shell
# Pull from a base image
FROM node:12-alpine
# Copy the files from the current directory to app/
COPY . app/
# Use app/ as the working directory
WORKDIR app/
# Install dependencies (npm ci is similar to npm i, but for automated builds)
RUN npm ci --only-production
# Build production client side React application
RUN npm run build
# Listen on the specified port
EXPOSE 5000
# Set Node server
ENTRYPOINT npm run start
```

Depending on the base image you choose, you might need to install additional dependencies, as certain base images (such as Node Alpine Linux) are meant to be as small as possible and don't have an extensive array of global programs installed.

### [](www.satyacodes.ml/#building-tagging-and-running-a-container)Building, tagging, and running a container
Building and running a container locally is pretty simple from here. Before attempting to deploy it to Docker Hub for use in automation, you'll want to see if it works locally.

### [](www.satyacodes.ml/#build)Build
First, you [build](https://docs.docker.com/engine/reference/commandline/build/) the image, with a name, and optionally a tag (if no tag is specified, it will be tagged `latest`).
```shell
# Build an image
docker build -t <image>:<tag> .
```


You'll see it start running through all the steps.
```shell
Sending build context to Docker daemon   2.88MB
Step 1/9 : FROM node:12-alpine
 ---> ...running through all the steps...
Successfully built 123456789123
Successfully tagged <image>:<tag>
```


It might take a minute or two depending on how many dependencies you have. Once it's complete, you can do `docker images` to see your new image.
```shell
REPOSITORY          TAG               IMAGE ID            CREATED              SIZE
<image>             latest            123456789123        About a minute ago   x.xxGB
```


### [](www.satyacodes.ml/#run)Run
The image is created, so you can [run](https://docs.docker.com/engine/reference/run/) a container based on it. Since I want to view it on `localhost:5000`, I'll set the left side of the port to `5000`, and the right side is which point the container is pointing at.
```shell
docker run -p 5000:5000 <image>:<tag> # Run on local port 5000 and container port 5000
```


Now that the container is created and running, you can `docker ps` to see it (or `docker ps -a` to see all containers, if it's not currently running).
```shell
CONTAINER ID        IMAGE               COMMAND                  CREATED              STATUS                      PORTS                    NAMES
987654321234        <image>             "/bin/sh -c 'npm run..."   6 seconds ago        Up 6 seconds                0.0.0.0:5000->5000/tcp   stoic_darwin
```


Going to `localhost:5000` will now display your fully built application, just as it will appear in production.
### [](www.satyacodes.ml/#tag-and-publish)Tag and publish
To use one of these images on a production server, you'll want to be able to retrieve it from Docker Hub. You'll have to create a repository for the project on Docker Hub. Once you do that, you'll have a place to send your image. You'll have to update the image name to be your Docker Hub username and repository, plus whatever tag you want.
Now you can build and tag like before with the new names, and `docker push` to the Docker Hub repository.
```shell
docker build -t <username>/<repository>:<tag> .
docker tag <username>/<repository>:<tag> <username>/<repository>:latest
docker push <username>/<repository>:<tag>
# It might look something like this:
docker build -t user/app:v1.0.0 .
docker tag user/app:v1.0.0 user/app:latest
docker push user/app:v1.0.0
```


If all went well, your image will now be live on Docker Hub and it will be easy to share.
### [](www.satyacodes.ml/#next-steps)Next steps
At this point, I can see that my application is running from a docker container locally, and I have that image on Docker Hub, so that's a big portion of the battle. From here, there are two more issues to cover:
-   Setting up a continuous integration tool to test and deploy code
-   Setting up a production server to receive and serve code
In my case, I used [Travis CI](https://travis-ci.org/) for CI/CD, and [DigitalOcean](https://www.digitalocean.com/) for the server.
> There are alternatives for the automation tool and server. For example, instead of Travis, you could use [CircleCI](https://circleci.com/) or [Github Actions](https://github.com/features/actions). Instead of DigitalOcean, you could use [AWS](https://aws.amazon.com/) or [Linode](https://www.linode.com/).
Since I went with Travis as I already had some testing setup, I can briefly go into what it entails to set up a Travis flow.



# [](www.satyacodes.ml/#travis-ci)Travis CI

**Travis CI** is a tool for testing and shipping code. I don't want to go too much into the specifics of setting up Travis because every use case will be unique, but I'll cover some of the basics in case you decide to use them. The concepts and configuration will be simple whether you're dealing with Travis, CircleCI, Jenkins, or whatever.


To use Travis CI, go to the [website](https://travis-ci.org/), create an account, and integrate it with your GitHub account. You'll have to find the GitHub repository you're planning on adding automation to and enable it. (I'm using GitHub, but I'm sure it integrates with BitBucket or GitLab or whatever else.)


Every time you start a build that's connected to Travis, it will spin up a server that runs all the commands you've specified, including deployment on specific branches.
### [](www.satyacodes.ml/#lifecycle)Lifecycle
A Travis configuration file, which will live at `.travis.yml` in the root of your project, has a concept of [lifecycle](https://docs.travis-ci.com/user/job-lifecycle/). From earliest to latest, this is what the lifecycle looks like.
-   `apt addons`
-   `cache components`
-   `before_install`
-   `install`
-   `before_script`
-   `script`
-   `before_cache`
-   `after_success` or `after_failure`
-   `before_deploy`
-   `deploy`
-   `after_deploy`
-   `after_script`
### [](www.satyacodes.ml/#testing)Testing
In my Travis configuration file, I'm going to set up the local Travis server. I've chosen a language of Node, a version of 12, and instructed it to install the necessary dependencies to use Docker.


Everything in this Travis file will run for all pull requests and branches on the GitHub repository, unless otherwise specified. This is useful because it means we can run tests on all incoming code to see if it's ready to merge into master and won't break the build. For this global configuration, I install everything locally, run a Webpack dev server in the background (specific to my flow), and run the tests.

> If you want code coverage badges on your repository, [this quick tutorial](https://www.taniarascia.com/display-build-status-and-test-coverage/) explains how to use Jest, Travis, and Coveralls to collect and display coverage.
.travis.yml
```shell
# Set the language
language: node_js
# Set the Node version
node_js:
  - '12'
services:
  # Use Docker command line
  - docker
install:
  # Install dependencies for tests
  - npm ci
before_script:
  # Start server and client for tests
  - npm run dev &
script:
  # Run tests
  - npm run test
```


This signifies the end of lifecycle for branches and pull requests only.
### [](www.satyacodes.ml/#deployment)Deployment
Assuming that all automated tests passed, you have the optional ability to deploy code to a production server. Since we only want to do that on `master`, you'll specify that in the deploy config. Before you try inlining code here like in the rest of the steps, I'll warn you that you have to have an actual script to call for the deployment.
```shell
deploy:
  # Build Docker container and push to Docker Hub
  provider: script
  script: bash deploy.sh
  on:
    branch: master
```

The deployment script will consist of two aspects:
-   Building, tagging and pushing the Docker image from the CI tool (Travis)
-   Pulling, stopping and starting the Docker container from the server (DigitalOcean, in this case)


First, we can set up the building, tagging, and pushing step, which is very similar to the way I did it manually above, except it needs a unique tagging strategy and automated logging in. I struggled with figuring out some aspects the deployment script, such as a tagging strategy, logging in using input, and encoding SSH keys, and dealing with SSH inception.


So, the first part of the script is getting the image on Docker Hub, which is straightforward. The tag naming I used combines the git hash with the git tag, if it exists. This ensures a unique tag and makes it easy to identify the build it's based on. Here, `DOCKER_USERNAME` and `DOCKER_PASSWORD` are custom environment variables, which you can set through the Travis UI. Travis will redact that sensitive data from showing up in the deploy feed.


deploy.sh
```shell
#!/bin/sh
set -e # Stop script from running if there are any errors
IMAGE="<username>/<repository>"                             # Docker image
GIT_VERSION=$(git describe --always --abbrev --tags --long) # Git hash and tags
# Build and tag image
docker build -t ${IMAGE}:${GIT_VERSION} .
docker tag ${IMAGE}:${GIT_VERSION} ${IMAGE}:latest
# Log in to Docker Hub and push
echo "${DOCKER_PASSWORD}" | docker login -u "${DOCKER_USERNAME}" --password-stdin
docker push ${IMAGE}:${GIT_VERSION}
```
The second part of the script depends entirely on what host you're using and the strategy they have for connecting. Since I'm using DigitalOcean, this meant using [doctl](https://github.com/digitalocean/doctl) CLI commands. For AWS it would be `aws`, and so on.

There wasn't too much initial setup for the server. I set up a Droplet based on a base image. This system does require installing and starting up Docker once manually, however. I used Ubuntu 18.04, so you can just [follow this simple guide](https://phoenixnap.com/kb/how-to-install-docker-on-ubuntu-18-04) to getting that step ready if you're using Ubuntu as well.


I won't supply the individual commands for the service because this will vary wildly, but what you'll do once you SSH into the deployment server will resemble this.
-   You'll want to find the currently running container and stop it. (You can do this by setting a `--name` on the container, and stopping and removing that container before setting the new one.)
-   Then you'll want to start up the new container in the background.
    -   Add `--restart-unless-stopped` to ensure if something happens, Docker will restart the container.
    -   Add `-d` to run it in the background, allowing you to escape from the script or run other commands.
-   You'll want to run the server's local port on `80`, so you can go to `example.com` instead of `example.com:5000`.
-   Finally, you'll want to prune all old containers and images.
```shell
# Stop, run, and clean
docker stop current-container
docker rm current-container
docker run --name=current-container --restart unless-stopped -d -p 80:5000 ${IMAGE}:${GIT_VERSION}
docker system prune -a -f
```
### [](www.satyacodes.ml/#a-few-things-to-note)A few things to note
You might get a warning when connecting to an SSH host from Travis, which will prevent the installation from continuing since it expects input.
```shell
The authenticity of host '<hostname> (<IP address>)' can't be established.
RSA key fingerprint is <key fingerprint>.
Are you sure you want to continue connecting (yes/no)?
```

I learned (from Vanya) that you can base64 encode a string to store it in a predictable format. In the install phase, you can decode a public key and write it to the `known_hosts` file to bypass that error.
```shell
echo <public key> | base64 # prints <base 64 encoded public key>
```

So in practice, the input would look something like this:
```shell
echo "123.45.67.89 ssh-rsa AAAAB3NzaC1yc2EAAAABIwAAAQEAklOUpkDHrfHY17SbrmTIpNLTGK9Tjom/BWDSU
GPl+nafzlHDTYW7hdI4yZ5ew18JH4JW9jbhUFrviQzM7xlELEVf4h9lFX5QVkbPppSwg0cda3
Pbv7kOdJ/MTyBlWXFCR+HAo3FXRitBqxiX1nKhXpHAZsMciLq8V6RjsNAQwdsdMFvSlVK/7XA
t3FaoJoAsncM1Q9x5+3V0Ww68/eIFmb1zuUFljQJKprrX88XypNDvjYNby6vw/Pb0rwert/En
mZ+AW4OZPnTPI89ZPmVMLuayrD2cE86Z/il8b+gw3r3+1nKatmIkjn2so1d01QraTlMqVSsbx
NrRFi9wrf+M7Q== you@example.com" | base64
```


And the base64 encoded output:
```shell
MTIzLjQ1LjY3Ljg5IHNzaC1yc2EgQUFBQUIzTnphQzF5YzJFQUFBQUJJd0FBQVFFQWtsT1Vwa0RIcmZIWTE3U2JybVRJcE5MVEdLOVRqb20vQldEU1UKR1BsK25hZnpsSERUWVc3aGRJNHlaNWV3MThKSDRKVzlqYmhVRnJ2aVF6TTd4bEVMRVZmNGg5bEZYNVFWa2JQcHBTd2cwY2RhMwpQYnY3a09kSi9NVHlCbFdYRkNSK0hBbzNGWFJpdEJxeGlYMW5LaFhwSEFac01jaUxxOFY2UmpzTkFRd2RzZE1GdlNsVksvN1hBCnQzRmFvSm9Bc25jTTFROXg1KzNWMFd3NjgvZUlGbWIxenVVRmxqUUpLcHJyWDg4WHlwTkR2allOYnk2dncvUGIwcndlcnQvRW4KbVorQVc0T1pQblRQSTg5WlBtVk1MdWF5ckQyY0U4NlovaWw4YitndzNyMysxbkthdG1Ja2puMnNvMWQwMVFyYVRsTXFWU3NieApOclJGaTl3cmYrTTdRPT0geW91QGV4YW1wbGUuY29tCg==
```

```shell
install:
  - echo <base 64 encoded public key> | base64 -d >> $HOME/.ssh/known_hosts
```

The same tactic can be used with the private key during the connection phase, as you'll likely need a private key to access your server. You'll just want to make sure the private key is securely stored in the Travis environment variable and absolutely not visible from the deployment feed.
Another thing to note is you might have to run your whole deployment script from a single line, like with `doctl`, which can require some fine-tuning.

```shell
doctl compute ssh <droplet> --ssh-command "all the commands go here && here"
```


### [](www.satyacodes.ml/#tlsssl-and-load-balancing)TLS/SSL and load balancing

Once I finished all this, my only final issue was that the server had no SSL. Since I'm running a Node server, there is a [massive amount of setup](https://www.digitalocean.com/community/tutorials/how-to-secure-a-containerized-node-js-application-with-nginx-let-s-encrypt-and-docker-compose) to get an Nginx reverse-proxy and Let's Encrypt up and running.


I really was not interested in doing all that manual SSL setup, so I simply created a load balancer, and pointed the DNS at the load balancer. With DigitalOcean, for example, creating an auto-renewing self-signed certificate on a load balancer is simple, free, and instant, and has the added benefit of allowing you to easily have SSL set up on multiple servers running behind a load balancer, should you choose to.

This also allows the server itself to have no concept of SSL at all, and still run everything on port `80`. Certainly much easier then the alternative, with other added benefits.  

Once that is set up, you can close of all inbound access on all ports for your server except port `80` from the load balancer and `22` for SSH, so trying to hit the server directly by any means except those two will be rejected.


[](www.satyacodes.ml/#conclusion)Conclusion
--------------------------------------------------------------------------------------------
After setting all this up, I'm no longer intimidated by Docker, or the concept of automation pipelines and CI/CD. I managed to set up a full continuous pipeline that tests my code before integrating it with production, and automatically deploys the code to a server. I'm still relatively new to this and I'm sure there are ways to make my process better and more efficient, so if you have any improvements or critiques, [feel free to let me know](mailto:hello@taniarascia.com) and I'll update the article accordingly. I hope this article helps you out and you learn as much as I did!
