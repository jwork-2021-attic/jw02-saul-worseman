package S191220072;

public class Matrix {

    private Position[][] positions;
    private int row;
    private int col;
    public Matrix(int row, int col) {
        this.row = row;
        this.col = col;
        this.positions = new Position[this.row][this.col];
    }

    

    public void put(Linable linable, int i, int j) {
        if (this.positions[i][j] == null) {
            this.positions[i][j] = new Position(null);
        }
        this.positions[i][j].setLinable(linable);
    }

    public Linable get(int i, int j) {
        if ((i < 0) || (i > this.row)) {
            return null;
        }
        if ((j < 0) || (i > this.col)) {
            return null;
        } else {
            return positions[i][j].linable;
        }
    }

    class Position {

        private Linable linable;

        Position(Linable linable) {
            this.linable = linable;
        }

        public void setLinable(Linable linable) {
            this.linable = linable;
            linable.setPosition(this);
        }

    }

    // @Override
    // public String toString() {
    //     String lineString = "\t";
    //     for (Position p : positions) {
    //         lineString += p.linable.toString();
    //     }
    //     return lineString;
    // }

    @Override
    public String toString() {
        String matrixString = "";
        for (Position[] p : positions) {
            matrixString += "\t";
            for(Position q: p){
                matrixString += q.linable.toString(); 
                }
            matrixString += "\n";
            }
        // for (Position p : positions) {
        //     if(timer == 0)
        //         lineString += "\t";
        //     lineString += p.linable.toString();
        //     timer++;
        //     if(timer == 8){
        //         timer = 0;
        //         lineString += "\n";
        //     }
        // }
        return matrixString;
    }

    public Linable[] toArray() {
        Linable[] linables = new Linable[this.row * this.col];
        for (int i = 0; i < this.row; i++) {
            for(int j = 0; j < this.col; j++){
                linables[i * this.col + j] = positions[i][j].linable;
                //System.out.println(linables[i]);
            }
        }

        return linables;

    }

}
