plugins {
    id("runtime")
}

dependencies {
    implementation(project(":channelizer-api"))
    implementation(project(":channelizer-common"))
    implementation(project(":channelizer-platform:platform-bukkit"))
}
