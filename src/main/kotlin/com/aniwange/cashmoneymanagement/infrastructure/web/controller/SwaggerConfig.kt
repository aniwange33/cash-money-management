package com.aniwange.cashmoneymanagement.infrastructure.web.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.info.BuildProperties
import org.springframework.boot.info.GitProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestMethod
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.builders.ResponseMessageBuilder
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.ResponseMessage
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger.web.*
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*

@Configuration
@EnableSwagger2
class SwaggerConfig {
    @Autowired
    lateinit var build: Optional<BuildProperties>
    @Autowired
    lateinit var git: Optional<GitProperties>

    @Bean
    fun api(): Docket {
        var version = "1.0"
        if (build.isPresent && git.isPresent) {
            val buildInfo = build.get()
            val gitInfo = git.get()
            version = "${buildInfo.version} - ${gitInfo.shortCommitId} - ${gitInfo.branch}"
        }
        val globalResponse = listOf<ResponseMessage>(
                ResponseMessageBuilder().code(HttpStatus.OK.value()).message("Request processed successfully").build(),
                ResponseMessageBuilder().code(HttpStatus.BAD_REQUEST.value()).message("Bad Request, check request details").build(),
                ResponseMessageBuilder().code(HttpStatus.UNAUTHORIZED.value()).message("Unauthorised request, invalid credentials").build(),
                ResponseMessageBuilder().code(HttpStatus.NOT_FOUND.value()).message("Request resource not found").build(),
                ResponseMessageBuilder().code(HttpStatus.CONFLICT.value()).message("Business logic conflict. Error due to unfulfilled business rules").build(),
                ResponseMessageBuilder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).message("Ops, Unable to process request due to server error").build()
        )
        return Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, globalResponse)
                .globalResponseMessage(RequestMethod.POST, globalResponse)
                .globalResponseMessage(RequestMethod.DELETE, globalResponse)
                .globalResponseMessage(RequestMethod.PUT, globalResponse)
                .apiInfo(apiInfo(version))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.aniwange.cashmoneymanagement.infrastructure.web.controller"))
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/**"))
                .build()
                .forCodeGeneration(true)
    }

    @Bean
    fun uiConfig(): UiConfiguration {
        return UiConfiguration(java.lang.Boolean.TRUE, java.lang.Boolean.FALSE,
                1, 1, ModelRendering.MODEL,
                java.lang.Boolean.FALSE, DocExpansion.LIST, java.lang.Boolean.FALSE, null,
                OperationsSorter.ALPHA, java.lang.Boolean.FALSE,
                TagsSorter.ALPHA, UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS, null)
    }
    private fun apiInfo(version: String): ApiInfo {
        return ApiInfoBuilder()
                .title("Cash Money management application")
                .description("API service for Cash Money Management application")
                .version(version)
                .build()
    }
}