dependencies {
    implementation(project(":domain"))
    implementation(platform("io.awspring.cloud:spring-cloud-aws-dependencies:3.0.3"))
    implementation("io.awspring.cloud:spring-cloud-aws-starter-sns")
}
