package br.com.davidcastro.picturesandvideos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.davidcastro.features.navigation.AppNavigation
import br.com.davidcastro.ui.theme.PicturesAndVideosTheme
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PicturesAndVideosTheme(darkTheme = true) {
                AppNavigation()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val cachePath = File(cacheDir, getString(br.com.davidcastro.ui.R.string.cache_dir))
        cachePath.deleteRecursively()
    }
}
