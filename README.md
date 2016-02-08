# getting started java spring redis


[![wercker status](https://app.wercker.com/status/bd30bf8bc30b7fc37997c106f1504538/m "wercker status")](https://app.wercker.com/project/bykey/bd30bf8bc30b7fc37997c106f1504538)

## Spring-boot version
             
This is an absolutely minimal example of a Java-based multi-docker application, under Continuous Integration by
[Wercker](http://wercker.com/). It consists of two containers:    

- a REST api service and 
- a REDIS key/value store service 

The REST api contains one resource which increments a page-hit counter in the REDIS service and prints a greeting 
containing this counter in a text/html response.

## Dev mode

Wercker allows you to run your apps and your dependencies locally - the so called [Dev Mode](http://blog.wercker.com/2015/05/15/Introducing-local-development.html).

To use dev mode you need a docker environment running locally. A convenient way to get this all installed is to download
the [Docker Toolbox](https://www.docker.com/docker-toolbox), and if you are not on Linux you need [Virtualbox](https://www.virtualbox.org/). 

There are several ways of preparing your Docker environment to suit your specific needs. One simplistic method would be:

### Starting your docker-machine

    $ docker-machine start default
                                
### Updating your environment

    $ eval "$(docker-machine env default)"
    
Check your environment variables and make note of the IP assigned to your docker-machine.

### Running your system under Wercker in Dev Mode

    $ cd <your project root = location of wercker.yml>
    $ wercker dev --publish 8080
    
 You should now be able to visit
 
    http://<ip of your docker-machine>:8080/hello
    
The IP is typically 192.168.99.100. To make sure:
    
    $ env|grep 'DOCKER_HOST'
    
# Deployment to Google Container Engine / Kubernetes

## Setup the initial environment

To get started with GKE follow the instructions [here](https://cloud.google.com/container-engine/docs/before-you-begin) 

If you haven't already - create the cluster      

    $ gcloud container clusters create ${POD_NAME} \             
        --num-nodes 1 \             
        --machine-type g1-small    
   
Create your redis Pod with a singe instance
    
    $ kubectl create -f kubernetes/redis-rc.json         
    
Then attach a service              
    
    $ kubectl create -f kubernetes/redis-svc.json

Next create the pod for the example service

    $ kubectl create -f kubernetes/java-spring-redis-rc.json
    
And create and expose this service as a Load Balancer to the external world

    $ kubectl create -f kubernetes/java-spring-redis-svc.json  
    
## Building and uprading with Wercker

You can use Wercker to build and deploy from the command line. 

    $ wercker build
    
will build the app and

    $ wercker deploy
    
will build the container and push it to docker hub. Finally

    $ wercker deploy --deploy-target upgrade
    
will replace the service on GKE with a new version.

## Known issues

* With the current setup it is not practical to use watch/reload functionality of Wercker Dev Mode. We are looking into this. The solution is likely to be made available as a Wercker Custom Step
                                                         
For further details refer to the source code as well as to the Wercker, Docker and Virtualbox documentation.
    









