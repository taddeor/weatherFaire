#!/usr/bin/env bash
#DIR="$( dirname $0 )"
SCRIPT=$0
APPLICATION_NAME=$1
if [ -z $SERVER_PORT ]; then
    SERVER_PORT=$2
fi

COMMAND=$3

PID_PATH=/tmp/$APPLICATION_NAME.pid


# https://security.stackexchange.com/questions/14386/what-do-i-need-to-configure-to-make-sure-my-software-uses-dev-urandom
# http://ruleoftech.com/2016/avoiding-jvm-delays-caused-by-random-number-generation
export JAVA_OPTS="$JAVA_OPTS -Djava.security.egd=file:/dev/./urandom"

# Setup the JVM
if [ "x$JAVA" = "x" ]; then
    if [ "x$JAVA_HOME" != "x" ]; then
        JAVA="$JAVA_HOME/bin/java"
    else
        JAVA="java"
    fi
fi


docker() {
	echo "Start $APPLICATION_NAME"

    detectServerHost

	export JAVA_OPTS="$JAVA_OPTS -Dserver.host=$SERVER_HOST"
	export JAVA_OPTS="$JAVA_OPTS -Dserver.port=$SERVER_PORT"
	[ -n "$JAVA_MS_OPT" ] && export JAVA_OPTS="$JAVA_OPTS -Xms${JAVA_MS_OPT}m"
    [ -n "$JAVA_MX_OPT" ] && export JAVA_OPTS="$JAVA_OPTS -Xmx${JAVA_MX_OPT}m"
    [ -n "$JAVA_PERMSIZE_OPT" ] && export JAVA_OPTS="$JAVA_OPTS -XX:MetaspaceSize=${JAVA_PERMSIZE_OPT}m"
    [ -n "$JAVA_MAXPERMSIZE_OPT" ] && export JAVA_OPTS="$JAVA_OPTS -XX:MaxMetaspaceSize=${JAVA_MAXPERMSIZE_OPT}m"
    [ -n "$ENV_JAVA_OPTIONS" ] && export JAVA_OPTS="$JAVA_OPTS ${ENV_JAVA_OPTIONS}"
    echo "JAVA_OPTS: $JAVA_OPTS "
	exec $JAVA -jar $JAVA_OPTS $APPLICATION_NAME.jar
}



detectServerHost(){
    if [ -z $SERVER_HOST ]; then
        echo "NETW_INTERFACE: $NETW_INTERFACE";

        if [ -z $NETW_INTERFACE ]; then
            echo "use eth0 network interface";
            export SERVER_HOST="$(ip addr show dev eth0 |grep "inet "| awk '{print $NF"="$2 }' | awk -F\/ '{print $1 }'  ORS=',')"
        else
            if [[ "$NETW_INTERFACE" == "all" ]]; then
                echo "use all network interfaces";
                export SERVER_HOST="$(ip route | grep -v default | awk -F ' ' '{print $3"="$1}' | awk -F\/ '{print $1 }'  ORS=',')"
            else
                if [[ "$NETW_INTERFACE" == "hostname" ]]; then
                    echo "use hostname";
                    export SERVER_HOST="hostname=$(hostname)"
                else
                    echo "use $NETW_INTERFACE network interface";
                    export SERVER_HOST="$(ip addr show dev $NETW_INTERFACE |grep "inet "| awk '{print $NF"="$2 }' | awk -F\/ '{print $1 }'  ORS=',')"
                fi
            fi
        fi
    fi

    if [ -z $SERVER_HOST ]; then
        echo "server host not found, use eth0 network interface";
        export SERVER_HOST="$(ip addr show dev eth0 |grep "inet "| awk '{print $NF"="$2 }' | awk -F\/ '{print $1 }'  ORS=',')"
    fi


    echo "SERVER_HOST: $SERVER_HOST"
}





showUsage() {
	echo "Usage: $SCRIPT [docker]"
}

case $COMMAND in


    docker )
        docker;;

    * )
        showUsage;;
esac

echo ""

