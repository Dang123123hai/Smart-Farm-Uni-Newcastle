/*Author: Hai Dang Luong
*Student No: C3441308
*Date: 19/04/2024
*Description: This is the interface which contain private data and private method.
It will take input from users and return output.
*/
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import javax.swing.JOptionPane;

public class Interface {
	private int farmNum = 0;
	private Farm[] farms = new Farm[3];
	private String farmName;
	private String[] sensorTypeList = { "Temperature", "Pressure", "Humidity", "Soil Temperature", "Soil Humidity",
			"Soil PH" };
	private String t; // type
	private double p, w;// price, weight
	private int q; // quantity

	// method to validate if the sensor is in the sensor list.
	private boolean sensorVerified(String t) {
		for (int i = 0; i < sensorTypeList.length; i++) {
			if (sensorTypeList[i].equalsIgnoreCase(t)) {
				return true;
			}
		}
		return false;
	}

	// method to add "s" at the end if the number is plural
	private String pluralCheck(int n) {
		if (n > 1) {
			return "s";
		}
		return "";
	}

	// run() mthod
	private void run() {
		int actionNum;
		// add sensor info variable:
		// print action menu/
		actionNum = Integer.parseInt(JOptionPane.showInputDialog(
				"Please choose one action:\n1. Add a farm.\n2. Remove a farm.\n3. Add sensor to farm.\n4. Remove sensors from a farm.\n5. Query a list of farms.\n6. Query a list of sensors in a farm.\n7. Query about sensor's presence of farm.\n8. Query for sensors cumulative value of a farm.\n9. Export farms and sensors information file. \n10. Exit.\nPlease input action number: "));
		// while loop for case action number is different to 10
		while (actionNum != 10) {
			switch (actionNum) {
				case 1:
					// add a farm
					addFarm();
					break;
				case 2:
					// remove a farm
					removeFarm();
					break;
				case 3:
					// add a sensor to a farm
					addSensor();
					break;
				case 4:
					// remove a sensor of a farm
					removeSensor();
					break;
				case 5:
					// return a farm list with total quantity of sensor
					farmList();
					break;
				case 6:
					// return a sensor list with its data of a specific farm
					sensorList();
					break;
				case 7:
					// check a choice sensor if it exist in any farm
					sensorInfo();
					break;
				case 8:
					// Query the cumulative value of a farm
					farmValue();
					break;
				case 9:
					// Export Farms and Sensor info
					farmDataExport();
					break;
			}
			// ask to input the action number again if the input is others than 1-->10
			actionNum = Integer.parseInt(JOptionPane.showInputDialog(
					"Error: Wrong action number!\nPlease choose one action:\n1. Add a farm.\n2. Remove a farm.\n3. Add sensor to farm.\n4. Remove sensors from a farm.\n5. Query a list of farms.\n6. Query a list of sensors in a farm.\n7. Query about sensor's presence of farm.\n8. Query for sensors cumulative value of a farm.\n9. Export farms and sensors information file. \n10. Exit.\nPlease input action number: "));
		}
		// if input=10 end the program.
		JOptionPane.showMessageDialog(null, "Program ended. Have a great day!");
	};

	// add a farm method
	private void addFarm() {
		// case number of farms is maximum
		if (farmNum == 3) {
			JOptionPane.showMessageDialog(null, "Error: Farm quantity reach limit!");
			return;

		}
		// when number of farms is <3
		farmName = JOptionPane.showInputDialog("Please input name for the new farm");
		for (int i = 0; i < farmNum; i++) {
			// case farm name already exist
			if (farmName.equalsIgnoreCase(farms[i].getName())) {
				JOptionPane.showMessageDialog(null, "Error: Farm " + farmName + " have already existed!");
				return;
			}
		}
		// if farm name not exist--> add farm
		farmNum++;
		farms[farmNum - 1] = new Farm();
		farms[farmNum - 1].setName(farmName);
		JOptionPane.showMessageDialog(null, "Farm " + farmName + " has been successfully added");

	};

	// remove farm method
	private void removeFarm() {
		// case no farm to remove-->
		if (farmNum == 0) {
			JOptionPane.showMessageDialog(null, "Error: No farm to remove!");
			return;
		}
		// case farms exist--> ask for input farm name
		farmName = JOptionPane.showInputDialog("Please input name for the farm you want to remove!");
		for (int i = 0; i < farmNum; i++) {
			// if farm name is exist--> remove farm
			if (farmName.equalsIgnoreCase(farms[i].getName())) {
				for (int j = i; j < farmNum - 1; j++) {
					farms[j] = farms[j + 1];
				}
				;
				farmNum--;
				JOptionPane.showMessageDialog(null, "Farm " + farmName + " has been successfully removed!");
				return;
			}
		}
		// if farm name is wrong-->
		JOptionPane.showMessageDialog(null, "Error: Farm " + farmName + " does not exist");
	};

	// add sensor method
	// to deal with sensor i mainly used the method that i created in farm.java file
	// This will enhance the code flow by taking input or return output by Interface
	// but excecute sensor action through each farm.
	private void addSensor() {
		// case when no farms exist-->
		if (farmNum == 0) {
			JOptionPane.showMessageDialog(null, "Error: No farm exist to add sensor!");
			return;
		}
		// case farm exist--> ask for input farm name
		farmName = JOptionPane.showInputDialog("Please input name for the farm you want to add sensor: ");
		for (int i = 0; i < farmNum; i++) {
			if (farms[i].getName().equalsIgnoreCase(farmName)) {
				if (farms[i].getSensorNum() == 4) {
					// sensors amount reach maximum --> choice to add sensor quantities for specific
					// sensor
					String choice = JOptionPane.showInputDialog(
							"Sensor types reach limit. \nDo you want to add more quantity for a specific sensor?: Y or N?");
					// input Y to choose a sensor to add quantity, "N" or others will cancel the
					// task.
					if (choice.equalsIgnoreCase("Y")) {
						t = JOptionPane.showInputDialog(
								"Available exist sensors in farm: " + Arrays.toString(farms[i].getSensorList())
										+ "\nPlease input name of the sensor you want to increase quantity: ");
						// check if types in the exist sensor list?-->ask for re-input if not exist.
						while (!farms[i].SensorValidate(t)) {
							t = JOptionPane
									.showInputDialog("Error: Wrong sensor type. \nAvailable exist sensors in farm: "
											+ Arrays.toString(farms[i].getSensorList())
											+ "\nPlease re-input name of the sensor you want to increase quantity: ");
						}
						// loop exist sensors list to find the index of the right sensor.
						for (int j = 0; j < farms[i].getSensorNum(); j++) {
							if (t.equalsIgnoreCase(farms[i].getSensorType(j))) {
								// ask for quantity, if quantity<0 ask again
								q = Integer.parseInt(JOptionPane.showInputDialog("Sensor " + farms[i].getSensorType(j)
										+ " exists, with price $" + farms[i].getSensorPrice(j) + " and weight "
										+ farms[i].getSensorWeight(j) + "\nInput quantity you want to add more: "));
								while (q < 0) {
									q = Integer.parseInt(JOptionPane.showInputDialog(
											"Error: Quantity can not be negative \nRe-input quantity:"));
								}
								// add sensor quantity using addSensorQuantity method which take quantity and
								// index of sensor
								farms[i].addSensorQuantity(q, j);
								JOptionPane.showMessageDialog(null,
										"Sensor " + farms[i].getSensorType(j)
												+ " quantity, has been succesfully increased to: "
												+ farms[i].getSensorQuantity(j));
								return;
							}
							;
						}

					} else {
						// Cancel the task if choice is "N" or others than "Y"
						JOptionPane.showMessageDialog(null, "The task has been canceled");
						return;
					}
				} else {
					// case number of sensors is smaller than 4--> ask for sensor name
					t = JOptionPane.showInputDialog("Available sensors list: " + Arrays.toString(sensorTypeList)
							+ "\nPlease input name of the sensor you want to add: ");
					// verify if sensor in the available sensor list--> if wrong ask for sensor
					// again
					while (!sensorVerified(t)) {
						t = JOptionPane.showInputDialog("Error: Wrong sensor type. \nAvailable sensors types: "
								+ Arrays.toString(sensorTypeList)
								+ "\nPlease re-input name of the sensor you want to add: ");
					}
					;
					// loop sensors of the farm list to get index
					for (int j = 0; j < farms[i].getSensorNum(); j++) {
						// if that sensors already exist--> ask for number to increase quantity.
						if (t.equalsIgnoreCase(farms[i].getSensorType(j))) {
							q = Integer.parseInt(JOptionPane.showInputDialog("Sensor " + farms[i].getSensorType(j)
									+ " exists, with price $" + farms[i].getSensorPrice(j) + " and weight "
									+ farms[i].getSensorWeight(j) + "\nInput quantity you want to add more: "));
							while (q < 0) {
								q = Integer.parseInt(JOptionPane
										.showInputDialog("Error: Quantity can not be negative \nRe-input quantity:"));
							}
							farms[i].addSensorQuantity(q, j);
							JOptionPane.showMessageDialog(null, "Sensor " + farms[i].getSensorType(j)
									+ " quantity, has been succesfully increased to: " + farms[i].getSensorQuantity(j));
							return;
						}
						;
					}
					;
					// sensor name not in list, sensor type amount < 4 --> add more sensor type
					q = Integer.parseInt(JOptionPane.showInputDialog("Input quantity for sensor " + t + ":"));
					while (q < 0) {
						q = Integer.parseInt(JOptionPane.showInputDialog(
								"Error: Quantity can not be negative. \nRe-Input quantity for sensor " + t + ":"));
					}
					w = Double.parseDouble(JOptionPane.showInputDialog("Input weight for sensor " + t + ":"));
					while (w < 0) {
						w = Double.parseDouble(JOptionPane.showInputDialog(
								"Error: Weight can not be negative. \nRe-Input Weight for sensor " + t + ":"));
					}
					p = Double.parseDouble(JOptionPane.showInputDialog("Input price for sensor " + t + ":"));
					while (p < 0) {
						p = Double.parseDouble(JOptionPane.showInputDialog(
								"Error: Price can not be negative. \nRe-Input Price for sensor " + t + ":"));
					}
					// adding sensor's type, price, weight, quantity to database.
					farms[i].addSensorType(t);
					// n is the index of the end sensor
					int n = farms[i].getSensorNum() - 1;
					farms[i].setSensorQuantity(q, n);
					farms[i].setSensorPrice(p, n);
					farms[i].setSensorWeight(w, n);
					JOptionPane.showMessageDialog(null,
							"Sensor " + farms[i].getSensorType(n) + " with quantity: " + farms[i].getSensorQuantity(n)
									+ ", weight: " + farms[i].getSensorWeight(n) + " ,price: $"
									+ farms[i].getSensorPrice(n) + " has been added successfully to farm "
									+ farms[i].getName());
					return;
				}
				;

			}
			;
		}
		// in case farm does not exist-->
		JOptionPane.showMessageDialog(null, "Error: Farm " + farmName + " does not exist!");

	};

	// remove sensor method
	private void removeSensor() {
		// when no farm exist--> error notify
		if (farmNum == 0) {
			JOptionPane.showMessageDialog(null, "Error: No farm exist to remove sensor!");
			return;
		}
		// when farm exist--> ask to input farm name
		farmName = JOptionPane.showInputDialog("Please input name for the farm you want to remove sensor: ");
		for (int i = 0; i < farmNum; i++) {
			// if farm name is correct and but no sensors in farm--> raise error
			if (farms[i].getName().equalsIgnoreCase(farmName)) {
				if (farms[i].getSensorNum() == 0) {
					JOptionPane.showMessageDialog(null, "Error: Farm " + farmName + " does not have any sensors.");
					return;
				} else {
					// when farm is correct and sensorNum>0 --> ask for input sensor type
					t = JOptionPane.showInputDialog(
							"Available exist sensors in farm: " + Arrays.toString(farms[i].getSensorList())
									+ "\nPlease input name of the sensor you want to reduce quantity: ");
					while (!farms[i].SensorValidate(t)) {
						t = JOptionPane
								.showInputDialog("Error: Wrong sensor type. \nAvailable exist sensors in farm: "
										+ Arrays.toString(farms[i].getSensorList())
										+ "\nPlease re-input name of the sensor you want to reduce quantity: ");
					}
					// ask for input quantity to reduce
					q = Integer.parseInt(JOptionPane
							.showInputDialog("Input the number of sensors you want to reduce for sensor " + t));
					// get sensor index
					int j = farms[i].findSensorPosition(t);
					// if the quantity is negative or higher than exist sensor quantity--> raise
					// error and ask for quantity input again
					while (q < 0 || q > farms[i].getSensorQuantity(j)) {
						q = Integer.parseInt(JOptionPane.showInputDialog(
								"Error: The number to remove can not be negative or higher than the exist sensor quantity. \nRe-input the number of sensors you want to reduce for sensor "
										+ t));
					}
					// reduce quantity from sensor if input quantity satisfy condition.
					farms[i].addSensorQuantity(-q, j);
					JOptionPane.showMessageDialog(null,
							q + " items of Sensor " + farms[i].getSensorType(j) + " removed from farm "
									+ farms[i].getName() + ".\n The quantity of sensor left is: "
									+ farms[i].getSensorQuantity(j));
					// when sensor quantity reach 0--> remove sensor from the farm.
					if (farms[i].getSensorQuantity(j) == 0) {
						JOptionPane.showMessageDialog(null,
								"Hence the quantity of Sensor " + farms[i].getSensorType(j)
										+ " has reached 0. \nTherefore " + farms[i].getSensorType(j)
										+ " will be removed from farm " + farms[i].getName());
						farms[i].dropSensor(j);

					}
					;
					return;

				}
			}

		}
		// when farm is not exist -->
		JOptionPane.showMessageDialog(null, "Error: Farm " + farmName + " does not exist!");
	};

	// 5. Method to query for list of farms.
	private void farmList() {
		if (farmNum == 0) {
			// in this case: no farm exist
			JOptionPane.showMessageDialog(null, "No farm exist.");
		} else { // case: at least 1 farm exist
			// I create 1 array for farm name and its order before sorting.
			String[] farmArray = new String[farmNum];
			int[] farmOrder = new int[farmNum];
			for (int i = 0; i < farmNum; i++) {
				farmArray[i] = farms[i].getName();
				farmOrder[i] = i;
			}
			String aux;
			int au;
			// Sorting using bubble sort, the farmOrder number will be sorted according to
			// the farmArray.
			// The reason I do this is: the position of farm name will be still remained, so
			// when i do queries to get sensor number, it still have the right order.
			for (int i = farmNum - 1; i >= 0; i--) {
				for (int j = 0; j < i; j++)
					if (farmArray[j].compareTo(farmArray[j + 1]) > 0) {
						aux = farmArray[j];
						au = farmOrder[j];
						farmArray[j] = farmArray[j + 1];
						farmOrder[j] = farmOrder[j + 1];
						farmArray[j + 1] = aux;
						farmOrder[j + 1] = au;

					}
			}
			// Create the output text.
			String text = "";
			for (int i = 0; i < farmArray.length; i++) {
				text = text + "\nFarm " + farmArray[i] + " has " + farms[farmOrder[i]].getSensorNum() + " sensor type"
						+ pluralCheck(farms[farmOrder[i]].getSensorNum()) + ", with total quantity of "
						+ farms[farmOrder[i]].getTotalSensorQuantity() + " sensor"
						+ pluralCheck(farms[farmOrder[i]].getTotalSensorQuantity()) + ".";
			}
			JOptionPane.showMessageDialog(null, text);
		}

	};

	// 6. query for list of sensors in a farm
	private void sensorList() {
		// case no farm exist--> raise error
		if (farmNum == 0) {
			JOptionPane.showMessageDialog(null, "Error: No farm exist!");
		} else {
			// when farms exist--> ask for farm name
			farmName = JOptionPane
					.showInputDialog("Please input name for the farm you want to query for a list of sensor!");
			for (int i = 0; i < farmNum; i++) {
				if (farmName.equalsIgnoreCase(farms[i].getName())) {
					// farm name is true--> case no sensor in farm-->
					if (farms[i].getSensorNum() == 0) {
						JOptionPane.showMessageDialog(null, "No sensors in farm " + farms[i].getName() + ".");
					} else {
						// case sensor in farm--> loop to return sensors info of that farm
						String text = "";
						for (int j = 0; j < farms[i].getSensorNum(); j++) {
							text = text + "\n Sensor " + farms[i].getSensorType(j) + " has price $"
									+ farms[i].getSensorPrice(j) + ", weight " + farms[i].getSensorWeight(j)
									+ "kg, and quantity " + farms[i].getSensorQuantity(j) + ".";
						}
						JOptionPane.showMessageDialog(null, text);
					}
					return;
				}
			}
			JOptionPane.showMessageDialog(null, "Error: Farm " + farmName + " does not exist");
		}
		;
	};

	// 7. Query about sensor's presence in the farms by taking sensor input.
	private void sensorInfo() {
		// raise error if no Farm.
		if (farmNum == 0) {
			JOptionPane.showMessageDialog(null, "Error: No farm exist!");
		} else {
			// Ask for input sensor name
			t = JOptionPane.showInputDialog("Sensor list: " + Arrays.toString(sensorTypeList)
					+ ".\nInput name of the sensors you want to query for the presence: ");
			while (!sensorVerified(t)) {
				// if sensor name is not in the list--> ask for input again
				t = JOptionPane
						.showInputDialog("Error: Wrong sensor type. \nAvailable sensors type: "
								+ Arrays.toString(sensorTypeList)
								+ "\nPlease re-input name of the sensor you want to query for the presence: ");

			}
			// when sensor in list--> start to find that sensor in farms-->
			String text = "";
			for (int i = 0; i < farmNum; i++) {
				for (int j = 0; j < farms[i].getSensorNum(); j++) {
					// get sensor data if sensor is exist in farms
					if (farms[i].getSensorType(j).equalsIgnoreCase(t)) {
						text = text + "\nSensor " + farms[i].getSensorType(j) + " is in farm " + farms[i].getName()
								+ " with quantity " + farms[i].getSensorQuantity(j) + ".";
					}
				}
			}
			// if no sensor in farm--> raise error
			if (text.equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(null, "Error: The sensor " + t + " does not exist!");
			} else {
				JOptionPane.showMessageDialog(null, text);
			}

		}

	};

	// 8. query for cumulative value of a farm
	private void farmValue() {
		// raise error if no farm exist
		if (farmNum == 0) {
			JOptionPane.showMessageDialog(null, "Error: No farm exist!");
		} else {
			// if farm exist--> ask for farm name to query for sensors
			farmName = JOptionPane.showInputDialog(
					"Please input name for the farm you want to query for a Cumulative value of all sensors!");
			for (int i = 0; i < farmNum; i++) {
				if (farmName.equalsIgnoreCase(farms[i].getName())) {
					if (farms[i].getSensorNum() == 0) {
						// if no sensors in that farm--> return cumulative value= $0
						JOptionPane.showMessageDialog(null,
								"Farm " + farms[i].getName() + " has cumulative sensor value: $0.");
					} else {
						// if sensors exist--> multiply price and quantity to get sensor value
						// loop to add up that multiplication.
						double value = 0;
						for (int j = 0; j < farms[i].getSensorNum(); j++) {
							value = value + farms[i].getSensorQuantity(j) * farms[i].getSensorPrice(j);
						}
						JOptionPane.showMessageDialog(null,
								"Farm " + farms[i].getName() + " has cumulative sensor value: $" + value + ".");
					}
					return;
				}
			}
			JOptionPane.showMessageDialog(null, "Error: Farm " + farmName + " does not exist");
		}
		;
	};

	// 9. export farm and sensor data
	private void farmDataExport() {
		String fileName = "farmsData.txt";
		PrintWriter outputStream;
		try {
			outputStream = new PrintWriter(fileName);
		} catch (FileNotFoundException e) {
			// raise error if file error
			JOptionPane.showMessageDialog(null, "Error creating the file " + fileName + "!");
			return;
		}
		// stream the data into file
		for (int i = 0; i < farmNum; i++) {
			if (farms[i].getSensorNum() == 0) {
				// if no sensor--> return only farm name
				outputStream.println(farms[i].getName());
			} else {
				// if sensors exist--> loop for sensor to get sensor data and put it in file.
				for (int j = 0; j < farms[i].getSensorNum(); j++) {
					outputStream.println(
							farms[i].getName() + " " + farms[i].getSensorType(j) + " " + farms[i].getSensorPrice(j)
									+ " " + farms[i].getSensorWeight(j) + " " + farms[i].getSensorQuantity(j));
				}
			}
		}
		outputStream.close();
		// file created successfully notification:
		JOptionPane.showMessageDialog(null,
				"Farms and sensors information has been exported successfully. Data will be stored in farmsData.txt file.");
	};

	public static void main(String[] args) {
		Interface intFace = new Interface();
		intFace.run();

	}
}
