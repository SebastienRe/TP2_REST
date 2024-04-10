package Q13;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;

public class CarnetService {
    public void saveCarnet(Carnet carnet) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Carnet.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(carnet, new File("./carnet.xml"));
    }

    public Carnet loadCarnet() throws Exception {
        JAXBContext context = JAXBContext.newInstance(Carnet.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (Carnet) unmarshaller.unmarshal(new File("./carnet.xml"));
    }
}