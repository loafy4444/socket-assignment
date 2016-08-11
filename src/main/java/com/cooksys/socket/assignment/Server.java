package com.cooksys.socket.assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.cooksys.socket.assignment.model.Config;
import com.cooksys.socket.assignment.model.Student;

public class Server {

	/**
	 * Reads a {@link Student} object from the given file path
	 *
	 * @param studentFilePath
	 *            the
	 * @param jaxb
	 * @return
	 * @throws JAXBException
	 */
	public static Student loadStudent(String studentFilePath, JAXBContext jaxb) throws JAXBException {
		Unmarshaller unmarshal = jaxb.createUnmarshaller();
		Student st = (Student) unmarshal.unmarshal(new File(studentFilePath));
		return st;
	}

	public static void main(String[] args) throws JAXBException {
		String path = "config/config.xml";
		
		JAXBContext jaxb = Utils.createJAXBContext();
		Marshaller marshal = jaxb.createMarshaller();
		
		Config con = Utils.loadConfig(path, jaxb);
			
		Student st = loadStudent(con.getStudentFilePath(), jaxb);
		
		try (
				ServerSocket ss = new ServerSocket(con.getLocal().getPort());
				){
			while (true) {
				try (Socket s = ss.accept(); 
						InputStream in = s.getInputStream(); 
						Reader base = new InputStreamReader(in); 
						BufferedReader r = new BufferedReader(base);
						OutputStream out = s.getOutputStream();
						Writer write = new OutputStreamWriter(out);
						BufferedWriter w = new BufferedWriter(write);) {

					marshal.marshal(st, w);
					out.flush();
							
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} 

	}
}
