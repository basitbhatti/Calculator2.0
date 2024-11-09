package com.portfolioapps.calculator2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.portfolioapps.calculator2.ui.theme.Calculator2Theme
import com.portfolioapps.calculator2.utils.CalculatorAction
import com.portfolioapps.calculator2.utils.CalculatorOperation
import com.portfolioapps.calculator2.utils.CalculatorState
import com.portfolioapps.calculator2.viewmodel.CalculatorViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Calculator2Theme {
                val viewModel by viewModels<CalculatorViewModel>()
                val state = viewModel.state
                HomeScreen(state = state, viewModel::onAction)
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Calculator2Theme {
//        ButtonGray(text = 9)
//        HomeScreen(CalculatorState())
    }
}

@Composable
fun HomeScreen(state: CalculatorState,
               onAction : (CalculatorAction) -> Unit,
               modifier: Modifier = Modifier) {

    val fontSize by remember {
        mutableStateOf(30.sp)
    }

    val fontFamily = FontFamily(
        listOf(
            Font(resId = R.font.regular, FontWeight.Normal),
            Font(resId = R.font.medium, FontWeight.Medium),
            Font(resId = R.font.bold, FontWeight.Bold),
            Font(resId = R.font.lite, FontWeight.Light)
        )
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(0.5f),
            contentAlignment = Alignment.BottomCenter
        ) {

            Column(
                modifier = modifier.fillMaxWidth()
            ) {

                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
                    Text(
                        text = state.number1 + (state.operation ?: "") + state.number2,
                        fontFamily = fontFamily,
                        fontSize = 50.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(end = 20.dp)
                    )
                }

                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
                    Text(
                        text = "= 225,000",
                        fontFamily = fontFamily,
                        fontSize = fontSize,
                        color = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier.padding(end = 20.dp)
                    )
                }

                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 15.dp)
                        .height(0.5.dp)
                        .background(MaterialTheme.colorScheme.tertiary)
                )
            }
        }

        //TODO ---------------------------------- (SCREEN INTERVAL) -----------------------------

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(0.5f),
            contentAlignment = Alignment.BottomCenter
        ) {

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .weight(1f)
                ) {

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {

                        ButtonSignGray(text = "C", onClick = {
                            onAction(CalculatorAction.Clear)
                        })

                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = modifier
                                .clickable {
                                    onAction(CalculatorAction.Delete)
                                }
                                .clip(
                                    RoundedCornerShape(12.dp)
                                )
                                .size(60.dp)
                                .background(MaterialTheme.colorScheme.secondary),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_backspace),
                                modifier = Modifier.size(24.dp),
                                contentDescription = "backspace"
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {

                        ButtonSignGray(text = "/", onClick = {
                            onAction(CalculatorAction.Operation(CalculatorOperation.Divide))

                        })

                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {

                        ButtonSignGray(text = "x", onClick = {
                            onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
                        })

                    }
                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .weight(1f)
                ) {

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {

                        ButtonGray(text = "7", onClick = {
                            onAction(CalculatorAction.Number(7))

                        })

                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {

                        ButtonGray(text = "8", onClick = {
                            onAction(CalculatorAction.Number(8))

                        })

                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {

                        ButtonGray(text = "9", onClick = {
                            onAction(CalculatorAction.Number(9))
                        })

                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {

                        ButtonSignGray(text = "-", onClick = {
                            onAction(CalculatorAction.Operation(CalculatorOperation.Subtract))

                        })

                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .weight(1f)
                ) {

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        ButtonGray(text = "4", onClick = {
                            onAction(CalculatorAction.Number(4))

                        })
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        ButtonGray(text = "5", onClick = {
                            onAction(CalculatorAction.Number(5))

                        })
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        ButtonGray(text = "6", onClick = {
                            onAction(CalculatorAction.Number(6))

                        })
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {

                        ButtonSignGray(text = "+", onClick = {
                            onAction(CalculatorAction.Operation(CalculatorOperation.Add))
                        })

                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(2f)
                ) {

                    Column(
                        modifier = Modifier
                            .weight(0.75f)
                            .height(180.dp)
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .weight(0.5f)
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ) {

                                ButtonGray(text = "1", onClick = {
                                    onAction(CalculatorAction.Number(1))
                                })

                            }

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ) {

                                ButtonGray(text = "2", onClick = {
                                    onAction(CalculatorAction.Number(2))
                                })
                            }

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ) {
                                ButtonGray(text = "3", onClick = {
                                    onAction(CalculatorAction.Number(3))
                                })
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .weight(0.5f),
                        ) {

                            Box(
                                modifier = Modifier
                                    .weight(2f)
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(
                                            start = 15.dp, end = 15.dp, top = 15.dp, bottom = 15.dp
                                        )
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(MaterialTheme.colorScheme.secondary),
                                    contentAlignment = Alignment.Center
                                ) {
                                    TextButton(modifier = Modifier.fillMaxSize(),
                                        shape = RoundedCornerShape(12.dp),
                                        onClick = {
                                            onAction(CalculatorAction.Number(0))
                                        }) {
                                        Text(
                                            text = "0",
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(start = 15.dp),
                                            fontSize = 30.sp,
                                            color = MaterialTheme.colorScheme.tertiary
                                        )
                                    }
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ) {
                                ButtonGray(text = ".", onClick = {
                                    onAction(CalculatorAction.Decimal)
                                })
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .clickable {
                                onAction(CalculatorAction.Calculate)
                            }
                            .weight(0.25f)
                            .height(180.dp),
                        contentAlignment = Alignment.Center
                    ) {

                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(62.dp)
                                .padding(vertical = 15.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(MaterialTheme.colorScheme.primary),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            Text(
                                text = "=",
                                fontSize = 40.sp,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight.Normal,
                                color = Color.White,
                                modifier = Modifier.padding(bottom = 5.dp)
                            )
                        }

                    }

                }

            }

        }
    }
}

@Composable
fun ButtonGray(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .clip(
                RoundedCornerShape(12.dp)
            )
            .size(60.dp)
            .background(MaterialTheme.colorScheme.secondary),
        contentAlignment = Alignment.Center
    ) {
        TextButton(modifier = Modifier.fillMaxSize(), shape = RoundedCornerShape(12.dp),
            onClick = {
                onClick()
            }) {
            Text(
                text = text,
                fontSize = 30.sp,
                fontFamily = FontFamily(Font(resId = R.font.regular)),
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}

@Composable
fun ButtonSignGray(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .clip(
                RoundedCornerShape(12.dp)
            )
            .size(60.dp)
            .background(MaterialTheme.colorScheme.secondary),
        contentAlignment = Alignment.Center
    ) {
        TextButton(modifier = Modifier.fillMaxSize(), shape = RoundedCornerShape(12.dp),
            onClick = {
                onClick()
            }
        ) {
            Text(
                text = text,
                fontSize = 30.sp,
                fontFamily = FontFamily(Font(resId = R.font.regular)),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}