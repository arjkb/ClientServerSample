/*
	Server program
*/

import java.net.*;
import java.io.*;
import java.util.*;
public class MyServer {
	public static void main(String args[]) throws IOException	{
		final int PORT_ADDRESS = 3000;
		
		//create a socket which listens to port 3000
		ServerSocket ss = new ServerSocket(3000);	
		Socket soc = null;
		
		ss.setSoTimeout(30000);	
		/*
			sets the time-out value for how long the server waits for a 
			client during the accept()
		*/	

		try	{
			//accepts a request from the client
			soc = ss.accept();

			//attach the other end of the socket to a Scanner object
			Scanner in = new Scanner( soc.getInputStream() );

			while( in.hasNextLine() )	{
				String message = in.nextLine();	//retrieve the message...
				System.out.print("\n SERVER: " + message); //print the message
			}
		}
		catch(Exception E)	{
			System.out.println("\n Oops! Something went wrong!");
			E.printStackTrace();
		}
		finally	{
			soc.close();
			ss.close();
			System.out.println("\n Goodbye from server!");
		}
	}
}
