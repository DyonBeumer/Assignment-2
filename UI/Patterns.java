package UI;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import Domein.Pattern;
@XmlRootElement

public class Patterns {
	List<Pattern> allePatterns = null;

	
	public List<Pattern> getAllePatterns() {
		return allePatterns;
	}

	public void setAllePatterns(List<Pattern> patternlist) {
		this.allePatterns =  patternlist;
	}
	
	

}
