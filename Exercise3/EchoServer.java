package Exercise3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
public static void main(String[] args) {
	String op1="";
	Socket s1;
	ServerSocket serv;
	BufferedReader r2;
	PrintStream p1;
	try {
		serv=new ServerSocket(8080);
		s1=serv.accept();
		r2=new BufferedReader(new InputStreamReader(s1.getInputStream()));
		p1=new PrintStream(s1.getOutputStream());
		while(!op1.equals("end")) {
			op1=r2.readLine();
			System.out.println("Message Recieved and sentback");
			p1.println(op1);
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
}
}
