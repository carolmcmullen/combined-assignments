package com.cooksys.ftd.assignments.file;

import com.cooksys.ftd.assignments.file.model.Contact;
import com.cooksys.ftd.assignments.file.model.Instructor;
import com.cooksys.ftd.assignments.file.model.Session;
import com.cooksys.ftd.assignments.file.model.Student;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    /**
     * Creates a {@link Student} object using the given studentContactFile.
     * The studentContactFile should be an XML file containing the marshaled form of a
     * {@link Contact} object.
     *
     * @param studentContactFile the XML file to use
     * @param jaxb the JAXB context to use
     * @return a {@link Student} object built using the {@link Contact} data in the given file
     */
    public static Student readStudent(File studentContactFile, JAXBContext jaxb) {
    	try {
    		Unmarshaller jaxbUnmarshaller = jaxb.createUnmarshaller();
    		Student student = (Student) jaxbUnmarshaller.unmarshal(studentContactFile);
    		return student;
    	} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null; 
    }

    /**
     * Creates a list of {@link Student} objects using the given directory of student contact files.
     *
     * @param studentDirectory the directory of student contact files to use
     * @param jaxb the JAXB context to use
     * @return a list of {@link Student} objects built using the contact files in the given directory
     */
    public static List<Student> readStudents(File studentDirectory, JAXBContext jaxb) {
    	List<Student> students = new ArrayList<>();
		File[] listOfFiles = studentDirectory.listFiles();

		    for (int i = 0; i < listOfFiles.length; i++) {
		    	Contact studentContact = readContact(listOfFiles[i],jaxb);
		    	Student student = new Student();
		    	student.setContact(studentContact);
		    	students.add(student);
		    } 
		return students;
    }

    /**
     * Creates an {@link Instructor} object using the given instructorContactFile.
     * The instructorContactFile should be an XML file containing the marshaled form of a
     * {@link Contact} object.
     *
     * @param instructorContactFile the XML file to use
     * @param jaxb the JAXB context to use
     * @return an {@link Instructor} object built using the {@link Contact} data in the given file
     */
    public static Instructor readInstructor(File instructorContactFile, JAXBContext jaxb) {
        	try {
        		Unmarshaller jaxbUnmarshaller = jaxb.createUnmarshaller();
        		Instructor instructor = (Instructor) jaxbUnmarshaller.unmarshal(instructorContactFile);
        		return instructor;
        	} catch (JAXBException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	return null; 
        }
    
    public static Contact readContact(File infile, JAXBContext jaxb) {
    	try {
    		Unmarshaller jaxbUnmarshaller = jaxb.createUnmarshaller();
    		Contact contact = (Contact) jaxbUnmarshaller.unmarshal(infile);
    		return contact;
    	} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null; 
    }


    /**
     * Creates a {@link Session} object using the given rootDirectory. A {@link Session}
     * root directory is named after the location of the {@link Session}, and contains a directory named
     * after the start date of the {@link Session}. The start date directory in turn contains a directory named
     * `students`, which contains contact files for the students in the session. The start date directory
     * also contains an instructor contact file named `instructor.xml`.
     *
     * @param rootDirectory the root directory of the session data, named after the session location
     * @param jaxb the JAXB context to use
     * @return a {@link Session} object built from the data in the given directory
     */
    public static Session readSession(File rootDirectory, JAXBContext jaxb) {
    	try {
    		Unmarshaller jaxbUnmarshaller = jaxb.createUnmarshaller();
    		Session session = (Session) jaxbUnmarshaller.unmarshal(rootDirectory);
    		return session;
    	} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null; 
    }

    /**
     * Writes a given session to a given XML file
     *
     * @param session the session to write to the given file
     * @param sessionFile the file to which the session is to be written
     * @param jaxb the JAXB context to use
     */
    public static void writeSession(Session session, File sessionFile, JAXBContext jaxb) {
  	  try {

  		
  		Marshaller jaxbMarshaller = jaxb.createMarshaller();

  		// output pretty printed
  		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

  		jaxbMarshaller.marshal(session, sessionFile);
  		jaxbMarshaller.marshal(session, System.out);

  	      } catch (JAXBException e) {
  		e.printStackTrace();
  	      }
    	
    }

    /**
     * Main Method Execution Steps:
     * 1. Configure JAXB for the classes in the com.cooksys.serialization.assignment.model package
     * 2. Read a session object from the <project-root>/input/memphis/ directory using the methods defined above
     * 3. Write the session object to the <project-root>/output/session.xml file.
     *
     * JAXB Annotations and Configuration:
     * You will have to add JAXB annotations to the classes in the com.cooksys.serialization.assignment.model package
     *
     * Check the XML files in the <project-root>/input/ directory to determine how to configure the {@link Contact}
     *  JAXB annotations
     *
     * The {@link Session} object should marshal to look like the following:
     *      <session location="..." start-date="...">
     *           <instructor>
     *               <contact>...</contact>
     *           </instructor>
     *           <students>
     *               ...
     *               <student>
     *                   <contact>...</contact>
     *               </student>
     *               ...
     *           </students>
     *      </session>
     */
    public static void main(String[] args) {
    	
    	try {
    		//created a file object 
    		File instructorFile = new File("C:/users/ftd-1/Documents/GitHub/combined-assignments/4-file-io-serialization/input/memphis/08-08-2016/instructor.xml");
			JAXBContext instructorjaxbContext = JAXBContext.newInstance(Instructor.class);
			JAXBContext sessionjaxbContext = JAXBContext.newInstance(Session.class);
			
			Session session = new Session();
			Instructor instructor = new Instructor();
			Contact contact = readContact(instructorFile,instructorjaxbContext);
			instructor.setContact(contact);
			session.setInstructor(instructor);
			session.setLocation("Memphis");
			session.setStartDate("08-08-2016");
			File studentDirectory = new File("C:/users/ftd-1/Documents/GitHub/combined-assignments/4-file-io-serialization/input/memphis/08-08-2016/students");
			session.setStudents(readStudents(studentDirectory, instructorjaxbContext));
			File sessionFile = new File("C:/users/ftd-1/Documents/GitHub/combined-assignments/4-file-io-serialization/output/session.xml");
			writeSession(session, sessionFile, sessionjaxbContext);
    	} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
