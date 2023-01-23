package Exercise5;
import java.net.*;
import java.io.IOException;
import java.util.*;
import java.io.*;

public class ArpServer {
	@SuppressWarnings("deprecation")
	public static void main(String args[]) {
		
		String ip="";
		
		String command="arp -a ";
		
		String out="";
		try {
			
		
			while(true) {
				DatagramSocket s=new DatagramSocket(7080);
				byte [] send=new byte[1024];
				byte [] recv=new byte[1024];
				DatagramPacket p=new DatagramPacket(recv,recv.length,InetAddress.getLocalHost(),7080);
				s.receive(p);
				ip=new String(p.getData());
				String c_out=command+ip;
				Process p1=Runtime.getRuntime().exec(c_out.trim());
				System.out.println(c_out);
				Scanner r=new Scanner(p1.getInputStream());
				
				while(r.hasNext()) {
					out+=r.next();
					out+="\n";
				}
				//System.out.println(out.substring(out.indexOf(ip)));
				
				
				send=out.getBytes();
				out="";
				DatagramPacket send1=new DatagramPacket(send,send.length,InetAddress.getLocalHost(),p.getPort());
				s.send(send1);
				s.close();
				
			}
			
			
		
		} catch (IOException e) {
			
		
			e.printStackTrace();
		}
		
	}

}
