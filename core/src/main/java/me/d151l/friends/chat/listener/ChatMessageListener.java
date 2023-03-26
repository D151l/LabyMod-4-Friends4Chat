package me.d151l.friends.chat.listener;

import me.d151l.friends.chat.Friends4Chat;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.TranslatableComponent;
import net.labymod.api.client.component.event.ClickEvent;
import net.labymod.api.client.component.event.HoverEvent;
import net.labymod.api.client.component.format.TextColor;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.labymod.labyconnect.session.chat.LabyConnectChatMessageEvent;
import net.labymod.api.labyconnect.protocol.model.User;
import net.labymod.api.labyconnect.protocol.model.chat.ChatMessage;

/**
 * @author D151l
 * @created 26/03/2023 - 23:59
 * @project Friends4Chat
 */
public class ChatMessageListener {

  private final Friends4Chat friends4Chat;

  public ChatMessageListener(final Friends4Chat friends4Chat) {
    this.friends4Chat = friends4Chat;
  }

  @Subscribe
  public void onChatMessage(LabyConnectChatMessageEvent event) {
    if (!this.friends4Chat.configuration().getNotifyFriendMessageReceive().getOrDefault())
      return;

    if (event.message().sender().isSelf())
      return;

    final ChatMessage message = event.message();
    final User sender = message.sender();

    final String name = sender.getName();
    final Component friendName = Component.text(name, sender.gameUser().visibleGroup().getTextColor());

    final TextColor gray = TextColor.color(170, 170, 170);

    final TranslatableComponent translatable = Component.translatable("friends4chat.message.chat.notify",
        gray,
        this.friends4Chat.getPrefix(),
        friendName);

    translatable.clickEvent(ClickEvent.runCommand("/lmfriends openchat " + sender.gameUser().getUniqueId()));
    translatable.hoverEvent(HoverEvent.showText(Component.translatable("friends4chat.message.chat.hover", gray)));

    this.friends4Chat.displayMessage(translatable);
  }
}
