package int371.namjai.utill.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserDetailsService extends UserDetailsService {
//    UserDetails loadUserByEmail(String email) throws UsernameNotFoundException;
    UserPrincipal loadUserByEmail(String email) throws UsernameNotFoundException;
}
