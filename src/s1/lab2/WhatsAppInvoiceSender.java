package s1.lab2;

public class WhatsAppInvoiceSender implements InvoiceMessageSender {

    @Override
    public String send(Invoice invoice, String formattedInvoiceText) {
        String message = "To: sendwhatsapp"
                + "\nSubject: Invoice " + invoice.getNumber()
                + "\n\n" + formattedInvoiceText;

        System.out.println("Sending sendwhatsapp...");

        return message;
    }
}
