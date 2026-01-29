package com.example.bibletv.Design

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.focusable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
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
import androidx.navigation.NavHostController
import androidx.tv.material3.Border
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.Text
import com.example.bibletv.R
import com.example.bibletv.navigation.DestinationClass
import com.example.bibletv.navigation.NavViewModel

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun VerseReadingView() {
    val viewModel : NavViewModel = hiltViewModel()
    BackHandler {
        chapterScreen.value = true
        viewModel.onBackStack()
    }

    // Create focus requesters for bottom buttons and last lazy column item
    val previousButtonFocusRequester = remember { FocusRequester() }
    val nextButtonFocusRequester = remember { FocusRequester() }
    val lastItemFocusRequester = remember { FocusRequester() }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF0A0A1A)),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Spacer(modifier = Modifier.height(40.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
            ) {
                var dropdownFocused by remember { mutableStateOf(false) }

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .clip(RoundedCornerShape(10.dp))
                        .onFocusChanged { focusState ->
                            dropdownFocused = focusState.isFocused
                        }
                        .focusable(true)
                        .border(
                            width = 2.dp,
                            color = if (dropdownFocused) Color.White else Color.Transparent,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .background(Color(0xFF1A223A))
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Text("Genesis", color = Color.White, fontSize = 20.sp)
                    Spacer(modifier = Modifier.width(5.dp))
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Dropdown",
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                }

                Text(
                    "Chapter 1",
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier.align(Alignment.Center)
                )

                val imageList = listOf(
                    R.drawable.speakerlogo,
                    R.drawable.headsetlogo,
                    R.drawable.themeslogo
                )

                var iconFocusedStates by remember { mutableStateOf(List(imageList.size) { false }) }

                Row(
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    imageList.forEachIndexed { index, imageRes ->
                        Spacer(modifier = Modifier.width(20.dp))

                        val focusRequester = remember { FocusRequester() }

                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .focusRequester(focusRequester)
                                .onFocusChanged { focusState ->
                                    iconFocusedStates = iconFocusedStates
                                        .toMutableList()
                                        .apply {
                                            set(index, focusState.isFocused)
                                        }
                                }
                                .focusable(true)
                                .onKeyEvent { event ->
                                    if (event.type == KeyEventType.KeyDown &&
                                        (event.key == Key.Enter ||
                                                event.key == Key.NumPadEnter ||
                                                event.key == Key.DirectionCenter)
                                    ) {
                                        println("mbhhhh")
                                        viewModel.onNavigateToAudioBible()
                                        true
                                    } else false
                                }
                                .border(
                                    width = 2.dp,
                                    color = if (iconFocusedStates[index]) Color.White else Color.Transparent,
                                    shape = RoundedCornerShape(4.dp)
                                )
                                .background(Color(0xFF1C2540)),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(imageRes),
                                contentDescription = "Icon ${index + 1}",
                                modifier = Modifier.size(30.dp)
                            )
                        }

                        LaunchedEffect(Unit) {
                            if (index == 0) focusRequester.requestFocus()
                        }
                    }
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(20) { index ->
                    var isFocused by remember { mutableStateOf(false) }
                    val scale by animateFloatAsState(
                        targetValue = if (isFocused) 1.05f else 1f,
                        animationSpec = tween(200)
                    )

                    Card(
                        colors = CardDefaults.colors(Color(0xFF141C30)),
                        onClick = {},
                        border = CardDefaults.border(
                            border = Border(
                                BorderStroke(
                                    1.dp,
                                    if (isFocused) Color(0xFF5B7FFF) else Color.Transparent
                                )
                            )
                        ),
                        modifier = Modifier
                            .then(
                                if (index == 19) Modifier.focusRequester(lastItemFocusRequester)
                                else Modifier
                            )
                            .onFocusChanged { focusState ->
                                isFocused = focusState.isFocused
                            }
                            .focusProperties {
                                down = if (index == 19) {
                                    previousButtonFocusRequester
                                } else {
                                    FocusRequester.Default
                                }
                            }
                            .focusable(true)
                            .graphicsLayer(
                                scaleX = scale,
                                scaleY = scale,
                                shape = RoundedCornerShape(10.dp),
                                clip = true
                            )
                            .padding(10.dp)
                            .fillMaxWidth(0.85f)
                            .height(70.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(30.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFF3D7BFF)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text((index + 1).toString(), color = Color.White, fontSize = 15.sp)
                            }
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                "In the beginning God created the heavens and the earth.",
                                color = Color.White,
                                fontSize = 15.sp
                            )
                        }
                    }
                }
            }
        }

        // Bottom navigation overlay
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Box(
                modifier = Modifier
                    .blur(10.dp)
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(Color(0xFF141422).copy(0.5f))
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 40.dp, end = 40.dp, bottom = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                repeat(2) { index ->
                    var isFocus by remember { mutableStateOf(false) }
                    val currentFocusRequester = if (index == 0) {
                        previousButtonFocusRequester
                    } else {
                        nextButtonFocusRequester
                    }

                    Box(
                        modifier = Modifier
                            .width(120.dp)
                            .height(50.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                if (index == 0) Color(0xFF1C2540) else Color(0xFF2E63E6)
                            )
                            .focusRequester(currentFocusRequester)
                            .focusProperties {
                                up = lastItemFocusRequester
                            }
                            .onFocusChanged { focusState ->
                                isFocus = focusState.isFocused
                            }
                            .focusable(true)
                            .border(
                                width = 2.dp,
                                color = if (isFocus) Color.White else Color.Transparent,
                                shape = RoundedCornerShape(10.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (index == 0) {
                                Icon(
                                    Icons.Default.KeyboardArrowLeft,
                                    "",
                                    tint = Color.White
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                            }
                            Text(
                                if (index == 0) "Previous" else "Next",
                                fontSize = 15.sp,
                                color = Color.White
                            )
                            if (index == 1) {
                                Spacer(modifier = Modifier.width(5.dp))
                                Icon(
                                    Icons.Default.KeyboardArrowRight,
                                    "",
                                    tint = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}