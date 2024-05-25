package estudando.api.estudando.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import estudando.api.estudando.modal.EnderecoModal;

public interface EnderecoRepository extends JpaRepository<EnderecoModal,Long> {
    
}
