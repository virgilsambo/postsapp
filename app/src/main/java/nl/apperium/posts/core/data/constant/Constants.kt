package nl.apperium.posts.core.data.constant

import nl.apperium.posts.ApperiumApplication

// File provider authority
val FILE_PROVIDER_AUTHORITY = ApperiumApplication.instance.packageName + ".file.provider"

// Shared preferences keys
const val KEY_VERSION_CODE = "version_code"
const val KEY_WHATS_NEW_ENABLED = "whats_new_enabled"
const val KEY_APP_DISCLAIMER_ACCEPTED = "app_disclaimer_accepted"
const val KEY_APP_FIRST_LAUNCH = "app_first_launch"

// Config build types
const val CONFIG_BUILD_TYPE_BETA = "beta"
const val CONFIG_BUILD_TYPE_RELEASE = "release"
const val CONFIG_BUILD_TYPE_DEBUG = "debug"