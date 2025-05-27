package authController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import domain.AuthRequest;
import domain.AuthResponse;
import security.JwtUtil;
import service.UserService;
@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
	
	  @Autowired private UserService service;
	    @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
	    	return service.authenticate(req.getUsername(), req.getPassword())
	    		    .<ResponseEntity<?>>map(user -> ResponseEntity.ok(new AuthResponse(JwtUtil.generateToken(user))))
	    		    .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials"));

	    }
}
