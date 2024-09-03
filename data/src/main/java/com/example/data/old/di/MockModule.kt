package com.example.data.old.di

import com.example.data.old.mock.MockCommunityData
import com.example.data.old.mock.MockCommunityDetails
import com.example.data.old.mock.MockEventData
import com.example.data.old.mock.MockEventDetailsData
import com.example.data.old.mock.MockUserData
import org.koin.dsl.module

internal val mockModule = module {
    single<MockUserData> { MockUserData() }
    single<MockCommunityData> { MockCommunityData() }
    single<MockEventData> { MockEventData() }
    single<MockEventDetailsData> { MockEventDetailsData() }
    single<MockCommunityDetails> { MockCommunityDetails() }
}