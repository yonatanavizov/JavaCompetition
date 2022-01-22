package com.competition.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable
{
	ServerSocket serverSocket;
	int port;
	
	public Server(int port) throws IOException
	{
		this.port = port;
		serverSocket = new ServerSocket(port);
	}

	@Override
	public void run()
	{
		try {
			
			//Note that the accept() method blocks the current thread until a connection is made.
			//So it is critical that it will be in RUN that is called through a Thread Object.
			
			System.out.println("SERVER("+ String.valueOf(port)+"): Attempting Connection...");
			while(true)
			{
				Socket socket = serverSocket.accept();
				System.out.println("Connection accept: " + socket); // Who is connecting.
				
				//Read Data From Client
				InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String s = reader.readLine();
                //HandleRequest handler = new HandleRequest(reader);
                System.out.println(s);
                //System.out.println("data got: " + handler.get_request());
  
                //Send back this to client
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
				writer.write("Hello there -- " + s);
                
                reader.close();
                writer.close();
				socket.close();
				System.out.println("Connection closed: " + socket);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		System.out.println(">>Server terminated.");
		/*
			3. Read data from the client via an InputStream obtained from the client socket.
			3 == HandleRequest
			
			4. Send data to the client via the client socket’s OutputStream.
			4 == Server -> Services (Info we got from HandleRequest)
			
			5. Close the connection with the client.
		 */
		// Listen for Requests
		// Send to request Handler ? ==> Services ... Get Information ... 
		// when handle ends, return it ?
	}
}
