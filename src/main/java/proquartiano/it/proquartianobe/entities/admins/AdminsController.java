package proquartiano.it.proquartianobe.entities.admins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import proquartiano.it.proquartianobe.entities.admins.payload.NewAdminDTO;
import proquartiano.it.proquartianobe.exceptions.BadRequestException;
import proquartiano.it.proquartianobe.security.AuthService;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/admins")
public class AdminsController {
    @Autowired
    private AdminsService adminsService;


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin save(@RequestBody @Validated NewAdminDTO body, BindingResult validation) {
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

    @GetMapping("/profilo")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public UserDetails getProfile(@AuthenticationPrincipal UserDetails currentAdmin) {
        return currentAdmin;
    }

    @PutMapping("/profilo")
    public UserDetails editProfile(@AuthenticationPrincipal Admin currentAdmin, @RequestBody NewAdminDTO body) {
        return adminsService.findByIdAndUpdate(currentAdmin.getId(), body);
    }

    @DeleteMapping("/profilo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@AuthenticationPrincipal Admin currentAdmin) {
        adminsService.findByIdAndDelete(currentAdmin.getId());
    }
}
