FROM openjdk:8
ADD target/RestaurantSimulator-master-1.0-SNAPSHOT.jar restaurantSimulator.jar
EXPOSE 5050
ENTRYPOINT ["java", "-jar", "restaurantSimulator.jar"]