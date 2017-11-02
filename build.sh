#!/usr/bin/env bash

VERSION="1.1.1-release"

AUTO_MAN_BIN="${BASH_SOURCE-$0}"
AUTO_MAN_BIN="$(dirname "${AUTO_MAN_BIN}")"
AUTO_MAN_BIN_DIR="$(cd "${AUTO_MAN_BIN}"; pwd)"

cd ${AUTO_MAN_BIN_DIR}

mvn clean install -U -DskipTests

mkdir -p "${AUTO_MAN_BIN_DIR}/dist"

STARTUP_DIR="$AUTO_MAN_BIN_DIR/auto-man-startup"
cd ${STARTUP_DIR}
mvn clean assembly:assembly -DskipTests

cp -rf ${STARTUP_DIR}/target/auto-man-${VERSION}-bin.zip  "${AUTO_MAN_BIN_DIR}/dist"
