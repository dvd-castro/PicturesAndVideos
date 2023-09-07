package br.com.davidcastro.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.davidcastro.ui.theme.Dimens.dimen72dp
import br.com.davidcastro.ui.theme.Green
import br.com.davidcastro.ui.theme.TopAppBarBackground
import br.com.davidcastro.ui.utils.extensions.doIfFalse
import br.com.davidcastro.ui.utils.extensions.doIfTrue

@Composable
fun ToolbarWidget(
    canNavigateBack: Boolean,
    actionButton: (isBackAction: Boolean) -> Unit,
    onSearchClick: (text: String) -> Unit) {

    Row(
        modifier = Modifier
            .height(dimen72dp)
            .background(TopAppBarBackground),
        verticalAlignment = Alignment.CenterVertically
    ) {
        canNavigateBack.doIfTrue {
            IconButton(
                onClick = { actionButton(true) }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Localized description",
                    tint = Green
                )
            }
        }.doIfFalse {
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

        SearchWidget {
            onSearchClick(it)
        }
    }
}