package me.d151l.friends.chat.listener;

import me.d151l.friends.chat.Friends4Chat;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.TranslatableComponent;
import net.labymod.api.client.component.format.TextColor;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.labymod.labyconnect.session.friend.LabyConnectFriendStatusEvent;
import net.labymod.api.labyconnect.protocol.model.UserStatus;
import net.labymod.api.labyconnect.protocol.model.friend.Friend;

/**
 * @author D151l
 * @created 26/03/2023 - 16:51
 * @project Friends4Chat
 */
public class FriendStatusUpdateListener {

  private final Friends4Chat friends4Chat;

  public FriendStatusUpdateListener(final Friends4Chat friends4Chat) {
    this.friends4Chat = friends4Chat;
  }

  @Subscribe
  public void onStatusUpdate(final LabyConnectFriendStatusEvent event) {
    final Friend friend = event.friend();
    final String name = friend.getName();
    final Component friendName = Component.text(name, friend.gameUser().visibleGroup().getTextColor());
    final UserStatus state = event.getStatus();

    if (event.isOnline() && event.getPreviousStatus().equals(UserStatus.OFFLINE)) {
      if (this.friends4Chat.configuration().getFriendStateSettings().getUpdateToOnline().getOrDefault())
        this.notifyFriendStateUpdate(friendName, state);
      return;
    }

    if (event.wasOnline() && event.getStatus().equals(UserStatus.OFFLINE)) {
      if (this.friends4Chat.configuration().getFriendStateSettings().getUpdateToOffline().getOrDefault())
        this.notifyFriendStateUpdate(friendName, UserStatus.OFFLINE);
      return;
    }

    if (this.friends4Chat.configuration().getFriendStateSettings().getUpdateToOtherOnlineState().getOrDefault())
      this.notifyFriendStateUpdate(friendName, state);
  }

  private void notifyFriendStateUpdate(final Component friendName, final UserStatus status) {
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
