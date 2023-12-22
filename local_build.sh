#!/usr/bin/env bash

projDir=$(pwd)

sbt dist
cd "$projDir"/target/universal || exit
unzip ./*.zip