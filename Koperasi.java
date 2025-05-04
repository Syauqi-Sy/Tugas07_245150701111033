interface Payable {
    int getPayableAmount();
}

class Invoice implements Payable {
    private String prodName;
    private int qty;
    private int pricePerItem;

    Invoice(String prodName, int qty, int pricePerItem) {
        this.prodName = prodName;
        this.qty = qty;
        this.pricePerItem = pricePerItem;
    }

    @Override
    public int getPayableAmount() {
        return qty * pricePerItem;
    }

    public void printInvDetail() {
        System.out.printf("%-15s %5d x Rp%,8d = Rp%,10d\n", prodName, qty, pricePerItem, getPayableAmount());
    }
}

class Employee implements Payable {
    private String name;
    private int salPerMonth;
    private Invoice[] invoices;

    Employee(String name, int salPerMonth, Invoice[] invoices) {
        this.name = name;
        this.salPerMonth = salPerMonth;
        this.invoices = invoices;
    }

    public int getTotalInvAmount() {
        int total = 0;
        for (Invoice invoice : invoices) {
            total += invoice.getPayableAmount();
        }
        return total;
    }

    @Override
    public int getPayableAmount() {
        return salPerMonth - getTotalInvAmount();
    }

    public void printEmpDetail() {
        System.out.println("========================================");
        System.out.println("      KOPERASI KARYAWAN NV. Meneer");
        System.out.println("========================================");
        System.out.printf("Nama Karyawan  : %s\n", name);
        System.out.printf("Gaji Bulanan   : Rp%,d\n", salPerMonth);
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println("            Detail Belanja");
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.printf("%-15s %6s %11s %12s\n", "Produk", "Jumlah", "Harga/item", "Subtotal");
        System.out.println("----------------------------------------");
        for (Invoice invoice : invoices) {
            invoice.printInvDetail();
        }
        System.out.println("----------------------------------------");
        System.out.printf("Total Belanja  : Rp%,d\n", getTotalInvAmount());
        System.out.println("----------------------------------------");
        System.out.printf("Gaji Bersih    : Rp%,d\n", getPayableAmount());
        System.out.println("========================================");
    }
}

public class Koperasi {
    public static void main (String[] args) {
        Invoice[] invoices = {
            new Invoice("Pensil", 10, 2000),
            new Invoice("Buku Tulis", 5, 5000),
            new Invoice("Penghapus", 3, 1500)
        };

        Employee emp1 = new Employee("Joko", 5000000, invoices);
        emp1.printEmpDetail();
    }
}
