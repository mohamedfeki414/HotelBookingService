From openjdk:17
copy ./target/HotelBookingService-0.0.1-SNAPSHOT.jar HotelBookingService-0.0.1-SNAPSHOT.jar 
CMD ["java","-jar","HotelBookingService-0.0.1-SNAPSHOT.jar"] 