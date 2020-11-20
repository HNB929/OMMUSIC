package com.music.bean;

import java.util.List;

public class Singer {
    private Integer singerId;
    private String singerName;
    private String alias;
    private String language;
    private String birthday;
    private Integer height;
    private String sex;
    private String nation;
    private String birthplace;
    private String constellation;
    private Integer weight;
    private String picture;
    private Integer singerTypeid;
    private String job;
    private String company;
    private String representative;
    private String school;
    private String firstname;
    private String remark;  
    private SingerType singerType;
    //对应歌手的全部歌曲
    private List<Song> songList;
  

    public List<Song> getSongList() {
		return songList;
	}

	public void setSongList(List<Song> songList) {
		this.songList = songList;
	}




	@Override
	public String toString() {
		return "Singer [singerId=" + singerId + ", singerName=" + singerName + ", alias=" + alias + ", language="
				+ language + ", birthday=" + birthday + ", height=" + height + ", sex=" + sex + ", nation=" + nation
				+ ", birthplace=" + birthplace + ", constellation=" + constellation + ", weight=" + weight
				+ ", picture=" + picture + ", singerTypeid=" + singerTypeid + ", job=" + job + ", company=" + company
				+ ", representative=" + representative + ", school=" + school + ", firstname=" + firstname + ", remark="
				+ remark + ", singerType=" + singerType + ", songList=" + songList + "]";
	}

	public SingerType getSingerType() {
		return singerType;
	}

	public void setSingerType(SingerType singerType) {
		this.singerType = singerType;
	}



    public Integer getSingerId() {
		return singerId;
	}

	public void setSingerId(Integer singerId) {
		this.singerId = singerId;
	}

	public String getSingerName() {
		return singerName;
	}

	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}

	public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
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
        this.sex = sex == null ? null : sex.trim();
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace == null ? null : birthplace.trim();
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation == null ? null : constellation.trim();
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public Integer getSingerTypeid() {
        return singerTypeid;
    }

    public void setSingerTypeid(Integer singerTypeid) {
        this.singerTypeid = singerTypeid;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative == null ? null : representative.trim();
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname == null ? null : firstname.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}