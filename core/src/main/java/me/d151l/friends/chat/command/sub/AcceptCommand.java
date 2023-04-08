package me.d151l.friends.chat.command.sub;

import me.d151l.friends.chat.Friends4Chat;
import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.TextColor;
import net.labymod.api.labyconnect.LabyConnectSession;
import net.labymod.api.labyconnect.protocol.model.request.IncomingFriendRequest;
import net.labymod.api.user.GameUser;
import java.util.Objects;
import java.util.UUID;

/**
 * @author D151l
 * @created 08/04/2023 - 14:36
 * @project Friends4Chat
 */
public class AcceptCommand {

  private final Friends4Chat friends4Chat;
  private final TextColor gray;

  public AcceptCommand(final Friends4Chat friends4Chat) {
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

    session.acceptFriendRequest(uuid);
    final GameUser friend = Laby.references().gameUserService().gameUser(uuid);
    Laby.labyAPI().labyNetController().loadNameByUniqueId(uuid, result -> {

      if (result.isPresent()) {
        final Component friendName = this.friends4Chat.getNameHelper().getName(friend, result.get());
        friends4Chat.displayMessage(Component.translatable("friendsforchat.message.friend.requests.successfully.accepted",
            this.gray,
            friends4Chat.getPrefix(),
            friendName));
      } else {
        this.friends4Chat.displayMessage(Component.translatable("friendsforchat.message.friend.requests.error",
            this.gray,
            friends4Chat.getPrefix()));
      }
    });
    return true;
  }

}
