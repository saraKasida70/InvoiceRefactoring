package s1.lab2;

import java.util.Locale;

public class InvoiceFormatter {

    private final InvoiceCalculator calculator;

    public InvoiceFormatter(InvoiceCalculator calculator) {
        this.calculator = calculator;
    }

    public String format(Invoice invoice) {
        StringBuilder result = new StringBuilder();

        result.append("INVOICE ").append(invoice.getNumber()).append("\n");

        for (InvoiceItem item : invoice.getItems()) {
            double lineTotal = item.getPrice() * item.getQty();

            result.append(String.format(
                    Locale.US, "%s x%d = %.2f",
                    item.getName(),
                    item.getQty(),
                    lineTotal
            )).append("\n");
        }

        result.append(String.format(
                Locale.US, "TOTAL: %.2f",
                calculator.calculateTotal(invoice)
        ));

        return result.toString();
    }
}
