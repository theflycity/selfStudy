package pers.ken.javase.networkProgramming;

import java.io.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 测试客户端
 */
public class Client {
    public static void main(String[] args) throws IOException {
        //传输字节
//        new ByteClient().startByteClient();
        //传输文件
        new FileClient().startFileClient();
    }
}

class ByteClient{
    void startByteClient() throws IOException {
        //new调用，传入值为服务器地址、服务器端口
        //作用：创建一个流套接字Socket，连接服务器对象
        Socket socket = new Socket("127.0.0.1", 8888);//localhost
        //对象调用，无传入值，返回值为OutputStream
        //作用：创建字节输出流
        OutputStream out = socket.getOutputStream();
        //注意：建立套接字连接后，可以使用抽象类OutputStream的write方法
        out.write("你好，服务器".getBytes());
        //对象调用，无传入值，返回值为InputStream
        //作用：创建字节输入流
        InputStream in = socket.getInputStream();
        byte[] bytes = new byte[1024];
        //注意：建立套接字连接后，可以使用抽象类InputStream的read方法
        System.out.println(new String(bytes, 0, in.read(bytes)));
        socket.close();
    }
}

class FileClient {
    void startFileClient() throws IOException {
        Socket socket = new Socket("127.0.0.1", 8888);
        FileInputStream fis = new FileInputStream("C:/Temp/client/001.jpg");
        byte[] bytes = new byte[1024];
        int r ;
        OutputStream out = socket.getOutputStream();
        //一边读取文件，一边写文件
        while ((r = fis.read(bytes)) != -1) {
            out.write(bytes,0,r);
        }
        //对象调用，无传入值，无返回值
        //作用：向服务器发送一个TPC的终止序列符号，终止调用对象的字节输出流
        socket.shutdownOutput();
        //获取反馈
        InputStream in = socket.getInputStream();
        //r与bytes的复用
        r = in.read(bytes);
        System.out.println("服务器返回内容：：" + new String(bytes,0,r));
        fis.close();
        socket.close();
    }
}
