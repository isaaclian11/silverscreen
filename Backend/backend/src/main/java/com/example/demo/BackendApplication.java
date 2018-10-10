package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		
		System.out.println("+++++++++++++args.length = " + args.length);
		for (int i = 0; i < args.length; i++)
		{
			System.out.println("====:" + args[i]);
		}
		
		SpringApplication.run(BackendApplication.class, args);
		
		
	}
}
