package network.tcp.chat.server.command;

import network.tcp.chat.server.Session;

import java.io.IOException;

public interface Command {
    void execute(String[] args, Session session) throws IOException;
}
