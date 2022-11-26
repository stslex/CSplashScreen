package st.slex.feature_user.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import st.slex.core_network.model.ui.user.UserModel
import st.slex.feature_user.ui.BindPagerWithTabs
import st.slex.feature_user.ui.BindUserScreenMainHeader
import st.slex.feature_user.ui.UserPagerTabResource

@OptIn(ExperimentalPagerApi::class)
@Composable
fun UserContentSuccessComponent(
    modifier: Modifier = Modifier,
    userModel: UserModel,
    listPagesResource: List<UserPagerTabResource<out Any>>,
    onImageClick: (String, String) -> Unit,
    onUserClick: (String) -> Unit,
    onCollectionClick: (String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        BindUserScreenMainHeader(
            modifier = Modifier,
            user = userModel
        )
        BindPagerWithTabs(
            listPagesResource = listPagesResource,
            onImageClick = onImageClick,
            onUserClick = onUserClick,
            onCollectionClick = onCollectionClick
        )
    }
}