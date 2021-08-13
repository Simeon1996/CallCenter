package base;

public enum Rank {
    Responder(0),
    Manager(1),
    Director(2);

    private int value;

    Rank(int val) {
        value = val;
    }

    public int getValue() {
        return value;
    }
}
