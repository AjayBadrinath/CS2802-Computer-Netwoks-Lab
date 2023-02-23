package Exercise7;

public class CRC {
	
	
	public  String Xor(String s1,String s2) {
		String res="";
		for(int i=0;i<s2.length();i++) {
			if(s1.charAt(i)==s2.charAt(i)) {
				res+='0';
			}
			else {
				res+='1';
			}
		}
		return res;
	}
	
	/*
	 * 
	 * initial attempts ....
	public String divide(String dividend,String divisor) {
		
		
		int divisorLen=divisor.length();
		String res="";
		int k=0,l=0;
		String tmp=divisor;
		//String dividendEnc=dividend+''
		for (int i=0;i<divisorLen-1;i++) {
			dividend+='0';
		}
		int dividendLen=dividend.length();
		//100100%1010
		System.out.println(dividend);
		int track=0,kt=0;
		tmp=Xor(dividend.substring(0, divisorLen),divisor);
		track+=tmp.length();
		while(track<dividendLen) {
			
			
			for(int i=0;i<tmp.length();i++) {
				if(tmp.charAt(i)=='0') {
					l++;
				}
			}
		
			tmp=tmp.substring(l);
			kt=track+l;
			track+=tmp.length();
			if(kt>=dividendLen) {
				kt=dividendLen;
			}
			tmp+=dividend.substring(track+1,kt+1);
			track+=l;
			System.out.println(tmp);
			//tmp+=dividend.substring(l-1,divisorLen);
			if(divisorLen>tmp.length()) {
				divisorLen=tmp.length();
			}
			tmp=Xor(tmp,divisor);
			l=0;	
		}
		res=tmp;
		return res;
		
	}
	*/
	public String  padBit(String s1,String s2) {
		int d2=s2.length();
		for (int i=0;i<d2-1;i++) {
			s1+='0';
		}
		return s1;
	}
	
	public String Divide(String s1,String s2) {
		int d1=s1.length();
		String tmp="";
		int track=0;
		String tmp2="";
		tmp=Xor(s1.substring(0, s2.length()),s2);
		track+=tmp.length();
		while(track<d1) {
				if(tmp.charAt(0)=='0') {
					tmp=tmp.substring(1);
					tmp+=String.valueOf(s1.charAt(track++));
				}
		
			tmp2=tmp;
			tmp="";
			tmp=Xor(tmp2,s2);
		}
		
		return tmp2;
		
	}
	public boolean isTransmittedProperly(String originalBits,String CRCRemainder,String divisor) {
		//String k=new CRC().Divide(padBit(originalBits,divisor), divisor);
		String s2=originalBits+CRCRemainder.substring(1);
		//System.out.println(new CRC().Divide(s2, divisor));
		if(new CRC().Divide(s2, divisor).equals("0000")) {
			return true;
		}
		return false;
	}
	public static void main(String [] args) {
		String s1="10111011";
		System.out.println("No Error While Transmission: ");
		System.out.println(" Original BitArrangement:"+s1);
		
		String chk=s1;
		String s2="1001";
		System.out.println(" CRC Polynomial:"+s2);
		s1=new CRC().padBit(s1,s2);
		String s3=new CRC().Divide(s1, s2);
		//System.out.println(s3);
		chk+=s3.substring(1);
		//System.out.println(new CRC().Divide(chk, s2));
		/*Check for proper Transmission*/
		
		System.out.println("CRC Match :->"+new CRC().isTransmittedProperly("10111011","0110","1001"));
		/*Flip a bit in the original bit to simulate Error*/
		System.out.println("\n\nError Introduced into Bits:\n ErrenousBit Arrangement:10111111\n CRC Polynomial:1001\n");
		
		System.out.println("CRC Match :->"+new CRC().isTransmittedProperly("10111111","0110","1001"));
		//System.out.println(f.substring(4,5));
		//System.out.println(new CRC().Xor("1100","1101"));
	      //System.out.println(new CRC().Divide("10010000", "1101"));
		//System.out.println(new CRC().Divide("10111011", "1001"));
		//System.out.println(new CRC().Divide("1110", "1101"));
	}
}
