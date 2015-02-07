/*
	Program to test reading from a file
*/

import java.io.*;
import java.util.*;

public class FileTest	{
	public static void main(String []args) throws FileNotFoundException, IOException	{
		String text;
		FileReader fr = new FileReader("fol/ab1");
		BufferedReader br = new BufferedReader(fr);

		while( (text = br.readLine()) != null )	{
			System.out.print(text);
		}
	}
}
