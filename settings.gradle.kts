pluginManagement {
    repositories {
        maven {
            url = uri("file:///C:/maven-export")
//            metadataSources {
//                gradleMetadata()
//                mavenPom()
//                artifact()
//            }
        }
//        mavenLocal()
//        google ()
//        mavenCentral()
//        gradlePluginPortal()
    }
}
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven {
            url = uri("file:///C:/maven-export")
//            metadataSources {
//                gradleMetadata()
//                mavenPom()
//                artifact()
//            }
        }
//        mavenLocal()
//        google()
//        mavenCentral()
    }
}

rootProject.name = "1016_socialservice"
include(":app")
