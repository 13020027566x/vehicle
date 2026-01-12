#!/bin/bash

echo " ------------ 停止所有容器 ------------ ";
docker container stop $(docker container ls -q)

echo " ------------ 删除所有容器 ------------ ";
docker container rm $(docker container ls -a -q)

echo " ------------ 删除所有镜像 ------------ ";
docker image rm $(docker image ls -q)
