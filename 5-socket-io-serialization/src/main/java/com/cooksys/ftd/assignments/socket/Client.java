package com.cooksys.ftd.assignments.socket;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.cooksys.ftd.assignments.socket.model.Config;

public class Client extends Utils {

    /**
     * The client should load a {@link com.cooksys.ftd.assignments.socket.model.Config} object from the
     * <project-root>/config/config.xml path, using the "port" and "host" properties of the embedded
     * {@link com.cooksys.ftd.assignments.socket.model.RemoteConfig} object to create a socket that connects to
     * a {@link Server} listening on the given host and port.
     *
     * The client should expect the server to send a {@link com.cooksys.ftd.assignments.socket.model.Student} object
     * over the socket as xml, and should unmarshal that object before printing its details to the console.
     */
    public static void main(String[] args) {
    	String configFilePath = "C:/Users/ftd-1/Desktop/GitHub/combined-assignments/5-socket-io-serialization/config/config.xml";
		try {
			// set config object from config.xml
			Config config = loadConfig(configFilePath, JAXBContext.newInstance(Config.class));
			// creating a new Socket client 
			System.out.println("Starting connection...");
			try(Socket client = new Socket(config.getRemote().getHost(),
					config.getRemote().getPort()))
			{
				System.out.println("Just Connected");
				// creating an output stream to the Server
				OutputStream outToServer = client.getOutputStream();
				DataOutputStream out = new DataOutputStream(outToServer);
				
				out.writeUTF("Server says "+ client.getLocalSocketAddress());
				// creating an input stream from the Server
				InputStream inFromServer = client.getInputStream();
				DataInputStream in = new DataInputStream(inFromServer);
				System.out.println("Server says "+ in.readUTF());
			} catch(SocketTimeoutException ex) {
				System.out.println("Nobody connected :(");		
				
			}
		}
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();					
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
    	
    }
}
