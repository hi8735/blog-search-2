package com.assessment.externalapi.config

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients(basePackages = ["com.assessment.externalapi"])
class FeignConfig {
}
