package oasis.Week7;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private String address;
    private final List<Vehicle> vehicleList = new ArrayList<>();

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    /**
     * addVehicle().
     */
    public void addVehicle(Vehicle vehicle) {
        vehicleList.add(vehicle);
    }

    /**
     * removeVehicle().
     */
    public void removeVehicle(String registrationNumber) {
        vehicleList.removeIf(
                vehicle -> vehicle.getRegistrationNumber().equals(registrationNumber)
        );
    }

    /**
     * getVehiclesInfo().
     */
    public String getVehiclesInfo() {
        if (vehicleList.isEmpty()) {
            return String.format("%s has no vehicle!", getName());
        }

        StringBuilder info = new StringBuilder(String.format("%s has:\n\n", getName()));

        for (Vehicle vehicle : vehicleList) {
            info.append(vehicle.getInfo());
        }

        return info.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
