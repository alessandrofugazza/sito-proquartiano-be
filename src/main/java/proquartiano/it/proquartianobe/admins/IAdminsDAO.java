package proquartiano.it.proquartianobe.admins;

import proquartiano.it.proquartianobe.admins.payload.NewAdminDTO;

import java.io.IOException;
import java.util.UUID;

public interface IAdminsDAO {
    public Admin save(NewAdminDTO admin) throws IOException;

    public void findByIdAndDelete(UUID id);

    public Admin findByIdAndUpdate(UUID id, NewAdminDTO admin);

    public Admin findById(UUID id);

    public Admin findByEmail(String email);
}
