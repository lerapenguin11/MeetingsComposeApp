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
    NONE,
    DONE
}

enum class ToolbarTitleMode {
    TITLE, NONE, CHANGING_TITLE
}

internal enum class CurrentScreen {
    EVENT_SCREEN,
    COMMUNITY_SCREEN,
    STILL_SCREEN,
    COMMUNITY_DETAILS,
    EVENT_DETAILS,
    PROFILE_SCREEN,
    MY_MEETINGS_SCREEN
}

fun getToolbarTitle(param: String): ToolbarTitleMode {
    return when (param) {
        CurrentScreen.EVENT_SCREEN.name -> ToolbarTitleMode.TITLE
        CurrentScreen.COMMUNITY_SCREEN.name -> ToolbarTitleMode.TITLE
        CurrentScreen.STILL_SCREEN.name -> ToolbarTitleMode.TITLE
        CurrentScreen.COMMUNITY_DETAILS.name -> ToolbarTitleMode.CHANGING_TITLE
        CurrentScreen.EVENT_DETAILS.name -> ToolbarTitleMode.CHANGING_TITLE
        CurrentScreen.PROFILE_SCREEN.name -> ToolbarTitleMode.TITLE
        CurrentScreen.MY_MEETINGS_SCREEN.name -> ToolbarTitleMode.TITLE
        else -> {
            ToolbarTitleMode.NONE
        }
    }
}

fun getActionToolbar(param: String, isAction: Boolean = false): ActionMode {
    return when (param) {
        CurrentScreen.EVENT_SCREEN.name -> ActionMode.ADD_ICON
        CurrentScreen.COMMUNITY_SCREEN.name -> ActionMode.NONE
        CurrentScreen.STILL_SCREEN.name -> ActionMode.NONE
        CurrentScreen.COMMUNITY_DETAILS.name -> ActionMode.NONE
        CurrentScreen.PROFILE_SCREEN.name -> ActionMode.EDIT_ICON
        CurrentScreen.EVENT_DETAILS.name -> getActionEventScreen(isAction = isAction)
        else -> {
            ActionMode.NONE
        }
    }
}

fun getActionEventScreen(isAction: Boolean): ActionMode {
    return if (isAction) {
        ActionMode.NONE
    } else {
        ActionMode.DONE
    }
}

fun getBackNavigation(param: String): BackNavigationMode {
    return when (param) {
        CurrentScreen.EVENT_SCREEN.name -> BackNavigationMode.NONE
        CurrentScreen.COMMUNITY_SCREEN.name -> BackNavigationMode.NONE
        CurrentScreen.STILL_SCREEN.name -> BackNavigationMode.NONE
        CurrentScreen.COMMUNITY_DETAILS.name -> BackNavigationMode.BACK_ARROW
        CurrentScreen.EVENT_DETAILS.name -> BackNavigationMode.BACK_ARROW
        CurrentScreen.PROFILE_SCREEN.name -> BackNavigationMode.BACK_ARROW
        CurrentScreen.MY_MEETINGS_SCREEN.name -> BackNavigationMode.BACK_ARROW
        else -> {
            BackNavigationMode.NONE
        }
    }
}