package int371.namjai.domain.auth.auth;


import int371.namjai.utill.auth.IUserDetailsService;
import int371.namjai.utill.auth.TokenHelper;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@NoArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private TokenHelper tokenHelper;

    private IUserDetailsService iUserDetailsService;

    public TokenAuthenticationFilter(TokenHelper tokenHelper, IUserDetailsService iUserDetailsService) {
        this.tokenHelper = tokenHelper;
        this.iUserDetailsService=iUserDetailsService;
    }


    @Override
    public void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

//        String username;
        String email;
        String authToken = tokenHelper.getToken(request);

//        if (authToken != null) {
        if (!ObjectUtils.isEmpty(authToken)) {
            // get username from token
//            username = tokenHelper.getUsernameFromToken(authToken);
            email = tokenHelper.getEmailFromToken(authToken);
            if (!ObjectUtils.isEmpty(email)) {
                // get user
//                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UserDetails userDetails = iUserDetailsService.loadUserByUsername(email);
                if (tokenHelper.validateToken(authToken, userDetails)) {
                    // create authentication
                    TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
                    authentication.setToken(authToken);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }
}