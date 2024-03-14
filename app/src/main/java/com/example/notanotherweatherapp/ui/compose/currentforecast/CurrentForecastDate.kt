import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import java.time.LocalDate

@Composable
fun CurrentForecastDate(
    modifier: Modifier = Modifier,
) {
    val date = LocalDate.now()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "${date.month} ${date.dayOfMonth}, ${date.year}",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = TextUnit(3.0F, TextUnitType.Sp),
        )
        Box(
            modifier = Modifier
                .height(1.dp)
                .weight(1F)
                .padding(start = 4.dp)
                .background(Color.DarkGray)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CurrentForecastDatePreview() {
    CurrentForecastDate()
}