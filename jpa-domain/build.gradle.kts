plugins {
    kotlin("plugin.jpa") version "1.7.10"
}

dependencies {
    implementation(project(":jpa-infrastructure"))
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("org.springframework.cache.annotation.Cacheable")
}
