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

while getopts "p:a:d" opt
do
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
done

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
sed -i .autobak 's/\${GROUP_ID}/'$PACKAGE_NAME'/g;s/\${PROJECT_NAME}/'$PROJECT_NAME'/g;s/\${ARTIFACT_ID}/'$PROJECT_NAME'/g;s/\${AUTHOR_NAME}/'$AUTHOR_NAME'/g' $PROJECT_HOME/pom.xml
rm $PROJECT_HOME/*.autobak

# create core
mkdir -p ${CORE_LAYER_DIR}
cp ./resources/core/core-pom.xml ${CORE_LAYER_DIR}/pom.xml
sed -i .autobak 's/\${GROUP_ID}/'$PACKAGE_NAME'/g;s/\${PROJECT_NAME}/'$PROJECT_NAME'/g;s/\${PARENT_ARTIFACT}/'$PROJECT_NAME'/g' $CORE_LAYER_DIR/pom.xml
rm $CORE_LAYER_DIR/*.autobak

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
sed -i .autobak 's/\${MODULE_BASE_PACKAGE}/'${PACKAGE_NAME}.core'/g' ${CORE_LAYER_DIR}/src/main/resources/spring/core-context.xml
rm ${CORE_LAYER_DIR}/src/main/resources/spring/*.autobak
cp ./resources/core/root-core.xml ${CORE_LAYER_DIR}/src/main/resources/

# create infra
mkdir -p ${INFRA_LAYER_DIR}
cp ./resources/infra/infra-pom.xml ${INFRA_LAYER_DIR}/pom.xml
sed -i .autobak 's/\${GROUP_ID}/'$PACKAGE_NAME'/g;s/\${PROJECT_NAME}/'$PROJECT_NAME'/g;s/\${PARENT_ARTIFACT}/'$PROJECT_NAME'/g' $INFRA_LAYER_DIR/pom.xml
rm $INFRA_LAYER_DIR/*.autobak
# java
mkdir -p ${INFRA_LAYER_DIR}/src/main/java$PACKAGE_BASE_DIR/infra/sql
# resources
mkdir -p ${INFRA_LAYER_DIR}/src/main/resources/mybatis/mapper
cp ./resources/infra/db-mybatis.xml ${INFRA_LAYER_DIR}/src/main/resources/mybatis/
sed -i .autobak 's/\${MAPPER_PACKAGE}/'${PACKAGE_NAME}.infra.sql'/g' ${INFRA_LAYER_DIR}/src/main/resources/mybatis/db-mybatis.xml
rm ${INFRA_LAYER_DIR}/src/main/resources/mybatis/*.autobak
cp ./resources/infra/persistence-context.xml ${INFRA_LAYER_DIR}/src/main/resources/mybatis/
mkdir -p ${INFRA_LAYER_DIR}/src/main/resources/props/
cp ./resources/infra/database.properties ${INFRA_LAYER_DIR}/src/main/resources/props/
mkdir ${INFRA_LAYER_DIR}/src/main/resources/spring/
cp ./resources/module-context.xml ${INFRA_LAYER_DIR}/src/main/resources/spring/infra-context.xml
sed -i .autobak 's/\${MODULE_BASE_PACKAGE}/'${PACKAGE_NAME}.infra'/g' ${INFRA_LAYER_DIR}/src/main/resources/spring/infra-context.xml
rm ${INFRA_LAYER_DIR}/src/main/resources/spring/*.autobak
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
sed -i .autobak 's/\${GROUP_ID}/'$PACKAGE_NAME'/g;s/\${PROJECT_NAME}/'$PROJECT_NAME'/g;s/\${PARENT_ARTIFACT}/'$PROJECT_NAME'/g' $APPLICATION_LAYER_DIR/pom.xml
rm $APPLICATION_LAYER_DIR/*.autobak
# java 
mkdir -p ${APPLICATION_LAYER_DIR}/src/main/java${PACKAGE_BASE_DIR}/application/impl
# resources
mkdir -p ${APPLICATION_LAYER_DIR}/src/main/resources/spring/
cp ./resources/module-context.xml ${APPLICATION_LAYER_DIR}/src/main/resources/spring/application-context.xml
sed -i .autobak 's/\${MODULE_BASE_PACKAGE}/'${PACKAGE_NAME}.infra'/g' ${APPLICATION_LAYER_DIR}/src/main/resources/spring/application-context.xml
rm ${APPLICATION_LAYER_DIR}/src/main/resources/spring/*.autobak
cp ./resources/application/root-application.xml ${APPLICATION_LAYER_DIR}/src/main/resources/

# create facade
mkdir -p ${FACADE_LAYER_DIR}
cp ./resources/facade/facade-pom.xml ${FACADE_LAYER_DIR}/pom.xml
sed -i .autobak 's/\${GROUP_ID}/'$PACKAGE_NAME'/g;s/\${PROJECT_NAME}/'$PROJECT_NAME'/g;s/\${PARENT_ARTIFACT}/'$PROJECT_NAME'/g' $FACADE_LAYER_DIR/pom.xml
rm $FACADE_LAYER_DIR/*.autobak
# java 
mkdir -p ${FACADE_LAYER_DIR}/src/main/java${PACKAGE_BASE_DIR}/facade/dto

# create facade-impl
mkdir -p ${FACADE_IMPL_LAYER_DIR}
cp ./resources/facade/impl/facade-impl-pom.xml ${FACADE_IMPL_LAYER_DIR}/pom.xml
cp ./resources/facade/impl/assembly.xml ${FACADE_IMPL_LAYER_DIR}/
sed -i .autobak 's/\${GROUP_ID}/'$PACKAGE_NAME'/g;s/\${PROJECT_NAME}/'$PROJECT_NAME'/g;s/\${PARENT_ARTIFACT}/'$PROJECT_NAME'/g' $FACADE_IMPL_LAYER_DIR/pom.xml
rm $FACADE_IMPL_LAYER_DIR/*.autobak
# java
mkdir -p ${FACADE_IMPL_LAYER_DIR}/src/main/java${PACKAGE_BASE_DIR}/facade/impl/assembler
mkdir -p ${FACADE_IMPL_LAYER_DIR}/src/main/java${PACKAGE_BASE_DIR}/facade/impl/interaction
# resources
mkdir -p ${FACADE_IMPL_LAYER_DIR}/src/main/resources/META-INF/spring
cp ./resources/facade/impl/root.xml ${FACADE_IMPL_LAYER_DIR}/src/main/resources/META-INF/spring
mkdir -p ${FACADE_IMPL_LAYER_DIR}/src/main/resources/dubbo
cp ./resources/facade/impl/dubbo*.xml ${FACADE_IMPL_LAYER_DIR}/src/main/resources/dubbo/
sed -i .autobak 's/\${PROJECT_NAME}/'$PROJECT_NAME'/g;s/\${AUTHOR_NAME}/'$AUTHOR_NAME'/g' $FACADE_IMPL_LAYER_DIR/src/main/resources/dubbo/dubbo.xml
rm $FACADE_IMPL_LAYER_DIR/src/main/resources/dubbo/*.autobak
mkdir -p ${FACADE_IMPL_LAYER_DIR}/src/main/resources/props
cp ./resources/facade/impl/dubbo.properties ${FACADE_IMPL_LAYER_DIR}/src/main/resources/props/
sed -i .autobak 's/\${PROJECT_NAME}/'$PROJECT_LAYER-$PROJECT_NAME'/g' $FACADE_IMPL_LAYER_DIR/src/main/resources*/props/dubbo.properties
rm $FACADE_IMPL_LAYER_DIR/src/main/resources*/props/*.autobak
mkdir -p ${FACADE_IMPL_LAYER_DIR}/src/main/resources/spring
cp ./resources/module-context.xml ${FACADE_IMPL_LAYER_DIR}/src/main/resources/spring/facade-impl-context.xml
sed -i .autobak 's/\${MODULE_BASE_PACKAGE}/'${PACKAGE_NAME}.infra'/g' ${FACADE_IMPL_LAYER_DIR}/src/main/resources/spring/facade-impl-context.xml
rm ${FACADE_IMPL_LAYER_DIR}/src/main/resources/spring/*.autobak
cp ./resources/facade/impl/root-facade-impl.xml ${FACADE_IMPL_LAYER_DIR}/src/main/resources/
cp ./resources/facade/impl/log4j.properties ${FACADE_IMPL_LAYER_DIR}/src/main/resources/
# other env
mkdir -p ${FACADE_IMPL_LAYER_DIR}/src/main/resources.alpha/props/
cp ./resources/facade/impl/dubbo.properties ${FACADE_IMPL_LAYER_DIR}/src/main/resources.alpha/props/
mkdir -p ${FACADE_IMPL_LAYER_DIR}/src/main/resources.beta/props/
cp ./resources/facade/impl/dubbo.properties ${FACADE_IMPL_LAYER_DIR}/src/main/resources.beta/props/
mkdir -p ${FACADE_IMPL_LAYER_DIR}/src/main/resources.dev/props/
cp ./resources/facade/impl/dubbo.properties ${FACADE_IMPL_LAYER_DIR}/src/main/resources.dev/props/
mkdir -p ${FACADE_IMPL_LAYER_DIR}/src/main/resources.prod/props/
cp ./resources/facade/impl/dubbo.properties ${FACADE_IMPL_LAYER_DIR}/src/main/resources.prod/props/