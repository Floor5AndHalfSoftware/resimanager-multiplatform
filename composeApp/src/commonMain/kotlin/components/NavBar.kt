package components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import getPlatform


@Composable
fun Navbar(map: MutableMap<String, MutableState<Boolean>>) {
    val navigator = LocalNavigator.currentOrThrow
    val platform = getPlatform()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (platform.name.contains("Android")) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconButton(onClick = { /* Handle menu button click */ }) {
                                Icon(
                                    Icons.Filled.Menu,
                                    contentDescription = "Menu",
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
                if (platform.name.contains("Web") || platform.name.contains("Desktop")) {
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable {
                                map["SHOW_DASHBOARD"]?.value = true
                                map["SHOW_CONTACT_FORM"]?.value = false
                            }
                        ) {
                            Icon(Icons.Default.Home, contentDescription = "Home")
                            Text(
                                text = "Home",
                                color = MaterialTheme.colorScheme.onSurface, // Use theme text color
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable {}
                        ) {
                            Text(
                                text = "Gestión",
                                color = MaterialTheme.colorScheme.onSurface, // Use theme text color
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.weight(0.5f))
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.End
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* Handle search button click */ }) {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = "Search",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                IconButton(onClick = {
                    map["SHOW_DASHBOARD"]?.value = false
                    map["SHOW_CONTACT_FORM"]?.value = true
                }) {
                    Box {
                        Icon(
                            Icons.Filled.Email,
                            contentDescription = "Messages",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                IconButton(onClick = { /* Handle notification button click */ }) {
                    Box {
                        Icon(
                            Icons.Filled.Info,
                            contentDescription = "Notifications",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                IconButton(onClick = { map["SHOW_MODAL_INFO"]?.value = true }) {
                    Box {
                        Icon(
                            Icons.Filled.Settings,
                            contentDescription = "About",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                IconButton(onClick = { navigator.pop() }) {
                    Box {
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
