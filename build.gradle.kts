import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "2.7.9"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("jvm") version "1.7.10"
    kotlin("plugin.spring") version "1.7.10"
    kotlin("plugin.jpa") version "1.7.10"
}

java.sourceCompatibility = JavaVersion.VERSION_11

tasks.withType<BootJar>{
    this.enabled = false
}

tasks.getByName<Jar>("jar") {
    this.enabled = false
}

allprojects{
    group = "com.assessment"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral() // or another repository that hosts the Kotlin standard library
    }
}

subprojects {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.kapt")

    tasks.getByName("bootJar"){
        enabled = false
    }

    tasks.getByName("jar"){
        enabled = true
    }

    dependencies {
        implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.7.10")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
