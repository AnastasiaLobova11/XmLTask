package package1;
import javax.xml.bind.*;

import java.io.File;

public class main {
    public static void main(String[] args) throws JAXBException {

        File file = new File("./invoice.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Invoice.class);
        Unmarshaller um = jaxbContext.createUnmarshaller();
        Invoice obj = (Invoice)um.unmarshal(file);
    }
}
