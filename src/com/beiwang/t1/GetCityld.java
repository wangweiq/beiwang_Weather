package com.beiwang.t1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class GetCityld {
	public static String getId(String name) {
		String id=null;
		Properties cityId=new Properties();
		FileReader fr;
		try {
			fr = new FileReader("city/city.properties");
			cityId.load(fr);
			id=cityId.getProperty(name);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("´íÎó");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("´íÎó");
			e.printStackTrace();
		}
		
		return id;
	}
	
	public static void main(String[] args) {
		Scanner scanner =new Scanner(System.in);
		String  zz= scanner.next();
		System.out.println(getId(zz));
	}
}

