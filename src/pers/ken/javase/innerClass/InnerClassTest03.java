package pers.ken.javase.innerClass;

import com.sun.org.apache.bcel.internal.classfile.InnerClass;

public class InnerClassTest03 {
    public static void main(String[] args) {
//        OutClass.InnerClass oi = new OutClass().new InnerClass();
//        OutClass.InnerClass oi = new OutClass.InnerClass();


        class OutClass {
            public OutClass() {
//                InnerClass innerClass = new InnerClass();
                class InnerClass {
                    public InnerClass() {
                        int i;
                        System.out.println(666);
                    }
                }
            }

        }
    }
}

