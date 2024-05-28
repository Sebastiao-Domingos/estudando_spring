package estudando.api.estudando.repositories.users;


import org.springframework.data.jpa.repository.JpaRepository;

import estudando.api.estudando.modal.users.AdminModal;

public interface AdminRepository extends JpaRepository<AdminModal,Long> {
    
}
