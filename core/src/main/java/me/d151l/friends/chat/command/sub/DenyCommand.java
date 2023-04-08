package me.d151l.friends.chat.command.sub;

import me.d151l.friends.chat.Friends4Chat;
import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.TextColor;
import net.labymod.api.labyconnect.LabyConnectSession;
import net.labymod.api.labyconnect.protocol.model.request.IncomingFriendRequest;
import java.util.Objects;
import java.util.UUID;

/**
 * @author D151l
 * @created 08/04/2023 - 14:34
 * @project Friends4Chat
 */
public class DenyCommand {

  private final Friends4Chat friends4Chat;
  private final TextColor gray;

  public DenyCommand(final Friends4Chat friends4Chat) {
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
    final IncomingFriendRequest incomingRequest = Objects.requireNonNull(
            session)
        .getIncomingRequest(uuid);

    if (incomingRequest == null) {
      this.friends4Chat.displayMessage(Component.translatable("friendsforchat.message.friend.requests.not.found",
          this.gray,
          this.friends4Chat.getPrefix()
      ));
      return false;
    }

    session.declineFriendRequest(uuid);
    this.friends4Chat.displayMessage(Component.translatable("friendsforchat.message.friend.requests.successfully.deny",
        this.gray,
        this.friends4Chat.getPrefix()));
    return true;
  }
}
