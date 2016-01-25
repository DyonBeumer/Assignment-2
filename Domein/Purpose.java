package Domein;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Purpose	 {
	
	
	public enum purpose {
		CREATIONAL, BEHAVIORAL, STRUCTURAL
	}
}