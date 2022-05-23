package int371.namjai.utill.auth;


import int371.namjai.domain.user.UserRepository;
import int371.namjai.domain.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements IUserDetailsService {
    private  UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserPrincipal loadUserByEmail(String email) throws UsernameNotFoundException {
        try{
            User user = userRepository.findByEmailIgnoreCase(email);
            return new UserPrincipal(user);
        }catch(UsernameNotFoundException e) {
            throw  new UsernameNotFoundException("No user found with email"+email);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Users user = userRepository.findByUserName(username);
        User user = userRepository.findByEmailIgnoreCase(email);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", email));
        }
        return new UserPrincipal(user);
    }

}
