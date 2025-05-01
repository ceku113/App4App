    plugins {
        alias(libs.plugins.android.application)
        alias(libs.plugins.kotlin.android)
        alias(libs.plugins.kotlin.compose)
        alias(libs.plugins.ksp)

    }

    android {
        namespace = "com.edibudu.app4app"
        compileSdk = 35


        defaultConfig {
            applicationId = "com.edibudu.app4app"
            minSdk = 26
            targetSdk = 33
            versionCode = 1
            versionName = "1.0"
            javaCompileOptions {
                annotationProcessorOptions {
                    arguments += mapOf(
                        // tell Room where to write JSON schemas
                        "room.schemaLocation" to "$projectDir/schemas".toString()
                    )
                }
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

    dependencies {
        implementation(libs.androidx.datastore.preferences)
        implementation(libs.datastore)                // androidx.datastore:datastore:1.1.5
        implementation(libs.play.services.wearable)
        implementation(libs.androidx.ui)
        implementation(libs.androidx.ui.graphics)
        implementation(libs.androidx.ui.tooling.preview)
        implementation(libs.androidx.wear.tooling.preview)
        implementation(libs.androidx.activity.compose)
        implementation(libs.androidx.core.splashscreen)
        implementation(libs.androidx.tiles)
        implementation(libs.androidx.tiles.material)
        implementation(libs.androidx.tiles.tooling.preview)
        implementation(libs.horologist.compose.tools)
        implementation(libs.horologist.tiles)
        implementation(libs.androidx.watchface.complications.data.source.ktx)
        implementation(libs.androidx.appcompat)
        implementation(libs.androidx.constraintlayout)
        implementation(libs.room.runtime)    // androidx.room:room-runtime:2.7.1 :contentReference[oaicite:7]{index=7}
        implementation(libs.room.ktx)        // androidx.room:room-ktx:2.7.1 :contentReference[oaicite:8]{index=8}
        ksp(libs.room.compiler)              // androidx.room:room-compiler:2.7.1 via KSP :contentReference[oaicite:9]{index=9}
        implementation(libs.material)
        implementation(libs.androidx.wear)
        // Core Compose BOM
        implementation(platform(libs.androidx.compose.bom))
        // Wear‑specific Material components
        implementation(libs.androidx.compose.material)
        // Wear‑specific Foundation (additive to standard foundation)
        implementation(libs.androidx.compose.foundation)
        // Wear Navigation (optional, for multi‑screen flows)
        implementation(libs.androidx.navigation.compose)




        debugImplementation(libs.ui.tooling)
        implementation(libs.androidx.activity.compose)
        implementation(libs.androidx.animation.graphics)
        implementation(libs.androidx.activity.ktx)         // viewModels() delegate :contentReference[oaicite:7]{index=7}
        implementation(libs.androidx.lifecycle.viewmodel.ktx) // ViewModel & Factory support :contentReference[oaicite:8]{index=8}





        // Wear Navigation (if your app uses Compose Navigation)

        // Wear Compose preview annotations
        implementation(libs.androidx.compose.ui.tooling)

        androidTestImplementation(platform(libs.androidx.compose.bom))
        androidTestImplementation(libs.androidx.ui.test.junit4)
        debugImplementation(libs.androidx.ui.tooling)
        debugImplementation(libs.androidx.ui.test.manifest)
        debugImplementation(libs.androidx.tiles.tooling)

    }