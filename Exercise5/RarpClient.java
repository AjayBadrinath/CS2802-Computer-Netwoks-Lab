package Exercise5;
import java.io.*;
import java.util.*;
import java.net.*;

public class RarpClient {
	public static void main(String[]args)
	{
		System.out.println("Enter MAC ADDRESS: ");
		Scanner s=new Scanner(System.in);
		String ip=s.next();
		try {
			DatagramSocket s1=new DatagramSocket();
			byte []send=new byte[1024];
			byte [] recv=new byte[1024];
			send=ip.getBytes();
			DatagramPacket p1=new DatagramPacket(send,send.length,InetAddress.getLocalHost(),8090);
			s1.send(p1);
			DatagramPacket p2=new DatagramPacket(recv, recv.length, InetAddress.getLocalHost(), 8090);
			s1.receive(p2);
			String out=new String(p2.getData());
			System.out.println(out);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
				
		
	}

}
