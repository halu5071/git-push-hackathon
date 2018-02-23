/*
 *  GitHub-Client
 *
 *  DatasourceModule.kt
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

import android.content.Context
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import io.moatwel.github.data.datasource.AuthDataDataSource
import io.moatwel.github.data.datasource.CloudUserDataSource
import io.moatwel.github.data.datasource.Crypto
import io.moatwel.github.data.datasource.EventDataSourceFactory
import io.moatwel.github.data.network.retrofit.EventApi
import io.moatwel.github.data.network.retrofit.UserApi
import io.moatwel.github.domain.repository.UserRepository

@Module
class DataSourceModule {

  @Provides
  fun provideCloudUserDataSource(api: UserApi): CloudUserDataSource {
    return CloudUserDataSource(api)
  }

  @Provides
  fun provideAuthDataDataSource(context: Context, moshi: Moshi, crypto: Crypto): AuthDataDataSource {
    return AuthDataDataSource(context, moshi, crypto)
  }

  @Provides
  fun provideEventDataSourceFactory(api: EventApi,
                                    userRepository: UserRepository): EventDataSourceFactory {
    return EventDataSourceFactory(api, userRepository)
  }

  @Provides
  fun provideCrypto(context: Context): Crypto {
    return Crypto(context)
  }
}