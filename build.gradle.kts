description = "Solutions to programming koans bundled with a mini-framework"

plugins {
    base
}

allprojects {
    repositories {
        mavenCentral()
    }
}

tasks.wrapper {
    distributionType = Wrapper.DistributionType.ALL
}
