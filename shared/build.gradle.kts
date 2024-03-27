plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.sqlDelight)
    alias(libs.plugins.nativeCoroutines)
    id("dev.icerock.mobile.multiplatform-resources")
}

kotlin {
    task("testClasses")
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    sourceSets {
        all {
            languageSettings.optIn("kotlinx.cinterop.ExperimentalForeignApi")
        }

        commonMain.dependencies {
            // put your Multiplatform dependencies here
            implementation(libs.koinCore)
            implementation(libs.koinTest)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.core)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.bundles.ktor)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.serialization)
            implementation(libs.sqldelight.common)
            implementation(libs.kotlinx.datetime)
            implementation(libs.ktor.client.auth)
            implementation(libs.firebase.auth)
            implementation(libs.firebase.firestore)
            implementation(libs.firebase.common)
            implementation(libs.firebase.auth)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.logging)
            api(libs.kmm.viewmodel.core)

            implementation(libs.stately.concurrency)
            api(libs.kmm.viewmodel.core)
            api(libs.logging)

            implementation(libs.resources)
            api(libs.resources.compose)
            api(libs.resources.test)

        }

        androidMain {
            dependsOn(commonMain.get())
            dependencies {
                implementation(libs.ktor.client.okhttp)
                implementation(libs.sqldelight.android)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting

        val iosMain by creating {
            dependsOn(commonMain.get())
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(libs.ktor.client.darwin)
                implementation(libs.sqldelight.native)
            }
        }
    }
}

android {
    namespace = "com.wisemen.chargehub.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "com.wisemen.chargehub"
    multiplatformResourcesClassName = "SharedRes"
}

sqldelight {
    databases {
        create("ChargeHubDb") {
            packageName.set("db.chargehub")
        }
    }
}