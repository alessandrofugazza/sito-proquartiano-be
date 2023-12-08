package proquartiano.it.proquartianobe.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import proquartiano.it.proquartianobe.entities.admins.Admin;
import proquartiano.it.proquartianobe.entities.admins.AdminsService;
import proquartiano.it.proquartianobe.exceptions.UnauthorizedException;

@Service
public class AuthService {
    @Autowired
    private AdminsService adminsService;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;

    public AdminLoginSuccessDTO authenticateAdmin(AdminLoginDTO body) {
        Admin admin = adminsService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), admin.getPassword())) {
            String token = jwtTools.createToken(admin);
            String username = admin.getSignature();
            return new AdminLoginSuccessDTO(token, username);
        } else {
            throw new UnauthorizedException("Credenziali non valide.");
        }
    }
}
