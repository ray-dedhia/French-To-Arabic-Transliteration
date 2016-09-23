package p1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import org.jdom2.JDOMException;

public class FIAC5 {
	public static HashMap<String, HashMap<String, String>> map = new HashMap<>();
	//public static HashMap<String, PrintWriter> outMap = new HashMap<>();
	public static PrintWriter main;
	
	public FIAC5() {
		initMap();
	}

	public static void initMap() {
		// every word with short vowel (diacritics) & short (diacritic) + long
		// vowel
		
		/** All vowels: beginning (first letter of word), end => short vowel (for reference), omit, long vowel **/

		HashMap<String, String> beg = new HashMap<>(), endSV = new HashMap<>(), anyw = new HashMap<>(), endLV = new HashMap<>();
		map.put("beg", beg);
		map.put("endSV", endSV);
		map.put("anyw", anyw);
		map.put("endLV", endLV);

		anyw.put("b", "ب‎");
		anyw.put("p", "ب‎"); // no MSA equivalent for puh sound; mapped to ba';
								// also farsi/persian p
		anyw.put("d", "د‎‎");
		anyw.put("dˤ", "ض‎");
		anyw.put("dʒ", "ج‎");
		anyw.put("ð", "ذ‎");
		anyw.put("ðˤ", "ظ‎");
		anyw.put("c", "ك‎");
		anyw.put("f", "ف‎");
		anyw.put("ɡ", "ج‎"); // different font or something
		anyw.put("g", "ج‎"); // this one is a regular g (hard guh sound)
		anyw.put("h", "ه");
		anyw.put("ħ", "ح‎");
		anyw.put("j", "ي‎"); // ya' can also be the long vowel e
		anyw.put("k", "ك‎");
		anyw.put("l", "ل‎");
		anyw.put("m", "م‎‎");
		anyw.put("n", "ن‎");
		anyw.put("θ", "ث‎");
		anyw.put("q", "ق‎");
		anyw.put("r", "ر‎‎");
		anyw.put("s", "س‎");
		anyw.put("sˤ", "ص‎");
		anyw.put("ʃ", "ش‎");
		anyw.put("t", "ت");
		anyw.put("tˤ", "ط‎");
		anyw.put("w", "و‎");
		anyw.put("x", "خ");
		anyw.put("ɣ", "غ‎");
		anyw.put("z", "ز‎");
		anyw.put("ʕ", "ع‎");
		anyw.put("ʀ", "غ‎");
		anyw.put("v", "\u06A4"); // veh
		anyw.put("ʒ", "ج‎"); // z also corresponds to gim
		anyw.put("ʁ", "غ‎"); // ghayn
		anyw.put("ɚ", "ر‎"); // r colored vowel
		anyw.put("ŋ", "ن‎");
		anyw.put("ɲ", "ين‎"); // sounds like spanish enye //n + ya'

		// first letter: alif with hamza and kasrah underneath
		// if consonant before: consonant with kasrah underneath
		beg.put("ɛ", "\u0623" + "\u064E");
		endSV.put("ɛ", "\u064E");
		endLV.put("ɛ", "ي‎");
		//other.put("ɛ", "\u0627");

		// above consonant: fatha
		// first letter: alif with fatha above
		beg.put("a", "\u0623" + "\u064E");
		endSV.put("a", "\u064E");
		endLV.put("a", "\u0627");

		// consonant with fatha or consonant with alif
		// alif with fatha if first letter
		beg.put("ø", "\u0623" + "\u064E");
		endSV.put("ø", "\u064E");
		endLV.put("ø", "\u0627");

		// first letter: alif damma
		// above consonant: damma
		beg.put("œ", "\u0623" + "\u064F");
		endSV.put("œ", "\u064F");
		endLV.put("œ", "\u0627");

		beg.put("i", "\u0623" + "\u0650"); // alif hamza + kasrah if first letter
		endSV.put("i", "\u0650"); // otherwise kasrah (short vowel e) (add ya' to elongate vowel under previous consonant)
		//other.put("i", "\u0627");
		endLV.put("i", "ي‎");

		beg.put("y", "\u0623" + "\u0650"); // see i
		endSV.put("y", "\u0650"); // see i
		//other.put("y", "\u0627");
		endLV.put("y", "ي‎");

		// alif with damma if first letter
		// otherwise damma over consonant
		beg.put("u", "\u0623" + "\u064F");
		endSV.put("u", "\u064F");
		//other.put("u", "\u0627");
		endLV.put("u", "و‎");

		// fatha above consonant or alif with fatha above for first letter
		beg.put("ɑ", "\u0623" + "\u064E");
		endSV.put("ɑ", "\u064E");
		endLV.put("ɑ", "\u0627");
		/**NO; JUST USE FIRST**/
		//other.put("ɑ", "\u0627");		

		// first letter: alif with damma
		// otherwise damma
		beg.put("ɔ", "\u0623" + "\u064F"); // oh sound with waw
		endSV.put("ɔ", "\u064F");
		//other.put("ɔ", "\u0627");
		endLV.put("ɔ", "و‎");

		// alif with kasrah if first letter
		// otherwise consonant with ya' OR consonant with kasrah?
		beg.put("e", "\u0623" + "\u0650");
		endSV.put("e", "\u0650");
		endLV.put("e", "ي");
		//other.put("e", "\u0627");
		//other.put("e", "\u0650");

		// see u
		beg.put("o", "\u0623" + "\u064F");
		endSV.put("o", "\u064F");
		//other.put("o", "\u0627");
		endLV.put("o", "و‎");
		
		//see i
		beg.put("ɪ", "\u0623" + "\u0650");
		endSV.put("ɪ", "\u0650");
		//other.put("ɪ", "\u0627");
		endLV.put("ɪ", "ي‎");

		// alif with kasrah above for first letter
		// otherwise consonant with kasrah above
		beg.put("ə", "أ");
		endSV.put("ə", "\u064E");
		//other.put("ə", "\u0627");
		endLV.put("ə", "ي‎");
		beg.put("ǝ", "أ"); // different font or something
		endSV.put("ǝ", "\u064E"); // different font or something
		//other.put("ǝ", "\u0627"); // different font or something
		endLV.put("ǝ", "ي‎");

		// waw as a consonant (w sound)
		anyw.put("ɥ", "و‎");

		anyw.put("\u0303", ""); // this maps the combining tilde (which means
								// nasal sound) with nothing because it has no
								// arabic equivalent
		anyw.put("(", "");
		anyw.put(")", "");
		anyw.put(" ", " ");
		anyw.put("ˈ", "");

	}

	public static void getArabic(String pronun, String word) {
		String arabicShortVowels = "";
		for (int i = 0; i < pronun.length(); i++) {
			String letter = pronun.substring(i, i + 1);
			if (map.get("anyw").get(letter) != null) {
				arabicShortVowels += map.get("anyw").get(letter);
			} else if (map.get("beg").get(letter) != null && i == 0) {
				arabicShortVowels += map.get("beg").get(letter);
			} else if (map.get("endSV").get(letter) != null && i > 0 && i < pronun.length()) {
				//arabicNoVowels += map.get("end").get(letter);
				//if (map.get("other").get(letter) != null)
				//	repeat = true;
				arabicShortVowels += map.get("endSV").get(letter);
			} 
		}		
		
		//boolean repeat = false;
		String arabicNoVowels = "";
		for (int i = 0; i < pronun.length(); i++) {
			String letter = pronun.substring(i, i + 1);
			if (map.get("anyw").get(letter) != null) {
				arabicNoVowels += map.get("anyw").get(letter);
			} else if (map.get("beg").get(letter) != null && i == 0) {
				//arabic += map.get("beg").get(letter);
				arabicNoVowels += "\u0627";
			} //else if (map.get("end").get(letter) != null && i > 0 && i < pronun.length()) {
				//arabicNoVowels += map.get("end").get(letter);
				//if (map.get("other").get(letter) != null)
				//	repeat = true;
				// add nothing
			//} 
		}
		String arabicLongVowels = "";
		//if (repeat) {
			for (int i = 0; i < pronun.length(); i++) {
				String letter = pronun.substring(i, i + 1);
				if (map.get("anyw").get(letter) != null) {
					arabicLongVowels += map.get("anyw").get(letter);
				} else if (map.get("beg").get(letter) != null && i == 0) {
					//arabicLongVowels += map.get("beg").get(letter);
					arabicLongVowels += "\u0627";
				} else if (map.get("endLV").get(letter) != null && i > 0 && i < pronun.length()) {
					//if (map.get("other").get(letter) != null) {
					//	otherArabic += map.get("other").get(letter);
					//} else {
					//	otherArabic += map.get("end").get(letter);
					//}
					arabicLongVowels += map.get("endLV").get(letter);
				} 
			}
		//}
		
		/*for (String s : outMap.keySet()) {
			if (pronun.contains(s)) {
				//outMap.get(s).print(word.substring(6) + "\t\t\t\t\t\t\t\t");
				//outMap.get(s).print(pronun + "\t\t\t\t\t\t\t\t");
				//outMap.get(s).print(arabic + "\t\t\t\t\t\t\t\t");
				//outMap.get(s).println();
				//outMap.get(s).printf("%-40s %-40s %-40s %-40s\n", word.substring(6), pronun, arabic, otherArabic);
				outMap.get(s).println(word + "\t\t\t\t\t\t\t\t" + pronun + "\t\t\t\t\t\t\t\t" + arabicShortVowels + "\t\t\t\t\t\t\t\t" + arabicNoVowels + "\t\t\t\t\t\t\t\t" + arabicLongVowels);
				outMap.get(s).println();
			}
		}*/
		
		main.println(word + "\t" + pronun + "\t" + arabicShortVowels + "\t" + arabicNoVowels + "\t" + arabicLongVowels);

	
	}

	public static void main(String[] args) throws JDOMException, IOException {
		new FIAC5();
		
		/*outMap.put("ɛ", new PrintWriter(new FileWriter("/home/Documents/check_vowels/ɛ.txt")));
		outMap.put("a", new PrintWriter(new FileWriter("/home/Documents/check_vowels/a.txt")));
		outMap.put("ø", new PrintWriter(new FileWriter("/home/Documents/check_vowels/ø.txt")));
		outMap.put("œ", new PrintWriter(new FileWriter("/home/Documents/check_vowels/œ.txt")));
		outMap.put("i", new PrintWriter(new FileWriter("/home/Documents/check_vowels/i.txt")));
		outMap.put("y", new PrintWriter(new FileWriter("/home/Documents/check_vowels/y.txt")));
		outMap.put("u", new PrintWriter(new FileWriter("/home/Documents/check_vowels/u.txt")));
		outMap.put("ɑ", new PrintWriter(new FileWriter("/home/Documents/check_vowels/ɑ.txt")));
		outMap.put("ɔ", new PrintWriter(new FileWriter("/home/Documents/check_vowels/ɔ.txt")));
		outMap.put("e", new PrintWriter(new FileWriter("/home/Documents/check_vowels/e.txt")));
		outMap.put("o", new PrintWriter(new FileWriter("/home/Documents/check_vowels/o.txt")));
		outMap.put("ɪ", new PrintWriter(new FileWriter("/home/Documents/check_vowels/ɪ.txt")));
		outMap.put("ə", new PrintWriter(new FileWriter("/home/Documents/check_vowels/ə.txt")));
		outMap.put("ǝ", new PrintWriter(new FileWriter("/home/Documents/check_vowels/ə.txt")));*/
		//main = new PrintWriter(new FileWriter("/home/Documents/FTTA2.txt"));
		//BufferedReader in = new BufferedReader(new FileReader("/home/Documents/Words.txt"));
		
		/*for (PrintWriter pw : outMap.values()) {
			//pw.printf("%-40s %-40s %-40s %-35s\n", "Word", "Pronun.", "Arabic", "With Mod.");
			pw.println("Word\t\t\t\t\t\t\t\tPronun.\t\t\t\t\t\t\t\tArabic (Short Vowels)\t\t\tArabic (No Vowels)\t\t\t\tArabic (Long Vowels)");
			pw.println();
		}*/
		
		main.println("Word\tPronun.\tArabic (Short Vowels)\tArabic (No Vowels)\tArabic (Long Vowels)");
		main.println();
		
		String thisLine = "";
		String word = "";
		while ((thisLine = in.readLine()) != null) {
			if (thisLine.contains("WORD")) {
				String wo = thisLine.substring(6);
				if (/*w.length()==5 &&*/ !wo.matches(".*(\\p{Punct}|\\p{Digit}).*")) {
					word = wo;
				} else {
					word = "";
				}
			} else if (thisLine.contains("PRONUNCIATION")) {
				String pr = thisLine.substring(15);
				if (word.length()>0 /*&& p.length()==5*/)
					getArabic(pr, word);
			}
		}
		
		in.close();
		
		/*for (String key : outMap.keySet()) {
			outMap.get(key).close();
		}*/
		
		main.close();
		
	}
}

/*
 * ipa x (gutteral "kh" sound followed by "huh") is not a native phoneme of
 * French, but occurs in loan words such as khamsin, manhua or jota. ignore (haa
 * or xaa)
 */
