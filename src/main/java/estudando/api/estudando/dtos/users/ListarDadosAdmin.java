package estudando.api.estudando.dtos.users;

import estudando.api.estudando.modal.users.AdminModal;

public record ListarDadosAdmin(
    long id,
    String nome,
    String email,
    boolean status
) {
    
    public ListarDadosAdmin( AdminModal admin){
      this ( admin.getId(),
      admin.getNome(),
      admin.getEmail() , 
      admin.isStatus());
    }
}
