package Exercise3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	public static void main(String[]args) {
		String op1="";
		Socket s1;
		ServerSocket serv;
		BufferedReader r2,r1;
		PrintStream p1;
		try {
			serv=new ServerSocket(8080);
			s1=serv.accept();
			r1=new BufferedReader(new InputStreamReader(System.in));
			r2=new BufferedReader(new InputStreamReader(s1.getInputStream()));
			p1=new PrintStream(s1.getOutputStream());
			while(!op1.equals("end")) {
				System.out.println("Client"+r2.readLine());
				System.out.println("Server");
				op1=r1.readLine();
				
				
				p1.println(op1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
