/*
	Base implementation of a file server
*/

import java.net.*;
import java.util.*;
import java.io.*;

public class MyClient {
	public static void main(String []args) throws IOException	{
		//all socket implementation and stuff go here
		final int PORT_NUMBER = 3005;
		boolean flag = false;
		Scanner keyboard = new Scanner(System.in);
		
		Socket s = new Socket("localhost", PORT_NUMBER);
		String message = null;
		PrintWriter out = null;
		Scanner in = null;
		
		try {
			if( s.isConnected() )	{
				System.out.println("\n Connection Established!");

				out = new PrintWriter( s.getOutputStream(), true);
				in = new Scanner( s.getInputStream() );
				
				flag = true;
			}
			else	{
				System.out.println("\n ERROR: Cannot Establish Connection!");
			}
			
			out.println("Hello from client!");		
			
			while( flag )	{
				if( in.hasNextLine() )	{
					message = in.nextLine();
					System.out.println("Server says: " + message);
				}

				System.out.print("\n COMMAND?: ");
								message = keyboard.nextLine();

				if( message.equals("bye") )	{
					flag = false;
				}
				
				out.println(message);
			}
					
		}
		catch (Exception E)	{
			System.out.println("\n Oops! Something went wrong!");
			E.printStackTrace();			
		}
		finally	{
			//close the sockets
			s.close();	
		}		
	}
}
