# getting started java spring redis


[![wercker status](https://app.wercker.com/status/52084dac602bd0033c8ddec62ce72fd1/m "wercker status")](https://app.wercker.com/project/bykey/52084dac602bd0033c8ddec62ce72fd1)

## Spring-boot version
                        git ini
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
    
## Known issues

* With the current setup it is not practical to use watch/reload functionality of Wercker Dev Mode. We are looking into this. The solution is likely to be made available as a Wercker Custom Step
* The JUnit test starts an Embedded Jetty and waits for 6 seconds for it to start. A better solution for starting and stopping Jetty between unit tests will be required.
                                                          
For further details refer to the source code as well as to the Wercker, Docker and Virtualbox documentation.
    









