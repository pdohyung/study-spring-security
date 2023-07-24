package springsecurity.springsecurity.config;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import springsecurity.springsecurity.service.UserService;
import springsecurity.springsecurity.utils.JwtUtil;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

	private final UserService userService;
	private final String secretKey;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
		log.info("authorization : {}", authorization);

		//token 보내지 않으면 block
		if (authorization == null || !authorization.startsWith("Bearer ")){
			log.error("Authorization을 잘못보냈습니다.");
			filterChain.doFilter(request, response);
			return;
		}

		//token 꺼내기
		String token = authorization.split(" ")[1];

		//token Expired 검사
		if(JwtUtil.isExpired(token, secretKey)){
			log.error("Token이 만료되었습니다.");
			filterChain.doFilter(request, response);
			return;
		}

		//UserName Token 꺼내기
		String userName = "";

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, null,
			List.of(new SimpleGrantedAuthority("USER")));
		authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		filterChain.doFilter(request, response);
	}
}
