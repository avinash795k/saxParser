package testParser;

import java.util.ArrayList;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Handler
 * 
 * @author AvinashKumarMr
 */
public class Handler extends DefaultHandler{
	//This is the list which shall be populated while parsing the XML.
    private ArrayList<Context> userList = new ArrayList<Context>();
 
    //As we read any XML element we will push that in this stack
    private Stack<String> elementStack = new Stack<String>();
 
    //As we complete one user block in XML, we will push the User instance in userList
    private Stack<Context> objectStack = new Stack<Context>();
	
	@Override
	public void startDocument() throws SAXException
    {
        System.out.println("start of the document");
    }
 
    @Override
	public void endDocument() throws SAXException
    {
        System.out.println("end of the document document");
    }
 
    @Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
        //Push it in element stack
        this.elementStack.push(qName);
 
        //If this is start of 'user' element then prepare a new User instance and push it in object stack
        if ("user".equals(qName))
        {
            //New User instance
            Context user = new Context();
 
            //Set all required attributes in any XML element here itself
            if(attributes != null && attributes.getLength() == 1)
            {
                user.setId(Integer.parseInt(attributes.getValue(0)));
            }
            this.objectStack.push(user);
        }
        else if ("parent".equals(qName))
        {
        	Context user = new Context();
        	if(attributes != null && attributes.getLength() == 1)
        	{
        		user.setId(Integer.parseInt(attributes.getValue(0)));
        	}
        	this.objectStack.push(user);
        }
    }
 
    @Override
	public void endElement(String uri, String localName, String qName) throws SAXException
    {
        //Remove last added  element
        this.elementStack.pop();
 
        //User instance has been constructed so pop it from object stack and push in userList
        if ("user".equals(qName))
        {
            Context object = this.objectStack.pop();
            this.userList.add(object);
        }
        if ("parent".equals(qName))
        {
            Context object = this.objectStack.pop();
            this.userList.add(object);
        }
    }
 
    /**
     * This will be called everytime parser encounter a value node
     * */
    @Override
	public void characters(char[] ch, int start, int length) throws SAXException
    {
        String value = new String(ch, start, length).trim();
 
        if (value.length() == 0)
        {
            return; // ignore white space
        }
        
        //handle the value based on to which element it belongs
        if ("firstname".equals(currentElement()))
        {
            Context user = this.objectStack.peek();
            user.setFirstName(value);
        }
        else if ("lastname".equals(currentElement()))
        {
            Context user = this.objectStack.peek();
            user.setLastName(value);
        }
    }
 
    /**
     * Utility method for getting the current element in processing
     * */
    private String currentElement()
    {
        return this.elementStack.peek();
    }
 
    //Accessor for userList object
    public ArrayList<Context> getUsers()
    {
        return userList;
    }
}
