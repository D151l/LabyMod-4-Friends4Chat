package me.d151l.friends.chat.command;

import me.d151l.friends.chat.Friends4Chat;
import net.labymod.api.Laby;
import net.labymod.api.client.chat.command.Command;

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
    if (arguments.length == 2) {
      if (arguments[0].equals("server")) {
        Laby.labyAPI().serverController().joinServer(arguments[1]);
      }
    }
    return true;
  }
}