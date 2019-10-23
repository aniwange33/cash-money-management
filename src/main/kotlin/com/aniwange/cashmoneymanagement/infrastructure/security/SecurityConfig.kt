
//@Configuration
//@EnableWebSecurity
//class SecurityConfig(val tokenAuthProvider: TokenAuthenticationProvider) : WebSecurityConfigurerAdapter() {
//    private final val publicUrls : RequestMatcher = OrRequestMatcher (
//            AntPathRequestMatcher("/*"),
//            AntPathRequestMatcher("/v1/api/app/auth/**"),
//            AntPathRequestMatcher("/swagger-ui.html"),
//            AntPathRequestMatcher("/swagger-resources/**"),
//            AntPathRequestMatcher("/v2/api-docs"),
//            AntPathRequestMatcher("/webjars/**"),
//            AntPathRequestMatcher("/js/**"),
//            AntPathRequestMatcher("/css/**"),
//            AntPathRequestMatcher("/images/**"),
//            AntPathRequestMatcher("/fonts/**")
//    )
//    private final val protectedUrls = NegatedRequestMatcher(publicUrls)
//
//    override fun configure(auth: AuthenticationManagerBuilder) {
//        auth.authenticationProvider(this.tokenAuthProvider);
//    }
//
//    override fun configure(web: WebSecurity) {
//        web.ignoring().requestMatchers(publicUrls)
//    }
//
//    override fun configure(http: HttpSecurity) {
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().exceptionHandling().defaultAuthenticationEntryPointFor(forbiddenEntryPoint(), protectedUrls)
//                .and().authenticationProvider(tokenAuthProvider)
//                .addFilterBefore(restAuthenticationFilter(), AnonymousAuthenticationFilter::class.java)
//                .authorizeRequests().requestMatchers(protectedUrls).authenticated()
//                .and().cors().and().csrf().disable()
//                .formLogin().disable()
//                .httpBasic().disable()
//                .logout().disable()
//    }
//
//    @Bean
//    fun authSuccessHandler(): SimpleUrlAuthenticationSuccessHandler {
//        val successHandler = SimpleUrlAuthenticationSuccessHandler()
//        successHandler.setRedirectStrategy(NoRedirectStrategy())
//        return successHandler
//    }
//    @Bean
//    fun authFailureHandler() :  SimpleUrlAuthenticationFailureHandler{
//        return CustomAuthenticationFailureHandler()
//    }
//    @Bean
//    fun forbiddenEntryPoint(): AuthenticationEntryPoint {
//        return HttpStatusEntryPoint(HttpStatus.FORBIDDEN)
//    }
//
//    @Bean
//    fun restAuthenticationFilter(): TokenAuthenticationFilter {
//        val filter = TokenAuthenticationFilter(protectedUrls)
//        filter.setAuthenticationManager(authenticationManager())
//        filter.setAuthenticationSuccessHandler(authSuccessHandler())
//        filter.setAuthenticationFailureHandler(authFailureHandler())
//        filter.setAllowSessionCreation(true)
//        return filter;
//    }
//}
//
//class CustomAuthenticationFailureHandler: SimpleUrlAuthenticationFailureHandler() {
//    override fun onAuthenticationFailure(request: HttpServletRequest?, response: HttpServletResponse, exception: AuthenticationException) {
//        val apiResponse = ApiResponse<String>(exception.message!!)
//        response.addHeader("Content-Type", "application/json")
//        response.characterEncoding = "UTF-8"
//        response.status = HttpStatus.UNAUTHORIZED.value()
//        response.outputStream?.write(AppUtil.getObjectByteArray(apiResponse))
//    }
//}
//class NoRedirectStrategy : RedirectStrategy {
//    override fun sendRedirect(p0: HttpServletRequest?, p1: HttpServletResponse?, p2: String?) {
//       // do nothing here
//    }
//}
//
//@Configuration
//@EnableGlobalMethodSecurity(securedEnabled = true)
//class CustomGlobalMethodSecurity : GlobalMethodSecurityConfiguration() {
//    override fun accessDecisionManager(): AccessDecisionManager {
//       val accessDecisionManager = super.accessDecisionManager() as AffirmativeBased
//        accessDecisionManager.decisionVoters.filterIsInstance<RoleVoter>()
//                .map { it }.forEach { it.rolePrefix = "" }
//        return accessDecisionManager
//    }
//}
//
//
