// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    id("org.jetbrains.kotlin.plugin.parcelize") version "2.4.21" apply false
    id("com.google.gms.google-services") version "4.5.0" apply false
}