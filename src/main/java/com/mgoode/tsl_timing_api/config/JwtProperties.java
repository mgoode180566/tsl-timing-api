package com.mgoode.tsl_timing_api.config;

import com.mgoode.tsl_timing_api.users.model.User;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;

import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;

@Configuration
@ConfigurationProperties(prefix = "security.jwt")
@Getter
@Setter
public class JwtProperties {
	@Value("${security.jwt.secret}")
	private String secretKey;
	
	@Value("${security.jwt.issuer}")
	private String issuer;
	
	@Value("${security.jwt.expiration}")
	private Long tokenExpiration;
	
	public String createJwtUserToken(User user) {
		Instant now = Instant.now();
		JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
			.issuer(getIssuer())
			.issuedAt(now)
			.expiresAt(now.plusSeconds(getTokenExpiration()))
			.subject(user.getUserName())
			.claim("roles", user.getRoles())
			.build();
		var encoder = new NimbusJwtEncoder(new ImmutableSecret<>(getSecretKey().getBytes()));
		var params = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), jwtClaimsSet);
		return encoder.encode(params).getTokenValue();
	}
	
	@Bean
	public JwtDecoder getJwt() {
		var secretKey = new SecretKeySpec(getSecretKey().getBytes(), MacAlgorithm.HS256.getName());
		return NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(MacAlgorithm.HS256).build();
	}
}
