FROM nicb-registry.gts.rus.socgen/nicb/alpine-openjdk-21-gcompat:5

WORKDIR /app
COPY /infrastructure/build/libs/infrastructure.jar world-country-monitoring.jar

ENTRYPOINT exec java $JAVA_OPTS -jar world-country-monitoring.jar
