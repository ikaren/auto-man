#!/usr/bin/env bash

#================================================================================
# Auto-Man script by Jason <xiayuqing123@hotmail.com>
# auto build maven project with DDD.
# https://github.com/xiayuqing
# $Id$
#================================================================================

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
cp ./resources/core/core-pom.xml ${CORE_LAYER_DIR}/pom.xml
# java
mkdir -p ${CORE_LAYER_DIR}/src/main/java$PACKAGE_BASE_DIR/core/domain
mkdir -p ${CORE_LAYER_DIR}/src/main/java$PACKAGE_BASE_DIR/core/repository
mkdir -p ${CORE_LAYER_DIR}/src/main/java$PACKAGE_BASE_DIR/core/exception
mkdir -p ${CORE_LAYER_DIR}/src/main/java$PACKAGE_BASE_DIR/core/utils
mkdir -p ${CORE_LAYER_DIR}/src/main/java$PACKAGE_BASE_DIR/core/vo
mkdir -p ${CORE_LAYER_DIR}/src/main/java$PACKAGE_BASE_DIR/core/constants
# resources
mkdir -p ${CORE_LAYER_DIR}/src/main/resources/spring/
cp ./resources/module-context.xml ${CORE_LAYER_DIR}/src/main/resources/spring/core-context.xml
cp ./resources/core/root-core.xml ${CORE_LAYER_DIR}/src/main/resources/

# create infra
mkdir -p ${INFRA_LAYER_DIR}
cp ./resources/infra/infra-pom.xml ${INFRA_LAYER_DIR}/pom.xml
# java
mkdir -p ${INFRA_LAYER_DIR}/src/main/java$PACKAGE_BASE_DIR/infra/sql
# resources
mkdir -p ${INFRA_LAYER_DIR}/src/main/resources/mybatis/mapper
cp ./resources/infra/db-mybatis.xml ${INFRA_LAYER_DIR}/src/main/resources/mybatis/
cp ./resources/infra/persistence-context.xml ${INFRA_LAYER_DIR}/src/main/resources/mybatis/
mkdir -p ${INFRA_LAYER_DIR}/src/main/resources/props/
cp ./resources/infra/database.properties ${INFRA_LAYER_DIR}/src/main/resources/props/
mkdir ${INFRA_LAYER_DIR}/src/main/resources/spring/
cp ./resources/module-context.xml ${INFRA_LAYER_DIR}/src/main/resources/spring/infra-context.xml
cp ./resources/infra/root-infra.xml ${INFRA_LAYER_DIR}/src/main/resources/
# other env
mkdir -p ${INFRA_LAYER_DIR}/src/main/resources.alpha/props/
cp ./resources/infra/database.properties ${INFRA_LAYER_DIR}/src/main/resources.alpha/props/
mkdir -p ${INFRA_LAYER_DIR}/src/main/resources.beta/props/
cp ./resources/infra/database.properties ${INFRA_LAYER_DIR}/src/main/resources.beta/props/
mkdir -p ${INFRA_LAYER_DIR}/src/main/resources.dev/props/
cp ./resources/infra/database.properties ${INFRA_LAYER_DIR}/src/main/resources.dev/props/
mkdir -p ${INFRA_LAYER_DIR}/src/main/resources.prod/props/
cp ./resources/infra/database.properties ${INFRA_LAYER_DIR}/src/main/resources.prod/props/

# create application
mkdir -p ${APPLICATION_LAYER_DIR}
cp ./resources/application/application-pom.xml ${APPLICATION_LAYER_DIR}/pom.xml
# java 
mkdir -p ${APPLICATION_LAYER_DIR}/src/main/java${PACKAGE_BASE_DIR}/application/impl
# resources
mkdir -p ${APPLICATION_LAYER_DIR}/src/main/resources/spring/
cp ./resources/module-context.xml ${APPLICATION_LAYER_DIR}/src/main/resources/spring/application-context.xml
cp ./resources/application/root-application.xml ${APPLICATION_LAYER_DIR}/src/main/resources/

# create facade
mkdir -p ${FACADE_LAYER_DIR}
cp ./resources/facade/facade-pom.xml ${FACADE_LAYER_DIR}/pom.xml
# java 
mkdir -p ${FACADE_LAYER_DIR}/src/main/java${PACKAGE_BASE_DIR}/facade/dto

# create facade-impl
mkdir -p ${FACADE_IMPL_LAYER_DIR}
cp ./resources/facade/impl/facade-impl-pom.xml ${FACADE_IMPL_LAYER_DIR}/pom.xml
cp ./resources/facade/impl/assembly.xml ${FACADE_IMPL_LAYER_DIR}/
# java
mkdir -p ${FACADE_IMPL_LAYER_DIR}/src/main/java${PACKAGE_BASE_DIR}/facade/impl/assembler
mkdir -p ${FACADE_IMPL_LAYER_DIR}/src/main/java${PACKAGE_BASE_DIR}/facade/impl/interaction
# resources
mkdir -p ${FACADE_IMPL_LAYER_DIR}/src/main/META-INF/spring
cp ./resources/facade/impl/root.xml ${FACADE_IMPL_LAYER_DIR}/src/main/META-INF/spring

