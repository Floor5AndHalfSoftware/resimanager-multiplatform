import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import org.w3c.dom.Text

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        App()
        ChangeTitle("Condominio Web")
    }
}

@Composable
fun ChangeTitle(titulo: String) {
    SideEffect {
        document.title = titulo
    }
    Text("Contenido de la página")
}