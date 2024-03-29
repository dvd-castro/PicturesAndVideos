package br.com.davidcastro.ui.utils.extensions

import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.google.gson.Gson

fun <T: Any> T.toJsonStringArgs(): String =
    Uri.encode(Gson().toJson(this))

fun <T> NavBackStackEntry.getRouteArgs(key: String, out: Class<T>): T =
    Gson().fromJson(arguments?.getString(key), out)

fun <T> NavHostController.navigateWithArgs(route: String, args: T) {
    this.navigate("$route/${args?.toJsonStringArgs()}")
}

@Composable
fun Boolean?.doIfTrue(action: @Composable () -> Unit): Boolean {
    return when(this) {
        true -> {
            action()
            true
        }
        else -> {
            false
        }
    }
}

@Composable
fun Boolean?.doIfFalse(action: @Composable () -> Unit) {
    if(this == false) {
        action()
    }
}

fun String.toShapeDrawable(): ShapeDrawable {
    val colorInt = android.graphics.Color.parseColor(this)
    val color = Color(colorInt)
    val shapeDrawable = ShapeDrawable(RectShape())
    shapeDrawable.paint.color = color.toArgb()
    return shapeDrawable
}
