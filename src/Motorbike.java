public class Motorbike extends Vehicle {
    private int enginePower;

    public Motorbike(int enginePower) {
        this.enginePower = enginePower;
    }

    public Motorbike(String id, String brand, int year, int enginePower) {
        super(id, brand, year);
        this.enginePower = enginePower;
    }

    public int getEnginePower() {
        return enginePower;
    }

    @Override
    public void displayInfo() {
        System.out.println("Motorbike -" + super.toString() + ",EnginePower: " + enginePower);
    }

    @Override
    public double calculateTax() {
        double tax = 0;
        if (enginePower < 100) {
            return 500.0;
        } else {
            return 1000.0;
        }
    }

    @Override
    public String toString() {
        return "Motorbike [" + super.toString() + ",Engine Power= " + enginePower + "]";
    }
}
