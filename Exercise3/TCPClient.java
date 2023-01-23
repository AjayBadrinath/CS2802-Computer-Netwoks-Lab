package Exercise3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClient {
	public static void main(String [] args) {
		Socket s1;
		BufferedReader r1,r2;
		PrintStream p1;
		String op="";
		try {
			s1=new Socket(InetAddress.getLocalHost(),8080);
			r1=new BufferedReader(new InputStreamReader(System.in));
			r2=new BufferedReader(new InputStreamReader(s1.getInputStream()));
			p1=new PrintStream(s1.getOutputStream());
			while(!op.equals("end")) {
				System.out.print("Client :");
				op=r1.readLine();
				p1.println(op);
				System.out.println("Server: "+r2.readLine());
				
			}
		}  catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
