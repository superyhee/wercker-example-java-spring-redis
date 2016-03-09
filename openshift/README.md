# OpenShift-specific stuff

## route.js

This is only required to expose the service on under OpenShift. The *Route* service is specific to OpenShift and is not defined in Kubernetes.

    $ oc create -f openshift/route.json
    
and to remove it

    $ oc delete route java-spring-redis-route