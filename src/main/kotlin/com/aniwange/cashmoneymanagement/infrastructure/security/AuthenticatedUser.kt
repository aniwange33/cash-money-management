




//class AuthenticatedUser : UserDetails {
//
//    private var authorities: MutableList<SimpleGrantedAuthority> = ArrayList()
//    var id: Long = 0
//    var userId: String = ""
//    var email: String = ""
//    var userPassword: String = ""
//    //var enabled: Boolean = true
//    var blocked: Boolean = false
//   // private var deactivated: Boolean = false
//    private var expiredCredential: Boolean = false
//
//    fun addAuthority(grantedAuthority: SimpleGrantedAuthority) {
//        authorities.add(grantedAuthority)
//    }
//
//    override fun getAuthorities(): List<SimpleGrantedAuthority> = authorities
//
//    override fun isEnabled(): Boolean = !blocked
//
//    override fun getUsername(): String = email
//
//    override fun isCredentialsNonExpired(): Boolean = !expiredCredential
//
//    override fun getPassword(): String = userPassword
//
//    override fun isAccountNonExpired(): Boolean = true
//
//    override fun isAccountNonLocked(): Boolean = !blocked
//}
