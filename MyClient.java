/*
	CLIENT PROGRAM

		Supposed to take the name of the required webpage, and send this webpage to the server program.

		The web server processes this request, and sends the requested webpage back to the client.


	CODER	:	Arjun Krishna Babu	
*/

import java.net.*;		//for socket and networking functionalities
import java.io.*; 		//for input-output functionalities
import java.util.*;

public class MyClient	{
	public static void main(String args[]) throws IOException	{
		final int PORT_ADDRESS = 3001;
		//create a new Socket that listens to port 3000 on the same machine
		String webrequest = args[0];
		
		Socket s = new Socket("localhost", PORT_ADDRESS);

		System.out.println("\n Connection Estb Status: " + s.isConnected() );

		try	{
//			Socket s = new Socket("localhost", PORT_ADDRESS);
			
			s.setSoTimeout(30000); //value in milliseconds
			/*
				waits for 30 seconds for to establish connection with server
			*/


			PrintWriter out = new PrintWriter( s.getOutputStream() , true);
			Scanner in = new Scanner( s.getInputStream() );
			/*
				Associates a PrintWriter object with the output stream,
				and a Scanner object with the input stream of the socket.
			*/
			


			/*
				Write two messages into the socket.

				This message would be retrieved from the other end
				of the socket from the server program
			*/
//			out.println("This is message 1");
//			out.println("This is message 2");

			out.println(webrequest);
			
			System.out.println("GET BACK STREAM?: " + in.hasNextLine());
			while(in.hasNextLine())	{
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
