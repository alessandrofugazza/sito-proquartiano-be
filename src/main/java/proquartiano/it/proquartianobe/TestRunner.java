package proquartiano.it.proquartianobe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import proquartiano.it.proquartianobe.entities.admins.Admin;
import proquartiano.it.proquartianobe.entities.admins.AdminsRepository;
import proquartiano.it.proquartianobe.entities.articles.IArticlesDAO;
import proquartiano.it.proquartianobe.entities.categories.ICategoriesDAO;
import proquartiano.it.proquartianobe.entities.sections.Section;
import proquartiano.it.proquartianobe.entities.sections.SectionsRepository;
import proquartiano.it.proquartianobe.entities.tags.ITagsDAO;
import proquartiano.it.proquartianobe.enums.ESection;

@Component
public class TestRunner implements CommandLineRunner {

    @Autowired
    SectionsRepository sectionsRepo;
    @Autowired
    AdminsRepository adminsRepo;

    @Override
    public void run(String... args) throws Exception {
//        sectionsRepo.save(new Section(ESection.MERCATINO_LIBRI));
//        sectionsRepo.save(new Section(ESection.SAGRA));
//        sectionsRepo.save(new Section(ESection.CONCORSO_CORI));
//
//        adminsRepo.save(new Admin("AF", "alessandro.fugazza01@outlook.com", "1"));
    }
}
