package s1.lab2;

import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        InvoiceCalculator calculator = new InvoiceCalculator();
        InvoiceFormatter formatter = new InvoiceFormatter(calculator);
        DatabaseInvoiceStorage databaseStorage = new DatabaseInvoiceStorage();
        FileInvoiceStorage fileStorage = new FileInvoiceStorage();
        EmailInvoiceSender emailSender = new EmailInvoiceSender();
        WhatsAppInvoiceSender whatsAppSender = new WhatsAppInvoiceSender();
        InvoiceService invoiceService = new InvoiceService(
                calculator,
                formatter,
                databaseStorage,
                fileStorage,
                emailSender,
                whatsAppSender
        );

        // الفاتورة الأولى
        Invoice invoice1 = new Invoice("INV-1", "lina@uni.edu");
        invoice1.addItem("Book", 100.0, 2);

        System.out.println("===== INVOICE 1 =====");
        invoiceService.printInvoice(invoice1);

        invoiceService.saveToDatabase(invoice1, "DATABASE");
        System.out.println("DATABASE: " + DatabaseInvoiceStorage.DATABASE);

        invoiceService.sendEmail(invoice1);
        invoiceService.sendwhatsapp(invoice1);

        System.out.printf(
                Locale.US, "Final total: %.2f%n",
                invoiceService.calculateTotal(invoice1)
        );

        // الفاتورة الثانية
        Invoice invoice2 = new Invoice("INV-2", "ahmad@uni.edu");
        invoice2.addItem("Computer", 1200.0, 1);

        System.out.println("\n===== INVOICE 2 =====");
        invoiceService.printInvoice(invoice2);

        invoiceService.saveToDatabase(invoice2, "FILES");
        System.out.println("FILES: " + FileInvoiceStorage.FILES);

        invoiceService.sendEmail(invoice2);
        invoiceService.sendwhatsapp(invoice2);

        System.out.printf(
                Locale.US, "Final total: %.2f%n",
                invoiceService.calculateTotal(invoice2)
        );

        // اختبار حد الخصم: المجموع يساوي 1000 بالضبط
        Invoice testInvoice = new Invoice("TEST-1", "test@uni.edu");
        testInvoice.addItem("Item", 500.0, 2);

        System.out.printf(
                Locale.US,
                "%nBoundary test total: %.2f%n",
                invoiceService.calculateTotal(testInvoice)
        );
    }
}
