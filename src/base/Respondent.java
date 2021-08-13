package base;

public class Respondent extends Employee {

    public Respondent(CallCenter center) {
        super(center);
        rank = Rank.Responder;
    }
}
