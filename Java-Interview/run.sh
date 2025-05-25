SERVICE_NAME=altruistbusinessreport-api # service name
PATH_TO_JAR=/opt/AltruistBusinessReport/backend/AltruistReport-0.0.1-SNAPSHOT.jar # service jar file
PID_PATH_NAME=/tmp/altruistbusinessreport-api_server-pid # pid name
MIN_MEMORY=512m
MAX_MEMORY=1024m
case $1 in
    start)
        echo "Starting $SERVICE_NAME ..."
        if [ ! -f $PID_PATH_NAME ]; then
            #nohup java -jar -Dspring.profiles.active=dev $PATH_TO_JAR /tmp 2>> /dev/null >> /dev/null & # -Dspring.profiles.active=dev
                         nohup java  -Xms$MIN_MEMORY -Xmx$MAX_MEMORY -jar $PATH_TO_JAR --spring.config.location=file:/opt/AltruistBusinessReport/backend/application.properties &>/dev/null >>nohup.out &
            echo $! > $PID_PATH_NAME
            echo "$SERVICE_NAME started ..."
        else
            echo "$SERVICE_NAME is already running ..."
        fi
    ;;
    stop)
        if [ -f $PID_PATH_NAME ]; then
            PID=$(cat $PID_PATH_NAME);
            echo "$SERVICE_NAME stoping with PID..."$PID;
            kill $PID;
            echo "$SERVICE_NAME stopped ..."
            rm $PID_PATH_NAME
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;

        status)
        if [ -f $PID_PATH_NAME ]; then
            PID=$(cat $PID_PATH_NAME);
            echo "$SERVICE_NAME running with PID ..."$PID;
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;

    restart)
        if [ -f $PID_PATH_NAME ]; then
            PID=$(cat $PID_PATH_NAME);
            echo "$SERVICE_NAME stopping with PID..."$PID;
            kill $PID;
            echo "$SERVICE_NAME stopped ...";
            rm $PID_PATH_NAME
            echo "$SERVICE_NAME starting ..."
            nohup java -Xms$MIN_MEMORY -Xmx$MAX_MEMORY -jar $PATH_TO_JAR --spring.config.location=file:/opt/AltruistBusinessReport/backend/application.properties &>/dev/null >>nohup.out & # -Dspring.profiles.active=dev
            echo $! > $PID_PATH_NAME
                        PID=$(cat $PID_PATH_NAME);
            echo "$SERVICE_NAME started with PID..."$PID;
        else
            echo "$SERVICE_NAME is not running ..."
                        echo "$SERVICE_NAME starting ..."
           nohup java -Xms$MIN_MEMORY -Xmx$MAX_MEMORY -jar $PATH_TO_JAR --spring.config.location=file:/opt/AltruistBusinessReport/backend/application.properties &>/dev/null >>nohup.out &# -Dspring.profiles.active=dev
            echo $! > $PID_PATH_NAME
                        PID=$(cat $PID_PATH_NAME);
            echo "$SERVICE_NAME started with PID..."$PID;
        fi
    ;;
esac

