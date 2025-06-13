plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
//    alias(libs.plugins.kotlin.compose)
    kotlin("kapt")
}

android {
    namespace = "com.example.sfbookreader"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.sfbookreader"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

// 添加仓库配置
repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }  // 添加JitPack仓库
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

}

// 正确的Kotlin DSL格式依赖配置
dependencies {
    // 基础组件
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.activity:activity-ktx:1.8.0")  // 添加Activity KTX

    // ViewModel 和 LiveData
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    // RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // 图片加载
    implementation("com.github.bumptech.glide:glide:4.16.0")
    kapt("com.github.bumptech.glide:compiler:4.16.0")  // 使用kapt替代annotationProcessor

    // 文件处理
    implementation("androidx.documentfile:documentfile:1.0.1")

    // Room数据库
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

    // 协程
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    // PDF 解析 - 使用稳定版本
    implementation("com.github.barteksc:android-pdf-viewer:3.2.0")  // 移除beta后缀

    // EPUB 解析 - 使用正确的库
    implementation("nl.siegmann.epublib:epublib-core:3.1")  // 替换fbreader

    // 测试依赖
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}