// build.gradle.kts (项目级)
plugins {
    id("com.android.application") version "8.9.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.22" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}