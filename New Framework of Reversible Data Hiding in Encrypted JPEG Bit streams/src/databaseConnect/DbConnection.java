package databaseConnect;

import java.sql.*;
import java.util.ArrayList;

public class DbConnection {

	static Connection con;
	static Statement stm;
	static ResultSet rs;
	
	public static Connection getSqlConnection()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/vtjim02_2018","root","root");
		}catch (Exception e) {
				e.printStackTrace();
		}
		System.out.println("Connecton ok.....!!!!!     "+con);
		return con;
	}
	
	 public static String  loginValid(String name, String p) {
		 String res="invalid";
		 try{
		 stm=getSqlConnection().createStatement();
		 rs=stm.executeQuery("select *from register where name='"+name+"' and pass='"+p+"'");
		 while(rs.next())
		 {
			 res="Valid";
		 }
		 }catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
		 System.out.println("result in database side   "+ res);
		 return res;
		 
	}
	 
	 public int InsertValue(String name,String pass,  String  gender,String phno,String email)
	    {
			int i=0;
	    try
	    {
	       
	        stm=getSqlConnection().createStatement();
	        i=stm.executeUpdate("insert into register values('"+name+"','"+pass+"' ,'"+gender+"','"+phno+"','"+email+"')");
	        System.out.println("Insert Successfully...");
	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	    }
		
		return i;
	}
		
	 public int insertIpPort(String name,String ip ,  String port)
	    {
			int i=0;
			
	    try
	    {
 	       
	        stm=getSqlConnection().createStatement();
	        ResultSet rs=stm.executeQuery("select * from ip_port where clientname='"+name+"'");
	        if(rs.next())
	        {
	        	 i=stm.executeUpdate("update ip_port set port='"+port+"' where clientname='"+name+"'");
	        	 System.out.println("port update successfully...!!!!");
	        }
	     if(i==0)
	     {
	        i=stm.executeUpdate("insert into ip_port values('"+name+"','"+ip+"',  '"+port+"')");
	        System.out.println("Insert Successfully...");
	     }
	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	    }
		
		return i;
	}
	 
	 public int InsertSplitVPath(String svpath)
	    {
			int i=0;
	    try
	    {
	       
	        stm=getSqlConnection().createStatement();
	        i=stm.executeUpdate("insert into SplitVPath values('"+svpath+"','"+svpath+"\\video1.mpg"+"','"+svpath+"\\video2.mpg"+"','"+svpath+"\\video3.mpg"+"','"+svpath+"\\video4.mpg"+"','"+svpath+"\\video5.mpg"+"','"+svpath+"\\video6.mpg"+"')");
	        System.out.println("Insert Successfully...");
	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	    }
		
		return i;
	}
	 public int message(String user,String videopath, String msg, String empmsg, String receiver)
	 {
		 int i=0;
		 try{
			 stm=getSqlConnection().createStatement();
		        i=stm.executeUpdate("insert into msg_details values('"+user+"','"+videopath+"','"+msg+"','"+empmsg+"','"+receiver+"')");
		        System.out.println("Insert Successfully...");
		 }catch (Exception e) {
			e.printStackTrace();
		}
		 return i;
	 }
	 
	 public static  ArrayList getIPaddPort(String receiver) {
		 ArrayList al=new ArrayList();
		 
		 try{
		 stm=getSqlConnection().createStatement();
		 rs=stm.executeQuery("select ip,port from ip_port where clientname='"+receiver+"'");
		 while(rs.next())
		 {
			 al.add(rs.getString(1).toString());
			 al.add(rs.getString(2).toString());
		 }
		 }catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
		 System.out.println("result in database side   "+ al);
		 return al;
		 
	}
	 
}
