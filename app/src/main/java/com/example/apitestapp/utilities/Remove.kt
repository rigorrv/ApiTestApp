package com.example.apitestapp.utilities

import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector

val Remove: ImageVector
    get() {
        if (remove != null) {
            return remove!!
        }
        remove = materialIcon(name = "Filled.Remove") {
            materialPath {
                moveTo(19.0f, 13.0f)
                horizontalLineToRelative(-6.0f)
                horizontalLineToRelative(-2.0f)
                horizontalLineTo(5.0f)
                verticalLineToRelative(-2.0f)
                horizontalLineToRelative(6.0f)
                verticalLineTo(5.0f)
                verticalLineToRelative(6.0f)
                horizontalLineToRelative(6.0f)
                close()
            }
        }
        return remove!!
    }

private var remove: ImageVector? = null
