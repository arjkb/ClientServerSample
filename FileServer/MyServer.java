/*
	Base implementation of a file server
*/

import java.net.*;
import java.util.*;
import java.io.*;

public class MyServer {
	public static void main(String []args) throws IOException, FileNotFoundException	{
		//all socket implementation and stuff go here
		final int PORT_NUMBER = 3005;
		
		boolean flag = true;		
		
		ServerSocket ss = new ServerSocket(PORT_NUMBER);
		Socket client = ss.accept();

		String indexList = null;
		String message = null;
		String filename = null;
		PrintWriter out = null;
		Scanner in = null;
		
		try {
			out = new PrintWriter( client.getOutputStream(), true );
			in 	= new Scanner( client.getInputStream() );
			
			if( in.hasNextLine() )	{
				message = in.nextLine();
			}
			
			System.out.println("\n Client says: " + message);
			
			message = "Got you!";
			
			out.println(message);			

			
			while( flag )	{
				
				if( in.hasNextLine() )	{
					message = in.nextLine();
					System.out.println("Client says: " + message);	
//					message = "";
				}
				
				if( message.equals("name") )	{
					System.out.println("Sending back \"intel\" ");
					message = "intel";
				}
				else if( message.equals("index") )	{
					File folder = new File("fol");
					File [] listOfFiles = folder.listFiles();

					message = new String();

					for(int i = 0; i < listOfFiles.length; i++)	{
//						System.out.println(" " + i + " " + listOfFiles[i].getName() );
						message += " " + i + "." + listOfFiles[i].getName();
					}
//			out.println(message);
				}
				else if( message.equals("bye") )	{
					message = "bye from server";
					flag = false;
				}

				else if( message.startsWith("get")	)	{
				
					filename = "fol/";
					filename += message.substring(4);
					
					String text;
					System.out.println("Searching for file: " + filename);

					File f = new File(filename);
					if( f.exists() )	{
						message = new String();
						FileReader fr = new FileReader(filename);
						BufferedReader br = new BufferedReader(fr);
						while( (text = br.readLine()) != null )	{
							message += text;
//							out.println(message);
						}
					}
					else	{
						message = "nofile";
					}
				}
				else {
					message = "Unrecognized command";
					
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
			client.close();
			ss.close();
		}		
	} //end of main()
}
