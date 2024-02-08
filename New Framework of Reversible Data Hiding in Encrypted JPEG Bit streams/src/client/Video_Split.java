package client;
 

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;


public class Video_Split 
{
	String reqfile,p1;
	public static Video_Split sp;	
	
	public  Video_Split(String name)
	{
		int n=6;
		int p=0;
		reqfile=name;
		File x=new File(reqfile);
		String name1=x.getName();
		String a[]=name1.split("\\.");
		String spilt_name=a[0].toString();
		System.out.println("Video_Name   "+spilt_name);
	
	       try
	       {
	    	   File ff= new File("");
	    	   String path=ff.getAbsolutePath();
	           File f=new File(reqfile);
	           FileInputStream f1=new FileInputStream(f);
	           byte b[]=new byte[f1.available()];
	           f1.read(b);
	           int j=0,m=1;           
	           int length=b.length;
	           int divide=length/n;
	           byte b1[]=new byte[divide];
	           int sum=0;
	           File fi=new File("");
	           String splitpath=fi.getAbsolutePath();
	           File fi1=new File("D:\\Robust video\\Splited video\\"+spilt_name);
	           fi1.mkdirs();
	           String spiltvpath =fi1.getPath();
	           System.out.println("thangm   svpath"+spiltvpath);
	           System.out.println("kk directorrrrrryyy");
	           for(int k=1;k<=n;k++)
	           {              
	               for(int i=sum;i<(k*divide);i++)
	               {
	                   b1[j]=b[i];
	                   j++;
	               }
	               
	               p1=fi1.getAbsolutePath();
	               System.out.println("Splited Video Path is....!!!    "+p1);
	               FileOutputStream f2=new FileOutputStream(p1+"\\"+"video"+m+".mpg");
	             
	               f2.write(b1);
	               
	               f2.flush();
	               f2.close();
	               
	              
	               String s=new String(b1);              
	               m++;
	               j=0;
	               sum=sum+divide;
	               p=1;
	               //send the url to server
	               Socket client;
	               ArrayList al=new ArrayList();
	               al.add("SplitVPath");
	               al.add(spiltvpath);
	               client=new Socket("localhost",10001);
	               ObjectOutputStream out=new ObjectOutputStream(client.getOutputStream());
	               out.writeObject(al);
	               
	           }
	       }
	       catch(Exception e)
	       {
	            System.out.println(e);
	       }
	       
	    }
        public static void main(String args[])
        {
            Video_Split sp = new Video_Split("D:\\Robust video\\Splited video\\");
            
        }



}
