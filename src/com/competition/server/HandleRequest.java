package com.competition.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class HandleRequest implements Runnable
{
	Socket socket;
	public HandleRequest(Socket socket)
	{
		this.socket = socket;
	}

	
	private String readBytesRequest() throws IOException
	{		
		InputStream in = socket.getInputStream(); // stream of bytes to read
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int nRead;
		byte[] data = new byte[16384];
		while ((nRead = in.read(data, 0, data.length)) != -1)
		{
		  buffer.write(data, 0, nRead);
		}
		String contents = new String(buffer.toByteArray(), StandardCharsets.UTF_8);
		System.out.println(contents);
		
		in.close();
		
		return contents;
	}
	
	@Override
	public void run()
	{
		try
		{
			System.out.println(">> Request port: " + socket.getPort());
			String request = readBytesRequest();
			Type type = new TypeToken<RequestData>(){}.getType();
			Gson gson = new Gson();
			RequestData re = gson.fromJson(request, type);
			System.out.println(re.toString());
			socket.close();
			System.out.println("<< Request ended");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
}
