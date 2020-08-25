#!/bin/bash

cd docker
docker-compose up -d

DB_HEALTHY=0
while [ $DB_HEALTHY = 0 ] ; do
  echo "Esperando o banco de dados...";
  sleep 3;
  docker inspect --format='{{.State.Health.Status}}' postgres_xy_inc | grep 'healthy' > /dev/null && DB_HEALTHY=1 || DB_HEALTHY=0;
done;

cd ..
./gradlew flywayMigrate -Dserver=local_database clean build

