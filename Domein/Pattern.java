package Domein;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import Domein.Purpose.purpose;
import Domein.Scope.scope;
@XmlRootElement
public class Pattern implements PatternFacade {
	
	private String naam;
	@XmlElement
	private ArrayList<Keyword> words = null;
	@XmlElement
	private ArrayList<Consequence> consequences = null;
	private purpose mypurp;
	private scope myscope;
	@XmlElement
	private PatternDiagram mydiagram =  new PatternDiagram();
	public Pattern(){}

	/* (non-Javadoc)
	 * @see Domein.PatternFacade#getWords()
	 */
	@Override
	public ArrayList<String> getWords() {
		//Convert Keywords to Strings
		if(words != null) {
			ArrayList<String> list = new ArrayList<String>();
			for(Keyword k : words) {
				list.add(k.getKeyword());
			}
			return list;
		}
		return null;
	}
	/* (non-Javadoc)
	 * @see Domein.PatternFacade#getMypurp()
	 */
	@Override
	public purpose getMypurp() {
		return mypurp;
	}
	/* (non-Javadoc)
	 * @see Domein.PatternFacade#getMyscope()
	 */
	@Override
	public scope getMyscope() {
		return myscope;
	}
	/* (non-Javadoc)
	 * @see Domein.PatternFacade#getPatternDiagram()
	 */
	@Override
	public PatternDiagram getPatternDiagram(){
		return mydiagram;
	}
	/* (non-Javadoc)
	 * @see Domein.PatternFacade#getPatternDiagramAsFile()
	 */
	@Override
	public File getPatternDiagramAsFile() {
		File f = new File(mydiagram.toString());
		return f;
	}
	/* (non-Javadoc)
	 * @see Domein.PatternFacade#getFilepath()
	 */
	@Override
	public String getFilepath(){
		return mydiagram.getFilePath();
	}
	/* (non-Javadoc)
	 * @see Domein.PatternFacade#getNaam()
	 */
	@Override
	public String getNaam() {
		
		return naam;
	}
	
	/* (non-Javadoc)
	 * @see Domein.PatternFacade#setNaam(java.lang.String)
	 */
	@Override
	public void setNaam(String naam) {
		this.naam = naam;
	}
	
	/* (non-Javadoc)
	 * @see Domein.PatternFacade#setKeywords(java.util.ArrayList)
	 */
	@Override
	public void setKeywords(ArrayList<String> list){
		ArrayList<Keyword> keywords = new ArrayList<Keyword>();
	
		if(list != null) {
			for(String s : list) {
				Keyword k = new Keyword();
				k.setKeyword(s);
				keywords.add(k);
			}
		}
		words = keywords;		
	}
	
	/* (non-Javadoc)
	 * @see Domein.PatternFacade#getKeywords()
	 */
	@Override
	public ArrayList<Keyword> getKeywords(){
		return words;
	}
	

	/* (non-Javadoc)
	 * @see Domein.PatternFacade#setPro(java.util.ArrayList)
	 */
	@Override
	public void setPro(ArrayList<String> pro){
		ArrayList<Consequence> strings = new ArrayList<Consequence>();
		if(pro != null){
			for(String s : pro){
				Consequence p = new Consequence();
				p.setText(s);
				strings.add(p);
			}
		}
		consequences = strings;
	}
	/* (non-Javadoc)
	 * @see Domein.PatternFacade#setMypurp(Domein.Purpose.purpose)
	 */
	@Override
	@XmlElement
	public void setMypurp(purpose mypurp) {
		this.mypurp = mypurp;
	}
	/* (non-Javadoc)
	 * @see Domein.PatternFacade#setMyscope(Domein.Scope.scope)
	 */
	@Override
	@XmlElement
	public void setMyscope(scope myscope) {
		this.myscope = myscope;
	}

	/* (non-Javadoc)
	 * @see Domein.PatternFacade#getPros()
	 */
	@Override
	public ArrayList<String> getPros(){
		ArrayList<String> strings = new ArrayList<String>();
		if(consequences!=null){
	
		for(Consequence c : consequences){
		String s =	c.getText();
		strings.add(s);
		}
		}
		return strings;
		
	}
		
	
	/* (non-Javadoc)
	 * @see Domein.PatternFacade#toString()
	 */
	@Override
	public String toString() {
		return naam;
	}
	/* (non-Javadoc)
	 * @see Domein.PatternFacade#patternInfo()
	 */
	@Override
	public String patternInfo() {
		  String s = "Patternnaam = " + naam + " \nScope = " + myscope
		    + " \nPurpose " + mypurp;
		  String s1 = " \nKeywords: ";
		  String s2 = " \nConsequences: ";
		  for (Keyword k : words) {
		   s1 += " " + k.getKeyword();
		  }
		  for (Consequence c : consequences) {
		   s2 += " " + c.getText();
		  }
		  return s + s1 + s2;
		 }

	/* (non-Javadoc)
	 * @see Domein.PatternFacade#setPatternDiagram(java.io.File)
	 */
	@Override
	public void setPatternDiagram(File selectedFile) {
		mydiagram.setFilePath(selectedFile);
		
	}
	
}