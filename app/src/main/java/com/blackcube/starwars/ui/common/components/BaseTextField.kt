package com.blackcube.starwars.ui.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.blackcube.starwars.ui.common.components.styled_box.StyledBox

@Composable
fun BaseTextField(
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier.fillMaxWidth(),
    enabled: Boolean = true,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    options: KeyboardOptions = KeyboardOptions.Default,
    actions: KeyboardActions = KeyboardActions.Default,
    leading: (@Composable () -> Unit)? = null,
    trailing: (@Composable () -> Unit)? = null,
    multiline: Boolean = false
) = BasicTextField(
    modifier = modifier,
    value = value,
    onValueChange = onValueChange,
    singleLine = !multiline,
    keyboardOptions = options,
    keyboardActions = actions,
    cursorBrush = SolidColor(Color.LightGray),
    textStyle = TextStyle(
        color = Color.White
    ),
    decorationBox = { innerTextField ->
        StyledBox(
            modifier = contentModifier
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 12.dp)
                ) {
                    leading?.let { leading() }

                    Box {
                        if (value.isEmpty()) Text(
                            text = placeholder,
                            color = Color.LightGray
                        )

                        if (enabled) innerTextField()
                    }
                }

                trailing?.let { trailing() }
            }
        }
    }
)
