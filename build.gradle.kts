import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.jetbrains.intellij") version "1.5.3"
	kotlin("jvm") version "1.6.21"
	java
}

group = "ir.mmd.intellijDev"
version = "1.0.0"

repositories {
	mavenCentral()
}

intellij {
	version.set("2022.1")
	type.set("IC")
}

tasks {
	withType<JavaCompile> {
		sourceCompatibility = JavaVersion.VERSION_11.toString()
		targetCompatibility = JavaVersion.VERSION_11.toString()
	}
	
	withType<KotlinCompile> {
		kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
	}
	
	runIde {
		autoReloadPlugins.set(true)
	}
	
	buildSearchableOptions {
		enabled = false
	}
	
	patchPluginXml {
		version.set(project.version.toString())
		sinceBuild.set("202")
		untilBuild.set("")
	}
	
	signPlugin {
		certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
		privateKey.set(System.getenv("PRIVATE_KEY"))
		password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
	}
	
	publishPlugin {
		token.set(System.getenv("PUBLISH_TOKEN"))
	}
}
