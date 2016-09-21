#!/usr/bin/env bash

HOME="${BASH_SOURCE-$0}"
HOME="$(dirname "${HOME}")"
HOME="$(cd "${HOME}"; pwd)"

AUTHOR_NAME="AUTO-MAN_"
mkdir -p $HOME/project
PROJECT_BASE="$(cd "${HOME}/project/"; pwd)"

getopts "p:a:d" opt
case ${opt} in
    p) PACKAGE_NAME=$OPTARG
	;;
	a) if [ ! -n "$OPTARG" ]
	   then
	     AUTHOR_NAME="AUTO-MAN_"
	   else
	     AUTHOR_NAME=$OPTARG
	   fi
	;;
	d) if [ ! -n "$OPTARG" ]
	   then
	     PROJECT_BASE="$(cd "${HOME}/project/"; pwd)"
	   else
	     PROJECT_BASE=$OPTARG
	   fi
	;;
	?) echo "argument invalid!"
	   exit 1
	;;
esac

if [ ! -n "$PACKAGE_NAME" ]
    then
    echo "-p arg package name not be null!"
    exit 1
fi

if [ ! -n "$AUTHOR_NAME" ]
    then
    echo "-a arg author name not be null!"
    exit 1
fi

echo "Project Package Name : $PACKAGE_NAME"
echo "Project Author : $AUTHOR_NAME"
echo "Project Base : $PROJECT_BASE"

echo "Application building..."
OLD_IFS="$IFS"
IFS="."
PACKAGE_SPLIT_ARR=(${PACKAGE_NAME})
IFS="$OLD_IFS"

for s in ${PACKAGE_SPLIT_ARR[*]}
do
	PACKAGE_BASE_DIR="$PACKAGE_BASE_DIR/$s"
done

echo "package base dir : $PACKAGE_BASE_DIR"

PACKAGE_SPLIT_LEN=${#PACKAGE_SPLIT_ARR[@]}

if [ ${PACKAGE_SPLIT_LEN} == 1 ]
    then
    PROJECT_LAYER="${PACKAGE_SPLIT_ARR[0]}"
    PROJECT_NAME="${PACKAGE_SPLIT_ARR[0]}"
elif [ ${PACKAGE_SPLIT_LEN} -ge 2 ]
    then
    PROJECT_LAYER="${PACKAGE_SPLIT_ARR[$PACKAGE_SPLIT_LEN - 2]}"
    PROJECT_NAME="${PACKAGE_SPLIT_ARR[$PACKAGE_SPLIT_LEN - 1]}"
else
    echo "Package name invalid!"
    exit 1
fi

echo "project layer : $PROJECT_LAYER"
echo "project name : $PROJECT_NAME"



PROJECT_HOME="${PROJECT_BASE}/${PROJECT_LAYER}-${PROJECT_NAME}"

mkdir -p ${PROJECT_HOME}

CORE_LAYER_DIR="$PROJECT_HOME/$PROJECT_NAME-core"
INFRA_LAYER_DIR="$PROJECT_HOME/$PROJECT_NAME-infra"
APPLICATION_LAYER_DIR="$PROJECT_HOME/$PROJECT_NAME-application"
FACADE_LAYER_DIR="$PROJECT_HOME/$PROJECT_NAME-facade"
FACADE_IMPL_LAYER_DIR="$PROJECT_HOME/$PROJECT_NAME-facade-impl"

#create parent pom
cp ./resources/parent-pom.xml $PROJECT_HOME/pom.xml

# create core
mkdir -p ${CORE_LAYER_DIR}
cp ./resources/sub-module-pom.xml ${CORE_LAYER_DIR}/pom.xml
# java
mkdir -p ${CORE_LAYER_DIR}/src/main/java$PACKAGE_BASE_DIR/core/domain
mkdir -p ${CORE_LAYER_DIR}/src/main/java$PACKAGE_BASE_DIR/core/repository
mkdir -p ${CORE_LAYER_DIR}/src/main/java$PACKAGE_BASE_DIR/core/exception
mkdir -p ${CORE_LAYER_DIR}/src/main/java$PACKAGE_BASE_DIR/core/utils
mkdir -p ${CORE_LAYER_DIR}/src/main/java$PACKAGE_BASE_DIR/core/vo
mkdir -p ${CORE_LAYER_DIR}/src/main/java$PACKAGE_BASE_DIR/core/constants
# resources
mkdir -p ${CORE_LAYER_DIR}/src/main/resources/spring/
cp ./resources/core/core-context.xml ${CORE_LAYER_DIR}/src/main/resources/spring/
cp ./resources/core/root-core.xml ${CORE_LAYER_DIR}/src/main/resources/
# create infra
mkdir -p ${CORE_LAYER_DIR}/src/main/java$PACKAGE_BASE_DIR/sql
mkdir -p ${CORE_LAYER_DIR}/src/main/resources/mybatis/mapper
