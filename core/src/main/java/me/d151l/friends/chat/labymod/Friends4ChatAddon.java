package me.d151l.friends.chat.labymod;

import me.d151l.friends.chat.Friends4Chat;
import me.d151l.friends.chat.settings.AddonSettings;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

/**
 * @author D151l
 * @created 26/03/2023 - 16:24
 * @project labymod4-addon-template
 */
@AddonMain
public class Friends4ChatAddon extends LabyAddon<AddonSettings> {

  @Override
  protected void enable() {
    this.registerSettingCategory();

    new Friends4Chat(this);
  }

  @Override
  protected Class<AddonSettings> configurationClass() {
    return AddonSettings.class;
  }
}
