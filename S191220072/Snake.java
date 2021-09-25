package S191220072;

public class Snake {

    private static Snake theSnake;

    public static Snake getTheSnake() {
        if (theSnake == null) {
            theSnake = new Snake();
        }
        return theSnake;
    }

    private Snake() {

    }

    private Sorter sorter;

    public void setSorter(Sorter sorter) {
        this.sorter = sorter;
    }

    public String lineUp(Matrix matrix) {

        String log = new String();
        log = matrix.toString();
        log += "\n[frame]\n";
        
        if (sorter == null) {
            return null;
        }

        Linable[] linables = matrix.toArray();
        int[] ranks = new int[linables.length];
        //System.out.println(linables.length);
        for (int i = 0; i < linables.length; i++) {
            //System.out.println(linables[i]);
            ranks[i] = linables[i].getValue();
        }

        sorter.load(ranks);
        sorter.sort();

        String[] sortSteps = this.parsePlan(sorter.getPlan());

        for (String step : sortSteps) {
            this.execute(step);
            log += matrix.toString();
            log += "\n[frame]\n";
        }

        return log;

    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(String step) {
        String[] couple = step.split("<->");
        MonsterArray.getMonsterByColor(Integer.parseInt(couple[0]))
                .swapPosition(MonsterArray.getMonsterByColor(Integer.parseInt(couple[1])));
    }

}
