package S191220072;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Scene {

    public static void main(String[] args) throws IOException {
        Matrix matrix = new Matrix(8,8);
        var it = MonsterArray.iterator();
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++){
                if(it.hasNext())
                    matrix.put(it.next(),i,j);
            }

        Snake theSnake = Snake.getTheSnake();

        Sorter sorter = new QuickSorter();

        theSnake.setSorter(sorter);

        String log = theSnake.lineUp(matrix);

        BufferedWriter writer;
        writer = new BufferedWriter(new FileWriter("result.txt"));
        writer.write(log);
        writer.flush();
        writer.close();

    }

}
