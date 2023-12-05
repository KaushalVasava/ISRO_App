package com.lahsuak.apps.isroinfo.ui.screen

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lahsuak.apps.isroinfo.R
import com.lahsuak.apps.isroinfo.model.BaseState
import com.lahsuak.apps.isroinfo.model.Launch
import com.lahsuak.apps.isroinfo.ui.components.CenterProgressBar
import com.lahsuak.apps.isroinfo.ui.components.FullScreenCenterButton
import com.lahsuak.apps.isroinfo.ui.components.TextUtil
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
                items(state.data.sortedByDescending { it.LaunchDate },
                    key = {
                        it.UUID
                    }) {
                    LaunchItem(it)
                }
            }
        }
    }
}

@Composable
fun LaunchItem(launchItem: Launch) {
    val context = LocalContext.current

    Column(
        Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(
                Brush.horizontalGradient(
                    listOf(
                        Color(0xFFFBB59F),
                        Color.White
                    )
                ),
                alpha = 0.6f
            )
            .padding(8.dp)
    ) {
        Text(launchItem.Name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            Icon(Icons.Default.DateRange, null)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                TextUtil.getBoldText(stringResource(id = R.string.date_, launchItem.LaunchDate))
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(TextUtil.getBoldText(stringResource(id = R.string.type_, launchItem.LaunchType)))
        Spacer(modifier = Modifier.height(4.dp))
        Text(TextUtil.getBoldText(stringResource(id = R.string.payload_, launchItem.Payload)))
        Spacer(modifier = Modifier.height(4.dp))
        Divider()
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                launchItem.MissionStatus,
                color = if (launchItem.MissionStatus.contains("UN")) Color.White else Color.Black,
                fontSize = 12.sp,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        color = if (launchItem.MissionStatus.contains("UN")) Color.Red else Color.Green
                    )
                    .padding(4.dp)
            )
            Button(onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(launchItem.Link))
                context.startActivity(intent)
            }) {
                Icon(painterResource(id = R.drawable.ic_click), null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(stringResource(R.string.visit))
            }
        }
    }
}
