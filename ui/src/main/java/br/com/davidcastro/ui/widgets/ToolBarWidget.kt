package br.com.davidcastro.ui.widgets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun ToolbarWidgetPreview() {
    ToolbarWidget(actionMenu = {}, onSearchClick = {})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolbarWidget(actionMenu: ()-> Unit, onSearchClick: (text: String) -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            SearchWidget(onSearchClick)
        },
        navigationIcon = {
            IconButton(actionMenu) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Localized description"
                )
            }
        },
    )
}