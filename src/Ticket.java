import java.math.BigDecimal;
import java.util.Date;

public class Ticket {
    private BigDecimal cost;
    private String name;

    public int getAge() {
        return age;
    }

    private int age;

    public BigDecimal getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    private Date date;

    public Ticket(String name, int age, Date date) {
        this.name = name;
        this.age = age;
        this.date = date;
        if(age < 18){
            cost = new BigDecimal(40);
        }
        else{
            cost = new BigDecimal(70);
        }
    }
}
