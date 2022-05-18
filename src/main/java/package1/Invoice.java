package package1;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@XmlRootElement
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Status.class, InvoiceItem.class})
public class Invoice {
    @XmlElement
    private static long orderID = 0;
    @XmlElement
    private Date dateOfCreate;
    @XmlElement
    private Date dateOfChange;
    @XmlElement
    private BigDecimal amount = new BigDecimal(0);
    @XmlElement
    private long clientID;

    @XmlElement
    private Status status;
    @XmlElement
    private List<InvoiceItem> invoiceItem;

    public Invoice() {
        dateOfCreate = new Date();
        orderID++;
    }

    public Invoice(int clientID, Status status, List<InvoiceItem> invoiceItem) {
        dateOfCreate = new Date();
        this.clientID = clientID;
        this.invoiceItem = invoiceItem;
        this.status = status;
        for (InvoiceItem i : invoiceItem) {
            amount = amount.add(i.getAmount());
        }
        orderID++;
    }

    public String invoiceItem() {
        String s = "";
        for (InvoiceItem i : invoiceItem)
            s += i.toString();
        return s;
    }

    @Override
    public String toString() {
        String dC = "Not changed";
        if (dateOfChange != null) {
            dC = dateOfChange.toString();
        }
        return "Client ID: " + clientID + "\n"
                + "Order ID: " + orderID + "\n"
                + "Date of create: " + dateOfCreate + "\n"
                + "Date of change:" + dC + "\n"
                + "Status: " + status + "\n"
                + "Amount: " + amount + "\n"
                + "Positions:\n\n" +
                invoiceItem();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice dto = (Invoice) o;
        return clientID == dto.clientID
                && Objects.equals(dateOfCreate, dto.dateOfCreate)
                && Objects.equals(dateOfChange, dto.dateOfChange)
                && Objects.equals(amount, dto.amount) && status == dto.status
                && invoiceItem.equals(dto.invoiceItem);

    }

    @Override
    public int hashCode() {
        int result = Objects.hash(dateOfCreate, dateOfChange, amount, clientID, status);
        result = 31 * result + invoiceItem.hashCode();
        return result;
    }
}
