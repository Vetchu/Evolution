
public class Position  {
    final int x;
    final int y;

    public Position(int x, int y) {
        this.x=x;
        this.y=y;

    }

    public Position(Position other) {
        this.x=other.x;
        this.y=other.y;
    }

    @Override
    public String toString() {
        return "("+this.x+","+this.y+")";
    }
    public Position add(Position second){
        return new Position(second.x+this.x,second.y+this.y);
    }

    public boolean equals(Object other) {
        if (other == this) return true;
        if (!(other instanceof Position)) return false;
        return (this.hashCode()==other.hashCode());
    }

    @Override
    public int hashCode() {
        int hash = 13;
        hash += this.x * 31;
        hash += this.y * 17;
        return hash;
    }
}
