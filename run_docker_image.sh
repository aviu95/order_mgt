docker-compose -f docker-compose.yml down

echo "Building the service.."
./gradlew build -x test

docker stop orders

docker rm orders

docker build -f Dockerfile -t orders .


echo "starting the service"
docker-compose -f docker-compose.yml -p ecommerce_service  up
