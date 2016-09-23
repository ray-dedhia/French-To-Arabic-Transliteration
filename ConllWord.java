package p1;

public class ConllWord {
	public String word, arrowLabel, posCG, posFG;
	public int parentIndex, index;
	public boolean isCW;
	
	public ConllWord(String i, String w, String cg, String fg, String pI, String aL) {
		index = Integer.parseInt(i) - 1;
		word = w;
		arrowLabel = aL;
		posCG = cg;
		posFG = fg;
		parentIndex = Integer.parseInt(pI) - 1;
		isCW = true;
	}
	
	public ConllWord(int i, String w, String cg, String fg, int pI, String aL) {
		index = i;
		word = w;
		arrowLabel = aL;
		posCG = cg;
		posFG = fg;
		parentIndex = pI;
		isCW = true;
	}
	
	public ConllWord(String w) { //For just String input (no other data known)
		index = 0; 
		word = w;
		posCG = "";
		posFG = "";
		parentIndex = 0;
		arrowLabel = "";
		isCW = false;
	}
	
	public ConllWord() { //No data known yet
		index = 0; 
		word = "";
		posCG = "";
		posFG = "";
		parentIndex = 0;
		arrowLabel = "";
		isCW = true;
	}
	
	public String getWord() {
		return word;
	}
	
	public String getAL() {
		return arrowLabel;
	}
	
	public String getPOS() {
		return posCG + ", " + posFG;
	}
	
	public int getPI() {
		return parentIndex;
	}
	
	public int getIndex() {
		return index;
	}
	
	public String getCG() {
		return posCG;
	}
	
	public String getFG() {
		return posFG;
	}
	
	public ConllWord setPI(int pi) {
		parentIndex = pi;
		return this;
	}
	
	public ConllWord setAL(String al) {
		arrowLabel = al;
		return this;
	}
	
	public boolean isCW() {
		return isCW;
	}
}
