#!/usr/bin/env bash

#!/bin/bash

cat > java-spring-redis-rc.json <<EOF
{
  "kind": "ReplicationController",
  "apiVersion": "v1",
  "metadata": {
    "name": "java-spring-redis",
    "labels": {
      "run": "java-spring-redis"
    }
  },
  "spec": {
    "replicas": 1,
    "selector": {
      "run": "java-spring-redis"
    },
    "template": {
      "metadata": {
        "labels": {
          "run": "java-spring-redis",
          "deployment": "${WERCKER_GIT_COMMIT}"
        }
      },
      "spec": {
        "containers": [
          {
            "name": "java-spring-redis",
            "image": "combient/wercker-example-java-spring-redis:${WERCKER_GIT_COMMIT}",
            "ports": [
              {
                "containerPort": 8080,
                "protocol": "TCP"
              }
            ],
            "imagePullPolicy": "IfNotPresent"
          }
        ],
        "restartPolicy": "Always",
        "terminationGracePeriodSeconds": 30,
        "dnsPolicy": "ClusterFirst"
      }
    }
  }
}
EOF