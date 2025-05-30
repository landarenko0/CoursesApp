pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Courses App"
include(":app")
include(":domain")
include(":data:liked_courses")
include(":data:courses")
include(":domain:courses")
include(":domain:liked_courses")
include(":feature:onboarding")
include(":feature:core")
include(":feature:login")
include(":domain:auth")
include(":feature:home")
include(":data:auth")
include(":feature:liked_courses")
include(":feature:account")
