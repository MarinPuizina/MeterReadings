# MeterReadings

It is a SpringBoot application which has 3 GET and 1 POST REST API.   
It has h2 database for data persistence.   
When building applications, I usually go with this architecture.   

![alt text](https://imgur.com/Ggl0qth.jpg)   
I like using the DTO pattern to seperate the communication between the layers.   
It also helps with testability of the code.

The application should be working without any additional setup. 

**DATABASE**   
Endpoint to connect to h2 database is http://localhost:8080/h2-console    
Database user name is "sa" and password is not needed.    

Your H2 databse setup should look this to be able to connect to embedded database.

![alt text](https://i.imgur.com/zS6NbG8.jpg)   


I have inserted some data to be able to test the application.   
If you feel you need to add more data, feel free to edit this file:   
https://github.com/MarinPuizina/MeterReadings/blob/master/src/main/resources/data-h2.sql 

**Potential  issues**    
The only potential issues that I can think of, is with the H2 dependency.   
In the pom.xml file:   
https://github.com/MarinPuizina/MeterReadings/blob/master/pom.xml
   
I am using specific version for H2.

![alt text](https://imgur.com/oFqSkOJ.jpg)


You can try removing the version and let Spring decide which one it is going to use. 

# REQUEST SAMPLES FOR TESTING THE APP

**GET**  
1) http://localhost:8080/readings/addresses/Ulica 7/years/2019   
2) http://localhost:8080/readings/addresses/Ulica 7/years/2019/months  
3) http://localhost:8080/readings/addresses/Marmontova/years/2019/months/September 

**POST**   
1) http://localhost:8080/readings   
body:   
{
	"client": "Marin Puizina",
	"address": "Ulica 7",
	"year": 2019,
    "month": "November",
    "reading": 7
}
