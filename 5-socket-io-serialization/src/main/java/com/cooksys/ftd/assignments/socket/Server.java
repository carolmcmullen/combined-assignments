package com.cooksys.ftd.assignments.socket;

import com.cooksys.ftd.assignments.socket.model.Config;
import com.cooksys.ftd.assignments.socket.model.LocalConfig;
import com.cooksys.ftd.assignments.socket.model.RemoteConfig;
import com.cooksys.ftd.assignments.socket.model.Student;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Server extends Utils {

	/**
	 * Reads a {@link Student} object from the given file path
	 *
	 * @param studentFilePath
	 *            the file path from which to read the student config file
	 * @param jaxb
	 *            the JAXB context to use during unmarshalling
	 * @return a {@link Student} object unmarshalled from the given file path
	 */
	public static Student loadStudent(String studentFilePath, JAXBContext jaxb) {
		try {
			File studentFile = new File(studentFilePath);
			Unmarshaller jaxbUnmarshaller = jaxb.createUnmarshaller();
			Student student = (Student) jaxbUnmarshaller.unmarshal(studentFile);
			return student;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * The server should load a
	 * {@link com.cooksys.ftd.assignments.socket.model.Config} object from the
	 * <project-root>/config/config.xml path, using the "port" property of the
	 * embedded {@link com.cooksys.ftd.assignments.socket.model.LocalConfig}
	 * object to create a server socket that listens for connections on the
	 * configured port.
	 *
	 * Upon receiving a connection, the server should unmarshal a
	 * {@link Student} object from a file location specified by the config's
	 * "studentFilePath" property. It should then re-marshal the object to xml
	 * over the socket's output stream, sending the object to the client.
	 *
	 * Following this transaction, the server may shut down or listen for more
	 * connections.
	 */
	public static void main(String[] args) {
		//
		String configFilePath = "C:/Users/ftd-1/Desktop/GitHub/combined-assignments/5-socket-io-serialization/config/config.xml";
		Config c = new Config();
		c.setStudentFilePath("C:/Users/ftd-1/Desktop/GitHub/combined-assignments/5-socket-io-serialization/config/student.xml");
		LocalConfig l = new LocalConfig();
		l.setPort(4553);
		RemoteConfig r = new RemoteConfig();
		r.setHost("192.168.1.22");
		r.setPort(4553);
		c.setLocal(l);
		c.setRemote(r);
		
		try {
			// marshalling config (creating my config.xml file)
			unloadConfig(c,configFilePath,JAXBContext.newInstance(Config.class));
			// unmarshalling config 
			Config config = loadConfig(configFilePath, JAXBContext.newInstance(Config.class));
			// creating a new socket 
			try(ServerSocket serverSocket = new ServerSocket(config.getRemote().getPort()))
			{
				serverSocket.setSoTimeout(1000000);
				
				while(true){
					try{
						System.out.println("Waiting for client");
						Socket server = serverSocket.accept();
						
						System.out.println("Connected");
						
						//DataInputStream in = new DataInputStream(server.getInputStream());
						//System.out.println(in.readUTF());
						// create output stream
						DataOutputStream out = new DataOutputStream(server.getOutputStream());
						
						// unmarshalling student
						Student student = loadStudent(config.getStudentFilePath(),JAXBContext.newInstance(Student.class));
						//marshalling student
						unloadStudent(student,JAXBContext.newInstance(Student.class),out);
						//Gson gson = new Gson();
						//String jsonString = gson.toJson(student);
						//out.writeUTF("Student: " + jsonString);
						//out.close();
					}
					catch(SocketTimeoutException e){
						e.printStackTrace();
					}
				}
				
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

	// marshalling student 
	public static void unloadStudent(Student student, JAXBContext jaxb, DataOutputStream out) {
		try {
			
			Marshaller jaxbMarshaller = jaxb.createMarshaller();
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(student, out);
			//jaxbMarshaller.marshal(student, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

}
