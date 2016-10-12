#!/usr/bin/env bash
#================================================================================
# Auto-Man script by Jason <xiayuqing123@hotmail.com>
# auto build java project with DDD.
# https://github.com/xiayuqing
#
#================================================================================
APPLICATION_HOME="${BASH_SOURCE-$0}"
APPLICATION_HOME="$(dirname "${APPLICATION_HOME}")"
APPLICATION_HOME="$(cd "${APPLICATION_HOME}"; pwd)"

APPLICATION_BASE="$(cd "${APPLICATION_HOME}/../"; pwd)"
PROJECT_XML_HOME="$(cd "${APPLICATION_HOME}/../xml"; pwd)"
TEMPLATE_HOME="$(cd "${APPLICATION_HOME}/../template"; pwd)"

JVM_OPTS="-Dfile.encoding=UTF-8"

if [ "$JAVA_HOME" != "" ]; then
  JAVA="$JAVA_HOME/bin/java"
else
  JAVA=java
fi

for i in "$APPLICATION_HOME"/../lib/*.jar
do
  CLASSPATH="$i:$CLASSPATH"
done

APPLICATION_MAIN="org.jason.automan.startup.AutoManBoot"

echo "Auto-Man Starting ..."
"$JAVA" -cp "$CLASSPATH" $JVM_OPTS $APPLICATION_MAIN "${PROJECT_XML_HOME}" "${TEMPLATE_HOME}"
