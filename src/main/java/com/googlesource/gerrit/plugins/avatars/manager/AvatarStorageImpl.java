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
import org.eclipse.jgit.storage.file.FileBasedConfig;
import org.eclipse.jgit.util.FS;

@Singleton
public class AvatarStorageImpl implements AvatarStorage {

  public static final String SECTION   = "avatar";
  public static final String URL       = "url";
  public static final String COMMENTS  = "comments";
  public static final String ACCOUNTID = "AccountId";
  public static final String NAME      = "Name";
  public static final String USERNAME  = "UserName";
  public static final String EMAIL     = "Email";

  private final Path dataDir;

  @Inject
  AvatarStorageImpl(@PluginData Path dataDir) {
    this.dataDir = dataDir;
  }

  @Override
  public String getUrl(IdentifiedUser forUser, int imageSize)
      throws Exception {
    FileBasedConfig cfg = getUserAvatarConfig(forUser);
    return cfg.getString(SECTION, null, URL);
  }

  @Override
  public void setUrl (IdentifiedUser forUser, String url, int imageSize)
      throws Exception {
    FileBasedConfig cfg = getUserAvatarConfig(forUser);
    cfg.setString(SECTION, null, URL, url);
    cfg.save();
  }

  private FileBasedConfig getUserAvatarConfig(IdentifiedUser user)
      throws Exception {
    Path            file = dataDir.resolve(user.getAccountId() + ".config");
    FileBasedConfig cfg  = new FileBasedConfig(file.toFile(), FS.DETECTED);
    if (cfg.getFile().exists()) {
      cfg.load();
    }
    cfg.setInt   (COMMENTS, null, ACCOUNTID, user.getAccountId().get());
    cfg.setString(COMMENTS, null, NAME,      user.getName());
    cfg.setString(COMMENTS, null, USERNAME,  user.getUserName());
    cfg.setString(COMMENTS, null, EMAIL,     user.getNameEmail());
    return cfg;
  }

}
