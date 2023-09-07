package br.com.davidcastro.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import br.com.davidcastro.ui.theme.Dimens.dimen100dp
import br.com.davidcastro.ui.theme.Dimens.dimen28dp
import br.com.davidcastro.ui.theme.Dimens.dimen4dp
import br.com.davidcastro.ui.theme.Dimens.size14sp
import br.com.davidcastro.ui.theme.Dimens.size18sp
import br.com.davidcastro.ui.theme.Green

@Composable
fun BottomOption(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.width(dimen100dp).clickable { onClick() },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            tint = Green,
            contentDescription = null,
            modifier = Modifier
                .size(dimen28dp)
                .padding(bottom = dimen4dp)
        )
        Text(
            text = text,
            fontSize = size14sp,
            textAlign = TextAlign.Center,
            lineHeight = size18sp,
            color = Color.White
        )
    }
}