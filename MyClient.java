/*
	Client program
*/

import java.net.*;		//for socket and networking functionalities
import java.io.*; 		//for input-output functionalities

public class MyClient	{
	public static void main(String args[]) throws IOException	{
		final int PORT_ADDRESS = 3000;
		//create a new Socket that listens to port 3000 on the same machine

		Socket s = new Socket("localhost", PORT_ADDRESS);

		try	{
//			Socket s = new Socket("localhost", PORT_ADDRESS);
			
			s.setSoTimeout(30000); //value in milliseconds

			/*
				waits for 30 seconds for to establish connection with server
			*/

//			OutputStream out_stream = s.getOutputStream();

			/*
				associates a PrintWriter object with the output stream
				of the socket.
			*/
			
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);


			/*
				Write two messages into the socket.

				This message would be retrieved from the other end
				of the socket from the server program
			*/
			out.println("\n This is message 1");
			out.println("\n This is message 1");
		}
		catch(Exception E)	{
			//Incase some exception gets thrown, print the stack-trace
			//might be easier to debug

			System.out.println("\n Oops! Something went wrong!");
			E.printStackTrace();
		}
		finally	{
			s.close(); //close the socket
		}
	}
}
