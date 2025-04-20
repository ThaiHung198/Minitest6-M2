import java.util.*;
public class VehicleManager {
    // Dùng ArrayList cho danh sách phương tiện
    private List<Vehicle> vehicles;
    // Dùng LinkedList cho lịch sử thao tác

    private List<String> history;
    public VehicleManager() {
        this.vehicles = new ArrayList<>();
        this.history = new LinkedList<>();
    }
    private void logHistory(String message) {
        this.history.add(String.format("[%tT] %s",new Date(), message));
    }
    void addVehicle(Vehicle v) {
        boolean idExists = false;
        for (Vehicle existing : vehicles) {
            if (existing.getId().equalsIgnoreCase(v.getId())) {
                idExists = true;
                break;
            }
        }
        if (idExists) {
            System.out.println("(!) lỗi : ID '" + v.getId() + "' Đã tồn tại.");
            logHistory("Đã thử thêm ID trùng lặp: "+ v.getId());
        } else {
            this.vehicles.add(v);
            System.out.println("(+) Đã thêm: " + v.toString());
            logHistory("Đã thêm: "+ v.toString());
        }
    }
    public void removeVehicle(String id) {
        boolean removed = this.vehicles.removeIf(vehicles -> vehicles.getId().equalsIgnoreCase(id));
        if (removed) {
            System.out.println("(-) Đã xóa có ID: " + id);
            logHistory("Removed vehicle with ID: " + id);
        }else {
            System.out.println("(!) không tìm thấy xe nào có ID '"+id+"'để xóa.");
            logHistory("Đã cố gắng xóa ID không tồn tại: " + id);
        }
    }
    public void displayAllVehicles() {
        System.out.println("\n--- Danh sách phương tiện (" + vehicles.size() + ") ---");
        if (vehicles.isEmpty()){
            System.out.println("Danh sách trống.");
        }else {
            for (Vehicle v : vehicles) {
            v.displayInfo();
            }
        }
        System.out.println("-----------------------------");
        logHistory("Hiển thị tất cả các loại xe");
    }
    public void displayTaxReport() {
        System.out.println("\n--- Báo cáo thuế---");
        if (vehicles.isEmpty()){
            System.out.println("Danh sách trống.");
        }else {
            System.out.printf("%-15s | %-10s | %s\n","Loại xe", "ID","Thuế (VND)");
            System.out.println("---------------------------------");
            double totalTax = 0;
            for (Vehicle v : vehicles) {
                String type = v.getClass().getSimpleName();
                double tax = v.calculateTax();
                System.out.printf("%-15s | %-10s | %.2f\n",type,v.getId(),tax);
                totalTax += tax;
            }
            System.out.println("---------------------------------------");
            System.out.printf("Tổng cộng thuế : %.2f VND\n",totalTax);
        }
        System.out.println("-------------------");
        logHistory("Báo cáo thuế được hiển thị");
    }
    public void sortByYear(){
        if(vehicles.isEmpty()){
            System.out.println("Danh sách trống, không thể sắp xếp.");
            return;
        }
        Collections.sort(this.vehicles);
        System.out.println("(*)Đã sắp xếp theo năm sản xuất.");
        logHistory("Sắp xếp theo năm");
    }
    public void sortByBrand(){
        if (vehicles.isEmpty()){
            System.out.println("Danh sách trống,không thể sắp xếp");
            return;
        }
        this.vehicles.sort(new VehicleComparatorByBrand());
        System.out.println("(*) Đã sắp xếp theo tên hãng.");
        logHistory("Sắp xếp theo thương hiệu");
    }
    public void sortByTax(){
        if (vehicles.isEmpty()){
            System.out.println("Danh sách trống, không thể sắp xếp.");
            return;
        }
        this.vehicles.sort(new VehicleComparatorByTax());
        System.out.println("(*) Đã sắp xếp theo thuế.");
        logHistory("Sắp xếp theo thuế.");
    }
    // in lịch sử
    public void printHistory(){
        System.out.println("\n--- Lịch sử thao tác---");
        if (history.isEmpty()){
            System.out.println("Chưa có thao tác nào.");
        }else {
            int index = 1;
            for (String entry : history) {
                System.out.println(index++ + ". " + entry);
            }
        }
        System.out.println("--------------------------");
    }
}
