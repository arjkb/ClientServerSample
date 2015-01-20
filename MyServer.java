/*
	File	: MyServer.java
	CODER	: Arjun Krishna Babu
	GITHUB	: https://github.com/arjunkbabu/ClientServerSample

	SERVER PROGRAM
	Takes a request from a client program for a webpage (name as string),
	and send the webpage back to the client (if the webpage is found).
*/

import java.net.*;
import java.io.*;
import java.util.*;

public class MyServer {
	public static void main(String args[]) throws IOException, FileNotFoundException	{
		final int PORT_ADDRESS = 3001;
		String webpage; //variable to store the name of the webpage
		
		//create a socket which listens to port identified by PORT_ADDRESS
		ServerSocket ss = new ServerSocket(PORT_ADDRESS);	

		Socket clientSoc = null;
		
		ss.setSoTimeout(30000);	
		/*	sets the time-out value for how long the server waits for a 
			client during the accept()
		*/	

		System.out.println("\n Server started");

		try	{
			// accepts a request from the client
			clientSoc = ss.accept();
			// The client and server now conceptually share the same socket

			// attach the other end of the socket to a Scanner object
			Scanner in = new Scanner( clientSoc.getInputStream() );

			PrintWriter out = new PrintWriter(clientSoc.getOutputStream(),true);

			webpage = in.nextLine();	
			// store name of the webpage requested by the client (via socket)
		
			System.out.println("\n Recieved request for " + webpage);
				
			FileReader fin = new FileReader(webpage);
			/*	Opens the file.
				Throws FileNotFoundException if the file is not availiable.
			*/

			Scanner fileScanner = new Scanner( fin );
			//scanner object associated with the file


			//reads from file (webpage) line-by-line and send it to client:
			while( fileScanner.hasNextLine() )	{
				out.println( fileScanner.nextLine() );
			}

		}
		catch(Exception E)	{
			System.out.println("\n Oops! Something went wrong!");
			E.printStackTrace();
		}
		finally	{
			//tidy it up!
			clientSoc.close();
			ss.close();
			System.out.println("\n Goodbye from server!");
		}
	}
}
