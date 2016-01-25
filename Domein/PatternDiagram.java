package Domein;

import java.io.File;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class PatternDiagram {
	@XmlElement
	private String plaatje;
	public PatternDiagram(){
		
	}
	public void setFilePath(File selectedFile) {
		plaatje = selectedFile.toString();
		
	}
	public String getFilePath() {
		return plaatje;
	}
}