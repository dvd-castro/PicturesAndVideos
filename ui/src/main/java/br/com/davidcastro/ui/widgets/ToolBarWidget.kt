package br.com.davidcastro.ui.widgets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import br.com.davidcastro.ui.theme.Green

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolbarWidget(
    canNavigateBack: Boolean,
    actionButton: (isBackAction: Boolean) -> Unit,
    onSearchClick: (text: String) -> Unit) {

    CenterAlignedTopAppBar(
        title = {
            SearchWidget(onSearchClick)
        },
        navigationIcon = {
            if(canNavigateBack) {
                IconButton(
                    onClick = { actionButton(true) }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Localized description",
                        tint = Green
                    )
                }
            } else {
                IconButton(
                    onClick = { actionButton(false) }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Localized description",
                        tint = Green
                    )
                }
            }
        },
    )
}