package me.d151l.friends.chat.command;

import me.d151l.friends.chat.Friends4Chat;
import net.labymod.api.Laby;
import net.labymod.api.client.chat.command.Command;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.TextColor;
import net.labymod.api.labyconnect.LabyConnectSession;
import net.labymod.api.labyconnect.protocol.model.friend.Friend;
import net.labymod.api.labyconnect.protocol.model.request.IncomingFriendRequest;
import net.labymod.api.user.GameUser;
import net.labymod.api.util.io.web.result.Result;
import net.labymod.api.util.io.web.result.ResultCallback;
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
          this.friends4Chat.displayMessage(Component.translatable("friends4chat.message.format.incorrect",
              gray,
              this.friends4Chat.getPrefix()
          ));
          return true;
        }

        if (!Laby.labyAPI().labyConnect().isConnected()) {
          this.friends4Chat.displayMessage(Component.translatable("friends4chat.message.not.connect.labymod",
              gray,
              this.friends4Chat.getPrefix()
          ));
          return true;
        }

        final LabyConnectSession session = Laby.labyAPI().labyConnect().getSession();
        final IncomingFriendRequest incomingRequest = Objects.requireNonNull(
                session)
            .getIncomingRequest(uuid);

        if (incomingRequest == null) {
          this.friends4Chat.displayMessage(Component.translatable("friends4chat.message.friend.requests.not.found",
              gray,
              this.friends4Chat.getPrefix()
          ));
          return true;
        }

        session.acceptFriendRequest(uuid);
        final GameUser friend = Laby.references().gameUserService().gameUser(uuid);
        Laby.labyAPI().labyNetController().loadNameByUniqueId(uuid, result -> {

          if (result.isPresent()) {
            final Component friendName = Component.text(result.get(), friend.visibleGroup().getTextColor());
            friends4Chat.displayMessage(Component.translatable("friends4chat.message.friend.requests.successfully.accepted",
                gray,
                friends4Chat.getPrefix(),
                friendName));
          } else {
            this.friends4Chat.displayMessage(Component.translatable("friends4chat.message.friend.requests.error",
                gray,
                friends4Chat.getPrefix()));
          }
        });
      }

      if (arguments[0].equals("deny")) {
        final String uuidString = arguments[1];
        UUID uuid;

        try {
          uuid = UUID.fromString(uuidString);
        } catch (Exception exception) {
          this.friends4Chat.displayMessage(Component.translatable("friends4chat.message.format.incorrect",
              gray,
              this.friends4Chat.getPrefix()
          ));
          return true;
        }

        if (!Laby.labyAPI().labyConnect().isConnected()) {
          this.friends4Chat.displayMessage(Component.translatable("friends4chat.message.not.connect.labymod",
              gray,
              this.friends4Chat.getPrefix()
          ));
          return true;
        }

        final LabyConnectSession session = Laby.labyAPI().labyConnect().getSession();
        final IncomingFriendRequest incomingRequest = Objects.requireNonNull(
                session)
            .getIncomingRequest(uuid);

        if (incomingRequest == null) {
          this.friends4Chat.displayMessage(Component.translatable("friends4chat.message.friend.requests.not.found",
              gray,
              this.friends4Chat.getPrefix()
          ));
          return true;
        }

        session.declineFriendRequest(uuid);
        this.friends4Chat.displayMessage(Component.translatable("friends4chat.message.friend.requests.successfully.deny",
            gray,
            this.friends4Chat.getPrefix()));
      }

      if (arguments[0].equals("openchat")) {
        final String uuidString = arguments[1];
        UUID uuid;

        try {
          uuid = UUID.fromString(uuidString);
        } catch (Exception exception) {
          this.friends4Chat.displayMessage(Component.translatable("friends4chat.message.format.incorrect",
              gray,
              this.friends4Chat.getPrefix()
          ));
          return true;
        }

        if (!Laby.labyAPI().labyConnect().isConnected()) {
          this.friends4Chat.displayMessage(Component.translatable("friends4chat.message.not.connect.labymod",
              gray,
              this.friends4Chat.getPrefix()
          ));
          return true;
        }

        final LabyConnectSession session = Laby.labyAPI().labyConnect().getSession();
        assert session != null;
        final Friend friend = session.getFriend(uuid);

        if (friend == null) {
          this.friends4Chat.displayMessage(Component.translatable("friends4chat.message.friend.chat.not.found",
              gray,
              this.friends4Chat.getPrefix()
          ));
          return true;
        }

        friend.chat().openChat();
      }
    }
    this.friends4Chat.displayMessage(Component.translatable("friends4chat.message.command.help",
        gray,
        this.friends4Chat.getPrefix()
    ));
    return true;
  }
}