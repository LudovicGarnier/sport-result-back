# sport-result-back

# START Docker compose
docker compose -f ./docker-compose.yml up

# STOP Docker compose
docker-compose down

# GET Docker images
docker images

# Delete Docker images
docker rm -f {container_id}
docker rmi -f {container_id1} {container_id2}