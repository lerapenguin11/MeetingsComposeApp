package com.example.data.di

import com.example.data.mock.MockCommunityData
import com.example.data.mock.MockCommunityDetails
import com.example.data.mock.MockEventData
import com.example.data.mock.MockEventDetailsData
import com.example.data.mock.MockUserData
import org.koin.dsl.module

internal val mockModule = module {
    single<MockUserData> { MockUserData() }
    single<MockCommunityData> { MockCommunityData() }
    single<MockEventData> { MockEventData() }
    single<MockEventDetailsData> { MockEventDetailsData() }
    single<MockCommunityDetails> { MockCommunityDetails() }
}