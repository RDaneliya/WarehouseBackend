# WarehouseBackend

## Деплой проекта

1. Склонировать репозиторий
2. Прописать _docker-compose build --no-cache_
3. Прописать _docker-compose up_

## Пересборка проекта

1. Убить все запущенные контейнеры проекта
2. Прописать _docker volume prune_
3. Прописать docker system prune
4. Прописать _docker-compose build --no-cache_
5. Прописать _docker-compose up_