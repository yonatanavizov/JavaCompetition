/* 
 * Project Summary:
 * The project is a Competition handle for various sports.
 * It contains the functionality of added teams, matches, and contests.
 * At the end, you can also display the contests.
 * You can also find specific teams by their Summary Description via a word of your choice. 
 * 
 * Project Created by: 
 * Yonatan Avizov ID: 318432101
 * Asaf Dangoor ID: 313307340
 */

package com.competition.src;

import java.io.IOException;

import com.competition.dm.IDataModel;
import com.competition.server.Server;
import com.controller.Controller;


// The starting point of the application
// Currently used for Tests of the DB and Json Files.
public class Program
{
	public static void main(String[] args)
	{
		try {
			Server serv = new Server(4545);
			Thread t1 = new Thread(serv);
			
			t1.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*Controller con = new Controller();
		IDataModel[] db;
		try {
			db = con.get_db("Match");
			for(int i = 0; i < db.length;i++)
			{
				System.out.println(db[i].toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		
		System.out.println("end");
	}
}