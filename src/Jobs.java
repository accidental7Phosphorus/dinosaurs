public enum Jobs {
    JANITOR(100),
    MANAGER(200),
    CARETAKER(180),
    VETERINARIAN(320),
    DIRECTOR(2000),
    RESEARCHER(400);

    private float salary;
    Jobs(float salary){
        this.salary = salary;
    }
    public float getSalary(){
        return salary;
    }

    public Jobs distributeJob(int exp){
        if(exp <= 1){
            return JANITOR;
        }
        else if(exp <= 3){
            return CARETAKER;
        }
        else if(exp <= 5){
            return MANAGER;
        }
        else if(exp <= 7){
            return VETERINARIAN;
        }
        else {
            return RESEARCHER;
        }
    }
}