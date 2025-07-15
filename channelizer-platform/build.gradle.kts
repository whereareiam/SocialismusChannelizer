import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    alias(libs.plugins.shadow)
}

subprojects {
    plugins.apply(rootProject.libs.plugins.shadow.get().pluginId)

    tasks.withType<ShadowJar> {
        archiveBaseName.set(rootProject.name)

        val defaultDestination = rootProject.layout.buildDirectory.dir("libs")

        val customOutputDir = if (project.hasProperty("output")) {
            project.layout.dir(project.provider { File(project.property("output").toString()) })
        } else {
            null
        }

        destinationDirectory.set(customOutputDir ?: defaultDestination)
    }

    dependencies {
        rootProject.allprojects
            .filter { it != project && it.parent == rootProject }
            .forEach { subproject ->
                if (subproject.name != "channelizer-platform")
                    "implementation"(project(":${subproject.name}"))
            }
    }

    tasks.named<Jar>("jar") {
        dependsOn("shadowJar")
    }
}
