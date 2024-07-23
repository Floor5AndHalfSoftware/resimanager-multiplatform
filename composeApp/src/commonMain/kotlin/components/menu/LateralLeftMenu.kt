package components.menu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import components.menu.model.ItemLateralLeftMenu
import kotlinx.coroutines.launch

@Composable
fun LateralLeftMenu(
    drawerState: DrawerState,
    map: MutableMap<String, MutableState<Boolean>> = mutableMapOf(),
    content: @Composable () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val navigator = LocalNavigator.currentOrThrow
    val items = listOf(
        ItemLateralLeftMenu.ItemDashboard,
        ItemLateralLeftMenu.ItemSearch,
        ItemLateralLeftMenu.ItemAddAdmin,
        ItemLateralLeftMenu.ItemAbout,
        ItemLateralLeftMenu.ItemExit
    )
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    items.forEach { item ->
                        if (item.icon == Icons.Filled.Close) {
                            NavigationDrawerItem(
                                icon = { Icon(item.icon, contentDescription = null) },
                                label = { Text(item.title) },
                                selected = false,
                                onClick = { navigator.pop() }
                            )

                        } else {
                            NavigationDrawerItem(
                                icon = { Icon(item.icon, contentDescription = null) },
                                label = { Text(item.title) },
                                selected = false,
                                onClick = {
                                    updateMapInLeftMenu(item, map)
                                    coroutineScope.launch {
                                        drawerState.close()
                                    }
                                }
                            )
                        }
                    }
                }
            },
            content = content
        )
    }
}

fun updateMapInLeftMenu(item: ItemLateralLeftMenu, map: MutableMap<String, MutableState<Boolean>>) {
    item.clickMap["SHOW_ADMIN_REQUEST_FORM"]?.value?.let {
        map["SHOW_ADMIN_REQUEST_FORM"]?.value = it
    }
    item.clickMap["SHOW_DASHBOARD"]?.value?.let {
        map["SHOW_DASHBOARD"]?.value = it
    }
    item.clickMap["SHOW_MODAL_INFO"]?.value?.let {
        map["SHOW_MODAL_INFO"]?.value = it
    }
}