import java.util.Objects;

public abstract class Vehicle implements Taxable,Comparable<Vehicle> {
    protected String id;
    protected String brand;
    protected int year;

    public Vehicle() {
    }

    public Vehicle(String id, String brand, int year) {
        this.id = id;
        this.brand = brand;
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public abstract void displayInfo();

    @Override
    public int compareTo(Vehicle otherVehicle) {
        return Integer.compare(this.year, otherVehicle.year);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id='" + id + '\'' +
                ", brand='" + brand + '\'' +
                ", year=" + year +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        // Coi hai xe là giống nhau nếu ID giống nhau (không phân biệt hoa thường)
        return id != null ? id.equals(vehicle.id) : vehicle.id == null;
    }

    @Override
    public int hashCode() {
        // Hash code dựa trên ID (chuyển về chữ thường để nhất quán với equalsIgnoreCase)
        return Objects.hash(id != null ? id.toLowerCase() : null);
    }
}
