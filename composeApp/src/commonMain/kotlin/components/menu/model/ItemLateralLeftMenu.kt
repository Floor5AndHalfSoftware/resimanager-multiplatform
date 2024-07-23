package components.menu.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ItemLateralLeftMenu(
    val icon: ImageVector,
    val title: String,
    val path: String,
    val clickMap: MutableMap<String, MutableState<Boolean>>

) {
    data object ItemDashboard : ItemLateralLeftMenu(
        Icons.Filled.Home,
        "Dashboard",
        "dashboard",
        mutableMapOf(
            "SHOW_MODAL_INFO" to mutableStateOf(false),
            "SHOW_DASHBOARD" to mutableStateOf(true),
            "SHOW_ADMIN_REQUEST_FORM" to mutableStateOf(false)
        )
    )
    data object ItemSearch : ItemLateralLeftMenu(
        Icons.Filled.Search, "Buscar", "serach",
        mutableMapOf(
            "SHOW_SEARCH_FORM" to mutableStateOf(true),
            "SHOW_MODAL_INFO" to mutableStateOf(false),
            "SHOW_DASHBOARD" to mutableStateOf(false),
            "SHOW_ADMIN_REQUEST_FORM" to mutableStateOf(false)
        )
    )

    data object ItemAddAdmin : ItemLateralLeftMenu(
        Icons.Filled.AddCircle, "Nuevo Administrador", "addNewAdmin",
        mutableMapOf(
            "SHOW_SEARCH_FORM" to mutableStateOf(false),
            "SHOW_MODAL_INFO" to mutableStateOf(false),
            "SHOW_DASHBOARD" to mutableStateOf(false),
            "SHOW_ADMIN_REQUEST_FORM" to mutableStateOf(true)
        )
    )

    data object ItemAbout : ItemLateralLeftMenu(
        Icons.Filled.Settings, "Acerca de...", "about",
        mutableMapOf(
            "SHOW_SEARCH_FORM" to mutableStateOf(false),
            "SHOW_MODAL_INFO" to mutableStateOf(true),
            "SHOW_DASHBOARD" to mutableStateOf(false),
            "SHOW_ADMIN_REQUEST_FORM" to mutableStateOf(false)
        )
    )

    data object ItemExit : ItemLateralLeftMenu(
        Icons.Filled.Close, "Salir", "exit",
        mutableMapOf(
            "SHOW_SEARCH_FORM" to mutableStateOf(false),
            "SHOW_MODAL_INFO" to mutableStateOf(false),
            "SHOW_DASHBOARD" to mutableStateOf(false),
            "SHOW_ADMIN_REQUEST_FORM" to mutableStateOf(false)
        )
    )

}
