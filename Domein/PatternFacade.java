package Domein;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;

import Domein.Purpose.purpose;
import Domein.Scope.scope;

public interface PatternFacade {

	ArrayList<String> getWords();

	purpose getMypurp();

	scope getMyscope();

	PatternDiagram getPatternDiagram();

	File getPatternDiagramAsFile();

	String getFilepath();

	String getNaam();

	void setNaam(String naam);

	void setKeywords(ArrayList<String> list);

	ArrayList<Keyword> getKeywords();

	void setPro(ArrayList<String> pro);

	void setMypurp(purpose mypurp);

	void setMyscope(scope myscope);

	ArrayList<String> getPros();

	String toString();

	String patternInfo();

	void setPatternDiagram(File selectedFile);

}