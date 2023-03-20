plugins {
    kotlin("plugin.jpa") version "1.7.10"
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("org.springframework.cache.annotation.Cacheable")
}

dependencies {
    implementation(project(":domain"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}
