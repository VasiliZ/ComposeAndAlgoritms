import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.ui.Alignment
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.customlayout.TestTags.ANIMATED_VIEW_TAG
import com.example.customlayout.TestTags.START_ANIMATION_BUTTON_TAG
import com.example.customlayout.TestTags.START_ANIMATION_BUTTON_TEXT_TAG
import com.example.customlayout.animation.composable.AnimationVisibilityContainer
import com.example.customlayout.animation.model.AnimationScreenModel
import com.example.customlayout.ui.theme.CustomLayoutTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ComposeTest {

    @get:Rule
    var composeTestRule = createComposeRule()

    @Before
    fun init() {
        composeTestRule.setContent {
            CustomLayoutTheme {
                AnimationVisibilityContainer(
                    enter = expandIn(
                        animationSpec = tween(AnimationScreenModel.ANIMATION_DURATION)
                    ),
                    exit = shrinkOut(
                        animationSpec = tween(AnimationScreenModel.ANIMATION_DURATION),
                        shrinkTowards = Alignment.BottomEnd
                    )
                )
            }
        }
    }

    @Test
    fun checkViewVisibilityAfterButtonClicked() {
        composeTestRule.onNodeWithTag(START_ANIMATION_BUTTON_TAG).performClick()
        composeTestRule.onNodeWithTag(ANIMATED_VIEW_TAG).assertDoesNotExist()
    }

    @Test
    fun checkStartAnimationButtonText() {
        composeTestRule.onNodeWithTag(START_ANIMATION_BUTTON_TEXT_TAG, useUnmergedTree = true)
            .assertTextContains("Start animation")
    }
}