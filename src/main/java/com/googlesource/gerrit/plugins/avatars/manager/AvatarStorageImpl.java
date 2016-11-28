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

import com.google.gerrit.extensions.annotations.PluginData;
import com.google.gerrit.server.IdentifiedUser;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.nio.file.Path;

@Singleton
public class AvatarStorageImpl implements AvatarStorage {

  private final Path dataDir;

  @Inject
  AvatarStorageImpl(@PluginData Path dataDir) {
    this.dataDir = dataDir;
  }

  @Override
  public void saveUrl (IdentifiedUser forUser, String url, int imageSize) {
    System.out.println(String.format(">>>   %s:saveUrl(%s, %s, %s)", this.getClass(), forUser, imageSize, url));
    System.out.println(String.format(">>>      dataDir = %s", dataDir));
  }

}
