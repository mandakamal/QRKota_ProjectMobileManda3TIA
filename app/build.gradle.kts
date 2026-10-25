plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.qrkota.pekanbaru"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.qrkota.pekanbaru"
        minSdk = 23
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        viewBinding = true
    }
    android {
        // Pengaturan namespace, SDK, dan lainnya tetap dipertahankan.

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        kotlinOptions {
            jvmTarget = "1.8"
        }

    }
        dependencies {
            implementation("androidx.core:core-ktx:1.15.0")
            implementation("androidx.appcompat:appcompat:1.7.0")
            implementation("androidx.fragment:fragment-ktx:1.8.5")
            implementation("com.google.android.material:material:1.12.0")
            implementation("androidx.cardview:cardview:1.0.0")
            implementation("androidx.constraintlayout:constraintlayout:2.2.0")
            // Library scanner QR berbasis kamera. Gradle memerlukan internet saat pertama kali sync.
            implementation("com.journeyapps:zxing-android-embedded:4.3.0")
            implementation("com.google.zxing:core:3.5.3")
        }
    }