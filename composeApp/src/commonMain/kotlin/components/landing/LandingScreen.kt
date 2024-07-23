package components.landing

import Platform
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import components.menu.LateralLeftMenu
import components.menu.Navbar
import getPlatform

class LandingScreen : Screen {
    @Composable
    override fun Content() {
        val showDashboard = remember { mutableStateOf(true) }
        val showDialog = remember { mutableStateOf(false) }
        val showAdminRequestForm = remember { mutableStateOf(false) }
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

        val platform = getPlatform()
        val isAndroidVisualization = isAndroidVisualization(platform)

        val map = mutableMapOf<String, MutableState<Boolean>>()
        map["SHOW_MODAL_INFO"] = showDialog
        map["SHOW_DASHBOARD"] = showDashboard
        map["SHOW_ADMIN_REQUEST_FORM"] = showAdminRequestForm

        if (isAndroidVisualization) {
            LateralLeftMenu(drawerState, map) {
                Landing(map, drawerState, isAndroidVisualization)
            }
        } else {
            Landing(map, drawerState, isAndroidVisualization)
        }
    }
}

@Composable
fun Landing(
    map: MutableMap<String, MutableState<Boolean>>,
    drawerState: DrawerState,
    isAndroidVisualization: Boolean
) {

    Scaffold {
        BoxWithConstraints {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Navbar(map, isAndroidVisualization, drawerState)
                Spacer(modifier = Modifier.height(20.dp))
                BodySection(map)
            }
        }
    }
}

fun isAndroidVisualization(platform: Platform): Boolean {
    return platform.name.contains("Android")
}
