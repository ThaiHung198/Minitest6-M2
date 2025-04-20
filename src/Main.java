import java.time.Year;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        VehicleManager manager = new VehicleManager();

        int choice = -1;
        // khởi tạo lựa chọn ban đầu là giá trị không hợp lệ
        do {
            displayMenu();
            System.out.print(">> Nhập lựa chọn của bạn (0-8): ");
            if (sc.hasNextInt()) {
                choice = sc.nextInt();//đọc số nguyên mà người dùng nhập
                sc.nextLine();// để xóa ký tự xuống dòng(\n) còn sót lại sau khi thực hiện nextInt}
                switch (choice) {
                    case 1:
                        addVehicleUI(sc, manager);
                        break;
                    case 2:
                        removeVehicleUI(sc, manager);
                        break;
                    case 3:
                        manager.displayAllVehicles();
                        break;
                    case 4:
                        manager.displayTaxReport();
                        break;
                    case 5:
                        manager.sortByYear();
                        manager.displayAllVehicles();
                        break;
                    case 6:
                        manager.sortByBrand();
                        manager.displayAllVehicles();
                        break;
                    case 7:
                        manager.sortByTax();
                        manager.displayAllVehicles();
                        break;
                    case 8:
                        manager.printHistory();
                        break;
                    case 0:
                        System.out.println("đang thoát chương trình...");
                        break;
                    default:
                        System.out.println("(!) Lựa chọn không hợp lệ.Vui lòng chọn từ 0->8");
                }
            } else {
                System.out.println("(!) Lỗi: vui lòng nhập số nguyên");
                sc.nextLine();// đọc bỏ dòng nhập k hợp lệ
                choice = -1;// đặt lại choice về giá trị k hợp lệ để vòng lặp menu đc tiếp tục
            }
            if (choice != 0) {
                System.out.print("\n(Nhấn Enter để tiếp tục...)");
                sc.nextLine();
            }
        } while (choice != 0);
        System.out.println("chương trình kết thúc rồi, bye");
        sc.close();
    }

    public static void displayMenu() {
        System.out.println("\n======MENU QUẢN LÝ PHƯƠNG TIỆN======");
        System.out.println("1. Thêm phương tiện");
        System.out.println("2. Xóa phương tiện");
        System.out.println("3. Hiển thị danh sách phương tiện");
        System.out.println("4. Hiển thị báo cáo thuế");
        System.out.println("5. sắp xếp theo năm sản xuất");
        System.out.println("6. Sắp xếp theo hãng sản xuất");
        System.out.println("7. Sắp xếp theo thuế tăng dần");
        System.out.println("8. Xem lịch sử thao tác");
        System.out.println("0. Thoát");
        System.out.println("======================================");
    }

    public static void addVehicleUI(Scanner sc, VehicleManager manager) {
        System.out.println("\n---Thêm phương tiện");
        // sd hàm getInputInt
        int typeChoice = getInputInt(sc, "Chọn loại xe(1: Motorbike, 2: Car): ", 1, 2);
        System.out.print("Nhập ID:");
        String idInput = sc.nextLine();
        if (idInput == null || idInput.trim().isEmpty()) {
            System.out.println("(!) ID không được để trống.");
            return;
        }
        String id = idInput.trim();
        System.out.println("Nhập hãng sản xuất(Brand): ");
        String brand = sc.nextLine();
        if (brand == null || brand.trim().isEmpty()) {
            System.out.println("(!) Hãng không được để trống");
            return;
        }
        int currentYear = Year.now().getValue();
        int year = getInputInt(sc, "Nhập Năm sản xuất (ví dụ: " + currentYear + "): ", 1900, currentYear + 1);
        Vehicle newVehicle = null;
        if (typeChoice == 1) {
            int power = getInputInt(sc, "Nhập Công suất động cơ(Engine Power): ", 1, 10000);
            newVehicle = new Motorbike(id, brand, year, power);
        } else {
            int seats = getInputInt(sc, "Nhập số chỗ ngồi(Seats): ", 1, 100);
            newVehicle = new Car(id, brand, year, seats);
        }
        manager.addVehicle(newVehicle);
    }

    public static void removeVehicleUI(Scanner sc, VehicleManager manager) {
        System.out.println("\n--- Xóa phương tiện ---");
        System.out.println("Nhập ID của phương tiện cần xóa: ");
        String idToRemote = sc.nextLine();
        if (idToRemote == null || idToRemote.trim().isEmpty()) {
            System.out.println("(!) ID không được để trống");
        }
        manager.removeVehicle(idToRemote);
    }

    public static int getInputInt(Scanner sc, String label, int min, int max) {
        int value = min - 1;
        boolean validInput = false;
        while (!validInput) {
            System.out.print(label);
            if (sc.hasNextInt()) { // Kiểm tra xem có phải là số nguyên không
                value = sc.nextInt();
                sc.nextLine();
                if (value >= min && value <= max) {
                    // Kiểm tra xem có nằm trong khoảng cho phép không
                    validInput = true;
                    // Nếu hợp lệ thì thoát vòng lặp
                } else {
                    System.out.println("(!) Giá trị phải nằm trong khoảng từ " + min + " đến " + max + ".");
                }
            } else {
                System.out.println("(!) Lỗi: Vui lòng nhập một số nguyên hợp lệ.");
                sc.nextLine();
            }

        }
        return value;
    }
}