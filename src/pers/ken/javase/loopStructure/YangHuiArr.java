package pers.ken.javase.loopStructure;

public class YangHuiArr {
    public static void main(String[] args) {
    int[][] yangHui = new int[10][];
        for (int i = 0; i < yangHui.length; i++){
            yangHui[i] = new int[i+1];
        for (int j = 0; j < yangHui[i].length; j++) {
            if (j == 0 || j == i) {
                yangHui[i][j] = 1;
            } else {
                yangHui[i][j] = yangHui[i - 1][j - 1] + yangHui[i - 1][j];
            }
            System.out.print(yangHui[i][j]+" ");
            }
            System.out.println();
        }
    }
}
