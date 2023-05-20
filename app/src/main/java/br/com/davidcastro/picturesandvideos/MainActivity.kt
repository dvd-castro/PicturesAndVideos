package br.com.davidcastro.picturesandvideos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.davidcastro.ui.theme.PicturesAndVideosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PicturesAndVideosTheme {

            }
        }
    }
}
