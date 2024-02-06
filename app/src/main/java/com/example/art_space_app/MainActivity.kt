package com.example.art_space_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.art_space_app.ui.theme.Art_Space_AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Art_Space_AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Art_Space_AppTheme {
        ArtSpaceApp()
    }
}

object AppTheme {
    val buttonColorBlue = Color(0xFF495D92)
    val buttonHeightStandard = 40.dp
    val buttonWidthStandard = 150.dp

    val artInfoBackgroud = Color(0xFFECEBF4)

//    val sizeFontSmall = 8.sp
    val sizeFontMedium = 16.sp
    val sizeFontLarge = 24.sp

    val paddingSmall = 8.dp
    val paddingMedium = 16.dp
//    val paddingLarge = 32.dp
}

data class ArtProperty(
    val imageId: Int,
    val artNameId: Int,
    val artistNameId: Int,
    val yearId: Int
)

object ArtGallery {
    val artList = listOf(
        ArtProperty(R.drawable.image_1, R.string.ArtName_1, R.string.ArtistName_1, R.string.YearArt_1),
        ArtProperty(R.drawable.image_2, R.string.ArtName_2, R.string.ArtistName_2, R.string.YearArt_2),
        ArtProperty(R.drawable.image_3, R.string.ArtName_3, R.string.ArtistName_3, R.string.YearArt_3),
        ArtProperty(R.drawable.image_3, R.string.ArtName_3, R.string.ArtistName_3, R.string.YearArt_3),
    )
}

@Composable
fun ArtSpaceApp() {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        var currentArtIndex by remember { mutableIntStateOf(0) }
        val currentArt = ArtGallery.artList[currentArtIndex]

        ArtImage(currentArt.imageId, currentArt.artNameId)

        ArtInfo(
            currentArt.artNameId,
            currentArt.artistNameId,
            currentArt.yearId,
        )

        Row (
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(bottom = AppTheme.paddingMedium)
        ){
            Button(onClick = {
                currentArtIndex = (currentArtIndex - 1 + ArtGallery.artList.size) % ArtGallery.artList.size
            },
                colors = ButtonDefaults.buttonColors(AppTheme.buttonColorBlue),
                modifier = Modifier
                    .width(AppTheme.buttonWidthStandard)
                    .height(AppTheme.buttonHeightStandard)
            ) {
                Text(text = "Previous")
            }

            Button(onClick = {
                currentArtIndex = (currentArtIndex + 1 + ArtGallery.artList.size) % ArtGallery.artList.size
            },
                colors = ButtonDefaults.buttonColors(AppTheme.buttonColorBlue),
                modifier = Modifier
                    .width(AppTheme.buttonWidthStandard)
                    .height(AppTheme.buttonHeightStandard)
            ){
                Text(text = "Next")
            }
        }
    }
}

@Composable
fun ArtImage(imageId: Int, artNameId: Int){
    Column (
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .height(500.dp)
    ){
        Box(modifier = Modifier
            .size(width= 300.dp, height= 400.dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(8.dp))
        ){
            Image(
                painter = painterResource(id = imageId),
                contentDescription = stringResource(id = artNameId),
                modifier = Modifier
                    .matchParentSize()
                    .padding(AppTheme.paddingMedium)
            )
        }
    }
}

@Composable
fun ArtInfo(artNameId: Int, artistNameId: Int, yearId: Int){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
    ){
        Column(modifier = Modifier
            .width(300.dp)
            .background(AppTheme.artInfoBackgroud),
        ){
            Text(
                text = stringResource(id = artNameId),
                fontSize = AppTheme.sizeFontLarge,
                modifier = Modifier
                    .padding(horizontal = AppTheme.paddingMedium)
                    .padding(top = AppTheme.paddingSmall)
            )
            Row(
                modifier = Modifier
                    .padding(horizontal = AppTheme.paddingMedium)
                    .padding(bottom = AppTheme.paddingMedium)
            ){
                Text(
                    text = stringResource(id = artistNameId),
                    fontWeight = FontWeight.Bold,
                    fontSize = AppTheme.sizeFontMedium
                )
                Text(
                    text = " (" + stringResource(id = yearId) + ")",
                    fontStyle = FontStyle.Italic,
                    fontSize = AppTheme.sizeFontMedium
                )
            }
        }
    }
}