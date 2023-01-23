package Exercise2;

import java.net.URL;

import java.io.*;
public class downloadpage {
	
	public static void main(String []args) {
		try {
			URL page=new URL("https://www.snuchennai.edu.in/");
			System.out.println("Port : "+page.getDefaultPort());
			System.out.println("Host : "  +page.getHost());
			System.out.println("Protocol: "+page.getProtocol());
			byte []arr=page.openStream().readAllBytes();
			String out=new String(arr);
			System.out.println(out);
			FileOutputStream out1=new FileOutputStream("webdownload.html");
			out1.write(arr);
			out1.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
