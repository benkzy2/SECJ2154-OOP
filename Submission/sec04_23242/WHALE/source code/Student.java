public class Student extends User {
    // private attributes
    private String matricsNumber;
    private String major;
    private double CGPA;
    private Scholarship scholarship;
    private Programs prog;
    private StudentHistory studhist;

    // constructor
    public Student(String fn, String ln, int a, String em, Address add, String matricsNumber, String major, double CGPA,
            Scholarship scholarship, Programs prog, StudentHistory studhist) {
        super(fn, ln, a, em, add);
        this.matricsNumber = matricsNumber;
        this.major = major;
        this.CGPA = CGPA;
        this.scholarship = scholarship;
        this.prog = prog;
        this.studhist = studhist;
    }

    // getters and setters
    public void setMajor(String major) {
        this.major = major;
    }
    public void setCgpa(double CGPA) {

    }
    public String getMatricsNumber(){
        return matricsNumber;
    }
    public String getMajor(){
        return major;
    }
    public double getCgpa(){
        return CGPA;
    }

    @Override
    public String toString() {
        return "";
    }

}