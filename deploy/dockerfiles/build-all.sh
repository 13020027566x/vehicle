#!/bin/sh

mirror=fbt-registry.cn-beijing.cr.aliyuncs.com/$2
base_version=$3
curDir=$(pwd)

root=${curDir/finhub-vehicle*/}
root=${root}finhub-vehicle/

if [ -z $1 ]; then
  echo "请输入正确命令，例：./build-all.sh 1.0.0 dev 1.0.0"
  exit 1
fi

buildAndPush() {
  cd $root/$1/target
  pwd

  echo "开始打包..."$1:$2
  echo $mirror/$1:$2 FROM $mirror/base-java:$base_version
  docker build -t $mirror/$1:$2 -f - . <<EOF
FROM $mirror/base-java:$base_version
MAINTAINER mingkai.tao mingkai.tao@fenbeitong.com
RUN mkdir -p /data/apps/server
RUN mkdir -p /data/apps/log
ADD ./$1.jar /data/apps/server
WORKDIR /data/apps/server
EXPOSE 58080
EOF
  # docker push $mirror/$1:$2
  echo "结束打包..."$1:$2
}

buildMySQL() {
  cd $root/deploy
  pwd

  echo "开始打包..."$1
  echo $mirror/$1:$2 FROM mysql:5.7.25
  docker build -t $mirror/$1:$2 -f - . <<EOF
FROM mysql:5.7.25
MAINTAINER mingkai.tao mingkai.tao@fenbeitong.com
ENV MYSQL_ROOT_PASSWORD 123456
COPY ./sql /docker-entrypoint-initdb.d/
EOF
  # docker push $mirror/$1:$2
  echo "结束打包..."$1
}

buildRedis() {
  cd $root/deploy
  pwd

  echo "开始打包..."$1
  echo $mirror/$1:$2 FROM redis
  docker build -t $mirror/$1:$2 -f - . <<EOF
FROM redis
MAINTAINER mingkai.tao mingkai.tao@fenbeitong.com
COPY ./redis/redis.conf /etc/redis.conf
EXPOSE 6379
EOF
  # docker push $mirror/$1:$2
  echo "结束打包..."$1
}

buildZookeeper() {
  cd $root/deploy
  pwd

  echo "开始打包..."$1
  echo $mirror/$1:$2 FROM zookeeper
  docker build -t $mirror/$1:$2 -f - . <<EOF
FROM zookeeper
MAINTAINER mingkai.tao mingkai.tao@fenbeitong.com
COPY ./zookeeper/zoo.cfg /conf/zoo.cfg
EOF
  # docker push $mirror/$1:$2
  echo "结束打包..."$1
}

buildNacos() {
  cd $root/deploy
  pwd

  echo "开始打包..."$1
  echo $mirror/$1:$2 FROM nacos/nacos-server
  docker build -t $mirror/$1:$2 -f - . <<EOF
FROM nacos/nacos-server
MAINTAINER mingkai.tao mingkai.tao@fenbeitong.com
COPY ./nacos/custom.properties /home/nacos/init.d/custom.properties
EOF
  # docker push $mirror/$1:$2
  echo "结束打包..."$1
}

if [ -z $4 ]; then
  buildAndPush vehicle-server $1
  buildMySQL vehicle-mysql $1
  buildRedis vehicle-redis $1
  buildZookeeper vehicle-zookeeper $1
  buildNacos vehicle-nacos $1
else
  buildMySQL vehicle-mysql $1
  buildRedis vehicle-redis $1
  buildZookeeper vehicle-zookeeper $1
  buildNacos vehicle-nacos $1
fi
