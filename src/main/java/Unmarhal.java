import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Unmarhal {
    public static void  main(String[] args) throws JAXBException {

        File file = new File("./invoice.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(generated.Invoice.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        generated.Invoice obj1 = (generated.Invoice)unmarshaller.unmarshal(file);
        System.out.println(obj1);
    }
}

