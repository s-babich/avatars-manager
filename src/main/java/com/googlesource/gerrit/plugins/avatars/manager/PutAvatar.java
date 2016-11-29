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

//import com.google.gerrit.common.data.GlobalCapability;
//import com.google.gerrit.extensions.annotations.RequiresCapability;
import com.google.gerrit.extensions.restapi.AuthException;
import com.google.gerrit.extensions.restapi.DefaultInput;
import com.google.gerrit.extensions.restapi.Response;
import com.google.gerrit.extensions.restapi.RestModifyView;
import com.google.gerrit.server.account.AccountResource;
import com.google.gerrit.server.CurrentUser;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// curl -v -k -H "Content-Type: application/json" -X PUT \
// --digest --user username:http_password \
// --data '{"url":"https://avatar.site/avatar.gif"}' \
// https://gerrit.agromat.ua:8443/a/accounts/1/avatar

//@RequiresCapability(GlobalCapability.MODIFY_ACCOUNT)
@Singleton
public class PutAvatar
    implements RestModifyView<AccountResource, PutAvatar.Input> {

  private static final Logger log =
      LoggerFactory.getLogger(PutAvatar.class);

  public class Input {
    @DefaultInput
    public String url;
  }

  private final Provider<CurrentUser> self;
  private final AvatarStorage         storage;

  @Inject
  PutAvatar(Provider<CurrentUser> self, AvatarStorage storage) {
    this.self    = self;
    this.storage = storage;
  }

  @Override
  public Response<String> apply(AccountResource rsrc, Input input)
      throws AuthException {

    if (self.get().getAccountId() != rsrc.getUser().getAccountId()
        && !self.get().getCapabilities().canModifyAccount()) {
      throw new AuthException("You not allowed to change avatar for user Id: "
        + rsrc.getUser().getAccountId());
    }

    try {
      storage.setUrl(rsrc.getUser(), input.url, 0);
    } catch (Exception e) {
      // ToDo: Error processing
      log.error(String.format("!!!!!!!!!! %s", e), e);
    }

    return Response.ok(input.url);
  }

}
