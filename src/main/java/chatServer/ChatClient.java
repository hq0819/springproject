package chatServer;

import chatServer.serverHandler.ClientInboundHandler;
import chatServer.serverHandler.MessageHandlerCodec;
import chatServer.serverHandler.MessageLengthFieldBaseFrameDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ChatClient {
    private int port = 7777;
    private EventLoopGroup worker;
    private Bootstrap client ;

    public ChatClient(){
        worker = new NioEventLoopGroup();
        client = new Bootstrap();
        client.channel(NioSocketChannel.class)
                .group(worker)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new MessageHandlerCodec());
                        pipeline.addLast(new MessageLengthFieldBaseFrameDecoder(2048, Unpooled.copiedBuffer("##".getBytes())));
                        pipeline.addLast(new ClientInboundHandler());
                    }
                });
        try{
            ChannelFuture localhost = client.connect("localhost", port).sync();
            System.out.println("客户端启动成功");
            localhost.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            worker.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        ChatClient chatClient= new ChatClient();
    }
}
