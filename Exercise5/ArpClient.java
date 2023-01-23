package Exercise5;

import java.net.*;
import java.net.SocketException;
import java.io.*;
import java.util.*;
public class ArpClient {
	public static void main(String[] args) {
		Scanner r=new Scanner(System.in);
		try {
			DatagramSocket s1=new DatagramSocket();
			byte [] send=new byte[1024];
			byte [] recv=new  byte[1024];
			System.out.println("Enter IP Address:");
			String ip=r.next();
			send=ip.getBytes();
			DatagramPacket send1=new DatagramPacket(send,send.length,InetAddress.getLocalHost(),7080);
			DatagramPacket recv1=new DatagramPacket(recv,recv.length,InetAddress.getLocalHost(),7080);
			s1.send(send1);
			s1.receive(recv1);
			String s=new String(recv1.getData());
			System.out.println(s);
			s1.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
