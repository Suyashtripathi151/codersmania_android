plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.codersmania"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.codersmania"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildToolsVersion = "33.0.1"
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.gridlayout:gridlayout:1.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")


    // Retrofit for networking
    implementation("com.squareup.retrofit2:retrofit:2.11.0")

    // Converter for JSON (Gson)
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    // OkHttp logging interceptor (for logging network requests and responses)
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
}