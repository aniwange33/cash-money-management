package com.aniwange.cashmoneymanagement.infrastructure.security


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDecisionManager
import org.springframework.security.access.vote.AffirmativeBased
import org.springframework.security.access.vote.RoleVoter
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.RedirectStrategy
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.NegatedRequestMatcher
import org.springframework.security.web.util.matcher.OrRequestMatcher
import org.springframework.security.web.util.matcher.RequestMatcher
import java.nio.charset.Charset
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
@EnableWebSecurity
class SecurityConfig(val tokenAuthProvider: TokenAuthenticationProvider) : WebSecurityConfigurerAdapter() {
    private final val publicUrls : RequestMatcher = OrRequestMatcher (
            AntPathRequestMatcher("/*"),
            AntPathRequestMatcher("/v1/api/app/auth/**"),
            AntPathRequestMatcher("/swagger-ui.html"),
            AntPathRequestMatcher("/swagger-resources/**"),
            AntPathRequestMatcher("/v2/api-docs"),
            AntPathRequestMatcher("/webjars/**"),
            AntPathRequestMatcher("/js/**"),
            AntPathRequestMatcher("/css/**"),
            AntPathRequestMatcher("/images/**"),
            AntPathRequestMatcher("/fonts/**")
    )
    private final val protectedUrls = NegatedRequestMatcher(publicUrls)

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(this.tokenAuthProvider);
    }

    override fun configure(web: WebSecurity) {
        web.ignoring().requestMatchers(publicUrls)
    }

    override fun configure(http: HttpSecurity) {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().exceptionHandling().defaultAuthenticationEntryPointFor(forbiddenEntryPoint(), protectedUrls)
                .and().authenticationProvider(tokenAuthProvider)
                .addFilterBefore(restAuthenticationFilter(), AnonymousAuthenticationFilter::class.java)
                .authorizeRequests().requestMatchers(protectedUrls).authenticated()
                .and().cors().and().csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .logout().disable()
    }

    @Bean
    fun authSuccessHandler(): SimpleUrlAuthenticationSuccessHandler {
        val successHandler = SimpleUrlAuthenticationSuccessHandler()
        successHandler.setRedirectStrategy(NoRedirectStrategy())
        return successHandler
    }
    @Bean
    fun authFailureHandler() : SimpleUrlAuthenticationFailureHandler {
        return CustomAuthenticationFailureHandler()
    }
    @Bean
    fun forbiddenEntryPoint(): AuthenticationEntryPoint {
        return HttpStatusEntryPoint(HttpStatus.FORBIDDEN)
    }

    @Bean
    fun restAuthenticationFilter(): TokenAuthenticationFilter {
        val filter = TokenAuthenticationFilter(protectedUrls)
        filter.setAuthenticationManager(authenticationManager())
        filter.setAuthenticationSuccessHandler(authSuccessHandler())
        filter.setAuthenticationFailureHandler(authFailureHandler())
        filter.setAllowSessionCreation(true)
        return filter;
    }
}

class CustomAuthenticationFailureHandler: SimpleUrlAuthenticationFailureHandler() {
    override fun onAuthenticationFailure(request: HttpServletRequest?, response: HttpServletResponse, exception: AuthenticationException) {
        val apiResponse =exception.message
        response.addHeader("Content-Type", "application/json")
        response.characterEncoding = "UTF-8"
        response.status = HttpStatus.UNAUTHORIZED.value()
        if (apiResponse != null) {
            response.outputStream?.write(apiResponse.toByteArray(Charset.defaultCharset()))
        }
    }
}
class NoRedirectStrategy : RedirectStrategy {
    override fun sendRedirect(p0: HttpServletRequest?, p1: HttpServletResponse?, p2: String?) {
       // do nothing here
    }
}

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
class CustomGlobalMethodSecurity : GlobalMethodSecurityConfiguration() {
    override fun accessDecisionManager(): AccessDecisionManager {
       val accessDecisionManager = super.accessDecisionManager() as AffirmativeBased
        accessDecisionManager.decisionVoters.filterIsInstance<RoleVoter>()
                .map { it }.forEach { it.rolePrefix = "" }
        return accessDecisionManager
    }
}


