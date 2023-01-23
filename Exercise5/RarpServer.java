package Exercise5;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class RarpServer {
	public static void main(String[]args) {
		String ip[]={"165.165.80.80","165.165.79.1"};
		String mac[]={"6A:08:AA:C2","8A:BC:E3:FA"};
		
		String send2="";
		int flag=0;
		try {
			
			
			while(true) {
				DatagramSocket s=new DatagramSocket(8090);
				byte[] send=new byte[1024];
				byte [] recv=new byte[1024];
				DatagramPacket recv1=new DatagramPacket(recv,recv.length,InetAddress.getLocalHost(),8090);
				s.receive(recv1);
				String uin=new String(recv1.getData());
				System.out.println(uin);
				for(int i=0;i<ip.length;i++) {
					if(mac[i].equalsIgnoreCase(uin.trim())) {
						send2=ip[i];
						flag=1;
						break;
					}
				}
				if(flag==0) {
					send2="Not Found";
					
				}
				send=send2.getBytes();
				DatagramPacket send1=new DatagramPacket(send,send.length,InetAddress.getLocalHost(),recv1.getPort());
				System.out.println(send2);
				s.send(send1);
				s.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
