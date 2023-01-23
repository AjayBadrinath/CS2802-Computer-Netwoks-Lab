package Exercise4;
import java.net.*;
import java.util.Scanner;
import java.io.*;
public class UdpDnsServer {
@SuppressWarnings("deprecation")
public static void main(String [] args) {
	try {
		String command="nslookup ";
		String send1= "";
		String op="";
		
		while(true) {
			DatagramSocket s=new DatagramSocket(8210);
			byte[] send=new byte[1024];
			byte [] recv=new byte[1024];
			DatagramPacket rec=new DatagramPacket(recv, recv.length,InetAddress.getLocalHost(),8210);
			s.receive(rec);
			String s12=new String (rec.getData());
			command+=s12;
			System.out.println(command);
			InetAddress a= rec.getAddress();
			int port=rec.getPort();
			
			Process p=Runtime.getRuntime().exec(command.trim());
			Scanner r=new Scanner(p.getInputStream());
			//DatagramPacket send=new DatagramPacket(recv, port, a, port)
			while(r.hasNext()) {
				op+=r.next();
				op+="\n";
			}
			
			System.out.println(op);
			send1+=op;
			send=send1.getBytes();
			send1="";
			DatagramPacket sendd=new DatagramPacket(send, send.length,InetAddress.getLocalHost(),rec.getPort());
			s.send(sendd);
			s.close();
		}
		/*
		command+="www.google.com";
		Process p=Runtime.getRuntime().exec(command);
		Scanner r=new Scanner(p.getInputStream());
		while(r.hasNext()) {
			op+=r.next();
			op+="\n";
			
		}
		System.out.println(op);
		*/
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
