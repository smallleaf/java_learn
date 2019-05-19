package com.share1024.serialization.effecttest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2019-05-19
 */
public class UserInfo implements Serializable {


    private static final long serialVersionUID = 1L;

    private String userName;

    private int userId;


    public UserInfo(String userName, int userId) {
        this.userName = userName;
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public byte[] code(){
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        byte[] value = this.userName.getBytes();
        buffer.putInt(value.length);
        buffer.put(value);

        buffer.putInt(this.userId);
        buffer.flip();
        value = null;
        byte[] result = new byte[buffer.remaining()];
        buffer.get(result);
        return result;

    }

    public static void main(String[] args) throws IOException {
        UserInfo userInfo = new UserInfo("yesheng",123456);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(userInfo);

        os.flush();
        os.close();

        byte[] b = bos.toByteArray();

        System.out.println("====="+b.length);

        bos.close();

        System.out.println(userInfo.code().length);

    }
}
