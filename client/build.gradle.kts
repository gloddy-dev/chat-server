extra["springCloudVersion"] = "2023.0.0"

dependencies {
    implementation(project(":domain"))
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}
