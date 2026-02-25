package com.mahmutalperenunal.c3paiddemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.mahmutalperenunal.c3paiddemo.core.ui.theme.C3PaidDemoTheme
import com.mahmutalperenunal.c3paiddemo.navigation.AppNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            C3PaidDemoTheme {
                Surface {
                    val navController = rememberNavController()
                    AppNavGraph(navController = navController)
                }
            }
        }
    }
}