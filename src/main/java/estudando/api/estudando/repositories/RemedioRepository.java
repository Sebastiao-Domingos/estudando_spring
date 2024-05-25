package estudando.api.estudando.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import estudando.api.estudando.modal.RemedioModal;

public interface RemedioRepository extends JpaRepository<RemedioModal,Long> {

    
}
