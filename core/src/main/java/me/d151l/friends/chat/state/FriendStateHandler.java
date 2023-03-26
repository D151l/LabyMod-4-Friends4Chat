package me.d151l.friends.chat.state;

import me.d151l.friends.chat.Friends4Chat;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.TranslatableComponent;
import net.labymod.api.client.component.format.TextColor;
import net.labymod.api.labyconnect.protocol.model.UserStatus;
import net.labymod.api.util.I18n;

/**
 * @author D151l
 * @created 26/03/2023 - 21:03
 * @project Friends4Chat
 */
public class FriendStateHandler {

  private final Friends4Chat friends4Chat;

  public FriendStateHandler(final Friends4Chat friends4Chat) {
    this.friends4Chat = friends4Chat;
  }

  public void notifyFriendStateUpdate(final Component friendName, final UserStatus status) {
    final TranslatableComponent component = Component.translatable(
        "friends4chat.message.friend.status.update",
        TextColor.color(170, 170, 170),
        this.friends4Chat.getPrefix(),
        friendName,
        status.component()
    );
    this.friends4Chat.displayMessage(component);
  }
}
