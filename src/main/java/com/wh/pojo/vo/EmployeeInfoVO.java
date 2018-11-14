package com.wh.pojo.vo;

import java.util.Date;

/**
 * @author shawn
 * @create 2018-11-13 13:55
 **/
public class EmployeeInfoVO {
    //工号
    private String employeeNo;
    //名字
    private String name;
    //性别
    private String sex;
    //身份证
    private String idCard;
    //学历
    private String education;
    //电话
    private String tel;
    //邮箱
    private String email;
    //部门
    private String  department;
    //直属上级
    private String superName;
    //职位
    private String position;
    //技术方向
    private String techDirec;
    //技术等级
    private String  techLev;
    //出生日期
    private Date gmtBitrh;
    //入职日期
    private Date gmtEntry;
    //爱好
    private String hobby;
    //人员职级 执行级。。
    private String  employeeRank;
    //雇员状态 正编
    private String employmentStatus;
    //在职状态 在职 离职
    private String  personStatus;

    public EmployeeInfoVO() {
    }

    public EmployeeInfoVO(String employeeNo, String name, String sex, String idCard, String education, String tel, String email, String department, String superName, String position, String techDirec, String techLev, Date gmtBitrh, Date gmtEntry, String hobby, String employeeRank, String employmentStatus, String personStatus) {
        this.employeeNo = employeeNo;
        this.name = name;
        this.sex = sex;
        this.idCard = idCard;
        this.education = education;
        this.tel = tel;
        this.email = email;
        this.department = department;
        this.superName = superName;
        this.position = position;
        this.techDirec = techDirec;
        this.techLev = techLev;
        this.gmtBitrh = gmtBitrh;
        this.gmtEntry = gmtEntry;
        this.hobby = hobby;
        this.employeeRank = employeeRank;
        this.employmentStatus = employmentStatus;
        this.personStatus = personStatus;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSuperName() {
        return superName;
    }

    public void setSuperName(String superName) {
        this.superName = superName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTechDirec() {
        return techDirec;
    }

    public void setTechDirec(String techDirec) {
        this.techDirec = techDirec;
    }

    public String getTechLev() {
        return techLev;
    }

    public void setTechLev(String techLev) {
        this.techLev = techLev;
    }

    public Date getGmtBitrh() {
        return gmtBitrh;
    }

    public void setGmtBitrh(Date gmtBitrh) {
        this.gmtBitrh = gmtBitrh;
    }

    public Date getGmtEntry() {
        return gmtEntry;
    }

    public void setGmtEntry(Date gmtEntry) {
        this.gmtEntry = gmtEntry;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getEmployeeRank() {
        return employeeRank;
    }

    public void setEmployeeRank(String employeeRank) {
        this.employeeRank = employeeRank;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public String getPersonStatus() {
        return personStatus;
    }

    public void setPersonStatus(String personStatus) {
        this.personStatus = personStatus;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EmployeeInfoVO{");
        sb.append("employeeNo='").append(employeeNo).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", sex='").append(sex).append('\'');
        sb.append(", idCard='").append(idCard).append('\'');
        sb.append(", education='").append(education).append('\'');
        sb.append(", tel='").append(tel).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", department='").append(department).append('\'');
        sb.append(", superName='").append(superName).append('\'');
        sb.append(", position='").append(position).append('\'');
        sb.append(", techDirec='").append(techDirec).append('\'');
        sb.append(", techLev='").append(techLev).append('\'');
        sb.append(", gmtBitrh=").append(gmtBitrh);
        sb.append(", gmtEntry=").append(gmtEntry);
        sb.append(", hobby='").append(hobby).append('\'');
        sb.append(", employeeRank='").append(employeeRank).append('\'');
        sb.append(", employmentStatus='").append(employmentStatus).append('\'');
        sb.append(", personStatus='").append(personStatus).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
