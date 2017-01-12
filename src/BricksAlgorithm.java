import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BricksAlgorithm {
    public static void main(String args[]) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            br.readLine();
            System.out.println("PONG");
            size = Integer.parseInt(br.readLine());
            board = new int[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    board[j][i] = 0;
                }
            }
            Boolean cont = false;
            for (int xx = 0; xx < size; xx++) {
                for (int yy = 0; yy < size; yy = yy + 3) {
                    if (xx % 2 != 0) {
                        if (yy == 0)
                            yy++;
                    }
                    if (!cont) {
                        String inputText = br.readLine();
                        String zaczynaj = "ZACZYNAJ";
                        int previousx1 = 0;
                        int previousy1 = 0;
                        int previousx2 = 0;
                        int previousy2 = 0;

                        if (!inputText.equals(zaczynaj)) {
                            previousx1 = Integer.parseInt(inputText.split(" ")[0]) - 1;
                            previousy1 = Integer.parseInt(inputText.split(" ")[1]) - 1;
                            previousx2 = Integer.parseInt(inputText.split(" ")[2]) - 1;
                            previousy2 = Integer.parseInt(inputText.split(" ")[3]) - 1;
                            board[previousx1][previousy1] = 1;
                            board[previousx2][previousy2] = 1;
                        }
                    }
                    int x1 = -1;
                    int y1 = -1;
                    int x2 = -1;
                    int y2 = -1;
                    int x,y,direction;
                    int[] t = moves(xx, yy);
                    if (t[2] < size) {
                        x = t[0];
                        y = t[1];
                        direction = t[2];
                        cont = false;
                    } else {
                        cont = true;
                        continue;
                    }
                    if (direction == 3)
                        yy--;
                    while (true) {
                        boolean[] directions = possibleDirections(x, y);
                        if (board[x][y] == 0) {
                            if (direction == 0 && directions[0]) {
                                x1 = x;
                                y1 = y;
                                x2 = x;
                                y2 = y - 1;
                                break;
                            }
                            if (direction == 1 && directions[1]) {
                                x1 = x;
                                y1 = y;
                                x2 = x + 1;
                                y2 = y;
                                break;
                            }
                            if (direction == 2 && directions[2]) {
                                x1 = x;
                                y1 = y;
                                x2 = x;
                                y2 = y + 1;
                                break;
                            }
                            if (direction == 3 && directions[3]) {
                                x1 = x;
                                y1 = y;
                                x2 = x - 1;
                                y2 = y;
                                break;
                            }
                        }
                    }
                    board[x1][y1] = 1;
                    board[x2][y2] = 1;
                    x1 += 1;
                    y1 += 1;
                    x2 += 1;
                    y2 += 1;
                    System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
                }
            }
        }
        catch (Exception e) {

        }
    }
    private static int[] moves (int xx, int yy) {
        int []m = new int[3]; //x,y,direction
        if (board[xx][yy] != 0) {
            for (int i = yy; i < size; i++) {
                if (board[xx][i] == 0) {
                    yy = i;
                    break;
                }
                if (board[xx][i] != 0 && i == size -1) {
                    yy = size - 1;
                    break;
                }
            }
        }
        if (board[xx][yy] == 0) {
            if (yy + 1 != size) {
                if (board[xx][yy + 1] == 0) {
                    m[0] = xx;
                    m[1] = yy;
                    m[2] = 2;
                    return m;
                }
            }
            if (yy != 0)
                if (board[xx][yy - 1] == 0) {
                    m[0] = xx;
                    m[1] = yy;
                    m[2] = 0;
                    return m;
                }
            if (xx != 0)
                if (board[xx - 1][yy] == 0) {
                    m[0] = xx;
                    m[1] = yy;
                    m[2] = 3;
                    return m;
                }

        }
        m[2] = size;
        return m;
    }
    private static boolean[] possibleDirections(int x, int y) {
        boolean result[] = new boolean[4];
        result[0] = false;
        result[1] = false;
        result[2] = false;
        result[3] = false;
        try {
            if (board[x][y-1] == 0) result[0] = true;
        } catch (ArrayIndexOutOfBoundsException e) {
            result[0] = false;
        }

        try {
            if (board[x+1][y] == 0) result[1] = true;
        } catch (ArrayIndexOutOfBoundsException e) {
            result[1] = false;
        }

        try {
            if (board[x][y+1] == 0) result[2] = true;
        } catch (ArrayIndexOutOfBoundsException e) {
            result[2] = false;
        }

        try {
            if (board[x-1][y] == 0) result[3] = true;
        } catch (ArrayIndexOutOfBoundsException e) {
            result[3] = false;
        }

        return result;
    }
    private static int[][] board;
    private static int size;
}
