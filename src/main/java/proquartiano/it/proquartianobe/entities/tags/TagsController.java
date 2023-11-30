package proquartiano.it.proquartianobe.entities.tags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagsController {
    @Autowired
    TagsService tagsService;

    @GetMapping("/most-used")
    public List<String> getMostUsed() {
        return tagsService.getMostUsed();
    }
}
