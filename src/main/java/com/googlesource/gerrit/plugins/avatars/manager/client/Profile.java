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

package com.googlesource.gerrit.plugins.avatars.manager.client;

import com.google.gerrit.client.GerritUiExtensionPoint;
import com.google.gerrit.client.info.AccountInfo;
import com.google.gerrit.plugin.client.extension.Panel;
//import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
//import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Profile extends VerticalPanel {

  static class Factory implements Panel.EntryPoint {
    @Override
    public void onLoad(Panel panel) {
      AccountInfo accountInfo =
          panel.getObject(GerritUiExtensionPoint.Key.ACCOUNT_INFO).cast();
      panel.setWidget(new Profile(accountInfo));
    }
  }

  // Examples: gerrit-gwtui/src/main/java/com/google/gerrit/client

  Profile(AccountInfo accountInfo) {
//  Grid grid = new Grid(1, 2);
//  grid.setText(0, 0, "Avatar URL");
//  grid.setText(0, 1, "https://...");
//  add(grid);
    Button save = new Button("Save Avatar URL");
//  save.setEnabled(true);
//  save.setVisible(true);
    add(save);
  }

}
