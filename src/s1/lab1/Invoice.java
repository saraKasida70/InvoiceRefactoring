
package s1.lab1;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Invoice {

    private String number;
    private String customerEmail;
    private List<InvoiceItem> items;

    public static List<String> DATABASE = new ArrayList<>();
    public static List<String> FILES = new ArrayList<>();

    public Invoice(String number, String customerEmail) {
        this.number = number;
        this.customerEmail = customerEmail;
        this.items = new ArrayList<>();
    }

    public void addItem(String name, double price, int qty) {
        items.add(new InvoiceItem(name, price, qty));
    }

    public List<InvoiceItem> getItems() {
        return items;
    }

    public double calculateTotal() {
        double subtotal = 0.0;

        for (InvoiceItem item : items) {
            subtotal += item.getPrice() * item.getQty();
        }

        if (subtotal > 1000.0) {
            subtotal *= 0.95;
        }

        return subtotal * 1.15;
    }

    public String printInvoice() {
        StringBuilder result = new StringBuilder();

        result.append("INVOICE ").append(number).append("\n");

        for (InvoiceItem item : items) {
            double lineTotal = item.getPrice() * item.getQty();

            result.append(String.format(
                    Locale.US, "%s x%d = %.2f",
                    item.getName(),
                    item.getQty(),
                    lineTotal
            )).append("\n");
        }

        result.append(String.format(
                Locale.US, "TOTAL: %.2f", calculateTotal()
        ));

        String invoiceText = result.toString();
        System.out.println(invoiceText);

        return invoiceText;
    }

    public void saveToDatabase(String type) {
        String record = String.format(
                Locale.US, "%s;%s;%.2f",
                number, customerEmail, calculateTotal()
        );

        if ("DATABASE".equals(type)) {
            DATABASE.add(record);
        } else {
            FILES.add(record);
        }
    }

    public String sendEmail() {
        String invoiceText = printInvoice();

        String message = "To: " + customerEmail
                + "\nSubject: Invoice " + number
                + "\n\n" + invoiceText;

        System.out.println("Sending email...");

        return message;
    }

    public String sendwhatsapp() {
        String invoiceText = printInvoice();

        String message = "To: sendwhatsapp"
                + "\nSubject: Invoice " + number
                + "\n\n" + invoiceText;

        System.out.println("Sending sendwhatsapp...");

        return message;
    }
}
