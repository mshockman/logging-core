plugins {
    kotlin("jvm") version "2.3.0"
    `java-library`
    `maven-publish`
}

val repoUrl = "https://maven.pkg.github.com/mshockman/postgres-liquibase-init"

group = "dev.shockman.logging"
val raw = providers.gradleProperty("releaseVersion").orNull
version = raw?.removePrefix("v") ?: "0.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    api("org.slf4j:slf4j-api:2.0.17")
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(25)
}

tasks.test {
    useJUnitPlatform()
}


java {
    withSourcesJar()
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri(repoUrl)
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                    ?: findProperty("gpr.user") as String?
                password = System.getenv("GITHUB_TOKEN")
                    ?: findProperty("gpr.key") as String?
            }
        }
    }
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            pom {
                name.set(rootProject.name)
                description.set("Logging utility library for Kotlin")
                url.set(repoUrl)
            }
        }
    }
}