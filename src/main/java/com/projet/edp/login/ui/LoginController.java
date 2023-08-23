package com.projet.edp.login.ui;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	/**
	 * Si la ressource "/user" est accessible, 
	 * elle renverra l'utilisateur actuellement authentifié (une authentification), 
	 * sinon Spring Security interceptera la demande et enverra une réponse 401 via un AuthenticationEntryPoint.
	 * @param user
	 * @return
	 */
	  @RequestMapping("/user")
	  public Principal user(Principal user) {
	    return user;
	  }
	  
	  @RequestMapping("/resource")
	  public Map<String, Object> home() {
	        Map<String, Object> model = new HashMap<String, Object>();
	        model.put("id", UUID.randomUUID().toString());
	        model.put("content", "Hello World");
	        return model;
	    }
	  
	  @RequestMapping(value = "/{path:[^\\.]*}")
	    public String redirect() {
	        return "forward:/";
	    }


}
