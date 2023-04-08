package me.d151l.friends.chat.command;

import me.d151l.friends.chat.Friends4Chat;
import me.d151l.friends.chat.command.sub.AcceptCommand;
import me.d151l.friends.chat.command.sub.DenyCommand;
import me.d151l.friends.chat.command.sub.OpenChatCommand;
import net.labymod.api.Laby;
import net.labymod.api.client.chat.command.Command;
import net.labymod.api.client.component.format.TextColor;

/**
 * @author D151l
 * @created 26/03/2023 - 22:27
 * @project Friends4Chat
 */
public class LMFriendsCommand extends Command {

  private final Friends4Chat friends4Chat;

  private final OpenChatCommand openChatCommand;
  private final DenyCommand denyCommand;
  private final AcceptCommand acceptCommand;

  public LMFriendsCommand(final Friends4Chat friends4Chat) {
    super("lmfriends", "lmfriend");

    this.friends4Chat = friends4Chat;

    this.openChatCommand = new OpenChatCommand(this.friends4Chat);
    this.denyCommand =new DenyCommand(this.friends4Chat);
    this.acceptCommand = new AcceptCommand(this.friends4Chat);
  }

  @Override
  public boolean execute(String prefix, String[] arguments) {

    if (arguments.length == 2) {
      if (arguments[0].equals("server")) {
        Laby.labyAPI().serverController().joinServer(arguments[1]);

        return true;
      }

      if (arguments[0].equals("accept")) {
        final boolean successfully = this.acceptCommand.runCommand(arguments);

        return true;
      }

      if (arguments[0].equals("deny")) {
        final boolean successfully = this.denyCommand.runCommand(arguments);

        return true;
      }

      if (arguments[0].equals("openchat")) {
        final boolean successfully = this.openChatCommand.runCommand(arguments);

        return true;
      }
    }
    return false;
  }
}