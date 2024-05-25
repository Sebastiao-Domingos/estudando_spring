package estudando.api.estudando.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import estudando.api.estudando.modal.UserModal;

public interface UserRepository extends JpaRepository<UserModal, Long> {

} 
