package chatServer.message;

import lombok.Data;

@Data
public class SingleChatMessage extends Message{
    private String tagetUserId;
    @Override
    public MessageType messageType() {
        return MessageType.SINGLECHATMESSAGE;
    }
}
