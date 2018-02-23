/*
 *  GitHub-Client
 *
 *  AppComponent.kt
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

package io.moatwel.github.presentation.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.moatwel.github.App
import io.moatwel.github.data.di.DataSourceModule
import io.moatwel.github.data.di.NetworkModule
import io.moatwel.github.data.di.RepositoryModule
import io.moatwel.github.presentation.di.activity.LoginActivityBuilder
import io.moatwel.github.presentation.di.activity.MainActivityBuilder
import javax.inject.Singleton

@Singleton
@Component(modules = [
  AndroidSupportInjectionModule::class,
  AppModule::class,
  NetworkModule::class,
  RepositoryModule::class,
  DataSourceModule::class,
  MainActivityBuilder::class,
  LoginActivityBuilder::class
])
interface AppComponent : AndroidInjector<App> {

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(app: App): Builder

    fun build(): AppComponent
  }

  override fun inject(app: App)
}