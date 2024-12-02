package com.blackcube.starwars.ui.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    contentModifier: Modifier?,
    value: String,
    onValueChange: (String) -> Unit,
    onSearch: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    BaseTextField(
        value = value,
        contentModifier = contentModifier ?: Modifier,
        placeholder = "Введите текст для поиска",
        onValueChange = onValueChange,
        options = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        actions = KeyboardActions {
            onSearch(value)
            focusManager.clearFocus()
        },
        leading = {
            Icon(
                modifier = Modifier
                    .padding(end = 8.dp),
                imageVector = Icons.Default.Search,
                tint = Color.Gray,
                contentDescription = "Search Icon"
            )
        },
        trailing = {
            if (value.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Clear",
                    tint = Color.Gray,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clip(CircleShape)
                        .clickable { onValueChange("") }
                )
            }
        }
    )
}

@Preview
@Composable
fun SearchBarPreview() {
    val (value, setValue) = useState("")
    SearchBar(null, value, setValue) {}
}