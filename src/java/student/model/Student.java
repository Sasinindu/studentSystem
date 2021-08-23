package student.model;

import java.util.Date;

/**
 *
 * @author Sasinindu Tharaka
 */
public class Student {
    
    protected int id;
    protected int bcno;
    protected String name;
    protected Date dob;
    protected String gender;

    public Student() {}

    public Student(int bcno ,String name, Date dob, String gender) {
        super();
        
        this.bcno= bcno;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
    }

    public Student(int id, int bcno , String name, Date dob, String gender) {
        
        super();
        this.id = id;
        this.bcno=bcno;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public int getBcno() {
        return bcno;
    }
    public void setBcno(int bcno) {
        this.bcno = bcno;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
    
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    
}