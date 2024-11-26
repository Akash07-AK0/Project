package Project;

import java.sql.Connection;
import java.sql.DriverManager;

public class n 
{
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		//System.out.println("System succesdddddddddsfully");

		try 
		{
		String url="JDBC:mysql://localhost:3306/";
		String username="root";
		String password="akash";
		
		Connection con= DriverManager.getConnection(url,username, password);
		System.out.println("System successfully");
		System.out.println("System successfully");

		
		} 
		catch (Exception s) 
		{
			// TODO Auto-generated catch block
			s.printStackTrace();
		}
		///System.out.println("System successfully2");

	}
	

}
