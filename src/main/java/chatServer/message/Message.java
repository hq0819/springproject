package chatServer.message;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class Message implements Serializable {
    private String token;
    private int version;
    private int magicNum;
    private String content;

    public abstract MessageType messageType();

}
