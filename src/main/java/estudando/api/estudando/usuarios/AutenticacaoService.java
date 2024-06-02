package estudando.api.estudando.usuarios;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service //Indica ao spring que esta classe e um service
public class AutenticacaoService  implements UserDetailsService{
    @Autowired
    private UsuarioRepository repository ;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        return repository.findByLogin(username);
    }
    
}
