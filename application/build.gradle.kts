dependencies {
    implementation(project(":external-api"))
    implementation(project(":domain"))
    implementation(project(":common"))
    implementation(project(":infrastructure"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("it.ozimov:embedded-redis:0.7.3") {
        exclude(group = "org.slf4j", module = "slf4j-simple")
    }
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework:spring-tx")
}
