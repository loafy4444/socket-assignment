package com.cooksys.socket.assignment.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class LocalConfig {
    @XmlAttribute
    private int port;

    public LocalConfig(){
    	
    }

    public LocalConfig(int port) {
		this.port = port;
	}

	public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
