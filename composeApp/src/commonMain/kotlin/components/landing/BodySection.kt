package components.landing

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import components.modal.InfoModal

@Composable
fun BodySection(map: MutableMap<String, MutableState<Boolean>>) {
    if (map["SHOW_MODAL_INFO"] != null && map["SHOW_MODAL_INFO"]?.value == true) {
        map["SHOW_MODAL_INFO"]?.let { InfoModal(it) }
    }
    if (map["SHOW_DASHBOARD"] != null && map["SHOW_DASHBOARD"]?.value == true) {
        Dashboard()
    }
    if (map["SHOW_ADMIN_REQUEST_FORM"] != null && map["SHOW_ADMIN_REQUEST_FORM"]?.value == true) {
        AdminRequestForm()
    }
}