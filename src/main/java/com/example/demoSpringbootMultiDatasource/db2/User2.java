package com.example.demoSpringbootMultiDatasource.db2;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Entity
@Table(name = "USER2")
public class User2 {

	@Id
	@Comment("รหัสผู้ใช้")
	@Column(name = "user_id")
	private int userid;

	@Comment("ชื่อผู้ใช้")
	@Column(name = "user_name", length = 255, nullable = false)
	private String username;

	@Comment("ผู้สร้างรายการ")
	@Column(name = "created_by", nullable = false)
	private String createdBy;

	@Comment("วันที่สร้างรายการ")
	@Column(name = "created_date")
	private java.time.OffsetDateTime createdDate;
	
	@Comment("วันที่ปรับปรุงรายการ")
	@Column(name = "update_date")
	private java.time.OffsetDateTime updateDate;
	
}
