import java.math.BigDecimal;

public class ServiceEmpDecorator extends EmployeeDecorator{
    public ServiceEmpDecorator(Employee emp) {
        super(emp);
    }

    @Override
    public BigDecimal work(BigDecimal money) {
        super.work(money);
        System.out.println("He is cleaning and take care of dinosaurs!");
        money = money.subtract(new BigDecimal(40));
        System.out.println("Remaining money after paying for work: " + money);
        return money;
    }
}
