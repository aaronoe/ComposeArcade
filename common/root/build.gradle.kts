plugins {
    id("multiplatform-compose-setup")
    id("android-setup")
    id("kotlin-parcelize")
}

kotlin {

    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(project(":common:utils"))
                implementation(project(":common:sudoku"))

                implementation(Deps.ArkIvanov.Decompose.decompose)
                implementation(Deps.ArkIvanov.Decompose.extensionsCompose)
            }
        }
    }
}
