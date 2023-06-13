package com.jblog.vo;

public class BlogVo {
	
	private String id;
	private String blogtitle;
	private String logofile;
	
	public BlogVo(String id, String blogtitle, String logofile) {
		this.id = id;
		this.blogtitle = blogtitle;
		this.logofile = logofile;
	}

	public BlogVo() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBlogtitle() {
		return blogtitle;
	}

	public void setBlogtitle(String blogtitle) {
		this.blogtitle = blogtitle;
	}

	public String getLogofile() {
		return logofile;
	}

	public void setLogofile(String logofile) {
		this.logofile = logofile;
	}

	@Override
	public String toString() {
		return "BlogVo [id=" + id + ", blogtitle=" + blogtitle + ", logofile=" + logofile + "]";
	}
	
	
}
