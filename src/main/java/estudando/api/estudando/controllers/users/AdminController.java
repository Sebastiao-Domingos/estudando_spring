package estudando.api.estudando.controllers.users;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import estudando.api.estudando.dtos.users.AdminDtoPost;
import estudando.api.estudando.modal.users.AdminModal;
import estudando.api.estudando.repositories.users.AdminRepository;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/administradores")
public class AdminController {
    @Autowired
    AdminRepository repository;

    @GetMapping
    public ResponseEntity<List<AdminModal>> getAdministradores(){
        var admins = repository.findAll();
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneAdministrador(@PathVariable long id){
        Optional<AdminModal> adminOptianal = repository.findById(id);

        if(adminOptianal.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(adminOptianal.get());
    }

    @PostMapping
    public void adicionarAdmin(@RequestBody @Valid AdminDtoPost dados){
        var admin = new AdminModal();
        BeanUtils.copyProperties(dados, admin);
        repository.save(admin);
    }
}
