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
	
    public static void main(String[] args) {
    	String path = "config/config.xml";
    	Student st = null;
    	Config con = null;
    	Unmarshaller unmarshal = null;
    	
		try {
			JAXBContext jaxb = Utils.createJAXBContext();
			unmarshal = jaxb.createUnmarshaller();
			con = Utils.loadConfig(path, jaxb);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
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
    		
    	}catch(IOException | JAXBException e){
    		e.printStackTrace();
    	}
    	
    	
    }
}
