plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "io.github.duzhaokun123.yapatch"
    compileSdk = 35

    defaultConfig {
        applicationId = "io.github.duzhaokun123.yapatch"
        minSdk = 33
        targetSdk = 35
        versionCode = 8
        versionName = "0.1.7"
    }

    androidResources {
        generateLocaleConfig = true
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
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(projects.patch)
    implementation(libs.viewbindingutil)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.gson)
}