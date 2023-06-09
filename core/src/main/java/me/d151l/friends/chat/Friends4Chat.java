package me.d151l.friends.chat;

import me.d151l.friends.chat.command.LMFriendsCommand;
import me.d151l.friends.chat.helper.NameHelper;
import me.d151l.friends.chat.listener.ChatMessageListener;
import me.d151l.friends.chat.listener.FriendRequestListener;
import me.d151l.friends.chat.listener.FriendServerListener;
import me.d151l.friends.chat.listener.FriendStatusUpdateListener;
import me.d151l.friends.chat.settings.AddonSettings;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.client.component.Component;
import net.labymod.api.models.addon.annotation.AddonMain;
import net.labymod.api.util.I18n;

/**
 * @author D151l
 * @created 26/03/2023 - 16:22
 * @project labymod4-addon-template
 */
@AddonMain
public class Friends4Chat extends LabyAddon<AddonSettings> {

  private static Friends4Chat instance;

  private final NameHelper nameHelper;

  private Component prefix;

  public Friends4Chat() {
    instance = this;

    this.nameHelper = new NameHelper(this);
  }

  @Override
  protected void enable() {
    this.registerSettingCategory();

    this.registerListener(new FriendStatusUpdateListener(this));
    this.registerListener(new FriendServerListener(this));
    this.registerListener(new FriendRequestListener(this));
    this.registerListener(new ChatMessageListener(this));

    this.registerCommand(new LMFriendsCommand(this));
  }

  @Override
  protected Class<AddonSettings> configurationClass() {
    return AddonSettings.class;
  }

  public static Friends4Chat getInstance() {
    return instance;
  }

  public NameHelper getNameHelper() {
    return nameHelper;
  }

  public Component getPrefix() {
    if (this.prefix == null)
      this.prefix = Component.text(I18n.getTranslation("friendsforchat.message.prefix"));

    return prefix;
  }
}
