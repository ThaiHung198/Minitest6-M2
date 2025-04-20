public class Car extends Vehicle implements Taxable {
    private int numberOfSeats;

    public Car(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Car(String id, String brand, int year, int numberOfSeats) {
        super(id, brand, year);
        this.numberOfSeats = numberOfSeats;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public double CalculateTax() {
        double tax;
        return tax = numberOfSeats * 300;
    }

    @Override
    public void displayInfo() {
        System.out.println("Car     -" + super.toString() + ",Seat= " + numberOfSeats);
    }

    @Override
    public double calculateTax() {
        return this.numberOfSeats * 300.0;
    }

    @Override
    public String toString() {
        return "Car     [" + super.toString() +
                "Seats=" + numberOfSeats +
                "]";
    }
}
