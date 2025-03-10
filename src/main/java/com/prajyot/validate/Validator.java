package com.prajyot.validate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Validator {
	
	static final Logger logger = LogManager.getLogger(Validator.class);
	public static void main(String[] args) {
//		String email = "prajy.ot@gmail.co.nm";
//		System.out.println(mailValidator(email));
		
//		String name = "prajyot ";
////		System.out.println(name);
////		for(int i=0 ; i< name.length() ; i++) {
////			Character c = name.charAt(i);
////			if(!Character.isAlphabetic(c) ) {
////				System.out.println("true");
////			}else {
////				System.out.println("false");
////			}
////		}
//		System.out.println(nameValidator(name));
		
		
//		String gender="other";
//		System.out.println(gender);
//		if(gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("other"))
//			System.out.println("true");
//		else
//			System.out.println("false");
		
		
//		String mobileNumber="9834980457";
//		System.out.println(mobileNumber);
//		System.out.println(mobileValidator(mobileNumber));
//		for(int i=0 ; i< mobileNumber.length() ; i++) {
//		Character c = mobileNumber.charAt(i);
//		if(!Character.isDigit(c) ) {
//			System.out.println("true");
//		}else {
//			System.out.println("false");
//		}
//		}
		
		
//		String salary="800";
//		System.out.println(salary);
//		System.out.println(salaryValidator(salary));
		
		
//		String password="Praj@t18";
//		System.out.println(password);
//		System.out.println(passwordValidator(password));
	}
	
	public boolean passwordValidator(String password) {
		logger.info("passwordValidator() called");
		boolean capital = false;
		boolean lower = false;
		boolean symbol = false ;
		boolean number = false ;
		String 	specialChar ="!@#$%^&*()-_+=<>?";
		if(password.length()< 8) {
			return false;
		}
		for(int i=0 ; i< password.length() ; i++) {
			Character c = password.charAt(i);
			if(Character.isUpperCase(c)) {
				capital = true;
			}
			if(Character.isDigit(c)) {
				number = true;
			}
			if(Character.isLowerCase(c)) {
				lower = true;	
			}
			if(specialChar.indexOf(c) >= 0) {
				symbol = true;
			}
		}
		if(capital == true && lower == true && symbol == true && number == true )
			return true;
		else 
			return false;
	}

	public  boolean mailValidator(String email) {
		logger.info("mailValidator() called");
		int pos = email.indexOf("@");
		if (pos==-1 || email.indexOf('@', pos + 1) != -1) {
			logger.info("email contains something else");
	           return false;
        }
		
		String afterSymbolString = email.substring(pos+1);
		int dot = afterSymbolString.indexOf(".");
		if (dot==-1 || afterSymbolString.indexOf('.', dot + 1) != -1) {
			logger.info("email contains something else");
	           return false;
		}
		logger.info("email Valid");
    	 return true;
	}
	
	public boolean nameValidator(String name) {
		logger.info("nameValidator() called");
		for(int i=0 ; i< name.length() ; i++) {
			Character c = name.charAt(i);
			if(!Character.isAlphabetic(c) && !Character.isSpaceChar(c)) {
				logger.info("name contains something else");
				return false;
			}
		}
		logger.info("name Valid");
		return true;
	}

	public boolean genderValidator(String gender) {
		logger.info("nameValidator() called");
		if(gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("other")) {
			logger.info("gender valid");
			return true;
		}
		else {
			logger.info("gender invalid");
			return false;
		}
	}
	
	public boolean employeeIdValidator(String employeeId) {
		logger.info("employeeIdValidator() called");
		
		for(int i=0 ; i< employeeId.length() ; i++) {
			Character c = employeeId.charAt(i);
			if(!Character.isDigit(c)) {
				logger.info("employeeID contains alphabet ");
				return false;
			}
		}
		return true;
	}
	
	public boolean mobileValidator(String mobileNumber) {
		logger.info("mobileValidator() called");
		for(int i=0 ; i< mobileNumber.length() ; i++) {
			Character c = mobileNumber.charAt(i);
			if(!Character.isDigit(c) ) {
				logger.info("mobile number invalid");
				return false;
			}
		}
		if(mobileNumber.length()!= 10) {
			logger.info("mobile number invalid");
			return false;
		}
		if(mobileNumber.charAt(0) == '0') {
			logger.info("mobile number invalid");
			return false;
		}
		logger.info("mobile number valid");
		return true;
		
	}
	
	public   boolean salaryValidator(String salary) {
		logger.info("salaryValidator() called");
		int pos = salary.indexOf(".");
		if (salary.indexOf('.', pos + 1) != -1) {
			logger.info("salary  invalid");
	           return false;
        }
		for(int i=0 ; i< salary.length() ; i++) {
			Character c = salary.charAt(i);
			if(!Character.isDigit(c) && salary.charAt(i)!= '.') {
				logger.info("salary  invalid");
				return false;
			}
		}
		logger.info("salary valid");
		return true;
		
	}
}
