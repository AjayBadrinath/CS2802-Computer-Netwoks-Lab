package Exercise3;
import java.io.*;
import java.net.*;

public class FileServer {
	private static DataInputStream in=null;
	private static DataOutputStream out=null;
	
	
	public static void main(String args[]) {
		try(ServerSocket s=new ServerSocket(8080)){
			Socket client;
			client=s.accept();
			
			in=new DataInputStream(client.getInputStream());
			out=new DataOutputStream(client.getOutputStream());
			recv("oslabex2.pdf");
			in.close();
			//in.
			out.close();
			
			
		}catch(Exception e) {
			e.printStackTrace();;
		}
	}
	public static void recv(String fname) {
		int bits=0;
		
		try {
			FileOutputStream f=new FileOutputStream(fname);
			long inr=in.readLong();
			byte [] buf=new byte[4*1024];
			while(inr>0 && (bits=in.read(buf, 0, (int)Math.min(inr,buf.length )))!=-1) {
				f.write(buf,0,bits);
				inr-=bits;
				
			}
			f.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
				
		
		
		
	}
}
