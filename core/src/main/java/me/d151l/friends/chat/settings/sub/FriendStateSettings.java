package me.d151l.friends.chat.settings.sub;

import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.annotation.ParentSwitch;
import net.labymod.api.configuration.loader.property.ConfigProperty;

/**
 * @author D151l
 * @created 26/03/2023 - 18:06
 * @project Friends4Chat
 */
public class FriendStateSettings extends Config {

  @ParentSwitch
  @SwitchSetting
  private ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @SwitchSetting
  private ConfigProperty<Boolean> updateToOnline = new ConfigProperty<>(true);

  @SwitchSetting
  private ConfigProperty<Boolean> updateToOffline = new ConfigProperty<>(true);

  @SwitchSetting
  private ConfigProperty<Boolean> updateToOtherOnlineState = new ConfigProperty<>(false);

  public ConfigProperty<Boolean> getUpdateToOffline() {
    return updateToOffline;
  }

  public ConfigProperty<Boolean> getUpdateToOnline() {
    return updateToOnline;
  }

  public ConfigProperty<Boolean> getUpdateToOtherOnlineState() {
    return updateToOtherOnlineState;
  }

  public ConfigProperty<Boolean> getEnabled() {
    return enabled;
  }
}
