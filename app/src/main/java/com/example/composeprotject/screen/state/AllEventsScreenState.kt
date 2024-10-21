package com.example.composeprotject.screen.state

import com.example.composeprotject.ui.component.utils.CommonString

enum class AllEventsScreenState(val res: Int) {
    ALL_COMMUNITIES(res = CommonString.all_communities),
    ALL_UPCOMING_MEETINGS(res = CommonString.all_upcoming_meetings),
    ALL_RELEVANT_MEETINGS(res = CommonString.all_relevant_meetings)
}