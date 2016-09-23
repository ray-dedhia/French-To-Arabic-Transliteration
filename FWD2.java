package p1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.jdom2.JDOMException;
import javax.xml.parsers.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class FWD2 {

	public static HashMap<String, String> frWords = new HashMap<>(); //word, pronunciation
	
	public FWD2() {
		
	}
	
	public static class SaxHandler extends DefaultHandler {
		PrintWriter out;
		String title = "";
		boolean inTitle = false;
		boolean inText = false;
		boolean prevTitleValid = false;

		StringBuilder buf = new StringBuilder();
		StringBuilder buf2 = new StringBuilder();
		
		public SaxHandler(PrintWriter pw) {
			out = pw;
		}
		
	    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
	    	//System.out.println("I just started an element!");
	    	//System.out.println("localName: " + localName);
	    	if (localName.equals("title")) {
	    		inTitle = true;
	    	} else if (localName.equals("text")) {
	    		inText = true;
	    	}
	    }

	    public void endElement(String uri, String localName, String qName) throws SAXException {
	    	//System.out.println("I just ended an element!");
	    	//System.out.println("localName: " + localName);
	    	if (localName.equals("title")) {
	    		inTitle = false;
	    		title = buf.toString();
	    		buf.setLength(0);
	    		if (!title.contains(":")) {
	    			prevTitleValid = true;
	    		} else {
	    			prevTitleValid = false;
	    		}	    		
	    	} else if (localName.equals("text")) {
	    		inText = false;
	    		String text = buf2.toString();
	    		buf2.setLength(0);
	    		if (prevTitleValid) {
	    			if (text.contains("== {{langue|fr}} ==")) {
	    				int index = text.indexOf("''' {{pron|");
	    				String pron = "";
	    				if (index != -1) {
		    				for (int i = index + 11; i < text.length(); i++) {
		    					if (text.substring(i, i+1).equals("|") || text.substring(i, i+1).equals("}")) {
		    						break;
		    					} else {
		    						pron += text.substring(i, i+1);
		    					}
		    				}
		    				if (pron.length() > 0) {
			    				//out.println("WORD: " + title);
			    				//out.println("PRONUNCIATION: " + pron);
		    					frWords.put(title, pron);
		    				}
	    				} 
	    			}
	    		}
	    	}
	    }

	    public void characters(char ch[], int start, int length) throws SAXException {
	    	if (inTitle) {
	    		buf.append(ch, start, length);
	    	} else if (inText && !buf2.toString().contains("''' {{pron|") && !buf2.toString().contains("''' {{m}}")) {
	    		buf2.append(ch, start, length);
	    		//if (buf2.length() > 500000)
	    		//	out.println("length: " + buf2.length()); //nothing greater than this within first 16000 words
	    	}
	    	
	    }

	}

	public static void main(String[] args) throws JDOMException, IOException, ParserConfigurationException, SAXException {
		//PrintWriter out = new PrintWriter(new FileWriter("/home/Documents/Words.txt"));
		SAXParserFactory spf = SAXParserFactory.newInstance();
		spf.setNamespaceAware(true);
		SAXParser saxParser = spf.newSAXParser();
		XMLReader xmlReader = saxParser.getXMLReader();
		xmlReader.setContentHandler(new SaxHandler(out));
		//xmlReader.parse("/home/Documents/frwiktionary-latest-pages-articles.xml");
		
		 Map<String, String> map = new TreeMap<String, String>(frWords); 
         System.out.println("Done sorting");
         Set set = map.entrySet();
         Iterator iterator = set.iterator();
         while(iterator.hasNext()) {
              Map.Entry me = (Map.Entry)iterator.next();
              out.println("WORD: " + me.getKey());
              out.println("PRONUNCIATION: " + me.getValue());
         }
		out.close();
	}
}