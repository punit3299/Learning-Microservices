package com.config.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigController {

//	Value Injector
	@Value("${my.Message}")
	private String greetMessage;
	
	@Value("${my.AppMessage}")
	private String appMessage;
	
//	Default value for value injection
	@Value("${defaultMessage : This is my Default Message}")
	private String defaultMessage;
	
//	List for value injection
	@Value("${listMessage}")
	private List<Integer> listMessage;
	
	@Autowired
	private DbSetting dbSetting;
	
	@Autowired
	private TestBean tb;
	
	@Autowired
	private Environment env;
	
	@GetMapping("/greeting")
	public String greeting() {
		return greetMessage;
	}
	
	@GetMapping("/app")
	public String myApp() {
		return appMessage;
	}
	
	@GetMapping("/default")
	public String checkDefaultMessage() {
		return defaultMessage;
	}
	
	@GetMapping("/list")
	public List<Integer> checkListMessage() {
		return listMessage;
	}
	
	@GetMapping("/setting")
	public String getSetting() {
		return dbSetting.getUsername()+", "+dbSetting.getPassword();
	}
	
	@GetMapping("/test")
	public String getTestBean() {
		tb.setId(1);
		tb.setName("Bean");
		return tb.getId()+", "+tb.getName();
	}
	
	@GetMapping("/env")
	public String getEnvDetails() {
		return env.toString();
	}
	
}
