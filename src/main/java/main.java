import javax.xml.bind.*;
import java.io.File;

public class main {
    public static void main(String[] args) throws JAXBException {

        String s[] ={"bag", "phone", "paper", "pen"} ;
        int n[] ={2, 1, 4, 100} ;
        String c[] ={"200.2", "40000.99", "500", "23.5"} ;

        Invoice[] arr = new Invoice[4];

        for (int i=0;i<arr.length;++i) {
            arr[i] = new Invoice(c[i], n[i], s[i]);
        }

        DTO d1 = new DTO(1234567, Status.PAID, arr);
        //System.out.println(d1);

        JAXBContext context = JAXBContext.newInstance(Invoice.class);
        Marshaller mar= context.createMarshaller();
        Unmarshaller um = context.createUnmarshaller();
        File file = new File("./invoice.xml");
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(arr[0],file);
        Invoice obj = (Invoice)um.unmarshal(file);
        System.out.println(obj);
        System.out.println(arr[0].equals(obj));





    }
}