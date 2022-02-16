package chatServer;

import chatServer.serverHandler.MessageHandlerCodec;
import chatServer.serverHandler.MessageLengthFieldBaseFrameDecoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;

public class ChatServer {
    private int port=7777;
    NioEventLoopGroup boss;
    NioEventLoopGroup worker;
    ServerBootstrap server;

    public ChatServer(){
        new ChatServer(7777);
    }

    public ChatServer(int port){
        this.port = port;
        this.boss =  new NioEventLoopGroup();
        this.worker =  new NioEventLoopGroup();
        this.server = new ServerBootstrap();
        this.server.channel(NioServerSocketChannel.class)
                    .group(boss,worker)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new LoggingHandler());
                           pipeline.addLast(new MessageLengthFieldBaseFrameDecoder(2048, Unpooled.copiedBuffer("##".getBytes())));
                           pipeline.addLast(new MessageHandlerCodec());
                        }
                    });
        try{
            ChannelFuture op = this.server.bind(7777).sync();
            op.addListener(future -> {
                System.out.println("服务器启动成功！");
            });
            op.channel().closeFuture().sync();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.boss.shutdownGracefully();
            this.worker.shutdownGracefully();
        }

    }


    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();


    }
}
