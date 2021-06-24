object Deps {

    object JetBrains {
        object Kotlin {
            // __KOTLIN_COMPOSE_VERSION__
            private const val VERSION = "1.5.10"
            const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$VERSION"
            const val testCommon = "org.jetbrains.kotlin:kotlin-test-common:$VERSION"
            const val testJunit = "org.jetbrains.kotlin:kotlin-test-junit:$VERSION"
            const val testAnnotationsCommon = "org.jetbrains.kotlin:kotlin-test-annotations-common:$VERSION"
            const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0"
        }

        object Compose {
            // __LATEST_COMPOSE_RELEASE_VERSION__
            private const val VERSION = "0.4.0"
            const val gradlePlugin = "org.jetbrains.compose:compose-gradle-plugin:$VERSION"
        }
    }

    object Android {
        object Tools {
            object Build {
                const val gradlePlugin = "com.android.tools.build:gradle:4.0.1"
            }
        }
    }

    object AndroidX {
        object AppCompat {
            const val appCompat = "androidx.appcompat:appcompat:1.3.0-beta01"
        }

        object Activity {
            const val activityCompose = "androidx.activity:activity-compose:1.3.0-alpha02"
        }
    }

    object ArkIvanov {
        object Decompose {
            private const val VERSION = "0.2.6"
            const val decompose = "com.arkivanov.decompose:decompose:$VERSION"
            const val extensionsCompose = "com.arkivanov.decompose:extensions-compose-jetbrains:$VERSION"
        }
    }
}
