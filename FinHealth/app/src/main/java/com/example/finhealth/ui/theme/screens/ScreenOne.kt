package com.example.finhealth.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finhealth.ui.theme.screens.GainOutlay.CardValue
import com.example.finhealth.viewModel.GainOutlayViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finhealth.data.models.GainOutlay.GainOutlayModel

@Preview
@Composable
fun ScreenContent() {

    val viewModel: GainOutlayViewModel = viewModel()
    val gainOutlays = viewModel.gainOutlays.collectAsState()
    var editingOutlay by remember { mutableStateOf<GainOutlayModel?>(null) }

    Scaffold (
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(top = 75.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                CardValue()

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                ) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(8.dp) // Sombra do card
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Últimos 5 Registros",
                                style = MaterialTheme.typography.titleMedium
                            )

                            RegisterList(
                                modifier = Modifier.fillMaxWidth(),
                                gainOutlays = gainOutlays.value,
                                onEdit = { editingOutlay = it }
                            )
                        }
                    }
                }
            }
        }
    )

    editingOutlay?.let { outlay ->
        EditRegisterModal(
            gainOutlay = outlay,
            onDismiss = { editingOutlay = null }, // Fecha o modal
            onSave = { updatedOutlay ->
                viewModel.updateAndSaveGainOutlay(updatedOutlay) // Atualiza no ViewModel
                editingOutlay = null
            },
            onDelete = { outlayToDelete ->
                viewModel.deleteGainOutlay(outlayToDelete) // Deleta no ViewModel
                editingOutlay = null
            }
        )
    }
}