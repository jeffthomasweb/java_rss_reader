/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Readxml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.net.*;
import java.net.http.*;
import java.io.*;
import java.util.*;
import org.xml.sax.InputSource;

public class App {
    //I used HttpClient to request the RSS Feed data. If you would like to read the RSS Feed data from a 
    //local text file uncomment the next line and add the text file name. 	 	
    //private static final String FILENAME = "local_text_file.txt";
    public static void main(String[] args) throws Exception {
	//Request the RSS feed. Uncomment lines 31-34 if using a local text file.
	HttpClient myhc = HttpClient.newHttpClient();
	HttpRequest myreq = HttpRequest.newBuilder(new URI("https://feeds.npr.org/1001/rss.xml")).build();
	HttpResponse<String> myresp = myhc.send(myreq, HttpResponse.BodyHandlers.ofString());
	String resp = myresp.body();        
	List<String> results = new ArrayList<String>();
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

          	DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new InputSource(new StringReader(resp)));
		//Uncomment the next line if reading RSS from a local text file
          	//Document doc = db.parse(new File(FILENAME));

                doc.getDocumentElement().normalize();

          	NodeList list = doc.getElementsByTagName("item");

          	for (int temp = 0; temp < list.getLength(); temp++) {

              		Node node = list.item(temp);

              		if (node.getNodeType() == Node.ELEMENT_NODE) {

                  		Element element = (Element) node;

                  		String title = element.getElementsByTagName("title").item(0).getTextContent();
                  		results.add(title);
				String description = element.getElementsByTagName("description").item(0).getTextContent();
				results.add(description);

                  		
                  		System.out.println("title : " + title);
                  		System.out.println("description : " + description);

			}
			
			}
		//Uncomment line 72 if you'd like to print the RSS Feed stories as a list. This would be a duplicate as we already printed
		//the stories using a for loop at line 50.   
		//System.out.println(results);
	} catch (ParserConfigurationException | SAXException | IOException e) {
          e.printStackTrace();
      }

  }

