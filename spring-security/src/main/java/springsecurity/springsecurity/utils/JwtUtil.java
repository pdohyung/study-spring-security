package springsecurity.springsecurity.utils;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
	public static String createJwt(String userName, String secretKey, Long expiredMs){
		// 비밀키는 토큰에 서명하는데 쓰고 유저 네임은 리뷰를 쓸때 토큰에 있는 유저네임을 꺼내서 확인한다.
		Claims claims = Jwts.claims(); // map과 비슷하다.
		claims.put("userName", userName);

		return Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + expiredMs))
			.signWith(SignatureAlgorithm.HS256, secretKey)
			.compact();
	}

	public static boolean isExpired(String token, String secretKey){
		return Jwts.parser()
			.setSigningKey(secretKey)
			.parseClaimsJws(token)
			.getBody()
			.getExpiration()
			.before(new Date());
	}
}
