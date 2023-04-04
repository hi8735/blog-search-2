plugins {
    kotlin("plugin.jpa") version "1.7.10"
}

allOpen {
    annotation("org.springframework.cache.annotation.Cacheable")
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":common"))
    implementation("com.h2database:h2")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}
