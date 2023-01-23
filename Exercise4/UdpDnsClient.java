package Exercise4;
import java.net.*;
import java.io.*;
public class UdpDnsClient {
public static void main(String args[]) {
	BufferedReader r=new BufferedReader(new InputStreamReader(System.in));
	try {
		
		DatagramSocket s=new DatagramSocket();
		byte[] send=new byte[1024];
		byte [] recv=new byte[1024];
		System.out.println("Enter host Name:");
		String input=r.readLine();
		send=input.getBytes();
		DatagramPacket p=new DatagramPacket(send, send.length, InetAddress.getLocalHost(), 8210);
		
		DatagramPacket q=new DatagramPacket(recv,recv.length,InetAddress.getLocalHost(),8210);
		
		s.send(p);
		s.receive(q);
		String ip=new String(q.getData());
		System.out.println("IP Address: "+ip);
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
