package testParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * testParser
 * 
 * @author AvinashKumarMr
 */
public class testParser {
	public testParser() {
		super();
	}
	
	public static void main(String[] args) throws FileNotFoundException
    {
        //Locate the file
        File xmlFile = new File("C:\\Users\\AvinashKumarMr\\eclipse-workspace\\testParser\\src\\testParser\\sample.xml");
 
        //Create the parser instance
        xmlParser parser = new xmlParser();
 
        //Parse the file
        ArrayList<Context> users = parser.parseXml(new FileInputStream(xmlFile));
 
        //Verify the result
        System.out.println(users);
    }
}
