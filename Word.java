package p1;

public class Word {
	String theWord = "";
	String partOfSpeech = "";
	
	public Word(String w, String pos) {
		theWord = w;
		partOfSpeech = pos;
	}
	
	public String getWord() {
		return this.theWord;
	}
	
	public String getPOS() {
		return this.partOfSpeech;		
	}
}
