package com.stage.pfe;

import com.stage.pfe.dao.UserRepository;
import com.stage.pfe.entities.Role;
import com.stage.pfe.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Modifying or overriding the default spring boot security.
 */
@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private OAuth2ClientContext oauth2ClientContext;





	private AuthorizationCodeResourceDetails authorizationCodeResourceDetails;
	private ResourceServerProperties resourceServerProperties;


	private static final String GOOGLE_PLUS_DOMAIN_ATTRIBUTE = "leanovia.com";
	private static final String CSRF_COOKIE_NAME = "XSRF-TOKEN";
	private static final String CSRF_HEADER_NAME = "X-XSRF-TOKEN";




	@Autowired
	public void setOauth2ClientContext(OAuth2ClientContext oauth2ClientContext) {
		this.oauth2ClientContext = oauth2ClientContext;
	}

	@Autowired
	public void setAuthorizationCodeResourceDetails(AuthorizationCodeResourceDetails authorizationCodeResourceDetails) {
		this.authorizationCodeResourceDetails = authorizationCodeResourceDetails;
	}

	@Autowired
	public void setResourceServerProperties(ResourceServerProperties resourceServerProperties) {
		this.resourceServerProperties = resourceServerProperties;
	}

	/* This method is for overriding the default AuthenticationManagerBuilder.
     We can specify how the user details are kept in the application. It may
     be in a database, LDAP or in memory.*/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
	}

	/* This method is for overriding some configuration of the WebSecurity
     If you want to ignore some request or request patterns then you can
     specify that inside this method.*/
	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

	/*This method is used for override HttpSecurity of the web Application.
    We can specify our authorization criteria inside this method.*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
				// Starts authorizing configurations.
				.authorizeRequests()
				// Ignore the "/" and "/index.html"
				.antMatchers("/**.html", "/**.js").permitAll()
				// Authenticate all remaining URLs.
				.anyRequest().fullyAuthenticated()
				.and()
				.formLogin()
				.loginPage("/google/login")
				.permitAll()
				.and()
				// Setting the logout URL "/logout" - default logout URL.
				.logout()
				.invalidateHttpSession(true)
				// After successful logout the application will redirect to "/" path.
				.logoutSuccessUrl("/google/login")
				.permitAll()
				.and()
				// Setting the filter for the URL "/google/login".
				.addFilterAt(filter(), BasicAuthenticationFilter.class)
				.csrf().disable()
				;

	}

	/*This method for creating filter for OAuth authentication.*/
	private OAuth2ClientAuthenticationProcessingFilter filter() {
		//Creating the filter for "/google/login" url
		OAuth2ClientAuthenticationProcessingFilter oAuth2Filter = new OAuth2ClientAuthenticationProcessingFilter(
				"/google/login");

		//Creating the rest template for getting connected with OAuth service.
		//The configuration parameters will inject while creating the bean.
		OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(authorizationCodeResourceDetails,
				oauth2ClientContext);
		oAuth2Filter.setRestTemplate(oAuth2RestTemplate);

		// Setting the token service. It will help for getting the token and
		// user details from the OAuth Service.
		oAuth2Filter.setTokenServices(new UserInfoTokenServices(resourceServerProperties.getUserInfoUri(),
				resourceServerProperties.getClientId()));

		return oAuth2Filter;
	}
	@Bean
	public PrincipalExtractor principalExtractor(UserRepository userRepository) {
		return map -> {

			String principalId = (String) map.get("sub");
			User user = userRepository.findByPrincipalId(principalId);
			if (user == null) {
				user = new User();
				user.setPrincipalId(principalId);
				user.setEmail((String) map.get("email"));
				user.setName((String) map.get("name"));
				user.setPhoto((String) map.get("picture"));
			}
			userRepository.save(user);
			return user;
		};
	}

	@Bean
	public OAuth2RestOperations template (){return new OAuth2RestOperations() {
		@Override
		public OAuth2AccessToken getAccessToken() throws UserRedirectRequiredException {
			return null;
		}

		@Override
		public OAuth2ClientContext getOAuth2ClientContext() {
			return null;
		}

		@Override
		public OAuth2ProtectedResourceDetails getResource() {
			return null;
		}

		@Override
		public <T> T getForObject(String s, Class<T> aClass, Object... objects) throws RestClientException {
			return null;
		}

		@Override
		public <T> T getForObject(String s, Class<T> aClass, Map<String, ?> map) throws RestClientException {
			return null;
		}

		@Override
		public <T> T getForObject(URI uri, Class<T> aClass) throws RestClientException {
			return null;
		}

		@Override
		public <T> ResponseEntity<T> getForEntity(String s, Class<T> aClass, Object... objects) throws RestClientException {
			return null;
		}

		@Override
		public <T> ResponseEntity<T> getForEntity(String s, Class<T> aClass, Map<String, ?> map) throws RestClientException {
			return null;
		}

		@Override
		public <T> ResponseEntity<T> getForEntity(URI uri, Class<T> aClass) throws RestClientException {
			return null;
		}

		@Override
		public HttpHeaders headForHeaders(String s, Object... objects) throws RestClientException {
			return null;
		}

		@Override
		public HttpHeaders headForHeaders(String s, Map<String, ?> map) throws RestClientException {
			return null;
		}

		@Override
		public HttpHeaders headForHeaders(URI uri) throws RestClientException {
			return null;
		}

		@Override
		public URI postForLocation(String s, Object o, Object... objects) throws RestClientException {
			return null;
		}

		@Override
		public URI postForLocation(String s, Object o, Map<String, ?> map) throws RestClientException {
			return null;
		}

		@Override
		public URI postForLocation(URI uri, Object o) throws RestClientException {
			return null;
		}

		@Override
		public <T> T postForObject(String s, Object o, Class<T> aClass, Object... objects) throws RestClientException {
			return null;
		}

		@Override
		public <T> T postForObject(String s, Object o, Class<T> aClass, Map<String, ?> map) throws RestClientException {
			return null;
		}

		@Override
		public <T> T postForObject(URI uri, Object o, Class<T> aClass) throws RestClientException {
			return null;
		}

		@Override
		public <T> ResponseEntity<T> postForEntity(String s, Object o, Class<T> aClass, Object... objects) throws RestClientException {
			return null;
		}

		@Override
		public <T> ResponseEntity<T> postForEntity(String s, Object o, Class<T> aClass, Map<String, ?> map) throws RestClientException {
			return null;
		}

		@Override
		public <T> ResponseEntity<T> postForEntity(URI uri, Object o, Class<T> aClass) throws RestClientException {
			return null;
		}

		@Override
		public void put(String s, Object o, Object... objects) throws RestClientException {

		}

		@Override
		public void put(String s, Object o, Map<String, ?> map) throws RestClientException {

		}

		@Override
		public void put(URI uri, Object o) throws RestClientException {

		}

		@Override
		public <T> T patchForObject(String s, Object o, Class<T> aClass, Object... objects) throws RestClientException {
			return null;
		}

		@Override
		public <T> T patchForObject(String s, Object o, Class<T> aClass, Map<String, ?> map) throws RestClientException {
			return null;
		}

		@Override
		public <T> T patchForObject(URI uri, Object o, Class<T> aClass) throws RestClientException {
			return null;
		}

		@Override
		public void delete(String s, Object... objects) throws RestClientException {

		}

		@Override
		public void delete(String s, Map<String, ?> map) throws RestClientException {

		}

		@Override
		public void delete(URI uri) throws RestClientException {

		}

		@Override
		public Set<HttpMethod> optionsForAllow(String s, Object... objects) throws RestClientException {
			return null;
		}

		@Override
		public Set<HttpMethod> optionsForAllow(String s, Map<String, ?> map) throws RestClientException {
			return null;
		}

		@Override
		public Set<HttpMethod> optionsForAllow(URI uri) throws RestClientException {
			return null;
		}

		@Override
		public <T> ResponseEntity<T> exchange(String s, HttpMethod httpMethod, HttpEntity<?> httpEntity, Class<T> aClass, Object... objects) throws RestClientException {
			return null;
		}

		@Override
		public <T> ResponseEntity<T> exchange(String s, HttpMethod httpMethod, HttpEntity<?> httpEntity, Class<T> aClass, Map<String, ?> map) throws RestClientException {
			return null;
		}

		@Override
		public <T> ResponseEntity<T> exchange(URI uri, HttpMethod httpMethod, HttpEntity<?> httpEntity, Class<T> aClass) throws RestClientException {
			return null;
		}

		@Override
		public <T> ResponseEntity<T> exchange(String s, HttpMethod httpMethod, HttpEntity<?> httpEntity, ParameterizedTypeReference<T> parameterizedTypeReference, Object... objects) throws RestClientException {
			return null;
		}

		@Override
		public <T> ResponseEntity<T> exchange(String s, HttpMethod httpMethod, HttpEntity<?> httpEntity, ParameterizedTypeReference<T> parameterizedTypeReference, Map<String, ?> map) throws RestClientException {
			return null;
		}

		@Override
		public <T> ResponseEntity<T> exchange(URI uri, HttpMethod httpMethod, HttpEntity<?> httpEntity, ParameterizedTypeReference<T> parameterizedTypeReference) throws RestClientException {
			return null;
		}

		@Override
		public <T> ResponseEntity<T> exchange(RequestEntity<?> requestEntity, Class<T> aClass) throws RestClientException {
			return null;
		}

		@Override
		public <T> ResponseEntity<T> exchange(RequestEntity<?> requestEntity, ParameterizedTypeReference<T> parameterizedTypeReference) throws RestClientException {
			return null;
		}

		@Override
		public <T> T execute(String s, HttpMethod httpMethod, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor, Object... objects) throws RestClientException {
			return null;
		}

		@Override
		public <T> T execute(String s, HttpMethod httpMethod, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor, Map<String, ?> map) throws RestClientException {
			return null;
		}

		@Override
		public <T> T execute(URI uri, HttpMethod httpMethod, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor) throws RestClientException {
			return null;
		}
	};}

	@Bean
	public AuthoritiesExtractor authoritiesExtractor(UserRepository userRepository) {
		return map -> {
			String url = (String) map.get("hd");
			if(url != null) {	if (url.equals("leanovia.com")) {
									return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
																}
									throw new BadCredentialsException("Not in Leanovia Team");
								}
			throw new BadCredentialsException("Not in Leanovia Team");

		};
	}
}
