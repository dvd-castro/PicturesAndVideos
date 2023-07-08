package br.com.davidcastro.ui.widgets

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.davidcastro.ui.theme.Green


@Composable
@Preview
private fun SearchWidgetPreview() {
    SearchWidget(onSearchClick = {})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchWidget(onSearchClick: (text: String)-> Unit) {

    var text by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }

    TextField(
        value = text,
        onValueChange = { text = it },
        singleLine = true,
        shape = ShapeDefaults.Medium,
        placeholder = { Text(text = "Pesquisar...", fontSize = 13.sp )},
        trailingIcon = {
            IconButton(onClick = { onSearchClick.invoke(text.text) }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Botão de pesquisar",
                    tint = Green
                )
            } },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        modifier = Modifier.height(50.dp),
        textStyle = TextStyle.Default.copy(fontSize = 13.sp),
    )
}