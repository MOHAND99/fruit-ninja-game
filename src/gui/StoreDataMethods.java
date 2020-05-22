package gui;

import entities.adapted.Data;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public final class StoreDataMethods {

    private StoreDataMethods() {
    }

    public static void saveDataToXml(String xmlFileName, Data data) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Data.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(data, new File(xmlFileName));
    }
    public static Data loadDataFromXmlFile(String filePath) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Data.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (Data) unmarshaller.unmarshal(new File(filePath));
    }
}
