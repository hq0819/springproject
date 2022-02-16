package chatServer.serverHandler;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

public class MessageLengthFieldBaseFrameDecoder  extends DelimiterBasedFrameDecoder//LengthFieldBasedFrameDecoder {
{
    public MessageLengthFieldBaseFrameDecoder(int maxFrameLength, ByteBuf delimiter) {
        super(maxFrameLength, delimiter);
    }
}


