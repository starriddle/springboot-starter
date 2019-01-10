package com.starriddle.starter.springboot.admin.server;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import de.codecentric.boot.admin.server.config.AdminServerProperties;

/**
 * 权限配置，放开静态资源及登入登出页面的权限
 *
 * @author CYL
 * @date 2019-01-10
 */
@Configuration
public class SecuritySecureConfig extends WebSecurityConfigurerAdapter {

    private final String adminContextPath;

    public SecuritySecureConfig(AdminServerProperties properties) {
        this.adminContextPath = properties.getContextPath();
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        SavedRequestAwareAuthenticationSuccessHandler handler = new SavedRequestAwareAuthenticationSuccessHandler();
        handler.setTargetUrlParameter("redirectTo");
        security.authorizeRequests().antMatchers(adminContextPath + "/assets/**").permitAll()
                .antMatchers(adminContextPath + "/login").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage(adminContextPath + "/login").successHandler(handler)
                .and().logout().logoutUrl(adminContextPath + "/logout")
                .and().httpBasic().and().csrf().disable();
    }
}
