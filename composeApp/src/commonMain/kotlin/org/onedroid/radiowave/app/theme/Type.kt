package org.onedroid.radiowave.app.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import radiowave.composeapp.generated.resources.Poppins_100
import radiowave.composeapp.generated.resources.Poppins_200
import radiowave.composeapp.generated.resources.Poppins_300
import radiowave.composeapp.generated.resources.Poppins_400
import radiowave.composeapp.generated.resources.Poppins_500
import radiowave.composeapp.generated.resources.Poppins_600
import radiowave.composeapp.generated.resources.Poppins_700
import radiowave.composeapp.generated.resources.Poppins_800
import radiowave.composeapp.generated.resources.Poppins_900
import radiowave.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font

@Composable
fun PoppinsFontFamily() = FontFamily(
    Font(Res.font.Poppins_100, weight = FontWeight.Thin),
    Font(Res.font.Poppins_200, weight = FontWeight.ExtraLight),
    Font(Res.font.Poppins_300, weight = FontWeight.Light),
    Font(Res.font.Poppins_400, weight = FontWeight.Normal),
    Font(Res.font.Poppins_500, weight = FontWeight.Medium),
    Font(Res.font.Poppins_600, weight = FontWeight.SemiBold),
    Font(Res.font.Poppins_700, weight = FontWeight.Bold),
    Font(Res.font.Poppins_800, weight = FontWeight.ExtraBold),
    Font(Res.font.Poppins_900, weight = FontWeight.Black),
)

@Composable
fun AppTypography() = Typography().run {
    val fontFamily = PoppinsFontFamily()
    copy(
        displayLarge = displayLarge.copy(fontFamily = fontFamily),
        displayMedium = displayMedium.copy(fontFamily = fontFamily),
        displaySmall = displaySmall.copy(fontFamily = fontFamily),
        headlineLarge = headlineLarge.copy(fontFamily = fontFamily),
        headlineMedium = headlineMedium.copy(fontFamily = fontFamily),
        headlineSmall = headlineSmall.copy(fontFamily = fontFamily),
        titleLarge = titleLarge.copy(fontFamily = fontFamily),
        titleMedium = titleMedium.copy(fontFamily = fontFamily),
        titleSmall = titleSmall.copy(fontFamily = fontFamily),
        bodyLarge = bodyLarge.copy(fontFamily = fontFamily),
        bodyMedium = bodyMedium.copy(fontFamily = fontFamily),
        bodySmall = bodySmall.copy(fontFamily = fontFamily),
        labelLarge = labelLarge.copy(fontFamily = fontFamily),
        labelMedium = labelMedium.copy(fontFamily = fontFamily),
        labelSmall = labelSmall.copy(fontFamily = fontFamily)
    )
}