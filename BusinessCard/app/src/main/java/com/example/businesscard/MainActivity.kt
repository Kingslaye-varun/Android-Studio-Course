package com.example.businesscard
import android.graphics.fonts.FontStyle
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BusinessCard(
                        modifier = Modifier
                            .padding(innerPadding)
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0xFFE3F2FD), // Light Blue
                                        Color(0xFFBBDEFB)  // Slightly darker shade
                                    )
                                )
                            )
                        )
                }
            }
        }
    }
}

@Composable
fun BusinessCard(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        HeaderSection()
        ContactInfoSection()
    }
}

@Composable
fun HeaderSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        // Add a contrasting circular background with padding
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(120.dp) // Circular size
                .background(Color(0xFFE3F2FD), shape = CircleShape) // Light blue background
                .padding(10.dp) // Padding between background and logo
                .shadow(elevation = 8.dp, shape = CircleShape) // Shadow for better contrast
        ) {
            Image(
                painter = painterResource(id = R.drawable.android_logo),
                contentDescription = "Logo",
                modifier = Modifier.fillMaxSize() // Fill the box size
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        // Name - Ensure it's clearly visible in both light and dark mode
        Text(
            text = "Varun Rahatgaonkar",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground // Ensure text color contrasts with background
            )
        )
        Text(
            text = "Android Developer",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurface // Use onSurface for secondary text
            )
        )
    }
}

@Composable
fun ContactInfoRow(iconRes: Int, text: String, onClick: () -> Unit = {}) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp)
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary, // Use primary color for icons
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onBackground // Ensure text is visible in both themes
            )
        )
    }
}


@Composable
fun ContactInfoSection() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(16.dp)
    ) {
        Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f), thickness = 1.dp)
        ContactInfoRow(iconRes = R.drawable.icons8_phone_24, text = "+91 9372148550")
        Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f), thickness = 1.dp)
        ContactInfoRow(iconRes = R.drawable.icons8_email_24, text = "rahatgaonkarvarun@gmail.com")
        Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f), thickness = 1.dp)
        ContactInfoRow(iconRes = R.drawable.icons8_linkedin_24, text = "LinkedIn Profile")
    }
}

@Composable
fun BusinessCardTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme(),
        typography = Typography,
        content = content
    )
}

// Handle Dark and Light Theme Color Schemes
fun darkColorScheme() = darkColorScheme(
    primary = Color(0xFF90CAF9),
    onPrimary = Color.Black,
    secondary = Color(0xFFBBDEFB),
    onSecondary = Color.Black,
    background = Color(0xFF121212),
    onBackground = Color.White,
    surface = Color(0xFF121212),
    onSurface = Color.White
)

fun lightColorScheme() = lightColorScheme(
    primary = Color(0xFF90CAF9),
    onPrimary = Color.Black,
    secondary = Color(0xFFBBDEFB),
    onSecondary = Color.Black,
    background = Color(0xFFFAFAFA),
    onBackground = Color.Black,
    surface = Color(0xFFFFFFFF),
    onSurface = Color.Black
)
