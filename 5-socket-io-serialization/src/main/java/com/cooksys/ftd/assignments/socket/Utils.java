package com.cooksys.ftd.assignments.socket;

import com.cooksys.ftd.assignments.socket.model.Config;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Shared static methods to be used by both the {@link Client} and {@link Server} classes.
 */
public class Utils {
    /**
     * @param Client 
     * @return a {@link JAXBContext} initialized with the classes in the
     * com.cooksys.socket.assignment.model package
     */
    public static JAXBContext createJAXBContext(Client client) {
        try{
			return JAXBContext.newInstance(Client.class);         	 
        } catch (JAXBException e) {
        	e.printStackTrace();
        }
    	return null; 
        
    }
    	

	/**
     * Reads a {@link Config} object from the given file path.
     *
     * @param configFilePath the file path to the config.xml file
     * @param jaxb the JAXBContext to use
     * @return a {@link Config} object that was read from the config.xml file
     */
    //I changed configFilePath to File instead of String 
    public static Config loadConfig(String configFilePath, JAXBContext jaxb) {
    	try {
    		File configFile = new File(configFilePath);
    		Unmarshaller jaxbUnmarshaller = jaxb.createUnmarshaller();
    		Config config = (Config) jaxbUnmarshaller.unmarshal(configFile);
    		return config;
    	} catch (JAXBException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    public static void unloadConfig(Config config, String configFilePath, JAXBContext jaxb) {
		try {
			File configFile = new File(configFilePath);
			Marshaller jaxbMarshaller = jaxb.createMarshaller();
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(config, configFile);
			jaxbMarshaller.marshal(config, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
}
