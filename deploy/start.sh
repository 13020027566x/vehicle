#!/bin/bash

curDir=$(pwd)

root=${curDir/finhub-vehicle*/}
root=${root}finhub-vehicle/

cd $root
pwd

if [ -z $1 ]; then
  echo " ------------ 源码正在打包，打包过程大约需要 2 分钟。------------ ";
  ./mvnw clean install -Dmaven.compile.fork=true -Dmaven.test.skip=true -Dmaven.test.failure.ignore=true -U -e -X -f pom.xml -P tx-dev

  echo " ------------ 镜像正在打包，打包过程大约需要 2 分钟。------------ ";
fi


if [ -z $1 ]; then
  sh ./deploy/dockerfiles/build-all.sh 1.0.0 dev 1.0.0
else
  sh ./deploy/dockerfiles/build-all.sh 1.0.0 dev 1.0.0 $1
fi


root=${root/deploy*/}
root=${root}deploy/

cd $root
pwd

docker network create --driver=bridge vehicle_network
docker-compose up -d
echo " ------------ 系统正在启动，启动过程大约需要 5 分钟。------------ ";
