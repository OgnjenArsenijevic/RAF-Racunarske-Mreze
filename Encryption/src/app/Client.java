package app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client
{

	public Client() throws Exception
	{
		Socket socket = new Socket("192.168.56.1", 2019);
		System.out.println("Uspesno povezivanje na server");
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
		Scanner keyboard = new Scanner(System.in);

		System.out.println(in.readLine());

		while (true)
		{
			System.out.println(in.readLine());
			String msg = keyboard.nextLine();
			String changed = "";
			for (int i = 0; i < msg.length(); i++)
			{
				if (msg.charAt(i) != ' ')
					changed += (char) (((int) msg.charAt(i) + 5) % 128);
				else
					changed += " ";
			}
			System.out.println(changed);
			out.println(changed);
			System.out.println(in.readLine());
			if (msg.toLowerCase().contains("exit"))
				break;
		}

		socket.close();
		keyboard.close();
	}

	public static void main(String[] args)
	{
		try
		{
			new Client();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
