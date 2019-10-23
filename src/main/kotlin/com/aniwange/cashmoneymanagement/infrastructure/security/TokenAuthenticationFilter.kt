

//class TokenAuthenticationFilter(requestMatcher: RequestMatcher) : AbstractAuthenticationProcessingFilter(requestMatcher) {
//
//    private val BEARER = "Bearer"
//    @Throws(AuthenticationException::class)
//    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
//        val param: String? = request.getHeader(AUTHORIZATION)
//        if(param == null || !param.startsWith(BEARER)) {
//            logger.info("Authorization-header: ${request.getHeader("authorization")}")
//            throw BadCredentialsException("Bad Authorization Token Format.")
//        }
//        val token = param.removePrefix(BEARER).trim();
//        //  logger.info("Token is $token")
//        val auth = UsernamePasswordAuthenticationToken(authenticationDetailsSource.buildDetails(request), token)
//        return authenticationManager.authenticate(auth)
//    }
//
//    @Throws(IOException::class, ServletException::class)
//    override fun successfulAuthentication(
//            request: HttpServletRequest,
//            response: HttpServletResponse,
//            chain: FilterChain?,
//            authResult: Authentication) {
//        super.successfulAuthentication(request, response, chain, authResult)
//        chain!!.doFilter(request, response)
//    }
//
//}

