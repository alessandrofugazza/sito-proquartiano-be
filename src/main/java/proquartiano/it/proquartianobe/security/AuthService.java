package proquartiano.it.proquartianobe.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proquartiano.it.proquartianobe.admins.Admin;
import proquartiano.it.proquartianobe.admins.AdminsService;
import proquartiano.it.proquartianobe.exceptions.UnauthorizedException;

@Service
public class AuthService {
    @Autowired
    private AdminsService adminsService;

    @Autowired
    private JWTTools jwtTools;

    public String authenticateAdmin(AdminLoginDTO body) {
        Admin admin = adminsService.findByEmail(body.email());
        if (body.password().equals(admin.getPassword())) {
            return jwtTools.createToken(admin);
        } else {
            throw new UnauthorizedException("Credenziali non valide.");
        }
    }
}
