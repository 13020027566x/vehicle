#!/bin/sh

mirror=$2
rmi() {
  echo "删除本地镜像..." $1
  docker rmi $mirror/$1:$2
}

if [ -z $1 ]; then
  echo "请输入希望删除镜像的版本号，例：./rmi-all.sh 1.0.0 xxx.tencentcloudcr.com/tx-test"
  exit 1
fi

rmi vehicle-server $1
rmi vehicle-mysql $1
rmi vehicle-redis $1
rmi vehicle-zookeeper $1
rmi vehicle-nacos $1
