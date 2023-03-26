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
    final String name = friend.getName();
    final Component friendName = Component.text(name, friend.gameUser().visibleGroup().getTextColor());
    String addressName = event.serverInfo().getAddress();

    final TextColor gray = TextColor.color(255, 255, 255);

    final TextComponent address = TextComponent.builder()
        .text(addressName)
        .color(gray)
        .clickEvent(ClickEvent.runCommand("/lmfriends server " + addressName))
        .hoverEvent(HoverEvent.showText(Component.translatable("friends4chat.message.friend.server.update.hover").color(gray)))
        .build();

    final TranslatableComponent component = Component.translatable(
        "friends4chat.message.friend.server.update.message",
        gray,
        this.friends4Chat.getPrefix(),
        friendName,
        address
    );
    this.friends4Chat.displayMessage(component);
  }
}
