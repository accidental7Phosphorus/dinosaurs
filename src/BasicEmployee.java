import java.math.BigDecimal;

public class BasicEmployee implements Employee{
    private int experience;

    public BasicEmployee(int experience, String name, Jobs job) {
        this.experience = experience;
        this.name = name;
        this.job = job;
    }

    private String name;
    private Jobs job;

    public BasicEmployee(BasicEmployee employee) {
        this.experience = employee.experience;
        this.name = employee.name;
        this.job = employee.job;
    }

    public Jobs getJob() {
        return job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public BasicEmployee(int experience, Jobs job, String name) {
        this.experience = experience;
        this.job = job;
        this.name = name;
    }

    @Override
    public BigDecimal work(BigDecimal money) {
        System.out.println(this.job.name() + " is working.");
        return null;
    }
}
