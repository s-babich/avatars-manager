// Copyright (C) 2016 The Android Open Source Project
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

import com.google.gerrit.server.avatar.AvatarProvider;
import com.google.gerrit.server.IdentifiedUser;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class AvatarProviderImpl implements AvatarProvider {

  private static final Logger log =
      LoggerFactory.getLogger(PutAvatar.class);

  private final AvatarStorage storage;

  @Inject
  public AvatarProviderImpl (AvatarStorage storage) {
    this.storage = storage;
  }

  @Override
  public String getUrl(IdentifiedUser forUser, int imageSize) {
    try {
      return storage.getUrl(forUser, imageSize);
    } catch (Exception e) {
      log.error("!!!", e);
      return null;
    }
  }

  @Override
  public String getChangeAvatarUrl(IdentifiedUser forUser) {
    return null;
  }

}
