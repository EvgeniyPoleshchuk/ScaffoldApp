package com.example.scaffoldapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun AccountView(){
     Column(modifier = Modifier.fillMaxSize().padding(16.dp)){
         Row(modifier = Modifier.fillMaxWidth(),
             horizontalArrangement = Arrangement.SpaceBetween){
             Row(){
                 Icon(painter = painterResource(R.drawable.baseline_account_circle_24),"")
                 Column(Modifier.padding(start = 8.dp)){
                   Text("Tutoirial")
                   Text("@ZHeka.com")
                 }
             }
             Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight,"")
         }
         Row(Modifier.padding(top = 16.dp)){
             Icon(painter = painterResource(R.drawable.baseline_music_note_24),"")
             Text("My Music",Modifier.padding(start = 8.dp))
         }
         Divider()
     }
}