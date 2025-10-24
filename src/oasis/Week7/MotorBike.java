package oasis.Week7;

public class MotorBike extends Vehicle {
    private boolean hasSidecar;

    /**
     * constructor.
     */
    public MotorBike(
            String brand, String model, String registrationNumber, Person owner, boolean hasSideCar
    ) {
        super(brand, model, registrationNumber, owner);
        this.hasSidecar = hasSideCar;
    }

    @Override
    public String getInfo() {
        return String.format(
                "Motor Bike:\n"
                        + "\tBrand: %s\n"
                        + "\tModel: %s\n"
                        + "\tRegistration Number: %s\n"
                        + "\tHas Side oasis.Week7.Car: %b\n"
                        + "\tBelongs to %s - %s\n",
                getBrand(), getModel(), getRegistrationNumber(),
                isHasSidecar(), getOwner().getName(), getOwner().getAddress()
        );
    }

    public boolean isHasSidecar() {
        return hasSidecar;
    }

    public void setHasSidecar(boolean hasSidecar) {
        this.hasSidecar = hasSidecar;
    }
}
