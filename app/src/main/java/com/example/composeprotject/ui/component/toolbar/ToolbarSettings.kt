package com.example.composeprotject.ui.component.toolbar

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class ToolbarSettings(
    val backNavigationMode: BackNavigationMode = BackNavigationMode.NONE,
    val actionMode: ActionMode = ActionMode.NONE,
    val toolbarTitleMode: ToolbarTitleMode = ToolbarTitleMode.TITLE,
) : Parcelable

enum class BackNavigationMode {
    BACK_ARROW, NONE
}

enum class ActionMode {
    ADD_ICON,
    EDIT_ICON,
    NONE
}

enum class ToolbarTitleMode {
    TITLE, NONE
}

internal enum class CurrentScreen{
    EVENT_SCREEN,
    COMMUNITY_SCREEN,
    STILL_SCREEN
}

fun getToolbarTitle(param : String) : ToolbarTitleMode{
    return when(param){
        CurrentScreen.EVENT_SCREEN.name -> ToolbarTitleMode.TITLE
        CurrentScreen.COMMUNITY_SCREEN.name -> ToolbarTitleMode.TITLE
        CurrentScreen.STILL_SCREEN.name -> ToolbarTitleMode.TITLE
        else -> {ToolbarTitleMode.NONE}
    }
}

fun getActionToolbar(param : String) : ActionMode{
    return when(param){
        CurrentScreen.EVENT_SCREEN.name -> ActionMode.ADD_ICON
        CurrentScreen.COMMUNITY_SCREEN.name -> ActionMode.NONE
        CurrentScreen.STILL_SCREEN.name -> ActionMode.NONE
        else -> {ActionMode.NONE}
    }
}

fun getBackNavigation(param : String) : BackNavigationMode{
    return when(param){
        CurrentScreen.EVENT_SCREEN.name -> BackNavigationMode.NONE
        CurrentScreen.COMMUNITY_SCREEN.name -> BackNavigationMode.NONE
        CurrentScreen.STILL_SCREEN.name -> BackNavigationMode.NONE
        else -> {BackNavigationMode.NONE}
    }
}