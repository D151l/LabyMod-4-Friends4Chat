package me.d151l.friends.chat.settings;

import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.property.ConfigProperty;

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

  @Override
  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }
}
