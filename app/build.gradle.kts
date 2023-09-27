plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")

}
android {
    namespace = "com.example.swvlclone"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.swvlclone"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    val room_version = "2.5.2"
    val lifecycle_version = "2.6.1"
    val google_maps_version = "2.14.0"
    val hilt_version = "2.48"

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycle_version")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material:1.6.0-alpha05")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("com.googlecode.libphonenumber:libphonenumber:8.13.20")


    //Country Code Picker
    implementation("com.canopas.jetcountrypicker:jetcountrypicker:1.0.9")

    //Coil for network images
    implementation("io.coil-kt:coil-compose:2.4.0")

    //Navigation
    implementation("androidx.navigation:navigation-compose:2.7.0")

    // Timber
    implementation("com.jakewharton.timber:timber:5.0.1")

    //Gson
    implementation("com.google.code.gson:gson:2.10.1")

    //System UI Controller
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.33.0-alpha")

    //Google Maps
    implementation("com.google.android.gms:play-services-maps:18.1.0")
    implementation("com.google.maps.android:maps-compose:$google_maps_version")
    implementation("com.google.maps.android:maps-compose-utils:$google_maps_version")
    implementation("com.google.maps.android:maps-compose-widgets:$google_maps_version")

    //Firebase auth
    implementation(platform("com.google.firebase:firebase-bom:32.2.3"))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.android.gms:play-services-auth:20.7.0")
    implementation("com.github.stevdza-san:OneTapCompose:1.0.7")

    //Firebase FireStore
    implementation("com.google.firebase:firebase-firestore-ktx")


    //Hilt
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-compiler:$hilt_version")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    //Room
    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    //Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")


    //Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
kapt {
    correctErrorTypes = true
}