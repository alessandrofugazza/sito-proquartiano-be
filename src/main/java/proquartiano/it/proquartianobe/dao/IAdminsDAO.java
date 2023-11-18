package proquartiano.it.proquartianobe.dao;

import proquartiano.it.proquartianobe.payload.admins.NewAdminDTO;
import proquartiano.it.proquartianobe.entities.Admin;

import java.io.IOException;
import java.util.UUID;

public interface IAdminsDAO {
    public Admin save(NewAdminDTO admin) throws IOException;

    public void findByIdAndDelete(UUID id);

    public Admin findByIdAndUpdate(UUID id, NewAdminDTO admin);

    public Admin findById(UUID id);
}
