package com.edibudu.app4app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import com.edibudu.app4app.R
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                var showSmoke by remember { mutableStateOf(false) }

                Scaffold(
                    timeText = { TimeText() },
                    vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.primary)
                            .padding(WindowInsets.safeDrawing.asPaddingValues()),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(80.dp)
                                    .offset(y = 12.dp),
                                contentAlignment = Alignment.BottomEnd
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.ic_smoke),
                                    contentDescription = "Cigarette",
                                    modifier = Modifier.fillMaxSize(),
                                    colorFilter = ColorFilter.tint(MaterialTheme.colors.onPrimary)
                                )
                                if (showSmoke) {
                                    key(count) {
                                        SmokeEmitter(
                                            modifier = Modifier
                                                .align(Alignment.TopEnd)
                                                .offset(x = 20.dp, y = 7.dp),
                                            onComplete = { showSmoke = false }
                                        )
                                    }
                                }
                            }

                            Row(horizontalArrangement = Arrangement.spacedBy(25.dp)) {
                                Button(onClick = {
                                    vm.decrement()                              // ← route decrement through VM
                                }) {
                                    Text(text = "–")
                                }
                                Button(onClick = {
                                    vm.onSmoke()                                  // ← route increment through VM :contentReference[oaicite:7]{index=7}
                                    showSmoke = true
                                }) {
                                    Text(text = "+")
                                }
                            }

                            Text(
                                text = count.toString(),
                                style = MaterialTheme.typography.title1,
                                color = MaterialTheme.colors.onPrimary
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SmokeEmitter(
    modifier: Modifier = Modifier,
    onComplete: () -> Unit
) {
    val offsetY = remember { Animatable(0f) }
    val alpha   = remember { Animatable(1f) }
    LaunchedEffect(Unit) {
        launch { alpha.animateTo(0f, tween(800)) }
        launch { offsetY.animateTo(-60f, tween(1200)) }
        kotlinx.coroutines.delay(1200)
        onComplete()
    }
    Image(
        painter = painterResource(R.drawable.smoke_frame),
        contentDescription = "Smoke puff",
        modifier = modifier
            .size(40.dp)
            .offset { IntOffset(0, offsetY.value.dp.roundToPx()) },
        alpha = alpha.value
    )
}
