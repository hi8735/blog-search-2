import org.springframework.boot.gradle.tasks.bundling.BootJar

tasks.withType<BootJar>{
    this.archiveFileName.set("blog-search-api.jar")
    this.enabled = true
}

tasks.getByName("jar") {
    this.enabled = false
}

dependencies {
    implementation(project(":application"))
    implementation(project(":common"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("io.springfox:springfox-boot-starter:3.0.0")

    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.aspectj:aspectjweaver")

//    testImplementation("org.springframework.boot:spring-boot-starter-aop")
//    testImplementation("org.aspectj:aspectjweaver")
}
