package com.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//REST API
@RestController
public class HelloWorldController {
	
	
	private MessageSource messageSource;
	
	public HelloWorldController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	//L1: Create HelloWorld RestAPI
	//@RequestMapping(method = RequestMethod.GET, path = "/hello-world")
	@GetMapping(path="hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	//Run by command
    //localhost:8080/hello-world
	
	//L2: Enhance Hello World API to return a Bean

	//Hello World RESTAPI return a Bean
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}
	
	//Run by command
	//localhost:8080/hello-world-bean
	
	//Path Parameters
	// /users/{id}/todos/{id} => /users/1/todos/101// 
	// /hello-world/path-variable/{name}
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		//return new HelloWorldBean("Hello World " + name);
		return new HelloWorldBean(String.format("Hello World, %s", name));
	}
	
	//Run by command
	//http://localhost:8080/hello-world/path-variable/geetha
	//Output: {"message": "Hello World, geetha"}
	
	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized() {

		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "Default Message" , locale);
		//return new HelloWorldBean("Hello World V2");
	}//
	// - Example: 'en' - English (Good Morning)
	// - Example: 'nl' - Dutch (Goedemorgen)
	// - Example: 'fr' - French (Bonjour)
	
	//Run by http://localhost:8080/hello-world-internationalized
	//Output: Good Morning
	
	//IN Talend API .. Add Accept-Language:nl as a header and run the app, you will see Goedemorgen
	
}
