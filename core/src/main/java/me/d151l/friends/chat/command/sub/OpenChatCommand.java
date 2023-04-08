package me.d151l.friends.chat.command.sub;

import me.d151l.friends.chat.Friends4Chat;
import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.TextColor;
import net.labymod.api.labyconnect.LabyConnectSession;
import net.labymod.api.labyconnect.protocol.model.friend.Friend;
import java.util.UUID;

/**
 * @author D151l
 * @created 08/04/2023 - 14:29
 * @project Friends4Chat
 */
public class OpenChatCommand {

  private final Friends4Chat friends4Chat;
  private final TextColor gray;

  public OpenChatCommand(final Friends4Chat friends4Chat) {
    this.friends4Chat = friends4Chat;

    this.gray = TextColor.color(170, 170, 170);
  }

  public boolean runCommand(String[] arguments) {
    final String uuidString = arguments[1];
    UUID uuid;

    try {
      uuid = UUID.fromString(uuidString);
    } catch (Exception exception) {
      this.friends4Chat.displayMessage(Component.translatable("friendsforchat.message.format.incorrect",
          this.gray,
          this.friends4Chat.getPrefix()
      ));
      return false;
    }

    if (!Laby.labyAPI().labyConnect().isConnected()) {
      this.friends4Chat.displayMessage(Component.translatable("friendsforchat.message.not.connect.labymod",
          this.gray,
          this.friends4Chat.getPrefix()
      ));
      return false;
    }

    final LabyConnectSession session = Laby.labyAPI().labyConnect().getSession();

    if (session == null) {
      this.friends4Chat.displayMessage(Component.translatable("friendsforchat.message.laby.session.null",
          this.gray,
          this.friends4Chat.getPrefix()
      ));
      return false;
    }

    final Friend friend = session.getFriend(uuid);

    if (friend == null) {
      this.friends4Chat.displayMessage(Component.translatable("friendsforchat.message.chat.not.found",
          this.gray,
          this.friends4Chat.getPrefix()
      ));
      return false;
    }

    friend.chat().openChat();
    return true;
  }
}
