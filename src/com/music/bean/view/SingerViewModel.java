package com.music.bean.view;

public class SingerViewModel {
	/** 歌手编号 */
    private Integer id;
    /** 歌手姓名 */
    private String name;
    /** 歌手别名 */
    private String alias;
    /** 语言 */
    private String language;
    /** 生日 */
    private String birthday;
    /** 身高（cm） */
    private Integer height;
    /** 性别 */
    private String sex;
    /** 国籍 */
    private String nation;
    /** 出生地 */
    private String birthplace;
    /** 星座 */
    private String constellation;
    /** 体重kg */
    private Integer weight;
    /** 图片地址 */
    private String picture;
    /** 类型编号 */
    private Integer singer_typeid;
    /** 职业 */
    private String job;
    /** 公司 */
    private String company;
    /** 代表作 */
    private String representative;
    /** 毕业院校 */
    private String school;
    /** 首字母 */
    private String firstname;
    /** 备注 */
    private String remark;
    
    /** 歌手类别名称 */
    private String singerTypeName;
    
    
    
	public String getSingerTypeName() {
		return singerTypeName;
	}
	public void setSingerTypeName(String singerTypeName) {
		this.singerTypeName = singerTypeName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getBirthplace() {
		return birthplace;
	}
	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}
	public String getConstellation() {
		return constellation;
	}
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public Integer getSinger_typeid() {
		return singer_typeid;
	}
	public void setSinger_typeid(Integer singer_typeid) {
		this.singer_typeid = singer_typeid;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getRepresentative() {
		return representative;
	}
	public void setRepresentative(String representative) {
		this.representative = representative;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "SingerViewModel [id=" + id + ", name=" + name + ", alias=" + alias + ", language=" + language
				+ ", birthday=" + birthday + ", height=" + height + ", sex=" + sex + ", nation=" + nation
				+ ", birthplace=" + birthplace + ", constellation=" + constellation + ", weight=" + weight
				+ ", picture=" + picture + ", singerTypeid=" + singer_typeid + ", job=" + job + ", company=" + company
				+ ", representative=" + representative + ", school=" + school + ", firstname=" + firstname + ", remark="
				+ remark + ", singerTypeName=" + singerTypeName + "]";
	}

	
    
    
}