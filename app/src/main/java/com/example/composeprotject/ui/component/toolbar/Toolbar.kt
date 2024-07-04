package com.example.composeprotject.ui.component.toolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.max
import kotlin.math.roundToInt
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.composeprotject.R
import com.example.composeprotject.ui.component.text.BaseText
import com.example.composeprotject.ui.theme.MeetTheme

@Composable
fun CustomToolbar(
    modifier: Modifier = Modifier,
    navigationIcon: (@Composable () -> Unit)? = { Icon(painterResource(id = R.drawable.ic_arrow_back), null) },
    actions: (@Composable RowScope.() -> Unit)? = null,
    toolbarTitle: ToolbarTitle? = null,
) {
    val collapsedFraction = 1f
    val fullyCollapsedTitleScale = when {
        toolbarTitle != null -> CollapsedTitleLineHeight.value / toolbarTitle.expandedTextStyle.lineHeight.value
        else -> 1f
    }

    val collapsingTitleScale = lerp(1f, fullyCollapsedTitleScale, collapsedFraction)
    val titleStartPadding = if (navigationIcon != null) (-8).dp else 8.dp
    Surface {
        Layout(
            content = {
                if (toolbarTitle != null) {
                    toolbarTitle.titleText?.let {
                        BaseText(
                            modifier = Modifier
                                .layoutId(ExpandedTitleId)
                                .offset(x = titleStartPadding, y = 0.dp)
                                .wrapContentHeight(align = Alignment.Top)
                                .graphicsLayer(
                                    scaleX = collapsingTitleScale,
                                    scaleY = collapsingTitleScale,
                                    transformOrigin = TransformOrigin(0f, 0f)
                                ),
                            text = it,
                            textStyle = toolbarTitle.expandedTextStyle,
                            textColor = toolbarTitle.titleColor
                        )
                    }
                    toolbarTitle.titleText?.let {
                        BaseText(
                            modifier = Modifier
                                .layoutId(CollapsedTitleId)
                                .wrapContentHeight(align = Alignment.Top)
                                .graphicsLayer(
                                    scaleX = collapsingTitleScale,
                                    scaleY = collapsingTitleScale,
                                    transformOrigin = TransformOrigin(0f, 0f)
                                ),
                            text = it,
                            textStyle = toolbarTitle.expandedTextStyle,
                            textColor = toolbarTitle.titleColor,
                            /*maxLines = 1,
                                            overflow = TextOverflow.Ellipsis*/
                        )
                    }
                }

                if (navigationIcon != null) {
                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                            .layoutId(NavigationIconId)
                    ) {
                        navigationIcon()
                    }
                }

                if (actions != null) {
                    Row(
                        modifier = Modifier
                            .wrapContentSize()
                            .layoutId(ActionsId)
                    ) {
                        actions()
                    }
                }
            },
            modifier = modifier.then(
                Modifier
                    .heightIn(min = MinCollapsedHeight)
                    .statusBarsPadding()
                    .background(MeetTheme.colors.neutralWhite))
        ) { measurables, constraints ->
            val horizontalPaddingPx = HorizontalPadding.toPx()
            val expandedTitleBottomPaddingPx = ExpandedTitleBottomPadding.toPx()

            val navigationIconPlaceable = measurables.firstOrNull { it.layoutId == NavigationIconId }
                ?.measure(constraints.copy(minWidth = 0))

            val actionsPlaceable = measurables.firstOrNull { it.layoutId == ActionsId }
                ?.measure(constraints.copy(minWidth = 0))

            val expandedTitlePlaceable = measurables.firstOrNull { it.layoutId == ExpandedTitleId }
                ?.measure(
                    constraints.copy(
                        maxWidth = (constraints.maxWidth - 2 * horizontalPaddingPx).roundToInt(),
                        minWidth = 0,
                        minHeight = 0
                    )
                )

            val additionalContentPlaceable = measurables.firstOrNull { it.layoutId == AdditionalContentId }
                ?.measure(constraints)

            val navigationIconOffset = when (navigationIconPlaceable) {
                null -> horizontalPaddingPx
                else -> navigationIconPlaceable.width + horizontalPaddingPx * 2
            }

            val actionsOffset = when (actionsPlaceable) {
                null -> horizontalPaddingPx
                else -> actionsPlaceable.width + horizontalPaddingPx * 2
            }

            val collapsedTitleMaxWidthPx =
                (constraints.maxWidth - navigationIconOffset - actionsOffset) / fullyCollapsedTitleScale

            val collapsedTitlePlaceable = measurables.firstOrNull { it.layoutId == CollapsedTitleId }
                ?.measure(
                    constraints.copy(
                        maxWidth = collapsedTitleMaxWidthPx.roundToInt(),
                        minWidth = 0,
                        minHeight = 0
                    )
                )

            val centralContentPlaceable = measurables.firstOrNull { it.layoutId == CentralContentId }
                ?.measure(
                    constraints.copy(
                        minWidth = 0,
                        maxWidth = (constraints.maxWidth - navigationIconOffset - actionsOffset).roundToInt()
                    )
                )

            val collapsedHeightPx = when {
                centralContentPlaceable != null ->
                    max(MinCollapsedHeight.toPx(), centralContentPlaceable.height.toFloat())
                else -> MinCollapsedHeight.toPx()
            }

            var layoutHeightPx = collapsedHeightPx


            // Calculating coordinates of widgets inside toolbar:

            // Current coordinates of navigation icon
            val navigationIconX = horizontalPaddingPx.roundToInt()
            val navigationIconY = ((collapsedHeightPx - (navigationIconPlaceable?.height ?: 0)) / 2).roundToInt()

            // Current coordinates of actions
            val actionsX = (constraints.maxWidth - (actionsPlaceable?.width ?: 0) - horizontalPaddingPx).roundToInt()
            val actionsY = ((collapsedHeightPx - (actionsPlaceable?.height ?: 0)) / 2).roundToInt()

            // Current coordinates of title
            var collapsingTitleY = 0
            var collapsingTitleX = 0

            if (expandedTitlePlaceable != null && collapsedTitlePlaceable != null) {
                // Measuring toolbar collapsing distance
                val heightOffsetLimitPx = expandedTitlePlaceable.height + expandedTitleBottomPaddingPx


                // Toolbar height at fully expanded state
                val fullyExpandedHeightPx = MinCollapsedHeight.toPx() + heightOffsetLimitPx

                // Coordinates of fully expanded title
                val fullyExpandedTitleX = horizontalPaddingPx
                val fullyExpandedTitleY =
                    fullyExpandedHeightPx - expandedTitlePlaceable.height - expandedTitleBottomPaddingPx

                // Coordinates of fully collapsed title
                val fullyCollapsedTitleX = navigationIconOffset
                val fullyCollapsedTitleY = collapsedHeightPx / 2 - CollapsedTitleLineHeight.toPx().roundToInt() / 2

                // Current height of toolbar
                layoutHeightPx = lerp(fullyExpandedHeightPx, collapsedHeightPx, collapsedFraction)

                // Current coordinates of collapsing title
                collapsingTitleX = lerp(fullyExpandedTitleX, fullyCollapsedTitleX, collapsedFraction).roundToInt()
                collapsingTitleY = lerp(fullyExpandedTitleY, fullyCollapsedTitleY, collapsedFraction).roundToInt()
            } else {
            }

            val toolbarHeightPx = layoutHeightPx.roundToInt() + (additionalContentPlaceable?.height ?: 0)


            // Placing toolbar widgets:

            layout(constraints.maxWidth, toolbarHeightPx) {
                navigationIconPlaceable?.placeRelative(
                    x = navigationIconX,
                    y = navigationIconY
                )
                actionsPlaceable?.placeRelative(
                    x = actionsX,
                    y = actionsY
                )
                centralContentPlaceable?.placeRelative(
                    x = navigationIconOffset.roundToInt(),
                    y = ((collapsedHeightPx - centralContentPlaceable.height) / 2).roundToInt()
                )
                if (expandedTitlePlaceable?.width == collapsedTitlePlaceable?.width) {
                    expandedTitlePlaceable?.placeRelative(
                        x = collapsingTitleX,
                        y = collapsingTitleY,
                    )
                } else {
                    expandedTitlePlaceable?.placeRelativeWithLayer(
                        x = collapsingTitleX,
                        y = collapsingTitleY,
                        layerBlock = { alpha = 1 - collapsedFraction }
                    )
                    collapsedTitlePlaceable?.placeRelativeWithLayer(
                        x = collapsingTitleX,
                        y = collapsingTitleY,
                        layerBlock = { alpha = collapsedFraction }
                    )
                }
                additionalContentPlaceable?.placeRelative(
                    x = 0,
                    y = layoutHeightPx.roundToInt()
                )
            }
        }

    }
}


private fun lerp(a: Float, b: Float, fraction: Float): Float {
    return a + fraction * (b - a)
}

data class ToolbarTitle(
    val titleText: String?,
    val expandedTextStyle: TextStyle,
    val titleColor : Color
) {

    companion object {
        @Composable
        fun toolbarTitleStyle(titleText: String) =
            ToolbarTitle(titleText, MeetTheme.typography.subheading1, MeetTheme.colors.neutralActive)
    }
}

private val MinCollapsedHeight = 56.dp
private val HorizontalPadding = 16.dp
private val ExpandedTitleBottomPadding = 8.dp
private val CollapsedTitleLineHeight = 30.sp

private const val ExpandedTitleId = "expandedTitle"
private const val CollapsedTitleId = "collapsedTitle"
private const val NavigationIconId = "navigationIcon"
private const val ActionsId = "actions"
private const val CentralContentId = "centralContent"
private const val AdditionalContentId = "additionalContent"