#!/usr/bin/env bash

AMPS_VERSION="8.7.0" # This version overrides whatever you have set in the pom.xml, but better if they are aligned
JDK_REQUIRED_VERSION="11"
APP_NAME="Confluence Sample App"
CONFLUENCE_VERSION=""
ADDITIONAL_MAVEN_PARAMS=""
JVM_ARGS="--jvmargs \"-Xmx4G\""
ARCHITECTURE=$(arch)

help () {
            echo
            echo "Usage: start-app.sh [options]"
            echo
            echo "Runs the ${APP_NAME} app on Confluence with version x.y.z. If not version provided then minimum supported one will be used."
            echo
            echo "The following options are available:"
            echo "x.y.z     Confluence version"
            echo "-a        Add additional Maven parameters"
            echo "-d        Remove development flag. For instance to test licensing"
            echo
            exit
}

while [ $# -gt 0 ]
do
    case "$1" in
        [0123456789]*)
            CONFLUENCE_VERSION=$1
            shift 1;;
        "-a")
            ADDITIONAL_MAVEN_PARAMS=$2
            shift 2;;
        "-d")
            APP_DEVELOPMENT_FLAGS=""
            shift 1;;
        "-?" | "-h" | "--help" | "-help" | "help")
            help;;
        *)
            shift 1
    esac
done

# Using specific Confluence version or default version in pom.xml
# shellcheck disable=SC2236
if [[ ! -z "$CONFLUENCE_VERSION" ]]
then
  echo "Preparing to run app $APP_NAME on Confluence version $CONFLUENCE_VERSION"
  CONFLUENCE_VERSION="-v $CONFLUENCE_VERSION"
else
  echo "Preparing to run app $APP_NAME on default Confluence version in pom.xml"
fi

# Check Java version available
JAVA_VERSION=$(java --version)
JAVA_VERSION_NUMBERS=(${JAVA_VERSION//./ })
JAVA_MAJOR_VERSION=${JAVA_VERSION_NUMBERS[1]}
if [ "$JAVA_MAJOR_VERSION" != $JDK_REQUIRED_VERSION ]
then
  echo "This app requires open JDK $JDK_REQUIRED_VERSION to run".
  exit 0
fi

# Set current folder to where the pom.xml is
cd "$(dirname "$0")"/..

if [[ ${ARCHITECTURE} == "arm64" ]]
 then
    export ATLAS_MVN="/opt/homebrew/bin/mvn"
  else
   export ATLAS_MVN="/usr/local/opt/maven/bin/mvn"
fi

# Build and execute atlas-debug command
ATLAS_COMMAND="atlas-debug $CONFLUENCE_VERSION -u $AMPS_VERSION --server localhost -DskipTests $JVM_ARGS"

if [[ ! -z "$ADDITIONAL_MAVEN_PARAMS" ]]
then
    ATLAS_COMMAND="$ATLAS_COMMAND $ADDITIONAL_MAVEN_PARAMS"
fi

echo "Executing ATLAS command: $ATLAS_COMMAND"
eval $ATLAS_COMMAND
echo ""
echo "Finished execution of ATLAS command: $ATLAS_COMMAND"
echo "**** TIPs ****"
echo " - You might want to manually execute atlas-clean command before this one if you have previously run the app on a different Confluence version."
echo " - When you quit this process please make sure you don't have java processes dangling in your system."
echo "**************"