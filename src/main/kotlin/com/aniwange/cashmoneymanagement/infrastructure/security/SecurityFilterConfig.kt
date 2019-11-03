package com.aniwange.cashmoneymanagement.infrastructure.security


import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


//private val logger = KotlinLogging.logger {}
@Configuration
class SecurityFilterConfig(val clientCredentialAuthenticationFilter: ClientCredentialAuthenticationFilter) {

    @Bean
    fun appApiFilterBean(): FilterRegistrationBean<ClientCredentialAuthenticationFilter> {
        val registrationBean = FilterRegistrationBean<ClientCredentialAuthenticationFilter>()
        registrationBean.filter = clientCredentialAuthenticationFilter;
        registrationBean.urlPatterns = listOf("/api/v1/*")
        return registrationBean
    }

}

@Component
class ClientCredentialAuthenticationFilter : Filter {
    @Value("\${app.client-key}")
    val appClientKey: String = ""

    override fun doFilter(request: ServletRequest, response: ServletResponse, filterChain: FilterChain) {
        val httpRequest = request as HttpServletRequest
        val httpResponse = response as HttpServletResponse
        /*
          FOR CORS: Access-Control-Allow-Origin is usually set by spring security for authenticated request.
         */
        if(httpResponse.getHeader("Access-Control-Allow-Origin") == null) {
            httpResponse.addHeader("Access-Control-Allow-Origin", "*")
        }

        if(httpRequest.method == HttpMethod.OPTIONS.name) {
            httpResponse.status = HttpServletResponse.SC_ACCEPTED;
            return
        }
        val clientKey = httpRequest.getHeader("client-key")
        if (appClientKey != clientKey) {
            val apiResponse ="Unauthorised Access: Invalid client key"
            response.addHeader("Content-Type", "application/json")
            response.characterEncoding = "UTF-8"
            response.status = HttpStatus.UNAUTHORIZED.value()
           throw  IllegalAccessException(apiResponse)
        }
        filterChain.doFilter(request, response)
    }
}
