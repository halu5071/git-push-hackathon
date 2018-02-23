/*
 *
 *  GitHub-Client
 *
 *  RepositoryModule.kt
 *
 *  Copyright 2018 moatwel.io
 *  author : halu5071 (Yasunori Horii)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package io.moatwel.github.data.di

import dagger.Module
import dagger.Provides
import io.moatwel.github.data.datasource.AuthDataDataSource
import io.moatwel.github.data.datasource.CloudUserDataSource
import io.moatwel.github.data.network.retrofit.EventApi
import io.moatwel.github.data.repository.AuthDataDataRepository
import io.moatwel.github.data.repository.EventDataRepository
import io.moatwel.github.data.repository.UserDataRepository
import io.moatwel.github.domain.repository.AuthDataRepository
import javax.inject.Singleton

@Module
class RepositoryModule {

  @Provides
  @Singleton
  fun provideUserRepository(cloudUserDataSource: CloudUserDataSource): UserDataRepository {
    return UserDataRepository(cloudUserDataSource)
  }

  @Provides
  @Singleton
  fun provideEventRepository(api: EventApi, userRepository: UserDataRepository): EventDataRepository {
    return EventDataRepository(api, userRepository)
  }

  @Provides
  @Singleton
  fun provideAuthDataRepository(authDataDataSource: AuthDataDataSource): AuthDataRepository {
    return AuthDataDataRepository(authDataDataSource)
  }
}