import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

enum Status {UNPAID, PAID, CANCELLED, REFUNDED}

@XmlRootElement
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class DTO {

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
    @XmlElementWrapper(name="items")
    private Invoice[] invoiceItem;


    public DTO(){
        dateOfCreate = new Date();
        orderID++;
    }
    public DTO(int clientID, Status status, Invoice[] invoiceItem) {
        dateOfCreate = new Date();
        this.clientID = clientID;
        this.invoiceItem = invoiceItem;
        this.status = status;
        for (Invoice i : invoiceItem) {
            amount=amount.add(i.getAmount());
        }
        orderID++;
    }


    public String InvoiceItem(){
        String s="";
        for(Invoice i:invoiceItem)
            s+=i.toString();
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
                + "Positions:\n\n"+
                InvoiceItem();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DTO dto = (DTO) o;
        return clientID == dto.clientID
                && Objects.equals(dateOfCreate, dto.dateOfCreate)
                && Objects.equals(dateOfChange, dto.dateOfChange)
                && Objects.equals(amount, dto.amount) && status == dto.status
                && Arrays.equals(invoiceItem, dto.invoiceItem);

    }

    @Override
    public int hashCode() {
        int result = Objects.hash(dateOfCreate, dateOfChange, amount, clientID, status);
        result = 31 * result + Arrays.hashCode(invoiceItem);
        return result;
    }
}
