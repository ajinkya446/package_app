import java.util.Properties

plugins {
//    id("com.android.application")
    id("com.android.library")
    id("maven-publish")
}

android {
    namespace = "com.ajinkya.myapplication"
    compileSdk = 34

    defaultConfig {
        multiDexEnabled = true
//        applicationId = "com.ajinkya.myapplication"
        minSdk = 24
        targetSdk = 34
//        versionCode = 1
//        versionName = "1.0"

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

    buildFeatures {
        dataBinding = true
    }

}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.ajinkya"
            artifactId = "myapplication"
            version = "2.0"
            artifact("$buildDir/outputs/aar/app-release.aar")
//            afterEvaluate {
//                from(components["release"])
//            }
        }
        repositories{
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/ajinkya446/MyLibrary")
                credentials {
                    username = "ajinkya446"
                    password ="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"

                }
            }

        }
    }
}

dependencies {
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("android.arch.persistence.room:guava:1.1.1")
    testImplementation("junit:junit:4.13.2")
    //noinspection GradleCompatible
    implementation("com.android.support:multidex:1.0.3")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
//    implementation("com.android.databinding:compiler:3.1.4")
}

//private fun PublicationContainer.myapplication(mavenPublication: Any, function: () -> Unit): PublicationContainer? {

//}
