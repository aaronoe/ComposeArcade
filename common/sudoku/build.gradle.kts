plugins {
    id("multiplatform-compose-setup")
    id("android-setup")
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(project(":common:utils"))
                implementation(project(":common:compose-ui"))
                implementation(Deps.ArkIvanov.Decompose.decompose)
                implementation(Deps.ArkIvanov.Decompose.extensionsCompose)
                implementation(Deps.JetBrains.Kotlin.coroutines)
            }
        }

        named("commonTest") {
            dependencies {
            }
        }
    }

}
