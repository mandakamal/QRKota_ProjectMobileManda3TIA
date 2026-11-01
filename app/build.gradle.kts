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
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        viewBinding = true
    }

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
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("com.google.android.material:material:1.12.0")

    // Materi pertemuan 6: splash screen dan coroutine
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    // Materi pertemuan 7: menampilkan foto pada Custom Adapter
    implementation("com.github.bumptech.glide:glide:4.16.0")
    // Materi pertemuan 9: GridLayout, ViewPager2, DotsIndicator
    implementation("androidx.gridlayout:gridlayout:1.0.0")
    implementation("androidx.viewpager2:viewpager2:1.1.0")
    implementation("com.tbuonomo:dotsindicator:5.1.0")
}
