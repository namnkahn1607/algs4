package oasis.Week7;

public class Car extends Vehicle {
    private int numberOfDoors;

    /**
     * constructor.
     */
    public Car(
            String brand, String model, String registrationNumber, Person owner, int numberOfDoors
    ) {
        super(brand, model, registrationNumber, owner);
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    public String getInfo() {
        return String.format(
                "oasis.Week7.Car:\n"
                        + "\tBrand: %s\n"
                        + "\tModel: %s\n"
                        + "\tRegistration Number: %s\n"
                        + "\tNumber of Doors: %d\n"
                        + "\tBelongs to %s - %s\n",
                getBrand(), getModel(), getRegistrationNumber(),
                getNumberOfDoors(), getOwner().getName(), getOwner().getAddress()
        );
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }
}