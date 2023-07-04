package st.slex.core_network.model.ui.user

import androidx.compose.runtime.Stable

@Stable
data class BadgeModel(
    val title: String,
    val primary: Boolean,
    val slug: String,
    val link: String
)
