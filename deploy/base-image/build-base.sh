#!/bin/sh

mirror=fbt-registry.cn-beijing.cr.aliyuncs.com/$1

curDir=$(pwd)

root=${curDir/finhub-vehicle*/}
root=${root}finhub-vehicle/

if [ -z $1 ]; then
  echo "请输入正确命令，例：sh ./deploy/base-image/build-base.sh dev "
  exit 1
fi

## AdoptOpenJDK 停止发布 OpenJDK 二进制，而 Eclipse Temurin 是它的延伸，提供更好的稳定性
## 感谢复旦核博士的建议！灰子哥，牛皮！
buildAndPush() {
  cd $root/deploy
  pwd

  echo "开始打包..."$1:$2
  echo $mirror/$1:$2 FROM eclipse-temurin:8-jre
  docker build -t $mirror/$1:$2 -f - . <<EOF
FROM eclipse-temurin:8-jre
MAINTAINER mingkai.tao mingkai.tao@fenbeitong.com
COPY ./base-image/sw-agent /data/apps/sw-agent
COPY ./base-image/prom-agent /data/apps/prom-agent
EOF
  # docker push $mirror/$1:$2
  echo "结束打包..."$1:$2
}

buildAndPush base-java 1.0.0
