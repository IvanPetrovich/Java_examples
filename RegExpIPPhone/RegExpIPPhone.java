import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Правило проверки IP и номера телефона 

public class RegExpIPPhone {

	private Pattern patternIP, patternPhone; 
	private Matcher m;
//												  0-199			200-249		 250-255
	private static final String IP_PATTERN = "^(([01]?\\d?\\d)|(2[0-4]\\d)|(25[0-5]))\\.(([01]?\\d?\\d)|(2[0-4]\\d)|(25[0-5]))\\.(([01]?\\d?\\d)|(2[0-4]\\d)|(25[0-5]))\\.(([01]?\\d?\\d)|(2[0-4]\\d)|(25[0-5]))$"; 
//													  [0-9]	 	
	
//															пробел или -
	private static final String PHONE_PATTERN = "^((\\+7)|7|8)?[-\\s]?(\\d\\d\\d)[-\\s]?(\\d\\d\\d)[-\\s]?([0-9][0-9])[-\\s]?([0-9][0-9])$";
	
	
	public RegExpIPPhone() {
		patternIP = Pattern.compile(IP_PATTERN);
		patternPhone = Pattern.compile(PHONE_PATTERN);
	}
	
	String checkIP(String ip) {
		m = patternIP.matcher(ip);
		return m.matches() + " - " + ip;
	}
	
	String checkPhone(String phone) {
		m = patternPhone.matcher(phone);
		return m.matches() + " - " + phone;
	}
	
	public static void main(String[] args) {
		RegExpIPPhone d = new RegExpIPPhone();
		System.out.println(" - Phone - \n");
//true		
		System.out.println(d.checkPhone("+7-999-999-99-99"));
		System.out.println(d.checkPhone("+79999999999"));
		System.out.println(d.checkPhone("7-999-999-99-99"));
		System.out.println(d.checkPhone("79999999999"));
		System.out.println(d.checkPhone("8 999 999 99 99"));
		System.out.println(d.checkPhone("999 999 99 99"));
//false		
		System.out.println();
		System.out.println(d.checkPhone("7 999 999 99 dd"));
		System.out.println(d.checkPhone("+8 999 999 99 99"));
		System.out.println(d.checkPhone("8 999 999 99 9"));
		
		
		System.out.println("\n - IP - \n");
//true	
		System.out.println(d.checkIP("199.200.249.250"));
		System.out.println(d.checkIP("255.255.255.255"));
		System.out.println(d.checkIP("0.0.0.0"));
//false		
		System.out.println();		
		System.out.println(d.checkIP("127.0.0.-1"));
		System.out.println(d.checkIP("0.0.0.0001"));
		System.out.println(d.checkIP("0.0.0.256"));
		System.out.println(d.checkIP(".0.0.1"));
		System.out.println(d.checkIP("а.й.п.и"));

	}

}