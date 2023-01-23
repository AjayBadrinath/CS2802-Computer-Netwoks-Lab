package Exercise3;
import java.io.*;
import java.net.*;
public class FileClient {
	static DataInputStream k;
	static DataOutputStream k1;
	public static void main(String [] args) {
		
		try(Socket s=new Socket("localhost",8080)){
			k=new DataInputStream(s.getInputStream());
			k1=new DataOutputStream(s.getOutputStream());
			send("E:\\oslab\\oslabex1.pdf");
			k.close();
			k1.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void send(String path) {
		int bytes=0;
		File f1=new File(path);
		try {
			FileInputStream is=new FileInputStream(f1);
			k1.writeLong(f1.length());
			byte[] bfr=new byte[4*1024];
			while((bytes=is.read(bfr))!=-1) {
				k1.write(bfr,0,bytes);
				k1.flush();
			}
			is.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
}
