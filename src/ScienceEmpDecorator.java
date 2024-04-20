import java.math.BigDecimal;

public class ScienceEmpDecorator extends EmployeeDecorator{
    public ScienceEmpDecorator(Employee emp) {
        super(emp);
    }

    @Override
    public BigDecimal work(BigDecimal money) {
        super.work(money);
        System.out.println("He is discovering new types of Dinosaurs!");
        money = money.subtract(new BigDecimal(100));
        System.out.println("Remaining money after paying for work: " + money);
        return money;
    }
}
