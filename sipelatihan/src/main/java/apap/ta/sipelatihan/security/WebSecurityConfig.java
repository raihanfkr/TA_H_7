package apap.ta.sipelatihan.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/api/v1/**").permitAll()
                .antMatchers("/vendor/**").permitAll()
                .antMatchers("/user/profil").permitAll()
                .antMatchers("/user/**").hasAnyAuthority("Kepala Bagian")
                .antMatchers("/trainer/add/**").hasAnyAuthority("Staff Training")
                .antMatchers("/trainer/ubah/**").hasAnyAuthority("Staff Training")
                .antMatchers("/peserta/**").hasAnyAuthority("Staff Training", "Kepala Bagian")
                .antMatchers("/pelatihan/viewall").hasAnyAuthority("Staff Training", "Kepala Bagian", "Kepala Departemen HR")
                .antMatchers("/pelatihan/add").hasAnyAuthority("Staff Training", "Kepala Bagian")
                .antMatchers("/pelatihan/detail/**").hasAnyAuthority("Staff Training", "Kepala Bagian", "Kepala Departemen HR")
                .antMatchers("/pelatihan/ubah/**").hasAnyAuthority("Staff Training", "Kepala Bagian")
//                .antMatchers("/pelatihan/hapus/**").hasAnyAuthority("Staff Training", "Kepala Bagian")
                .antMatchers("/pelatihan/update-status/**").hasAnyAuthority("Kepala Bagian", "Kepala Departemen HR")

                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll()
                .and().cors().and().csrf().disable();
    }

    @Bean
    public BCryptPasswordEncoder encoder() { return new BCryptPasswordEncoder(); }

    @Bean
    public SpringSecurityDialect springSecurityDialect(){
        return new SpringSecurityDialect();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .passwordEncoder(encoder())
                .withUser("admin").password(encoder().encode("admin"))
                .roles("Kepala Bagian");
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }
}
