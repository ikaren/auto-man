#!/usr/bin/env bash

AUTO_MAN_BIN="${BASH_SOURCE-$0}"
AUTO_MAN_BIN="$(dirname "${AUTO_MAN_BIN}")"
AUTO_MAN_BIN_DIR="$(cd "${AUTO_MAN_BIN}/auto-man-parent"; pwd)"

cd ${AUTO_MAN_BIN_DIR}

mvn clean install -U -DskipTests

DIST_BIN_DIR="$AUTO_MAN_BIN_DIR/../dist"
mkdir -p ${DIST_BIN_DIR}

DIST_BIN_DIR="$(cd "$(dirname "${DIST_BIN_DIR}/.")"; pwd)"

# 打包
STARTUP_DIR="$AUTO_MAN_BIN_DIR/auto-man-startup/"

mvn clean assembly:assembly -DskipTests -Pdefault

cp -rf ${STARTUP_DIR}/target/auto-man-v*  ${DIST_BIN_DIR}
