package S191220072;

import S191220072.Matrix.Position;

public class Monster implements Linable {

    private final int r;
    private final int g;
    private final int b;

    private Position position;

    Monster(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int color() {
        return this.r + this.g + this.b;
    }

    @Override
    public String toString() {
        return "\033[48;2;" + this.r + ";" + this.g + ";" + this.b + ";38;2;0;0;0m    " + this.color() + "  \033[0m";
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    public void swapPosition(Monster another) {
        Position p = another.position;
        this.position.setLinable(another);
        p.setLinable(this);
    }

    @Override
    public int getValue() {
        return this.color();
        
    }

}

