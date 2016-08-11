package com.cooksys.socket.assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.cooksys.socket.assignment.model.Config;
import com.cooksys.socket.assignment.model.Student;

public class Client {
	
    public static void main(String[] args) throws JAXBException {
    	String path = "config/config.xml";
    	
    	JAXBContext jaxb = Utils.createJAXBContext();
		Unmarshaller unmarshal = jaxb.createUnmarshaller();
		
		Student st = null;
		
		Config con = Utils.loadConfig(path, jaxb);

		try(
    			Socket s = new Socket(con.getRemote().getHost(), con.getRemote().getPort()); 
				InputStream in = s.getInputStream(); 
				Reader base = new InputStreamReader(in); 
				BufferedReader r = new BufferedReader(base);
				OutputStream out = s.getOutputStream();
				Writer write = new OutputStreamWriter(out);
				BufferedWriter w = new BufferedWriter(write);) {
    		
    		st = (Student) unmarshal.unmarshal(r);
    				
    		System.out.println(st.toString());
    		
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    	
    	
    }
}
