package proquartiano.it.proquartianobe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proquartiano.it.proquartianobe.config.EmailSender;
import proquartiano.it.proquartianobe.dao.IAdminsDAO;
import proquartiano.it.proquartianobe.payload.admins.NewAdminDTO;
import proquartiano.it.proquartianobe.entities.Admin;
import proquartiano.it.proquartianobe.exceptions.BadRequestException;
import proquartiano.it.proquartianobe.exceptions.NotFoundException;
import proquartiano.it.proquartianobe.repositories.AdminsRepository;

import java.io.IOException;
import java.util.UUID;

@Service
public class AdminsService implements IAdminsDAO {
    @Autowired
    private AdminsRepository adminsRepo;
    @Autowired
    EmailSender emailSender;

    @Override
    public Admin save(NewAdminDTO body) throws IOException {
        adminsRepo.findByEmail(body.email()).ifPresent(admin -> {
            throw new BadRequestException("L'email " + admin.getEmail() + " è già utilizzata.");
        });
        adminsRepo.findByUsername(body.username()).ifPresent(admin -> {
            throw new BadRequestException("L'username " + admin.getUsername() + " è già utilizzato.");
        });
        Admin newAdmin = new Admin();
        newAdmin.setUsername(body.username());
        newAdmin.setEmail(body.email());
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
        found.setUsername(body.username());
        found.setEmail(body.email());
        return adminsRepo.save(found);
    }

    @Override
    public Admin findById(UUID id) throws NotFoundException {
        return adminsRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
}
