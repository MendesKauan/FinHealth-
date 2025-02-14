package com.example.finhealth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.finhealth.data.ROOM.DataBaseROOM.Companion.getInstance
import com.example.finhealth.data.repository.IRepository
import com.example.finhealth.data.repository.LocalRepository
import com.example.finhealth.ui.theme.screens.MainScreen
import com.example.finhealth.ui.theme.FinHealthTheme
import com.example.finhealth.viewModel.GainOutlayViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            MainScreen()
        }
    }
}
