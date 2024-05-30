package estudando.api.estudando.controllers.users;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import estudando.api.estudando.dtos.users.AdminDtoPost;
import estudando.api.estudando.dtos.users.AdminDtoUpdate;
import estudando.api.estudando.dtos.users.ListarDadosAdmin;
import estudando.api.estudando.modal.users.AdminModal;
import estudando.api.estudando.repositories.users.AdminRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/administradores")
public class AdminController {
    @Autowired
    AdminRepository repository;

    @GetMapping
    public ResponseEntity<List<ListarDadosAdmin>> getAdministradores(){
        var admins = repository.findAll();
        var administradores = admins.stream().map(ListarDadosAdmin::new).toList();
        return ResponseEntity.ok(administradores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneAdministrador(@PathVariable long id){
        var admin = repository.getReferenceById(id);
        return ResponseEntity.ok(new ListarDadosAdmin(admin));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Object> adicionarAdmin(@RequestBody @Valid AdminDtoPost dados , UriComponentsBuilder uriBuilder){
        var admin = new AdminModal();
        BeanUtils.copyProperties(dados, admin);
        repository.save(admin);
        var uri = uriBuilder.path("/administradores/{id}").buildAndExpand(admin.getId()).toUri();
        return ResponseEntity.created(uri).body( new ListarDadosAdmin(admin));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> atualizarAdmin(@PathVariable(name = "id") long id, @RequestBody @Valid AdminDtoUpdate dados){
        var admin = repository.getReferenceById(id);
        admin.atualizar(dados);
        return ResponseEntity.ok(new ListarDadosAdmin(admin));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> deletar(@PathVariable(name = "id") long id){
       var admin = repository.getReferenceById(id);
        repository.delete(admin);
        return ResponseEntity.ok("deleted");
     }
}
