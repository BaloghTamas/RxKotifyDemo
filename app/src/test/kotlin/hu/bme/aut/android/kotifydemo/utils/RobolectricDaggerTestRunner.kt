package hu.bme.aut.android.kotifydemo.utils

import org.junit.runners.model.InitializationError
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.manifest.AndroidManifest
import org.robolectric.res.FileFsFile
import org.robolectric.util.Logger
import org.robolectric.util.ReflectionHelpers

class RobolectricDaggerTestRunner @Throws(InitializationError::class)
constructor(klass: Class<*>) : RobolectricTestRunner(klass) {

    override fun getAppManifest(config: Config): AndroidManifest {
        if (config.constants == Void::class.java) {
            Logger.error("Field 'constants' not specified in @Config annotation")
            Logger.error("This is required when using RobolectricGradleTestRunner!")
            throw RuntimeException("No 'constants' field in @Config annotation!")
        }

        val type = getType(config)
        val flavor = getFlavor(config)
        val applicationId = getApplicationId(config)

        val res: FileFsFile
        if (FileFsFile.from(BUILD_OUTPUT, "res", flavor, type).exists()) {
            res = FileFsFile.from(BUILD_OUTPUT, "res", flavor, type)
        } else {
            // Use res/merged if the output directory doesn't exist for Data Binding compatibility
            res = FileFsFile.from(BUILD_OUTPUT, "res/merged", flavor, type)
        }
        val assets = FileFsFile.from(BUILD_OUTPUT, "assets", flavor, type)

        val manifest: FileFsFile
        if (FileFsFile.from(BUILD_OUTPUT, "manifests").exists()) {
            manifest = FileFsFile.from(BUILD_OUTPUT, "manifests", "full", flavor, type, "AndroidManifest.xml")
        } else {
            // Fallback to the location for library manifests
            manifest = FileFsFile.from(BUILD_OUTPUT, "bundles", flavor, type, "AndroidManifest.xml")
        }

        Logger.debug("Robolectric assets directory: " + assets.path)
        Logger.debug("   Robolectric res directory: " + res.path)
        Logger.debug("   Robolectric manifest path: " + manifest.path)
        Logger.debug("    Robolectric package name: " + applicationId)
        return AndroidManifest(manifest, res, assets, applicationId)
    }

    private fun getType(config: Config): String? {
        try {
            return ReflectionHelpers.getStaticField<String>(config.constants.java, "BUILD_TYPE")
        } catch (e: Throwable) {
            return null
        }

    }

    private fun getFlavor(config: Config): String? {
        try {
            return ReflectionHelpers.getStaticField<String>(config.constants.java, "FLAVOR")
        } catch (e: Throwable) {
            return null
        }

    }

    private fun getApplicationId(config: Config): String? {
        try {
            return ReflectionHelpers.getStaticField<String>(config.constants.java, "APPLICATION_ID")
        } catch (e: Throwable) {
            return null
        }

    }

    companion object {
        private val BUILD_OUTPUT = "build/intermediates"
    }
}
