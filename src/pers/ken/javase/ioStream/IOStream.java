package pers.ken.javase.ioStream;

import java.io.*;
import java.nio.charset.Charset;

/**
 * 目的一：测试字节流
 * 目的二：测试字符流
 * 目的三：测试打印流
 * 目的四：测试基本数据流
 * 目的五：异常的处理
 * 目的六：对象序列化
 * 需要手动释放资源
 */
public class IOStream {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //字节输出流
        new ByteStream().biteOutputStream();
        //字节输入流
        new ByteStream().biteInputStream();
        //文件复制
        new ByteStream().fileCopy();
        //字节缓冲流
        new ByteStream().biteBufferedStream();
        //字符输出流
        new CharacterStream().Write();
        //字符输入流
        new CharacterStream().Reader();
        //字符输出流便利类
        new CharacterStream().FileWrite();
        //字符输入流便利类
        new CharacterStream().FileRead();
        //字符缓冲流
        new CharacterStream().BufferedCharacterStream();
        //打印流
        new PrintStreamTest().printWriter();
        //基础数据流
        new DataStreamTest().dataStream();
        //异常处理
        new IOExceptionTreat().ioExceptionTreat();
        //
        new ObjectStreamTest().objectStreamTest();
    }
}

class ByteStream {
    File file = new File("C:/Temp/001.txt");

    void biteOutputStream() throws IOException {
        System.out.println("是否创建新文件 001.txt：" + file.createNewFile());
        //new调用，传入值为file或者string类型的路径
        //作用：创建字节输出流对象FileOutputStream
        FileOutputStream fos = new FileOutputStream(file);
        //对象调用，传入值为int，无返回值
        //作用：对调用对象写入对应int的byte值
        fos.write(48);
        fos.write(65);
        fos.write(97);
        byte[] bytes = {97, 98, 99, 100, 101, 102};
        //对象调用，传入值为byte数组，无返回值
        //作用：对调用对象写入byte数组
        fos.write(bytes);
        //对象调用，传入值为byte数组，无返回值
        //作用：对调用对象写入byte数组
        fos.write("你好，字节".getBytes(Charset.defaultCharset()));
        //对象调用，传入值为byte数组、开始索引、个数，无返回值
        //作用：对调用对象写入byte数组选中的部分
        fos.write(bytes, 1, 3);
        //new调用，传入值为file或者string类型的路径、boolean
        //作用：创建字节输出流对象FileOutputStream，第二个传入参数作用：追加写入
        FileOutputStream fos2 = new FileOutputStream("C:/Temp/001.txt", true);
        //对象调用，传入值为byte数组，无返回值
        //作用：换行
        fos2.write("\r\n".getBytes());
        fos2.write("结束，字节".getBytes());
        //对象调用，无传入值，无返回值
        //作用：释放资源
        fos.close();
    }

    void biteInputStream() throws IOException {
        //new调用，传入值为file或者string类型的路径
        //作用：创建字节输出流对象FileOutputStream
        FileInputStream fis = new FileInputStream(file);
        int i, j;
        //对象调用，无传入值，返回值int为数组长度
        //作用：读取单个字节或者字节数组，然后指针后移
        //注意中止的条件-1
        while ((i = fis.read()) != -1) {
            //注意这里的i不能写作fileInputStream.read()，原因：每次读取之后，指针自动后移一位
            //由于是一位一位读取，中文读取不出来
            System.out.print((char) i);
        }

        System.out.println();

        FileInputStream fis2 = new FileInputStream(file);
        byte[] bytes = new byte[1024];
        //对象调用，无传入值，返回值为int
        //作用：读取字节数组，然后指针后移
        //当用UTF-8时，大量的汉字几乎一定会有乱码，与数组总位数有关
        while ((j = fis2.read(bytes)) != -1) {
            System.out.print(new String(bytes, 0, j));
        }
        fis.close();
        fis2.close();
    }

    //文件拷贝
    void fileCopy() throws IOException {
        //创建字节输入流对象
        FileInputStream fis = new FileInputStream(file);
        //创建字节输出流对象
        FileOutputStream fos = new FileOutputStream("C:/Temp/002.txt");
        byte[] bytes = new byte[1024];
        int i;
        while ((i = fis.read(bytes)) != -1) {
            fos.write(bytes, 0, i);
        }
        fis.close();
        fos.close();
    }

    void biteBufferedStream() throws IOException {
        //创建字节输入流对象
        FileInputStream fis = new FileInputStream(file);
        //创建字节输出流对象
        FileOutputStream fos = new FileOutputStream("C:/Temp/002.txt");
        //new调用，传入值为字节输入流
        //作用：包装FileInputStream为BufferedInputStream，使其高效
        BufferedInputStream bis = new BufferedInputStream(fis);
        //new调用，传入值为字节输出流
        //作用：包装FileOutputStream为BufferedOutputStream，使其高效
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        byte[] bytes = new byte[1024];
        int i;
        while ((i = bis.read(bytes)) != -1) {
            bos.write(bytes, 0, i);
        }
        System.out.println();
        //只需要结束缓冲流就可以结束基础流
        bis.close();
        bos.close();
    }
}

class CharacterStream {
    File file = new File("C:/Temp/001.txt");

    void Write() throws IOException {
        System.out.println("是否创建新文件 001.txt：" + file.createNewFile());
        FileOutputStream fos = new FileOutputStream(file);
        //new调用，传入值为字节输出流、编码表
        //作用：包装FileOutputStream为OutputStreamWriter，变为字符输出流
        OutputStreamWriter osw = new OutputStreamWriter(fos, "gbk");
        //对象调用，传入值为String，无返回值
        osw.write("你好");
        //刷新调用对象流的缓冲（从内存写入文件）
        osw.flush();
        //释放资源
        osw.close();
    }

    void Reader() throws IOException {
        FileInputStream fis = new FileInputStream(file);
        //new调用，传入值为字节输入流、编码表
        //作用：包装FileInputStream为InputStreamReader，变为字符输入流
        InputStreamReader isr = new InputStreamReader(fis, "gbk");
        char[] chars = new char[1024];
        int i;
        //对象调用，传入值char[]，返回值为数组的长度int
        //作用：将调用对象的字符写入传入数组，返回数组的长度
        while ((i = isr.read(chars)) != -1) {
            //打印char[]
            System.out.println(new String(chars, 0, i));
        }
        isr.close();
    }

    void FileWrite() throws IOException {
        //new调用，传入值为String类的路径或file
        //作用：包装File为FileWriter，变为字符输出流的便利类
        FileWriter fileWriter = new FileWriter("C:/Temp/001.txt");
        fileWriter.write("你好，字节");
        fileWriter.close();
    }

    void FileRead() throws IOException {
        //new调用，传入值为String类的路径或file
        //作用：包装File为FileReader，变为字符输入流的便利类
        FileReader fileReader = new FileReader("C:/Temp/001.txt");
        char[] chars = new char[1024];
        int i;
        while ((i = fileReader.read(chars)) != -1) {
            //打印char[]
            System.out.println(new String(chars, 0, i));
        }
        fileReader.close();
    }

    void BufferedCharacterStream() throws IOException {
        FileWriter fileWriter = new FileWriter("C:/Temp/001.txt");
        //new调用，传入值为Writer
        //作用：包装Writer为BufferedWriter，使其高效
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("第一行");
        //对象调用，无传入值，无返回值
        //作用：换行
        bufferedWriter.newLine();
        bufferedWriter.write("第二行");
        bufferedWriter.flush();
        bufferedWriter.close();
        FileReader fileReader = new FileReader("C:/Temp/001.txt");
        //new调用，传入值为Reader
        //作用：包装Reader为BufferedReader，使其高效
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        //对象调用，无传入值，返回值为String
        //作用：读取一行的数据
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        bufferedReader.close();
    }
}

//打印流
class PrintStreamTest {
    void printWriter() throws IOException {
        FileWriter fileWriter = new FileWriter("C:/Temp/001.txt");
        //new调用，传入值为Write或OutputStream
        PrintWriter printWriter = new PrintWriter(fileWriter, true);
        //方便打印，原样输出
        printWriter.println("打印流");
        printWriter.close();
    }
}

class DataStreamTest {
    void dataStream() throws IOException {
        //new调用，传入值为OutputStream
        //作用：包装OutputStream为DataOutputStream，变为基本数据类型输出流
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("C:/Temp/001.txt"));
        //对象调用，传入值为相应类型，无返回值，
        //作用：写入基础数据类型
//        dos.writeChar(122);
        dos.write(48);
        dos.write(65);
        dos.write(90);
        dos.write(97);
        dos.write(122);
        dos.close();
        //new调用，传入值为InputStream
        //作用：包装InputStream为DataInputStream，变为基本数据类型输入流
        DataInputStream dis = new DataInputStream(new FileInputStream("C:/Temp/001.txt"));
        while (true) {
            try {
                //
                int i = dis.readByte();
                System.out.println("i = " + i);
            } catch (IOException e) {
//                e.printStackTrace();
                break;
            }
        }
        dis.close();
    }
}

class IOExceptionTreat {
    public void ioExceptionTreat() {
        FileOutputStream fos3 = null;
        FileOutputStream fos4 = null;
        try {
            fos3 = new FileOutputStream("C:/Temp/001.txt");
            //当输入对象不存在，write方法会报错，
//            fos4 = new FileOutputStream("q:/Temp/001.txt");
            fos3.write(48);
            fos3.write(65);
            fos3.write(97);
//            fos4.write(48);
//            fos4.write(65);
//            fos4.write(97);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos3 != null)
                    fos3.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //写第二个try是因为catch运行后，try不再运行
            try {
                if (fos4 != null)
                    fos4.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class ObjectStreamTest {
    void objectStreamTest() throws IOException, ClassNotFoundException {
        //new调用，传入值为OutputStream
        //作用：包装OutputStream为ObjectOutputStream，变为对象输出流
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:/Temp/001.txt"));
        Person 张三 = new Person("张三", 20);
        Person 李四 = new Person("李四", 20);
        //
        oos.writeObject(李四);
        oos.writeObject(张三);
        oos.close();
        //new调用，传入值为InputStream
        //作用：包装InputStream为ObjectInputStream，变为对象输入流
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:/Temp/001.txt"));
        while (true) {
            try {
                System.out.println(ois.readObject());
            } catch (EOFException e) {
                break;
            }
        }
        ois.close();
    }
}

//必须实现Serializable才能序列化
//静态和transient修饰的关键字不让序列化
class Person implements Serializable {
    private String name;
    private int age;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    //定义序列号
    static final long serialVersionUID = 42L;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}