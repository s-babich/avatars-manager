// Copyright (C) 2013 The Android Open Source Project
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.googlesource.gerrit.plugins.avatars.manager;

//import com.google.gerrit.extensions.registration.DynamicItem;
import com.google.gerrit.extensions.restapi.RestApiModule;
import com.google.gerrit.server.account.AccountResource;
//import com.google.gerrit.server.avatar.AvatarProvider;
import com.google.inject.AbstractModule;
//import com.google.inject.Inject;

public class Module extends AbstractModule {

//  private final DynamicItem<AvatarProvider> avatar;

//  @Inject
//  public Module (
//    DynamicItem<AvatarProvider> avatar
//  ) {
//    this.avatar = avatar;
//  }

  @Override
  protected void configure() {
//    System.out.println(String.format(">>>>>>>>>>>>>>> %s", avatar.get()));

//    DynamicItem.itemOf(binder(), com.google.gerrit.server.avatar.AvatarProvider.class);
//    DynamicItem.bind(binder(), AvatarProvider.class).to(AvatarProviderImpl.class);

//    bind(AvatarProvider.class).to(AvatarProviderImpl.class);

//    System.out.println(String.format(">>>>>>>>>>>>>>> %s", avatar.get()));
    install(new RestApiModule() {
      @Override
      protected void configure() {
        put(AccountResource.ACCOUNT_KIND, "avatar").to(PutAvatarURL.class);
      }
    });
  }
}
