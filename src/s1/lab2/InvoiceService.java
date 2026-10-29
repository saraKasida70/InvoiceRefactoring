package s1.lab2;

import java.util.Locale;

public class InvoiceService {

    private final InvoiceCalculator calculator;
    private final InvoiceFormatter formatter;
    private final InvoiceStorage databaseStorage;
    private final InvoiceStorage fileStorage;
    private final InvoiceMessageSender emailSender;
    private final InvoiceMessageSender whatsAppSender;

    public InvoiceService(
            InvoiceCalculator calculator,
            InvoiceFormatter formatter,
            InvoiceStorage databaseStorage,
            InvoiceStorage fileStorage,
            InvoiceMessageSender emailSender,
            InvoiceMessageSender whatsAppSender
    ) {
        this.calculator = calculator;
        this.formatter = formatter;
        this.databaseStorage = databaseStorage;
        this.fileStorage = fileStorage;
        this.emailSender = emailSender;
        this.whatsAppSender = whatsAppSender;
    }

    public double calculateTotal(Invoice invoice) {
        return calculator.calculateTotal(invoice);
    }

    public String printInvoice(Invoice invoice) {
        String invoiceText = formatter.format(invoice);
        System.out.println(invoiceText);
        return invoiceText;
    }

    public void saveToDatabase(Invoice invoice, String type) {
        String record = String.format(
                Locale.US, "%s;%s;%.2f",
                invoice.getNumber(),
                invoice.getCustomerEmail(),
                calculator.calculateTotal(invoice)
        );

        if ("DATABASE".equals(type)) {
            databaseStorage.save(record);
        } else {
            fileStorage.save(record);
        }
    }

    public String sendEmail(Invoice invoice) {
        String invoiceText = printInvoice(invoice);
        return emailSender.send(invoice, invoiceText);
    }

    public String sendwhatsapp(Invoice invoice) {
        String invoiceText = printInvoice(invoice);
        return whatsAppSender.send(invoice, invoiceText);
    }
}
