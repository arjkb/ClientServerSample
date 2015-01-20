/*
	File	: MyClient.java
	CODER	: Arjun Krishna Babu
	GITHUB	: https://github.com/arjunkbabu/ClientServerSample

	CLIENT PROGRAM

		Takes the name of the required webpage (through the commant line), 
		and send this webpage to the server program.

		The server program processes this request, and sends the
		requested webpage back to the client.

*/

import java.net.*;		//for socket and networking functionalities
import java.io.*; 		//for input-output functionalities
import java.util.*;

public class MyClient	{
	public static void main(String args[]) throws IOException	{
		final int PORT_ADDRESS = 3001;
		//create a new Socket that listens to port 3000 on the same machine

		String webrequest = args[0];
		/*	stores the name of the webpage to fetch onto a string.
			The name of the required webpage is passed as a command-line
			argument.
		*/
		
		Socket s = new Socket("localhost", PORT_ADDRESS);

		System.out.println("\n Connection Estb Status: " + s.isConnected() );

		try	{
			s.setSoTimeout(30000); //value in milliseconds
			//waits 30 seconds to  establish connection with server

			PrintWriter out = new PrintWriter( s.getOutputStream() , true);
			Scanner in = new Scanner( s.getInputStream() );
			/*
				Associates a PrintWriter object with the output stream,
				and a Scanner object with the input stream of the socket.
			*/

			out.println(webrequest); //send request to server
			
//			System.out.println("GET BACK STREAM?: " + in.hasNextLine());

			//prints back the reply from the server:
			while( in.hasNextLine() )	{
				System.out.println( in.nextLine() );
			}
		}
		catch(Exception E)	{
			//Incase some exception gets thrown, print the stack-trace
			//might be easier to debug
			System.out.println("\n Oops! Something went wrong!");
			E.printStackTrace();
		}
		finally	{
			s.close(); //close the socket
			System.out.println("\n Goodbye from client!");
		}
	}
}
