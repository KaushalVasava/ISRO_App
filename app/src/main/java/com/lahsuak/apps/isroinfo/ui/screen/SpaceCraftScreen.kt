package com.lahsuak.apps.isroinfo.ui.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lahsuak.apps.isroinfo.R
import com.lahsuak.apps.isroinfo.model.SpaceCraft
import com.lahsuak.apps.isroinfo.ui.components.TextUtil
import com.lahsuak.apps.isroinfo.ui.viewmodel.HomeViewModel

@Composable
fun SpaceCraftScreen(viewModel: HomeViewModel) {
    val spaceCrafts by viewModel.spaceCrafts.collectAsState()
    LazyColumn {
        items(spaceCrafts) {
            SpaceCraftItem(it)
        }
    }
}

@Composable
fun SpaceCraftItem(spaceCraft: SpaceCraft) {
    val context = LocalContext.current
    Card(Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(stringResource(id = R.string.name_, spaceCraft.name))
            Spacer(modifier = Modifier.height(4.dp))
            Text(stringResource(id = R.string.status_, spaceCraft.missionStatus))
            Spacer(modifier = Modifier.height(4.dp))
            Text(stringResource(id = R.string.application_, spaceCraft.application))
            Spacer(modifier = Modifier.height(4.dp))
            Text(stringResource(id = R.string.date_, spaceCraft.launchDate))
            Spacer(modifier = Modifier.height(4.dp))
            if (spaceCraft.orbitType.isEmpty().not()) {
                Text(stringResource(id = R.string.type_, spaceCraft.orbitType))
                Spacer(modifier = Modifier.height(4.dp))
            }
            val s = TextUtil.getLinkText(spaceCraft.link)
            Text(text = s, modifier = Modifier.clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(s.text))
                context.startActivity(intent)
            })
            Spacer(modifier = Modifier.height(4.dp))
            Text(stringResource(id = R.string.launch_vehicle_, spaceCraft.launchVehicle))
        }
    }
}