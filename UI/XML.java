package UI;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import Domein.Pattern;

public class XML implements SaveInterface, LoadInterface {

	@Override
	public List<Pattern> Load() {
		ArrayList<Pattern> patternlist = new ArrayList<Pattern>();
		try {
			File file = new File("C://T06//src//file.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Patterns.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Patterns patterns = (Patterns) jaxbUnmarshaller.unmarshal(file);
			patternlist = (ArrayList<Pattern>) patterns.getAllePatterns(); 
			System.out.println(patternlist);
		} catch (JAXBException e) {
			System.out.println("XML bestand is leeg");
		}
		return patternlist;
	}

	@Override
	public void Save(List<Pattern> patternlist) {
			Patterns allePatterns = new Patterns(); 
			    allePatterns.setAllePatterns(patternlist);
			try
			{

			File file = new File("C://T06//src//file.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Patterns.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(allePatterns, file);
		
		
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
}