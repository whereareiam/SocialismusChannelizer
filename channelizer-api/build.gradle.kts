java {
    withSourcesJar()
    withJavadocJar()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifactId = "SocialismusChannelizer"
            pom {
                name.set("SocialismusChannelizer")
                description.set("Public API for SocialismusChannelizer - Socialismus channel synchronization module")
            }
        }
    }
}

tasks.withType<Javadoc> {
    (options as StandardJavadocDocletOptions).apply {
        addStringOption("Xdoclint:none", "-quiet")
        title = "SocialismusChannelizer API"
        windowTitle = "SocialismusChannelizer API"
    }
}