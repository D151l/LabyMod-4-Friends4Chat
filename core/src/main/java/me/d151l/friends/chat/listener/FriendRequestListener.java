package me.d151l.friends.chat.listener;

import me.d151l.friends.chat.Friends4Chat;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.TextComponent;
import net.labymod.api.client.component.TranslatableComponent;
import net.labymod.api.client.component.event.ClickEvent;
import net.labymod.api.client.component.format.TextColor;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.labymod.labyconnect.session.request.LabyConnectIncomingFriendRequestAddEvent;
import net.labymod.api.labyconnect.protocol.model.request.IncomingFriendRequest;
import net.labymod.api.util.I18n;

/**
 * @author D151l
 * @created 26/03/2023 - 21:41
 * @project Friends4Chat
 */
public class FriendRequestListener {

  private final Friends4Chat friends4Chat;

  public FriendRequestListener(final Friends4Chat friends4Chat) {
    this.friends4Chat = friends4Chat;
  }

  @Subscribe
  public void onFriendRequestAdd(final LabyConnectIncomingFriendRequestAddEvent event) {
    if (!this.friends4Chat.configuration().getNotifyFriendRequest().getOrDefault())
      return;

    final IncomingFriendRequest request = event.request();
    final String name = request.getName();

    final Component requesterName = this.friends4Chat.getNameHelper().getName(request.gameUser(), name);

    final TextComponent acceptBottom = TextComponent.builder()
        .text(I18n.getTranslation("friendsforchat.message.bottom.accept"))
        .clickEvent(ClickEvent.runCommand("/lmfriends accept " + request.getUniqueId()))
        .build();

    final TextComponent denyBottom = TextComponent.builder()
        .text(I18n.getTranslation("friendsforchat.message.bottom.deny"))
        .clickEvent(ClickEvent.runCommand("/lmfriends deny " + request.getUniqueId()))
        .build();

    final TranslatableComponent component = Component.translatable(
        "friendsforchat.message.friend.requests.notify",
        TextColor.color(170, 170, 170),
        this.friends4Chat.getPrefix(),
        requesterName,
        this.friends4Chat.getPrefix(),
        acceptBottom,
        denyBottom
    );

    this.friends4Chat.displayMessage(component);
  }
}
