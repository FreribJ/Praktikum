import gmbh.kdb.hsw.gdp.domain.*;

public class SpecialProject extends Project {
    private Priority priority;

    public SpecialProject(ProjectName name, Skillset effort, Money reward, CompanyName customer, Day deadline, Priority priority) {
        super(name, effort, reward, customer, deadline);
        this.priority = priority;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
