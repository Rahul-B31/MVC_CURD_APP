package in.ineuron.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student2")
public class Student implements Serializable{


	private static final long serialVersionUID = 1L;

	public Student()
	{
		System.out.println("The Zero Arg Constructor Used By the Hibernate");
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="sid")
	private Integer sid;
	@Column(name="sage")
	private Integer sage;
	@Column(name="sname")
	private String sname;
	@Column(name="saddr")
	private String saddr;

	@Override
	public String toString() {
		return "Student [sid=" + sid + ", sage=" + sage + ", sname=" + sname + ", saddr=" + saddr + "]";
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getSage() {
		return sage;
	}
	public void setSage(Integer sage) {
		this.sage = sage;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSaddr() {
		return saddr;
	}
	public void setSaddr(String saddr) {
		this.saddr = saddr;
	}


}
