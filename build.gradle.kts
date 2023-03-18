import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
plugins {
    id("org.springframework.boot") version "2.7.9"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("jvm") version "1.7.10"
    kotlin("plugin.spring") version "1.7.10"
    kotlin("plugin.jpa") version "1.7.10"
}

//tasks.withType<BootJar>{
//    this.enabled = false
//}
//
//tasks.getByName<Jar>("jar") {
//    this.enabled = false
//}

allprojects{
    group = "com.assessment"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral() // or another repository that hosts the Kotlin standard library
    }
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-spring")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    dependencies {
        implementation(kotlin("stdlib"))
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
