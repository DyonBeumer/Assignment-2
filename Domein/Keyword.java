package Domein;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;




public class Keyword {
	private String text;
	public Keyword(){}
	
	public void setKeyword(String k){
		text = k;
	}
	
	public String getKeyword() {
		return text;
	}
}