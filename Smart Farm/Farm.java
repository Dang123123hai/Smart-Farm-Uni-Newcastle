/*Author: Hai Dang Luong
*Student No: C3441308
*Date: 19/04/2024
*Description: This is the farm file which contain private data and public method to
work with sensors and store or return farm data.
*/
public class Farm {
    private String name = "";
    private Sensor[] sensors = new Sensor[4];
    private int sensorNum = 0;

    public int getSensorNum() {
        return sensorNum;
    };

    public int getTotalSensorQuantity() {
        int sum = 0;
        for (int i = 0; i < sensorNum; i++) {
            sum = sum + sensors[i].getQuantity();
        }
        return sum;
    };

    public String[] getSensorList() {
        String[] senlist = new String[sensorNum];
        for (int i = 0; i < sensorNum; i++) {

            senlist[i] = sensors[i].getType();
        }
        ;
        return senlist;
    };

    public void addSensorNum() {
        sensorNum++;
    };

    public void reduceSensorNum() {
        sensorNum--;
    };

    public String getName() {
        return name;
    };

    public void setName(String n) {
        name = n;
    };

    public void addSensorType(String t) {
        sensorNum++;
        sensors[sensorNum - 1] = new Sensor();
        sensors[sensorNum - 1].setType(t);
    };

    public void addSensorQuantity(int q, int sensorPosition) {
        sensors[sensorPosition].setQuantity(sensors[sensorPosition].getQuantity() + q);
    };

    public void setSensorQuantity(int q, int sensorPosition) {
        sensors[sensorPosition].setQuantity(q);
    }

    public void setSensorWeight(double w, int sensorPosition) {
        sensors[sensorPosition].setWeight(w);
    };

    public void setSensorPrice(double p, int sensorPosition) {
        sensors[sensorPosition].setPrice(p);
    };

    public String getSensorType(int n) {
        return sensors[n].getType();
    };

    public int getSensorQuantity(int n) {
        return sensors[n].getQuantity();
    };

    public double getSensorWeight(int n) {
        return sensors[n].getWeight();
    }

    public double getSensorPrice(int n) {
        return sensors[n].getPrice();
    }

    public int findSensorPosition(String t) {
        for (int i = 0; i < sensorNum; i++) {
            if (sensors[i].getType().equalsIgnoreCase(t)) {
                return i;
            }
        }
        return 4;
    }

    public boolean SensorValidate(String t) {
        for (int i = 0; i < sensorNum; i++) {
            if (sensors[i].getType().equalsIgnoreCase(t)) {
                return true;
            }
        }
        return false;
    }

    public void dropSensor(int n) {
        for (int i = n; i < sensorNum - 1; i++) {
            sensors[i] = sensors[i + 1];
        }
        ;
        sensorNum--;
    }
}
