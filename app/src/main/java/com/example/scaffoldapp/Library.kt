package com.example.scaffoldapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun Library() {
    LazyColumn() {
        items(libraries) { item ->
            LibItem(lib = item)

        }
    }
}

@Composable
fun LibItem(lib: Lib) {
    Card(modifier = Modifier.height(60.dp).fillMaxWidth().padding(5.dp),){
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Row() {
                Icon(painter = painterResource(lib.icon), "")
                Text(lib.name, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 5.dp))
            }
            Icon(Icons.AutoMirrored.Default.KeyboardArrowRight, "")


        }
    }


}