package primitives;

public final class Coordinate {
    final double _coord;

    public Coordinate(double coord) {
        this._coord = Util.alignZero(coord);
    }

    public Coordinate(Coordinate other) {
        this._coord = other._coord;
    }

    public double get() {
        return this._coord;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else {
            return !(obj instanceof Coordinate) ? false : Util.isZero(this._coord - ((Coordinate)obj)._coord);
        }
    }

    public String toString() {
        return this._coord.makeConcatWithConstants<invokedynamic>(this._coord);
    }
}
