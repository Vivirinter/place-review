package com.vivir.placereview.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
class MvcConfig : WebMvcConfigurerAdapter() {

    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry?.addViewController("/login")?.setViewName("login")
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry?.addResourceHandler(
            "/webjars/**",
            "/css/**"
        )
            ?.addResourceLocations(
                "classpath:/META-INF/resources/webjars/",
                "classpath:/static/css/"
            )
    }
}