package com.example.altai_checkers.items

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.altai_checkers.R

@Composable
fun BackButton(navController: NavHostController){
    IconButton(onClick = { navController.popBackStack() },
               modifier = Modifier.padding(8.dp)) {
        Icon(Icons.Filled.ArrowBack,
             contentDescription = stringResource(R.string.back_button),
             modifier = Modifier.size(60.dp))
    }
}