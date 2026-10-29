package s1.lab2;

import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private String number;
    private String customerEmail;
    private List<InvoiceItem> items;

    public Invoice(String number, String customerEmail) {
        this.number = number;
        this.customerEmail = customerEmail;
        this.items = new ArrayList<>();
    }

    public void addItem(String name, double price, int qty) {
        items.add(new InvoiceItem(name, price, qty));
    }

    public String getNumber() {
        return number;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public List<InvoiceItem> getItems() {
        return items;
    }
}
