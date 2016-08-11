package com.cooksys.socket.assignment;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.cooksys.socket.assignment.model.Config;
import com.cooksys.socket.assignment.model.LocalConfig;
import com.cooksys.socket.assignment.model.RemoteConfig;
import com.cooksys.socket.assignment.model.Student;

public class SetupXml {

	public static void main(String[] args) throws JAXBException {
		String host = "localhost";
		int port = 12345;
		File stfile = new File("config/student.xml");
		File cffile = new File("config/config.xml");
		
		Student st = new Student("Ben", "Ricks", "Eclipse",  "Java",  "Stuff and Things at 10.1.1.109");
		LocalConfig lc = new LocalConfig(port);
		RemoteConfig rc = new RemoteConfig(host, port);
		Config cf = new Config( lc,  rc,  "config/student.xml");
		
		JAXBContext context = JAXBContext.newInstance(Student.class, Config.class);
		Marshaller marshal = context.createMarshaller();
		Unmarshaller unmarshal = context.createUnmarshaller();
		
		marshal.marshal(st, stfile);
		marshal.marshal(cf,  cffile);
		
		
	
		
		

	}

}
