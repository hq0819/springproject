package chatServer.message;

public class LoginMessage extends Message{
    @Override
    public MessageType messageType() {
        return MessageType.LOGINMESSAGE;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
