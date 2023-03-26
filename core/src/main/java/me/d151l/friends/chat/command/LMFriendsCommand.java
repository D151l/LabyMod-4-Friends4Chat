package me.d151l.friends.chat.command;

import me.d151l.friends.chat.Friends4Chat;
import net.labymod.api.Laby;
import net.labymod.api.client.chat.command.Command;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.TextColor;
import net.labymod.api.labyconnect.LabyConnectSession;
import net.labymod.api.labyconnect.protocol.model.friend.Friend;
import net.labymod.api.labyconnect.protocol.model.request.IncomingFriendRequest;
import java.util.Objects;
import java.util.UUID;

/**
 * @author D151l
 * @created 26/03/2023 - 22:27
 * @project Friends4Chat
 */
public class LMFriendsCommand extends Command {

  private final Friends4Chat friends4Chat;

  public LMFriendsCommand(final Friends4Chat friends4Chat) {
    super("lmfriends", "lmfriend");

    this.friends4Chat = friends4Chat;
  }


  @Override
  public boolean execute(String prefix, String[] arguments) {
    final TextColor gray = TextColor.color(170, 170, 170);

    if (arguments.length == 2) {
      if (arguments[0].equals("server")) {
        Laby.labyAPI().serverController().joinServer(arguments[1]);
      }

      if (arguments[0].equals("accept")) {
        final String uuidString = arguments[1];
        UUID uuid;

        try {
          uuid = UUID.fromString(uuidString);
        } catch (Exception exception) {
          this.friends4Chat.displayMessage("");
          return true;
        }
        final LabyConnectSession session = Laby.labyAPI().labyConnect().getSession();
        final IncomingFriendRequest incomingRequest = Objects.requireNonNull(
                session)
            .getIncomingRequest(uuid);

        if (incomingRequest == null) {
          this.friends4Chat.displayMessage(Component.translatable("friends4chat.message.friend.request.not.found",
              gray,
              this.friends4Chat.getPrefix()
          ));
          return true;
        }

        session.acceptFriendRequest(uuid);
        final Friend friend = session.getFriend(uuid);
        assert friend != null;
        final String name = friend.getName();
        final Component friendName = Component.text(name, friend.gameUser().visibleGroup().getTextColor());

        this.friends4Chat.displayMessage(Component.translatable("friends4chat.message.friend.request.successfully.accepted",
            gray,
        this.friends4Chat.getPrefix(),
            friendName));
      }

      if (arguments[0].equals("deny")) {
        final String uuidString = arguments[1];
        UUID uuid;

        try {
          uuid = UUID.fromString(uuidString);
        } catch (Exception exception) {
          this.friends4Chat.displayMessage("");
          return true;
        }
        final LabyConnectSession session = Laby.labyAPI().labyConnect().getSession();
        final IncomingFriendRequest incomingRequest = Objects.requireNonNull(
                session)
            .getIncomingRequest(uuid);

        if (incomingRequest == null) {
          this.friends4Chat.displayMessage(Component.translatable("friends4chat.message.friend.request.not.found",
              gray,
              this.friends4Chat.getPrefix()
          ));
          return true;
        }

        session.declineFriendRequest(uuid);
        this.friends4Chat.displayMessage(Component.translatable("friends4chat.message.friend.request.successfully.deny",
            gray,
            this.friends4Chat.getPrefix()));
      }
    }
    return true;
  }
}