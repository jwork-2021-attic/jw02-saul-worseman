package S191220072;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class Scene {

    public static void main(String[] args) throws IOException {
        Line line = new Line(64);
        int i = 0;
        var it = MonsterArray.iterator();
        while(it.hasNext() && i < 64){
            line.put(it.next(),i);
            i++;
        }

        Snake theSnake = Snake.getTheSnake();

        Sorter sorter = new BubbleSorter();

        theSnake.setSorter(sorter);

        String log = theSnake.lineUp(line);

        BufferedWriter writer;
        writer = new BufferedWriter(new FileWriter("result.txt"));
        writer.write(log);
        writer.flush();
        writer.close();

    }

}
