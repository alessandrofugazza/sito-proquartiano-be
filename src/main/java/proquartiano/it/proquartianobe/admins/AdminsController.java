package proquartiano.it.proquartianobe.admins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import proquartiano.it.proquartianobe.admins.payload.NewAdminDTO;
import proquartiano.it.proquartianobe.exceptions.BadRequestException;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/admins")
public class AdminsController {
    @Autowired
    private AdminsService adminsService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin saveAdmin(@RequestBody @Validated NewAdminDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return adminsService.save(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @GetMapping("/{id}")
    public Admin findById(@PathVariable UUID id) {
        return adminsService.findById(id);
    }

    @PutMapping("/{id}")
    public Admin findByIdAndUpdate(@PathVariable UUID id, @RequestBody NewAdminDTO body) {
        return adminsService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID id) {
        adminsService.findByIdAndDelete(id);
    }
}
