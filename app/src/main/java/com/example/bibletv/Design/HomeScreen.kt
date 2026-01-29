package com.example.bibletv.Design

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.Text
import com.example.bibletv.R
import com.example.bibletv.navigation.NavViewModel

var chapterClick = mutableStateOf(false)
var chapterScreen = mutableStateOf(false)

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun HomeScreen(){
    val viewmodel : NavViewModel = hiltViewModel()
    var clickIndex by remember { mutableStateOf(0) }
    var verseScreenNav by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier.fillMaxSize()
            .background(Color(0xFF0A0A1A)),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(40.dp))
        if(!chapterClick.value) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.bookicon),
                    "",
                    modifier = Modifier.size(50.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    "Bible",
                    color = Color(0xFFE8ECF8),
                    fontSize = 25.sp
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Box(
                modifier = Modifier.fillMaxWidth(0.9f)
                    .background(Color.White.copy(0.1f))
                    .padding(10.dp).clip(RoundedCornerShape(10.dp)).focusable(true),
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.versedaylogo),
                            ""
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text("Verse of the Day", color = Color(0xFFE8ECF8))
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        "\"But they that wait upon the LORD shall renew their strength; they shall mount up with wings as " +
                                "eagles; they shall run, and not be weary; and they shall walk, and not faint.\"",
                        color = Color(0xFFE8ECF8),
                        modifier = Modifier.padding(10.dp)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        "— Isaiah 40:31", color = Color(0xFFC6CBDC),
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier.fillMaxWidth(0.9f).padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val logos = listOf(
                    R.drawable.bookicon,
                    R.drawable.searchlogo
                )

                val logoTitle = listOf(
                    "Bible",
                    "Search"
                )

                repeat(3) { index ->
                    Box(
                        modifier = Modifier.width(if (index == 0) 350.dp else 150.dp)
                            .height(150.dp).clip(RoundedCornerShape(10.dp)).background(
                                brush = Brush.linearGradient(
                                    listOf(
                                        if (index == 0) Color(0xFF00BC7D)
                                        else if (index == 1) Color(0xFF155DFC)
                                        else Color(0xFFFC15A3),
                                        if (index == 0) Color(0xFF00BC7D)
                                        else if (index == 1) Color(0xFF432DD7)
                                        else Color(0xFFD72D77),
                                        if (index == 1) Color(0xFF1C398E)
                                        else if (index == 2) Color(0xFF8E1C48)
                                        else Color(0xFF00BC7D)
                                    )
                                )
                            ).clickable {
                                if(index == 1){
                                    chapterClick.value = true
                                }
                            }.focusable(true),
                    )
                    {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement =
                                if (index == 0) Arrangement.SpaceEvenly else
                                    Arrangement.Center,
                            horizontalAlignment = if (index == 0) Alignment.Start else Alignment.CenterHorizontally
                        ) {
                            if (index == 0) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.continuereadinglogo),
                                        "", modifier = Modifier.size(40.dp)
                                    )
                                    Text(
                                        "Continue Reading", color = Color(0xFFE8ECF8),
                                        modifier = Modifier
                                    )
                                }
                                Text(
                                    "John 3: 12",
                                    color = Color(0xFFE8ECF8),
                                    modifier = Modifier.padding(start = 10.dp)
                                )

                                Box(
                                    modifier = Modifier.background(Color.White.copy(0.1f)).focusable(true)
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(start = 10.dp)
                                    ) {
                                        Text(
                                            "Read Now",
                                            color = Color(0xFFC6CBDC),
                                            modifier = Modifier.padding(start = 10.dp)
                                        )
                                        Icon(
                                            imageVector = Icons.Default.KeyboardArrowRight,
                                            contentDescription = "",
                                            tint = Color(0xFFC6CBDC),
                                            modifier = Modifier.padding(start = 10.dp)
                                        )
                                    }
                                }
                            } else {
                                Text(
                                    logoTitle[index - 1],
                                    color = Color(0xFFE8ECF8)
                                )

                                Image(
                                    painter = painterResource(logos[index - 1]), "",
                                    modifier = Modifier.size(50.dp).padding(10.dp)
                                )

                            }
                        }
                    }
                }
            }
        }else
        {
            if(!chapterScreen.value) {
                BackHandler {
                    chapterClick.value = false
                }
                Box(
                    modifier = Modifier.width(300.dp).height(50.dp)
                        .background(Color(0xFF1E2B4A)).clip(RoundedCornerShape(50.dp))
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = if (clickIndex == 0)
                            Arrangement.Start else Arrangement.End
                    ) {
                        var selectedIndex by remember { mutableStateOf(0) }
                        val options = listOf("Old Testament", "New Testament")

                        Row(
                            modifier = Modifier
                                .background(Color(0xFF1A2038), RoundedCornerShape(50.dp))
                                .padding(4.dp)
                                .width(320.dp)
                                .height(50.dp)
                                .clip(RoundedCornerShape(50.dp)),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            options.forEachIndexed { index, title ->
                                var isFocused by remember { mutableStateOf(false) }
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxHeight()
                                        .clip(RoundedCornerShape(50.dp))
                                        .onFocusChanged { focusState ->
                                            isFocused = focusState.isFocused
                                            if (focusState.isFocused) {
                                                selectedIndex = index
                                            }
                                        }
                                        .focusable(true)
                                        .background(
                                            if (selectedIndex == index) Color.White else Color.Transparent
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = title,
                                        color = if (selectedIndex == index) Color(0xFF1E2B4A)
                                        else Color(0xFFC6CBDC),
                                    )
                                }
                            }
                        }
                    }
                }
            }else{
                BackHandler {
                    chapterScreen.value = false
                }
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text("Select Chapter",
                        color = Color.White,
                        fontSize = 17.sp)
                }
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(5),
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                items(30){ index ->
                    var isFocused by remember { mutableStateOf(false) }
                    val scale by animateFloatAsState(
                        targetValue = if (isFocused) 1.1f else 1f, // Zoom in when focused
                        animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing)
                    )
                    Box(
                       modifier = Modifier
                           .onFocusChanged { focusState ->
                           isFocused = focusState.isFocused
                       }
                           .focusable(true)
                           .padding(20.dp)
                           .graphicsLayer(scaleX = scale, scaleY = scale,
                               shape = RoundedCornerShape(10.dp),
                               clip = true)
                           .size(120.dp)
                           .background(brush = Brush.linearGradient(
                               listOf(
                                   Color(0xFF2A5DBF),
                                   Color(0xFF11409A)
                               )
                           )
                           )
                           .onKeyEvent { event ->
                               // Handle D-pad center or Enter press
                               if (event.type == KeyEventType.KeyDown &&
                                   (event.key == Key.Enter || event.key == Key.NumPadEnter || event.key == Key.DirectionCenter)
                               ) {
                                   if (!verseScreenNav) {
                                       chapterScreen.value = true
                                   } else {
                                       verseScreenNav = true
                                   }

                                   if (verseScreenNav) {
                                       viewmodel.onNavigateToVerseReadingView()
                                   }
                                   verseScreenNav = true
                                   true // event consumed
                               } else {
                                   false // let others handle it
                               }
                           }
                           .border(1.dp,if(isFocused) Color.White else Color.Transparent, shape = RoundedCornerShape(10.dp))
                           .clip(RoundedCornerShape(10.dp)),
                       contentAlignment = Alignment.Center
                   ){
                       Text(if(!chapterScreen.value) "Genesis" else "01",
                           color = Color.White)
                   }
                }
            }
        }
    }
}