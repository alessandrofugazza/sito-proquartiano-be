package proquartiano.it.proquartianobe.entities.admins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import proquartiano.it.proquartianobe.entities.admins.payload.NewAdminDTO;
import proquartiano.it.proquartianobe.config.EmailSender;
import proquartiano.it.proquartianobe.enums.Role;
import proquartiano.it.proquartianobe.exceptions.BadRequestException;
import proquartiano.it.proquartianobe.exceptions.NotFoundException;

import java.io.IOException;
import java.util.UUID;

@Service
public class AdminsService implements IAdminsDAO {
    @Autowired
    private AdminsRepository adminsRepo;

    @Autowired
    EmailSender emailSender;

    @Autowired
    private PasswordEncoder bcrypt;

    public Admin save(NewAdminDTO body) throws IOException {
        adminsRepo.findByEmail(body.email()).ifPresent(admin -> {
            throw new BadRequestException("L'email " + admin.getEmail() + " è già utilizzata.");
        });
        adminsRepo.findBySignature(body.signature()).ifPresent(admin -> {
            throw new BadRequestException("L'username " + admin.getSignature() + " è già utilizzato.");
        });
        Admin newAdmin = new Admin();
        newAdmin.setSignature(body.signature());
        newAdmin.setEmail(body.email());
        newAdmin.setPassword(bcrypt.encode(body.password()));
        newAdmin.setRole(Role.ADMIN);
        Admin savedAdmin = adminsRepo.save(newAdmin);
        emailSender.sendRegistrationEmail(body.email());
        return savedAdmin;
    }

    @Override
    public void findByIdAndDelete(UUID id) throws NotFoundException {
        Admin found = this.findById(id);
        adminsRepo.delete(found);
    }

    @Override
    public Admin findByIdAndUpdate(UUID id, NewAdminDTO body) throws NotFoundException {
        Admin found = this.findById(id);
        found.setSignature(body.signature());
        found.setEmail(body.email());
        return adminsRepo.save(found);
    }

    @Override
    public Admin findById(UUID id) throws NotFoundException {
        return adminsRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public Admin findByEmail(String email) throws NotFoundException {
        return adminsRepo.findByEmail(email).orElseThrow(() -> new NotFoundException(email));
    }
}
