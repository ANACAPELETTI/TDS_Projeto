package br.edu.utfpr.tds.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//import br.edu.utfpr.tds.api.config.token.CustomTokenEnhancer;

@Profile("oauth-security")
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
				// Simulação de client com escopo read e write
				.withClient("angular")
				.secret("$2a$10$R8OyHvmX3lTHMcp8qoGlvu0B6eFf8HUpSmKiMtOPoF.VLInWAtwvu") //12345
				.scopes("read", "write")
				.authorizedGrantTypes("password", "refresh_token")
				.accessTokenValiditySeconds(900) // token vai expirar em 5 minutos
				.refreshTokenValiditySeconds(3600*24) // refresh token vai expirar em 1 dia
			.and()
				// Simulação de client com escopo read
				.withClient("mobile")
				.secret("$2a$10$cDb.sapiWXGqbNZ9RZ8hWexeNbKftGMB57KuJ0oTNtjZdRXrmHubG") // m0b1b30
				.scopes("read")
				.authorizedGrantTypes("password", "refresh_token")
				.accessTokenValiditySeconds(60) // token vai expirar em 5 minutos
				.refreshTokenValiditySeconds(3600*24); // refresh token vai expirar em 1 dia*/
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		// Token utilizado para adicionar o nome do usuário logado
		//TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		//tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));
		
		endpoints
		.tokenStore(tokenStore())
		.accessTokenConverter(accessTokenConverter())
		//.tokenEnhancer(tokenEnhancerChain)
		.reuseRefreshTokens(false)
		.userDetailsService(userDetailsService)
		.authenticationManager(authenticationManager);
	}
	
	/*@Bean
	public TokenEnhancer tokenEnhancer() {
		return new CustomTokenEnhancer();
	}*/

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
		accessTokenConverter.setSigningKey("tds");
		return accessTokenConverter;
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
}