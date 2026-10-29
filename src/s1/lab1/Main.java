
        package s1.lab1;

import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        // الفاتورة الأولى
        Invoice invoice1 = new Invoice("INV-1", "lina@uni.edu");
        invoice1.addItem("Book", 100.0, 2);

        System.out.println("===== INVOICE 1 =====");
        invoice1.printInvoice();

        invoice1.saveToDatabase("DATABASE");
        System.out.println("DATABASE: " + Invoice.DATABASE);

        invoice1.sendEmail();
        invoice1.sendwhatsapp();

        System.out.printf(
                Locale.US, "Final total: %.2f%n",
                invoice1.calculateTotal()
        );

        // الفاتورة الثانية
        Invoice invoice2 = new Invoice("INV-2", "ahmad@uni.edu");
        invoice2.addItem("Computer", 1200.0, 1);

        System.out.println("\n===== INVOICE 2 =====");
        invoice2.printInvoice();

        invoice2.saveToDatabase("FILES");
        System.out.println("FILES: " + Invoice.FILES);

        invoice2.sendEmail();
        invoice2.sendwhatsapp();

        System.out.printf(
                Locale.US, "Final total: %.2f%n",
                invoice2.calculateTotal()
        );

        // اختبار حد الخصم: المجموع يساوي 1000 بالضبط
        Invoice testInvoice = new Invoice("TEST-1", "test@uni.edu");
        testInvoice.addItem("Item", 500.0, 2);

        System.out.printf(
                Locale.US,
                "%nBoundary test total: %.2f%n",
                testInvoice.calculateTotal()
        );
    }
}
