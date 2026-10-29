package s1.lab2;

public interface InvoiceMessageSender {

    String send(Invoice invoice, String formattedInvoiceText);
}
