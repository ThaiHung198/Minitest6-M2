import java.util.Comparator;
public class VehicleComparatorByBrand implements Comparator<Vehicle> {

    @Override
    public int compare(Vehicle v1, Vehicle v2) {
        return v1.getBrand().compareToIgnoreCase(v2.getBrand());
    }
}
