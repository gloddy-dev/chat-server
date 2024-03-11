extra["springCloudVersion"] = "2023.0.0"

dependencies {
    implementation(project(":domain"))
    implementation(project(":client"))
    implementation(project(":in-message"))
    implementation(project(":out-message"))
    implementation(project(":client"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-websocket")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}
