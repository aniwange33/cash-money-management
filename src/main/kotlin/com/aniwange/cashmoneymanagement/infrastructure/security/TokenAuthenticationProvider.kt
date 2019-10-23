

//@Service
//class TokenAuthenticationProvider : AbstractUserDetailsAuthenticationProvider() {
//
//    @Autowired
//    lateinit var appUserService: AppUserService;
//
//    @Autowired
//    lateinit var tokenService: TokenService
//
//    @Autowired
//    lateinit var rolePrivilegeService: RolePrivilegeService
//
//    override fun retrieveUser(p0: String?, p1: UsernamePasswordAuthenticationToken?): UserDetails {
//        val token = p1?.credentials as String?
//        return token?.let { findByToken(it)} ?: throw UsernameNotFoundException("Invalid authorization token.")
//    }
//
//    override fun additionalAuthenticationChecks(p0: UserDetails?, p1: UsernamePasswordAuthenticationToken?) {
//       logger.info("additionalAuthenticationChecks called")
//    }
//
//    fun fromAppUserToAuthenticatedUser(appUser: AppUser): AuthenticatedUser {
//        val authUser = AuthenticatedUser()
//        authUser.email = appUser.email;
//        authUser.userId = appUser.userId
//        authUser.id = appUser.id!!
//        authUser.userPassword = appUser.password
//        authUser.blocked = appUser.accountStatus != AccountStatus.ACTIVE
//        rolePrivilegeService.getPrivilegesByRole(appUser.userRole).forEach {
//            authUser.addAuthority(SimpleGrantedAuthority(it.privilege.name))  }
//        return authUser
//    }
//
//    fun findByToken(token: String): AuthenticatedUser? {
//        val attributes = tokenService.verify(token)
//        if(attributes.isEmpty()) {
//            AppUtil.logInformation("token attributes is empty")
//            return null
//        }
//        val userId: String? = attributes["userId"]
//        val authKey: String? = attributes["authKey"]
//        if(userId == null || authKey == null) {
//            return null
//        }
//        val optionalAppUser = appUserService.findUserByUserId(userId)
//        if(!optionalAppUser.isPresent) {
//            return null
//        }
//        val appUser = optionalAppUser.get()
//        if(appUser.authKey != authKey) {
//            return null
//        }
//        return fromAppUserToAuthenticatedUser(appUser)
//    }
//}
