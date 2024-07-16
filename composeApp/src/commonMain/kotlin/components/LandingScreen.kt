package components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen

class LandingScreen() : Screen {
    @Composable
    override fun Content() {
        val showDashboard = remember { mutableStateOf(true) }
        val showDialog = remember { mutableStateOf(false) }
        val showContactForm = remember { mutableStateOf(false) }

        val map = mutableMapOf<String, MutableState<Boolean>>()
        map["SHOW_MODAL_INFO"] = showDialog
        map["SHOW_DASHBOARD"] = showDashboard
        map["SHOW_CONTACT_FORM"] = showContactForm

        Landing(map)
    }
}

@Composable
fun Landing(map: MutableMap<String, MutableState<Boolean>>) {
    Column { // Cambio de Row a Column
        Navbar(map)
        Spacer(modifier = Modifier.height(20.dp))
        BodySection(map)
    }
}