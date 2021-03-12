# Clio Project
Clio is a CRUD-type application that supports the management of home orders for the company "Cubanos", which is a fast food restaurant.
This application solves the problem of having to request the necessary data to take a home order every time a customer calls to place an order. The necessary data are: 
- Name.
- Direction.
- Phone.
- Floor (where the client lives if it is necessary).
- Other indications (Some clients have particular specifications). 

The task of requesting this data each time a customer calls takes considerable time, so Clio was initially thought of. With Clio the customer only has to give the data the first time he calls.

## Technologic stack 
The application is developed with Java and MySQL. In java the application logic and the interface are implemented, with MySQL the customer data is managed. The application is divided into 4 layers organized as follows: 
- Business: Is the layer where is the logic of the application implemented.
- Data: In this layer the connection to the database is made and it is in charge of doing the CRUD operations with SQL queries.
- Print: In charge of connecting with the printer and printing commands.
- Interface: Show the data in the screen and interact with the user.

MySQL is implemented by PHPmyAdmin locally with XAMPP. It was verified that the connection credentials between the database and the application were properly provided, and that the names of the tables for the queries are consistent.

## The interface

![Captura de pantalla de 2021-03-02 17-28-33](https://user-images.githubusercontent.com/43974127/109732668-9bd48980-7b8b-11eb-9fb2-2bfce295c53d.png)

As you can see, each button presents the ability to do the mentioned CRUD operations and an additional short menu to configure the connection to the database.

## License
This is an open source initiative under [MIT license](https://opensource.org/licenses/MIT).

