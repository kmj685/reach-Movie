plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.dapaeng12.ruachmovie"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.dapaeng12.ruachmovie"
        minSdk = 27
        targetSdk = 36
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

    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.recyclerview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    //메테리얼
    implementation("com.google.android.material:material:1.1.0")
    //리사이클러뷰
    implementation("androidx.recyclerview:recyclerview:1.4.0")
    // For control over item selection of both touch and mouse driven selection
    implementation("androidx.recyclerview:recyclerview-selection:1.2.0")
    //카드뷰
    implementation("androidx.cardview:cardview:1.0.0")
    //글라이드 이미지 라이브러리
    implementation("com.github.bumptech.glide:glide:5.0.5")
    //레드로핏2
    val retrofit_version = "3.0.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    //서버에서 들어온 Json 데이터를 안드에서 사용하는 data class로 바꿔주는 것
    implementation("com.squareup.retrofit2:converter-gson:$retrofit_version")
    //레트로핏 로깅 인터셉터
    implementation("com.squareup.okhttp3:logging-interceptor:5.1.0")
}