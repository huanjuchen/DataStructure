package xyz.huanju.sparsearray;

import java.io.*;

/**
 * 稀疏数组
 * <p>
 * 格式如下：
 * row                col                 val
 * 0   原数组总行数       原数组的总列数        原数组的非0值个数
 * 1   v1RowIndex         v1ColIndex           v1
 * 2   v2RowIndex         v2ColIndex           v2
 * ....
 *
 * @author HuanJu
 */
public class SparseArray {
    /**
     * 原数组转稀疏数组
     */
    public static int[][] toSparseArray(int[][] source) {
        int[][] result = null;
        //非0数值的个数
        int sum = 0;
        //遍历原数组获取非0数值的个数
        for (int i = 0; i < source.length; i++) {
            for (int j = 0; j < source[i].length; j++) {
                if (source[i][j] != 0) {
                    sum++;
                }
            }
        }
        //创建稀疏数组
        result = new int[sum + 1][3];
        //记录原数组的属性
        result[0][0] = source.length;
        result[0][1] = source[1].length;
        result[0][2] = sum;
        //给稀疏数组赋值
        int count = 0;
        for (int i = 0; i < source.length; i++) {
            for (int j = 0; j < source[i].length; j++) {
                //将不为0的记录
                if (source[i][j] != 0) {
                    count++;
                    result[count][0] = i;
                    result[count][1] = j;
                    result[count][2] = source[i][j];
                }
            }
        }
        return result;
    }
    /**
     * 稀疏数组转二维数组
     */
    public static int[][] toTwoDimArray(int[][] spaArr) {
        //取第一行的数据构建二维数组
        int[][] result = new int[spaArr[0][0]][spaArr[0][1]];
        for (int i = 1; i < spaArr.length; i++) {
            result[spaArr[i][0]][spaArr[i][1]] = spaArr[i][2];
        }
        return result;
    }
    /**
     * 持久化到文件
     */
    public static void persistence(int[][] spaArr, String path) {
        OutputStream outputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            outputStream = new FileOutputStream(new File(path));
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(spaArr);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    /**
     * 文件到数组
     */
    public static int[][] fileToTwoDmiArr(String path) {
        int[][] result = null;

        InputStream is = null;
        ObjectInputStream ois = null;

        try {
            is = new FileInputStream(new File(path));
            ois = new ObjectInputStream(is);
            result = (int[][]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void main(String[] args) {
        //原二维数组
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[5][3] = 1;

        //遍历原二维数组
        System.out.println("原二维数组...");
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                System.out.printf("%d\t", chessArr1[i][j]);
            }
            System.out.println();
        }
        //获取稀疏数组
        int[][] spaArr = toSparseArray(chessArr1);
        //遍历稀疏数组
        System.out.println();
        System.out.println("稀疏数组...");
        for (int i = 0; i < spaArr.length; i++) {
            for (int j = 0; j < spaArr[i].length; j++) {
                System.out.printf("%d\t", spaArr[i][j]);
            }
            System.out.println();
        }
        persistence(spaArr,"C:/Users/HuanJu/Desktop/spaArr.data");
        //稀疏数组转二维数组
        int[][] chessArr2 = toTwoDimArray(spaArr);

        //遍历二维数组
        System.out.println();
        System.out.println("稀疏数组转的二维数组");
        for (int i = 0; i < chessArr2.length; i++) {
            for (int j = 0; j < chessArr2[i].length; j++) {
                System.out.printf("%d\t", chessArr2[i][j]);
            }
            System.out.println();
        }

        int[][] spaArr2 = fileToTwoDmiArr("C:/Users/HuanJu/Desktop/spaArr.data");

        //遍历二维数组
        System.out.println();
        System.out.println("从文件获取的稀疏数组");
        for (int i = 0; i < spaArr2.length; i++) {
            for (int j = 0; j < spaArr2[i].length; j++) {
                System.out.printf("%d\t", spaArr2[i][j]);
            }
            System.out.println();
        }
    }
}
