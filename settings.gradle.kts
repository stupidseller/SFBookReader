// settings.gradle.kts
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        // 可选：添加阿里云镜像加速
        maven { url = uri("https://maven.aliyun.com/repository/public") }
    }
}

rootProject.name = "SFBookReader"
include(":app")