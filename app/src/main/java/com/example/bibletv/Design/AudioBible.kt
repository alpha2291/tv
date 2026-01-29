package com.example.bibletv.Design

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Border
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.example.bibletv.R

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable

fun AudioBible(){

        Box {
            Image(
                painter = painterResource(R.drawable.audiobgimg),
                "",
                modifier = Modifier.fillMaxSize()
            )
            Spacer(modifier = Modifier.height(20.dp))
            Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    "Genesis 1",
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
                val lastItemFocusRequester = remember { FocusRequester() }
                LazyColumn {
                items(20) { index ->
                    Row(
                        modifier = Modifier.fillMaxSize().padding(10.dp)
                            .focusRequester(lastItemFocusRequester).then(
                                if (index == 19) Modifier.focusRequester(lastItemFocusRequester)
                                else Modifier
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Card(
                            modifier = Modifier.fillMaxWidth(0.85f),
                            colors = CardDefaults.colors(Color.White.copy(0.4f)),
                            onClick = {},
                        ) {
                            Row(
                                modifier = Modifier.fillMaxSize().padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Box(
                                    modifier = Modifier.size(30.dp)
                                        .clip(CircleShape)
                                        .background(Color.White.copy(0.15f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(index.toString(), color = Color.White, fontSize = 15.sp)
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
        }
            var isFocus by remember { mutableStateOf(false) }

            if(isFocus){
                Box(
                    modifier = Modifier
                        .blur(30.dp)
                        .onFocusChanged { focusState ->
                            isFocus = focusState.isFocused
                        }
                        .focusable(true)
                        .fillMaxWidth(0.75f)
                        .fillMaxWidth()
                        .height(160.dp)
                        .align(Alignment.BottomCenter)
                        .background(Color(0xFF000000).copy(0.25f))
                ) {

                }

                Box(
                    modifier = Modifier.fillMaxWidth()
                        .align(Alignment.BottomCenter) .onFocusChanged { focusState ->
                            isFocus = focusState.isFocused
                        }
                        .focusable(true)
                        .fillMaxWidth(0.75f)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        var progressValue by remember { mutableStateOf(0.4f) }
                        val animatedProgress = animateFloatAsState(
                            targetValue = progressValue,
                            animationSpec = tween(durationMillis = 300)
                        )

                        LinearProgressIndicator(
                            progress = animatedProgress.value,
                            modifier = Modifier.onFocusChanged { focusState ->
                                isFocus = focusState.isFocused
                            }

                                .height(6.dp)
                                .clip(RoundedCornerShape(50)),
                            color = Color(0xFF2E63E6),
                            trackColor = Color.White.copy(0.4f)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(0.75f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                "00:01", color = Color.White,
                                fontSize = 15.sp
                            )
                            Text(
                                "04:20", color = Color.White,
                                fontSize = 15.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(0.75f),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Box(
                                modifier = Modifier.width(150.dp).height(40.dp)
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(Color(0xFF2E63E6)),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.readalonglogo),
                                        ""
                                    )
                                    Spacer(modifier = Modifier.width(5.dp))
                                    Text("Read Along", color = Color.White)
                                }
                            }
                            Box(
                                modifier = Modifier.width(150.dp).height(40.dp)
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(Color(0xFF2E63E6)),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.autoplaylogo),
                                        ""
                                    )
                                    Spacer(modifier = Modifier.width(5.dp))
                                    Text("Auto Play", color = Color.White)
                                }
                            }
                        }
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier.size(50.dp).clip(CircleShape)
                                        .background(Color(0xFFFFFFFF).copy(0.15f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.audioprevious),
                                        "",
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                                Box(
                                    modifier = Modifier.clip(CircleShape)
                                        .border(2.dp, Color.White, CircleShape)
                                        .size(60.dp)
                                        .background(Color(0xFF2E63E6)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.pause),
                                        "",
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                                Box(
                                    modifier = Modifier.size(50.dp).clip(CircleShape)
                                        .background(Color(0xFFFFFFFF).copy(0.15f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.audionext),
                                        "",
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                            }
                        }

                    }
                }
            }

    }
}