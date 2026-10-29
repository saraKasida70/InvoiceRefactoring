package s1.lab2;

public class EmailInvoiceSender implements InvoiceMessageSender {

    @Override
    public String send(Invoice invoice, String formattedInvoiceText) {
        String message = "To: " + invoice.getCustomerEmail()
                + "\nSubject: Invoice " + invoice.getNumber()
                + "\n\n" + formattedInvoiceText;

        System.out.println("Sending email...");

        return message;
    }
}
