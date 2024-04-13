package com.example.scaffoldapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun Subscription() {
    Column(modifier = Modifier.height(200.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Manage Subscription")
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(5.dp),
            shape = RoundedCornerShape(5.dp),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            Column(Modifier.padding(5.dp)) {
                Column(){
                    Text("Musical")
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween ) {
                        Text("Free Tier")
                        TextButton({}) {
                            Text("See All Plans", fontWeight = FontWeight.ExtraBold)
                            Icon(Icons.AutoMirrored.Default.KeyboardArrowRight, "")
                        }
                    }
                }
                Divider()
                Row(Modifier.padding(top =  10.dp)){
                    Icon(painter = painterResource(R.drawable.baseline_account_box_24),"")
                    Text("Get a Plan", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}