package com.example.demoSpringbootMultiDatasource;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoSpringbootMultiDatasourceApplication {

	public static void main(String[] args) {
		
		if (java.util.Locale.getDefault() != java.util.Locale.ENGLISH) {
			//ค่านี้มีผลกับฐานข้อมูล mariadb เรื่องปี ค.ศ. หรือ พ.ศ. ตอนส่งข้อมูลไม่ insert ,update
			java.util.Locale.setDefault(java.util.Locale.ENGLISH);
		}

		System.out.println("version "+System.getProperty("java.version"));
		System.out.println("user.country "+System.getProperty("user.country"));
		System.out.println("user.country.format "+System.getProperty("user.country.format"));
		System.out.println("user.language "+System.getProperty("user.language"));
		System.out.println("user.language.format "+System.getProperty("user.language.format"));
		System.out.println("timezone jvm "+TimeZone.getDefault().getID());
		
		SpringApplication.run(DemoSpringbootMultiDatasourceApplication.class, args);
	}

}
