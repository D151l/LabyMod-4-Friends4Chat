package me.d151l.friends.chat.helper;

import me.d151l.friends.chat.Friends4Chat;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.TextColor;
import net.labymod.api.labyconnect.protocol.model.friend.Friend;
import net.labymod.api.user.GameUser;

/**
 * @author D151l
 * @created 27/03/2023 - 16:35
 * @project Friends4Chat
 */
public class NameHelper {

  private final Friends4Chat friends4Chat;

  public NameHelper(final Friends4Chat friends4Chat) {
    this.friends4Chat = friends4Chat;
  }

  public Component getName(final GameUser gameUser, final String name) {
    if (!this.friends4Chat.configuration().getDisplayRankColor().getOrDefault())
      return Component.text(name, TextColor.color(170, 170, 170));

    return Component.text(name, gameUser.visibleGroup().getTextColor());
  }

  public Component getName(final Friend friend) {
    final String name = friend.getName();

    if (!this.friends4Chat.configuration().getDisplayRankColor().getOrDefault())
      return Component.text(name, TextColor.color(170, 170, 170));

    return Component.text(name, friend.gameUser().visibleGroup().getTextColor());
  }
}
