package com.pocketsecurities.pocketcomposecomponent.component.grid

import android.content.Context
import android.media.AudioManager
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pocketsecurities.pocketcomposecomponent.R
import com.pocketsecurities.pocketcomposecomponent.color_252525
import com.pocketsecurities.pocketcomposecomponent.color_414141
import com.pocketsecurities.pocketcomposecomponent.component.grid.data.HomeMenuGridBaseItem
import com.pocketsecurities.pocketcomposecomponent.component.grid.data.HomeMenuGridItem
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketTextConfig
import com.pocketsecurities.pocketcomposecomponent.model.drawableProvider.localDrawableProvider
import com.pocketsecurities.pocketcomposecomponent.utils.ObserveAsEvents
import com.pocketsecurities.pocketcomposecomponent.utils.isPreviewMode
import com.pocketsecurities.pocketcomposecomponent.view.BaseMenuItem
import com.pocketsecurities.pocketcomposecomponent.view.MenuItemBlank
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.ReorderableLazyGridState
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.rememberReorderableLazyGridState
import org.burnoutcrew.reorderable.reorderable
import java.util.UUID

/**
 * @sample
 *
 * @param homeMenuGridBaseItemList 輸入menu list
 * @param updateSortAction 更新menu排序
 * @param onClick export點擊事件
 */
@Composable
fun LocalHomeMenuReorderGrid(
    modifier: Modifier = Modifier,
    homeMenuGridBaseItemList: List<HomeMenuGridBaseItem>,
    updateSortAction: (List<HomeMenuGridBaseItem>) -> Unit = {},
    onClick: (String) -> Unit = {}
) {
    if (isPreviewMode()) {
        HomeMenuReorderGridRoot(
            modifier = modifier,
            homeMenuGridBaseItemList = homeMenuGridBaseItemList,
            updateSortAction = updateSortAction,
            onClick = onClick
        )
    } else {
        HomeMenuReorderGrid()
    }
}

@Composable
private fun HomeMenuReorderGridRoot(
    modifier: Modifier = Modifier,
    homeMenuGridBaseItemList: List<HomeMenuGridBaseItem>,
    updateSortAction: (List<HomeMenuGridBaseItem>) -> Unit = {},
    onClick: (String) -> Unit = {}
) {
    val vm = viewModel { HomeMenuReorderGridViewModel(homeMenuGridBaseItemList) }

    val state = rememberReorderableLazyGridState(
        onMove = vm::moveMenu,
        canDragOver = vm::isMenuDragEnabled
    )

    ObserveAsEvents(
        flow = vm.viewEvent,
        onEvent = { event ->
            updateSortAction(event)
        }
    )

    HomeMenuReorderGrid(
        modifier = modifier,
        state = state,
        items = vm.menus,
        onClick = onClick
    )
}


@Composable
private fun HomeMenuReorderGrid(
    modifier: Modifier = Modifier,
    state: ReorderableLazyGridState = rememberReorderableLazyGridState(onMove = { _, _ -> }),
    items: List<HomeMenuGridBaseItem> = emptyList(),
    onClick: (String) -> Unit = {}
) {

    val context = LocalContext.current
    val haptic = LocalHapticFeedback.current

    LazyVerticalGrid(
        columns = GridCells.Fixed(5),
        modifier = modifier.reorderable(state),
        state = state.gridState,
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        itemsIndexed(
            items = items,
            key = { _, item ->
                item.id
            }
        ) { index, item ->
            if (item.isLocked) {
                Box(
                    modifier = Modifier.aspectRatio(1f),
                    contentAlignment = Alignment.Center
                ) {
                    if (item.name.isNotEmpty()) {
                        BaseMenuItem(
                            titleConfig = PocketTextConfig(
                                value = item.name
                            ),
                            menuRes = item.iconResId,
                            withDash = false
                        )
                    } else {
                        MenuItemBlank(
                            modifier = Modifier.background(color_252525),
                            isActive = index == items.indexOfFirst { it.name.isEmpty() }
                        )
                    }
                }
            } else {
                ReorderableItem(state, item.id) { isDragging ->
                    var isItemDragging by remember { mutableStateOf(false) }
                    isItemDragging = isDragging

                    val elevation = animateDpAsState(if (isDragging) 8.dp else 0.dp, label = "")
                    val bg = animateColorAsState(
                        targetValue = if (isDragging) color_414141 else color_252525,
                        label = ""
                    )

                    LaunchedEffect(isItemDragging) {
                        if (isItemDragging) {
                            (context.getSystemService(Context.AUDIO_SERVICE) as AudioManager)
                                .playSoundEffect(AudioManager.FX_KEY_CLICK)

                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        }
                    }

                    BaseMenuItem(
                        modifier = Modifier
                            .detectReorderAfterLongPress(state)
                            .shadow(elevation.value)
                            .aspectRatio(1f)
                            .background(bg.value),
                        titleConfig = PocketTextConfig(
                            value = item.name
                        ),
                        menuRes = item.iconResId,
                        deleteRes = localDrawableProvider().getIconDeleteResource(),
                        onClick = {
                            onClick.invoke(item.id)
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewHomeMenuGrid() {
    val items = listOf(
        HomeMenuGridItem(
            id = UUID.randomUUID().toString(),
            name = "空吧哇",
            iconResId = R.drawable.pocket_shortcut_condition_order,
            isLocked = true,
            gaSelectKey = null
        ),
        HomeMenuGridItem(
            id = UUID.randomUUID().toString(),
            name = "空吧哇",
            iconResId = R.drawable.pocket_shortcut_condition_order,
            isLocked = false,
            gaSelectKey = null
        ),
        HomeMenuGridItem(
            id = UUID.randomUUID().toString(),
            name = "空吧哇",
            iconResId = R.drawable.pocket_shortcut_condition_order,
            isLocked = false,
            gaSelectKey = null
        ),
        HomeMenuGridItem(
            id = UUID.randomUUID().toString(),
            name = "空吧哇",
            iconResId = R.drawable.pocket_shortcut_condition_order,
            isLocked = false,
            gaSelectKey = null
        ),
        HomeMenuGridItem(
            id = UUID.randomUUID().toString(),
            name = "空吧哇",
            iconResId = R.drawable.pocket_shortcut_condition_order,
            isLocked = false,
            gaSelectKey = null
        ),
    )


    HomeMenuReorderGrid(
        state = rememberReorderableLazyGridState(
            onMove = { _, _ -> }
        ),
        items = items
    )
}