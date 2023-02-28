package com.example.altai_checkers.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import com.example.altai_checkers.R

data class TimeSettings(val mode: Boolean, val time: Int, val addition: Int)

val TimeSettingsSaver = listSaver<TimeSettings, Any>(
    save = { listOf(it.mode, it.time, it.addition) },
    restore = { TimeSettings(it[0] as Boolean, it[1] as Int, it[2] as Int) }
)

@Composable
fun MainScreen(navController: NavHostController) {
    var isDialogShown by remember {mutableStateOf(false)}
    var gamemode by remember {mutableStateOf("")}
    var gameWithBotSettings = rememberSaveable(stateSaver = TimeSettingsSaver) {
        mutableStateOf(TimeSettings(true, 5, 3))
    }
    var gameWithFriendSettings = rememberSaveable(stateSaver = TimeSettingsSaver) {
        mutableStateOf(TimeSettings(false, 10, 4))
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement = Arrangement.SpaceEvenly) {
            Box(modifier = Modifier.width(300.dp)){
                Text(stringResource(R.string.title),
                     fontSize = 48.sp,
                     lineHeight = 50.sp,
                     textAlign = TextAlign.Center)}
            ButtonWithSettings(stringResource(R.string.play_with_bot),
                               onClick = { navController.navigate("GameVsBotScreen") },
                               onSettingsClick = {
                                   gamemode = "bot"
                                   isDialogShown = true})
            ButtonWithSettings(stringResource(R.string.play_with_friend),
                               onClick = {navController.navigate("GameVsFriendScreen")},
                               onSettingsClick = {
                                   gamemode = "friend"
                                   isDialogShown = true})
            Button(onClick = { navController.navigate("StatisticsScreen") },
                   modifier = Modifier
                       .width(300.dp)
                       .height(80.dp),
                   content = {Text(stringResource(R.string.statistics),
                              fontSize = 24.sp)})
            Button(onClick = { navController.navigate("AboutScreen") },
                   modifier = Modifier
                       .width(300.dp)
                       .height(80.dp),
                   content = {Text(stringResource(R.string.about),
                   fontSize = 24.sp)})
        }
    }

    if (isDialogShown) {
        if (gamemode == "bot") {
            SettingsDialog(currentSettings = gameWithBotSettings,
                           onDismiss = {isDialogShown = false},
                           onConfirm = {settings: MutableState<TimeSettings> -> run {
                                            gameWithBotSettings = settings
                                            isDialogShown = false }})
        }
        if (gamemode == "friend")
            SettingsDialog(currentSettings = gameWithFriendSettings,
                           onDismiss = {isDialogShown = false},
                           onConfirm = {settings: MutableState<TimeSettings> -> run {
                                            gameWithFriendSettings = settings
                                            isDialogShown = false }})
    }
}

@Composable
fun ButtonWithSettings(text: String, onClick: () -> Unit, onSettingsClick: () -> Unit) {
    Button(onClick = onClick,
           modifier = Modifier
               .width(300.dp)
               .height(80.dp),
           content = {Text(text, fontSize = 24.sp)
                      IconButton(onClick = onSettingsClick){
                          Icon(Icons.Filled.Settings,
                               contentDescription = stringResource(R.string.settings),
                               modifier = Modifier.fillMaxSize(0.75f))}})
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SettingsDialog(currentSettings: MutableState<TimeSettings>,
                   onDismiss: () -> Unit,
                   onConfirm: (MutableState<TimeSettings>) -> Unit) {
    var settings = currentSettings
    var state by remember { mutableStateOf(settings.value.mode) }
    var time by remember { mutableStateOf(settings.value.time)}
    var addition by remember { mutableStateOf(settings.value.addition)}

    Dialog(onDismissRequest = { onDismiss() },
           properties = DialogProperties(usePlatformDefaultWidth = false)) {
        Box(modifier = Modifier.background(color = Color(255, 255, 255))) {
            Column(modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(15.dp),
                   verticalArrangement = Arrangement.spacedBy(25.dp)){
                Column(Modifier.selectableGroup()) {
                    Row(){
                        RadioButton(selected = state,
                                    onClick = { state = true })
                        Text(stringResource(R.string.with_addition),
                             fontSize = 22.sp) }
                    Row() {
                        RadioButton(selected = !state,
                                    onClick = { state = false })
                        Text(stringResource(R.string.without_addition),
                             fontSize = 22.sp) } }

                Column(modifier = Modifier.fillMaxWidth(),
                       verticalArrangement = Arrangement.spacedBy(15.dp)) {
                    TextField(value = time.toString(),
                              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                              onValueChange = {
                                  time = if (it.toIntOrNull() == null) 0
                                         else it.toInt() })
                    if (state)
                        TextField(value = addition.toString(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            onValueChange = {
                                addition = if (it.toIntOrNull() == null) 0
                                           else it.toInt() })
                }

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(30.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Button(onClick = { onDismiss() },
                           modifier = Modifier
                               .fillMaxWidth()
                               .weight(1f)) {
                                Text(text = stringResource(R.string.cancel),
                                     fontWeight = FontWeight.Bold,
                                     textAlign = TextAlign.Center) }
                    Button(onClick = {
                               settings = mutableStateOf(TimeSettings(state, time, addition))
                               onConfirm(settings) },
                           modifier = Modifier.fillMaxWidth()
                                              .weight(1f)) {
                                Text(text = stringResource(R.string.confirm),
                                     fontWeight = FontWeight.Bold,
                                     textAlign = TextAlign.Center) } }
            }
        }
    }
}