package me.d151l.friends.chat.listener;

import me.d151l.friends.chat.Friends4Chat;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.TextComponent;
import net.labymod.api.client.component.TranslatableComponent;
import net.labymod.api.client.component.event.ClickEvent;
import net.labymod.api.client.component.event.HoverEvent;
import net.labymod.api.client.component.format.TextColor;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.labymod.labyconnect.session.friend.LabyConnectFriendServerEvent;
import net.labymod.api.labyconnect.protocol.model.friend.Friend;

/**
 * @author D151l
 * @created 26/03/2023 - 22:01
 * @project Friends4Chat
 */
public class FriendServerListener {

  private final Friends4Chat friends4Chat;

  public FriendServerListener(final Friends4Chat friends4Chat) {
    this.friends4Chat = friends4Chat;
  }

  @Subscribe
  public void onServerUpdate(final LabyConnectFriendServerEvent event) {
    if (!this.friends4Chat.configuration().getNotifyServerUpdate().getOrDefault())
      return;

    final Friend friend = event.friend();
    final Component friendName = this.friends4Chat.getNameHelper().getName(friend);
    final String addressName = event.serverInfo().getAddress();

    final TextColor gray = TextColor.color(170, 170, 170);

    final TextComponent address = TextComponent.builder()
        .text(addressName)
        .color(TextColor.color(255, 255, 255))
        .clickEvent(ClickEvent.runCommand("/lmfriends server " + addressName))
        .hoverEvent(HoverEvent.showText(Component.translatable("friendsforchat.message.friend.server.update.hover").color(gray)))
        .build();

    final TranslatableComponent component = Component.translatable(
        "friendsforchat.message.friend.server.update.message",
        gray,
        this.friends4Chat.getPrefix(),
        friendName,
        address
    );
    this.friends4Chat.displayMessage(component);
  }
}
