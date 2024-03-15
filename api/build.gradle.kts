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

tasks {
    withType<Jar> { enabled = false }
    withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
        enabled = true
        archiveFileName.set("GloddyChat.jar")
        mainClass.set("gloddy.GloddyChatApplicationKt")
    }
}
