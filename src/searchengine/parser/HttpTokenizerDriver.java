/**  
 *  
 * This program is part of an implementation for the Mini-Google project which is 
 * about developing a search engine using efficient Datastructures.
 * 
 * Created by Mahender K on 12-10-2009
 */
package searchengine.parser;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.net.MalformedURLException;
import java.net.URL;
import searchengine.url.URLTextReader;

public class HttpTokenizerDriver {
	public static void main(String args[]) throws IOException{
		/*
		if(args.length < 1){
			System.out.println("Usage java searchengine.parser.HttpTokenizerDriver <URL>");
		}
		else{
			return;
		}*/
		StreamTokenizer st=null;
		try {
			st = new StreamTokenizer(new URLTextReader(new URL("http://obp.msitprogram.net")));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		// Set up the appropriate defaults
		st.eolIsSignificant(false);
		st.lowerCaseMode(true);
		st.wordChars('<','<');
		st.wordChars('>','>');
		st.wordChars('/','/');
		st.wordChars('=','=');
		st.wordChars('@','@');
		st.wordChars('!','!');
		st.wordChars('-','-');
		st.ordinaryChar('.');
		st.ordinaryChar('?');
		
		HttpTokenizer ht = new HttpTokenizer(st);
		int i=0;
		while((i=ht.nextToken())!=HttpTokenizer.HT_EOF){
			/*
			if(i==HttpTokenizer.HT_WORD){
				System.out.println("Word: "+st);
			}/*else if(i==st.TT_NUMBER){
				System.out.println("Line no:"+st.lineno()+"\tI :"+i+"\t"+st.nval);
			}else{
				System.out.println("Line no:"+st.lineno()+"\tWS :"+i);
			}*/
			System.out.println(ht+", Token "+i);
		}
	}
}
