# Smart Farms 

## Introduction
This project is part of the SENG6110 Programming Assignment 2, Trimester 1 2024 at the University of Newcastle. It extends the implementation of Assignment 1 by adding functionalities to manage farms using arrays and external files.

## Objective
The objective of this assignment is to develop a program that can manage up to 3 farms, each with up to 4 types of sensors. The program provides a menu of actions to manage farms and sensors, including adding, removing, querying, and exporting data.

## Specifications
The program should provide the following functionalities:
1. Add a farm.
2. Remove a farm.
3. Add a sensor to a farm.
4. Remove multiple sensor items at once.
5. Query for a list of farms.
6. Query for a list of sensors in a farm.
7. Query about a sensor's presence in the farms.
8. Query for the cumulative value of all sensors in a farm.
9. Export farm and sensor information to a file.
10. Exit the program.

## Program Requirements
The program consists of three classes:
- **Sensor**: Manages sensor details such as type, price, weight, and quantity.
- **Farm**: Manages farm details including the farm's name and its sensors.
- **Interface**: Provides the user interface to interact with the program.

## Classes and Methods
### Sensor Class
- **Attributes**:
  - `type`: String
  - `price`: double
  - `weight`: double
  - `quantity`: int

### Farm Class
- **Attributes**:
  - `name`: String
  - `sensors`: Array of Sensor

### Interface Class
- **Attributes**:
  - `farms`: Array of Farm

- **Methods**:
  - `run()`: Displays the menu to the user and handles user input.

## Usage Instructions
1. **Adding a Farm**: Specify the farm's name.
2. **Removing a Farm**: Specify the farm's name.
3. **Adding a Sensor**: Specify sensor details and the farm's name.
4. **Removing Sensors**: Specify the sensor type, farm name, and quantity.
5. **Querying Farms**: Lists all farms alphabetically.
6. **Querying Sensors in a Farm**: Specify the farm's name.
7. **Querying Sensor Presence**: Specify the sensor's name.
8. **Querying Cumulative Value**: Specify the farm's name.
9. **Exporting Data**: Export farm and sensor information to a file.

## Example Output
- **Adding a Farm**: `Farm <Farmname> added`
- **Removing Sensors**: `<numRemoved> items of Sensor <sensorType> removed from farm <farmName>`
- **Querying Farms**: `Farm <Farmname> has <number> sensors`
- **Querying Cumulative Value**: `Farm <Farmname> has cumulative sensor value $<number>`

## Installation
1. Clone the repository: `git clone <repository_url>`
2. Navigate to the project directory.
3. Compile the Java files: `javac *.java`
4. Run the program: `java Interface`# Smart-Farm-Uni-Newcastle
