package org.eclipse.vorto.middleware.web;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/context")
public class UserController {

	 @RequestMapping(value = {"/user"}, method = RequestMethod.GET)
	  public ResponseEntity<Map<String, Object>> getUser(Principal user,
	      final HttpServletRequest request) {
	    
	    Map<String, Object> map = new LinkedHashMap<>();
	    
	    if (user == null)
	      return new ResponseEntity<Map<String, Object>>(map, HttpStatus.UNAUTHORIZED);
	    
	    map.put("userId", user.getName());
	  
	    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	  }
}
