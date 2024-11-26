plugins {
    application
    java
    id("org.danilopianini.gradle-java-qa") version "1.75.0"
}
tasks.javadoc {
    isFailOnError = false
}

repositories {
    mavenCentral()
}

application {
    mainClass.set("it.unibo.mvc.DrawNumberApp")
}
