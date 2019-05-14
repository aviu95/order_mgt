echo "Building inprogress..."
./gradlew build -x test

echo "Running the service.."

java -jar build/libs/order_mgt-0.0.1-SNAPSHOT.jar 

