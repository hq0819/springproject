package chatServer.message;

public enum MessageType {
    LOGINMESSAGE("login","登录"),
    CALLBACKMESSAGE("cback","回执消息");

    private String code;
    private String name;
    MessageType(String code,String name){
        this.name = name;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

     public static MessageType getEnumByCode(String code){
        for (MessageType me : MessageType.values()){
            if (code.equals(me.getCode())){
                return me;
            }
        }
         return null;
    }

}
