package me.d151l.friends.chat.settings;

import me.d151l.friends.chat.settings.sub.FriendStateSettings;
import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.annotation.SettingSection;

/**
 * @author D151l
 * @created 26/03/2023 - 16:26
 * @project labymod4-addon-template
 */
@SuppressWarnings("FieldMayBeFinal")
@ConfigName("settings")
public class AddonSettings extends AddonConfig {

  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @SettingSection("friend-state")
  private final FriendStateSettings friendStateSettings = new FriendStateSettings();

  @SettingSection("servers")
  @SwitchSetting
  private final ConfigProperty<Boolean> notifyServerUpdate = new ConfigProperty<>(true);

  @SettingSection("friend-request")
  @SwitchSetting
  private final ConfigProperty<Boolean> notifyFriendRequest = new ConfigProperty<>(true);

  @Override
  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }

  public FriendStateSettings getFriendStateSettings() {
    return friendStateSettings;
  }

  public ConfigProperty<Boolean> getNotifyServerUpdate() {
    return notifyServerUpdate;
  }

  public ConfigProperty<Boolean> getNotifyFriendRequest() {
    return notifyFriendRequest;
  }
}
