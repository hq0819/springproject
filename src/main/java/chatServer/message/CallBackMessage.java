package chatServer.message;

public class CallBackMessage extends Message{
    @Override
    public MessageType messageType() {
        return MessageType.CALLBACKMESSAGE;
    }
}
