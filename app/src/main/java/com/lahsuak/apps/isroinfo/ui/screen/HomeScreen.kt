package com.lahsuak.apps.isroinfo.ui.screen

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lahsuak.apps.isroinfo.R
import com.lahsuak.apps.isroinfo.model.ApiFailure
import com.lahsuak.apps.isroinfo.model.BaseState
import com.lahsuak.apps.isroinfo.model.Launch
import com.lahsuak.apps.isroinfo.ui.components.CenterProgressBar
import com.lahsuak.apps.isroinfo.ui.components.FullScreenCenterButton
import com.lahsuak.apps.isroinfo.ui.viewmodel.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val launchesState by viewModel.launches.collectAsState()
    when (val state = launchesState) {
        is BaseState.Failed -> {
            Toast.makeText(LocalContext.current, "Something went wrong!", Toast.LENGTH_SHORT).show()
            FullScreenCenterButton(text = "Retry") {
                viewModel.getLaunches()
            }
        }

        BaseState.Loading -> {
            CenterProgressBar()
        }

        is BaseState.Success -> {
            LazyColumn {
                items(state.data) {
                    LaunchItem(it)
                }
            }
        }
    }
}

@Composable
fun LaunchItem(launchItem: Launch) {
    val context = LocalContext.current
    Card(Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(stringResource(id = R.string.name_, launchItem.Name), fontSize = 20.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(stringResource(id = R.string.status_, launchItem.MissionStatus))
            Spacer(modifier = Modifier.height(4.dp))
            Text(stringResource(id = R.string.date_, launchItem.LaunchDate))
            Spacer(modifier = Modifier.height(4.dp))
            Text(stringResource(id = R.string.type_, launchItem.LaunchType))
            Spacer(modifier = Modifier.height(4.dp))
            Text(stringResource(id = R.string.payload_, launchItem.Payload))
            Divider()
            Button(onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(launchItem.Link))
                context.startActivity(intent)
            }) {
                Text("Visit")
            }
        }
    }
}
