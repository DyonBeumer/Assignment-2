package Domein;

import javax.xml.bind.annotation.XmlRootElement;

import Domein.Consequence;
@XmlRootElement
public class Consequence {
	private String text;
	
	public void setText(String s){
		text = s;
	}
	
	public String getText() {
		return text;
	}
}
