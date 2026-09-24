# world-country-monitoring
backend module

How to start:

1. install Gradle 
    ubuntu ``sudo apt install gradle -y``
2. install java 21, and set in project
3. reload project as Gradle, click on Gradle file, run command in terminal ``gradle build``
4. start postgres in docker file or with command ``docker-compose up``
5. start application in file org.wcm.infrastructure.Application

After you can fill database by scheduler controller in swagger
http://localhost:8080/swagger-ui/index.html

## Деплой

Бэкенд деплоится как отдельный Docker Compose-проект (PostgreSQL + приложение).
Фронтенд — отдельный проект, он ходит в API через общую Docker-сеть `wcm-net`.

```bash
# один раз на сервере
docker network create wcm-net
git clone https://github.com/dabuldakov/world-country-monitoring.git ~/world-country-monitoring-backend
cd ~/world-country-monitoring-backend
cp .env.example .env   # заполнить пароль
docker compose up -d --build
```

Приложение слушает контейнерный порт `8080`, наружу не публикуется — доступ идёт
через nginx фронтенда по адресу `http://backend:8080`.

### Автодеплой

При push в `main` GitHub Actions по SSH заходит на сервер и пересобирает проект
(как в репозитории `makeup`):

```bash
cd "$DEPLOY_PATH"
git fetch --prune origin main && git reset --hard origin/main
docker compose up -d --build
```

Workflow — `.github/workflows/deploy.yml` (запуск вручную: Actions → deploy → Run workflow).

Секреты репозитория (Settings → Secrets and variables → Actions → Secrets):

| Секрет | Значение |
|--------|----------|
| `DEPLOY_HOST` | `90.188.89.63` |
| `DEPLOY_USER` | `dmitry_buldakov` |
| `DEPLOY_PORT` | `2222` |
| `DEPLOY_SSH_KEY` | приватный ключ `~/.ssh/deploy_ci_ed25519` |
| `DEPLOY_PATH` | `/home/dmitry_buldakov/world-country-monitoring-backend` |