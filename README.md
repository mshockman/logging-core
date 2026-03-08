# Description

This library provides basic logging utility functions.

## Usage

```kotlin
import dev.shockman.logging.logger


class MyClass {
    private val logger = logger()
    
    fun doSomething() {
        logger.logInfo { "Log something" }
        logger.logInfo(mapOf("key" to "value")) { "Log something with additional context" }
    }
}

```

## Installation

Add the package as a dependency.

```kotlin
dependencies { implementation("dev.shockman.logging:logging-core:<version>") }
```

If you are consuming it from GitHub Packages, make sure your build is configured to resolve packages from your GitHub Maven repository.

```kotlin
repositories {
    mavenCentral()

    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/mshockman/messaging")

        credentials {
            username = (findProperty("gpr.user") as String?) ?: System.getenv("GITHUB_ACTOR")
            password = (findProperty("gpr.key") as String?) ?: System.getenv("GITHUB_TOKEN")
        }
    }
}
```

## Releasing

This project is published through GitHub Actions when a version tag is pushed.

Example:

```bash
git tag v1.0.0 
git push origin v1.0.0
```

The build uses the tag name as the artifact version, with the leading `v` removed.