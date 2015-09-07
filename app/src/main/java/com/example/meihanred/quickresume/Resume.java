package com.example.meihanred.quickresume;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by meihanred on 4/12/15.
 */
public class Resume implements Serializable {
    private String filename;
    public String name;
    private int age;
    private String career;
    private String degree;
    private String email;
    private String phone;
    private int style;
    private ArrayList<String> experiencetitle;
    private ArrayList<String> experiencedetail;
    private ArrayList<String> educationtitle;
    private ArrayList<String> educationdetail;

    public ArrayList<String> getExperiencetitle() {
        return experiencetitle;
    }

    public ArrayList<String> getExperiencedetail() {
        return experiencedetail;
    }

    public void setExperiencetitle(ArrayList<String> experiencetitle) {

        this.experiencetitle = experiencetitle;
    }

    public void setExperiencedetail(ArrayList<String> experiencedetail) {
        this.experiencedetail = experiencedetail;
    }
    public void setEducationtitle(ArrayList<String> educationtitle) {
        this.educationtitle = educationtitle;
    }

    public void setEducationdetail(ArrayList<String> educationdetail) {
        this.educationdetail = educationdetail;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public String getFilename() {
        return filename;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCareer() {
        return career;
    }

    public String getDegree() {
        return degree;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getStyle() {
        return style;
    }


    public  Resume(){
        this.filename = "";
        this.name = "";
        this. age = 0;
        this.career = "";
        this.degree= "";
        this.email= "";
        this.phone= "";
        this.style = 0;
        this.experiencetitle = new ArrayList<String>();
        this.educationtitle = new ArrayList<String>();
        this.experiencedetail = new ArrayList<String>();
        this.educationdetail = new ArrayList<String>();
    };



    public ArrayList<String> getEducationdetail() {

        return educationdetail;
    }

    public ArrayList<String> getEducationtitle() {

        return educationtitle;
    }

    public Resume(String filename, String name, int age, String career, String degree, String email, String phone, int style, ArrayList<String> experiencetitle, ArrayList<String> experiencedetail, ArrayList<String> educationtitle, ArrayList<String> educationdetail) {
        this.filename = filename;
        this.name = name;
        this.age = age;
        this.career = career;
        this.degree = degree;
        this.email = email;
        this.phone = phone;
        this.style = style;
        this.experiencetitle = experiencetitle;
        this.experiencedetail = experiencedetail;
        this.educationtitle = educationtitle;
        this.educationdetail = educationdetail;

    }
}
