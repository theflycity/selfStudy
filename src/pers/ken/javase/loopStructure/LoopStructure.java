package pers.ken.javase.loopStructure;

/**
 * 测试循环
 * 测试一：for
 * 测试二：for的增强型（仅对数组和集合有效）
 * 测试三：doWhile
 * 测试四：while
 */
public class LoopStructure {
    public static void main(String[] args) {
        //for循环
        new LoopStructureTest().forCycle();
        //for增强型循环
        new LoopStructureTest().forArray();
        //doWhile循环
        new LoopStructureTest().doWhile();
        //while循环
        new LoopStructureTest().whileTest();
    }

}

class LoopStructureTest {
    void forCycle() {
        //for循环，for(变量声明；条件；循环体1){循环体2}循环体可以只写一个
        for (int high = 88444300, frequency = 0, i = 1; i < high; i = i * 2
        ) {
            frequency++;
            System.out.println(frequency);
        }
    }

    void forArray() {
        int[] arr = {1, 2, 3, 4};
        //增强型循环，for(数据类型 变量名 ：数组或者集合){}
        for (int i : arr) {
            System.out.println("i+1 = " + (i + 1));
        }
    }

    void doWhile() {
        int high = 88444300, frequency = 0, i = 1;
        //doWhile循环，do{循环体}while（条件）
        do {
            frequency++;
            i = i * 2;
        } while (i < high);
        System.out.println(frequency);
    }

    void whileTest() {
        int high = 88444300, frequency = 0, i = 1;
        //while()中不能声明变量
        while (i < high) {
            frequency++;
            i = i * 2;
            System.out.println(frequency);
        }
    }
}

