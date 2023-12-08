package proquartiano.it.proquartianobe.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public AdminLoginSuccessDTO login(@RequestBody AdminLoginDTO body) {
        // q is this bad practice?
        return authService.authenticateAdmin(body);
    }
}
