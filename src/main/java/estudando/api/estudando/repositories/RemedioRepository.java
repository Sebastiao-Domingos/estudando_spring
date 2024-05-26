package estudando.api.estudando.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import estudando.api.estudando.modal.RemedioModal;

public interface RemedioRepository extends JpaRepository<RemedioModal,Long> {

  List<RemedioModal> findByStatus(boolean status);
}
