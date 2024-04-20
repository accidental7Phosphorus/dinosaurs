import java.math.BigDecimal;

public abstract class EmployeeDecorator implements Employee{

    protected Employee emp;
    public EmployeeDecorator(Employee emp) {
        this.emp = emp;
    }
    @Override
    public BigDecimal work(BigDecimal money) {
        return this.emp.work(money);
    }
}
