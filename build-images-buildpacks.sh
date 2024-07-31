

cd multiplication
./mvnw spring-boot:build-image
cd ..

cd gamification
./mvnw spring-boot:build-image
cd ..

cd logs
./mvnw spring-boot:build-image
cd ..

cd gateway
./mvnw spring-boot:build-image
cd ..

cd challenges-frontend
docker build -t challenges-frontend:1.0 .
cd ..