# Используйте базовый образ с OpenJDK 21
# FROM openjdk:21-jdk-slim AS build
FROM nicb-registry.gts.rus.socgen/nicb/alpine-openjdk-21-gcompat:5 AS build

# Установите рабочую директорию
WORKDIR /app

# Копируйте файлы Gradle и проект
COPY . .

# Сделайте Gradle исполняемым
RUN chmod +x gradlew

# Соберите приложение
RUN ./gradlew build --no-daemon

# Запуск конечного образа
# FROM openjdk:21-jdk-slim
FROM nicb-registry.gts.rus.socgen/nicb/alpine-openjdk-21-gcompat:5

# Установите рабочую директорию
WORKDIR /app

# Скопируйте скомпилированное приложение из стадии сборки
# COPY /infrastructure/build/libs/infrastructure.jar world-country-monitoring.jar
COPY /build/libs/world-country-monitoring.jar world-country-monitoring.jar

# Укажите команду для запуска приложения
ENTRYPOINT ["java", "-jar", "world-country-monitoring.jar"]
