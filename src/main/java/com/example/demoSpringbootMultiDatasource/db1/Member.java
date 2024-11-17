/*  ไม่สามารถสร้างฟิลด์ให้แสดงลำดับตามที่เขาประกาศใน Entity ได้
 *  อ้างอิงจาก https://stackoverflow.com/questions/46945898/how-to-create-table-column-in-a-particular-order-in-hibernate
 *  it looks like to be a Known issue and it is not possible 
 *  to set the order of the columns through Hibernate
 */
package com.example.demoSpringbootMultiDatasource.db1;

import org.hibernate.annotations.Comment;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Builder(toBuilder=true) //เพื่อให้สามารถสร้าง object ใหม่ได้จาก object เดิมด้วยคำสั่ง objOld.toBuilder().build(); เป็นการ clone มาเป็นตัวใหม่
@Accessors(chain=true)  //ทำให้ใช้คำสั่ง Member mm = new Member().setFirstName("xx").setLastName("kk").setMemberGrade("A"); ได้
@AllArgsConstructor //สร้าง method Constructor แบบมี Argument ทุก member
@NoArgsConstructor  //สร้าง method Constructor แบบไม่มี Argument 
@Data
@Entity()
@Table(name = "MEMBER")
public class Member {

	//@Comment("xx") จากการทดสอบจะทำตอนสร้างตารางครั้งแรกเท่านั้น   11/11/67
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Comment("รหัสสมาชิก")
	@Column(name = "id")
	private Long id;

	@Comment("ชื่อสมาชิก")
	@Column(name = "firstName", length = 255)
	private String firstName;

	@Comment("นามสกุลสมาชิก")
	@Column(name = "lastName", length = 255)
	private String lastName;
	
	@Comment("เกรดสมาชิก เช่น A,B,C,D,E")
	@Column(name = "memberGrade", length = 2)
	private String memberGrade;

	@Comment("วันที่เกิดสมาชิก")
	@Column(name = "birthDate")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "Asia/Bangkok")  //ให้แสดงในรุปแบบที่ต้องการตอนออกเป็น json
	private java.sql.Date birthDate;
	
	@Comment("วันที่สร้างรายการ")
	@Column(name = "createDate")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Bangkok")  //ให้แสดงในรุปแบบที่ต้องการตอนออกเป็น json
	private java.sql.Timestamp createDate;

}