import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Objects;

@XmlRootElement
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class Invoice {
    @XmlElement
    private BigDecimal cost;
    @XmlElement
    private int count;
    @XmlElement
    private BigDecimal amount;
    @XmlElement
    private String name;



    public Invoice(String cost, int count, String name) {
        this.cost = new BigDecimal(cost);
        this.count = count;
        amount = this.cost.multiply(BigDecimal.valueOf(count));
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public int getCount() {
        return count;
    }

    public BigDecimal getAmount() {
        return amount;
    }


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Cost: " + cost + "\n" +
                "Count: " + count + "\n" +
                "Amount: " + amount + "\n" +
                "Name: "+ name+"\n\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return count == invoice.count &&
                Objects.equals(cost, invoice.cost) &&
                Objects.equals(amount, invoice.amount) &&
                Objects.equals(name, invoice.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cost, count, amount, name);
    }

    public Invoice(){
        this.cost = new BigDecimal(0);
        amount =new BigDecimal(0);
    }
}
