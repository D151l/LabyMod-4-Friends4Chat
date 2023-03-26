package me.d151l.friends.chat;

import me.d151l.friends.chat.labymod.Friends4ChatAddon;

/**
 * @author D151l
 * @created 26/03/2023 - 16:22
 * @project labymod4-addon-template
 */
public class Friends4Chat {

  private static Friends4Chat instance;
  private final Friends4ChatAddon addon;

  public Friends4Chat(Friends4ChatAddon addon) {
    instance = this;

    this.addon = addon;
  }

  public static Friends4Chat getInstance() {
    return instance;
  }

  public Friends4ChatAddon getAddon() {
    return addon;
  }
}
