package base;

public class Director extends Employee {

    public Director(CallCenter center) {
        super(center);
        rank = Rank.Director;
    }
}
