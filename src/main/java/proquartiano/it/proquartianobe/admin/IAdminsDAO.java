package proquartiano.it.proquartianobe.admin;

import proquartiano.it.proquartianobe.article.Article;

import java.util.UUID;

public interface IAdminsDAO {
    public Admin save(Admin admin);
    public void findByIdAndDelete(UUID id);

    public Admin findByIdAndUpdate(UUID id, Admin admin);
    public Admin findById(UUID id);
}
