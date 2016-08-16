package org.creativebee.fileserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {

		web.ignoring().antMatchers("/webjars/**", "/css/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/index").permitAll().anyRequest().authenticated();

		http.formLogin().loginProcessingUrl("/login").loginPage("/index").failureUrl("/index?error")
				.defaultSuccessUrl("/folder", true).usernameParameter("userid").passwordParameter("password");

		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout**")).logoutSuccessUrl("/index");

	}

	@Configuration
	protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {

		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {

			auth.inMemoryAuthentication().withUser("user1").password("password1").roles("BASIC_USER","HOGE_USER");
			auth.inMemoryAuthentication().withUser("user2").password("password2").roles("ADMIN_USER");
		}
	}
}
