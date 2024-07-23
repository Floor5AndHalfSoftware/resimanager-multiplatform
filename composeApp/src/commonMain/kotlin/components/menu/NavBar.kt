package components.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.launch

@Composable
fun Navbar(
    map: MutableMap<String, MutableState<Boolean>>,
    isAndroidVisualization: Boolean,
    leftDrawerState: DrawerState
) {
    val navigator = LocalNavigator.currentOrThrow
    val coroutineScope = rememberCoroutineScope()

    BoxWithConstraints {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(8.dp).weight(1f),
                horizontalAlignment = Alignment.Start
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (isAndroidVisualization) {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                leftDrawerState.open()
                            }
                        }) {
                            Icon(
                                Icons.Filled.Menu,
                                contentDescription = "Menu",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                    if (!isAndroidVisualization) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable {
                                map["SHOW_DASHBOARD"]?.value = true
                                map["SHOW_ADMIN_REQUEST_FORM"]?.value = false
                            }
                        ) {
                            Icon(Icons.Default.Home, contentDescription = "Home")
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Home",
                                color = MaterialTheme.colorScheme.onSurface,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
            if (!isAndroidVisualization) {
                Spacer(modifier = Modifier.weight(0.5f))
            }
            Column(
                modifier = Modifier.padding(8.dp).weight(1f),
                horizontalAlignment = Alignment.End
            ) {
                if (!isAndroidVisualization) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { /* Handle search button click */ }) {
                            Icon(
                                Icons.Filled.Search,
                                contentDescription = "Search",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        IconButton(onClick = {
                            map["SHOW_ADMIN_REQUEST_FORM"]?.value = true
                            map["SHOW_DASHBOARD"]?.value = false
                        }) {
                            Icon(
                                Icons.Filled.AddCircle,
                                contentDescription = "Nuevo Administrador",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        IconButton(onClick = { map["SHOW_MODAL_INFO"]?.value = true }) {
                            Icon(
                                Icons.Filled.Settings,
                                contentDescription = "About",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        IconButton(onClick = { navigator.pop() }) {
                            Icon(
                                Icons.Filled.Close,
                                contentDescription = "Salir",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}