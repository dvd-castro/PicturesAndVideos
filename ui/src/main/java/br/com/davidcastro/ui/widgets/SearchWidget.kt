package br.com.davidcastro.ui.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import br.com.davidcastro.ui.R
import br.com.davidcastro.ui.theme.Dimens.dimen16dp
import br.com.davidcastro.ui.theme.Dimens.dimen48dp
import br.com.davidcastro.ui.theme.Dimens.size12sp
import br.com.davidcastro.ui.theme.Gray
import br.com.davidcastro.ui.theme.Green

@Composable
@Preview
private fun SearchWidgetPreview() {
    SearchWidget(onSearchClick = {})
}

@Composable
fun SearchWidget(onSearchClick: (text: String)-> Unit) {
    val containerColor = Color.Transparent
    var text by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }

    TextField(
        value = text,
        onValueChange = { text = it },
        singleLine = true,
        shape = ShapeDefaults.Medium,
        textStyle = TextStyle.Default.copy(fontSize = size12sp),
        modifier = Modifier
            .height(dimen48dp)
            .padding(end = dimen16dp)
            .fillMaxWidth(),
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_placeholder),
                fontSize = size12sp
            )},
        trailingIcon = {
            IconButton(onClick = { onSearchClick(text.text) }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(id = R.string.description_icon_search),
                    tint = Green
                )
            }},
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Gray,
            unfocusedContainerColor = Gray,
            disabledContainerColor = Gray,
            focusedIndicatorColor = containerColor,
            unfocusedIndicatorColor = containerColor,
            disabledIndicatorColor = containerColor,
        ),
    )
}