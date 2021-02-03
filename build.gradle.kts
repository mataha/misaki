description = "Solutions to programming koans bundled with a mini-framework"

tasks.wrapper {
    distributionType = Wrapper.DistributionType.ALL
}

allprojects {
    repositories {
        jcenter()
        mavenCentral()
    }
}
