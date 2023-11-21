package proquartiano.it.proquartianobe.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import proquartiano.it.proquartianobe.config.EmailSender;
import proquartiano.it.proquartianobe.entities.admins.Admin;
import proquartiano.it.proquartianobe.entities.admins.AdminsRepository;
import proquartiano.it.proquartianobe.entities.admins.AdminsService;
import proquartiano.it.proquartianobe.entities.admins.payload.NewAdminDTO;
import proquartiano.it.proquartianobe.enums.Role;
import proquartiano.it.proquartianobe.exceptions.BadRequestException;
import proquartiano.it.proquartianobe.exceptions.UnauthorizedException;

import java.io.IOException;

@Service
public class AuthService {
    @Autowired
    private AdminsService adminsService;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;

    public String authenticateAdmin(AdminLoginDTO body) {
        Admin admin = adminsService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), admin.getPassword())) {
            return jwtTools.createToken(admin);
        } else {
            throw new UnauthorizedException("Credenziali non valide.");
        }
    }
}
