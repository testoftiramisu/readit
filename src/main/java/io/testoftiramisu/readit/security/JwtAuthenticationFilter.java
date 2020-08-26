package io.testoftiramisu.readit.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.function.Predicate;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  static Predicate<String> notEmpty = StringUtils::hasText;
  static Predicate<String> startsWithBearer = token -> token.startsWith("Bearer ");

  @Autowired private JwtProvider jwtProvider;
  @Autowired private UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      FilterChain filterChain)
      throws IOException, ServletException {
    String jwt = getJwtFromRequest(httpServletRequest);

    if (notEmpty.test(jwt) && jwtProvider.validateToken(jwt)) {
      String username = jwtProvider.getUsernameFromJwt(jwt);

      UserDetails userDetails = userDetailsService.loadUserByUsername(username);
      UsernamePasswordAuthenticationToken authenticationToken =
          new UsernamePasswordAuthenticationToken(null, userDetails.getAuthorities());

      authenticationToken.setDetails(
          new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }

  private String getJwtFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");

    return notEmpty.and(startsWithBearer).test(bearerToken)
        ? bearerToken.substring(7)
        : bearerToken;
  }
}