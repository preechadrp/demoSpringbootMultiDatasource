package com.example.demoSpringbootMultiDatasource.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoSpringbootMultiDatasource.db1.Member;
import com.example.demoSpringbootMultiDatasource.db1repos.MemberRepos;

@RestController
@RequestMapping("/member")
public class MemberRestController {

	@Autowired
	private MemberRepos memberRepos;
	
	@GetMapping(value = "/findAllTest")
	public List<Member> findAllTest() {
		
		List<Member> lstMember = new ArrayList<Member>();
		
		for (int n = 1; n <= 5; n++) {
		
			Member m1 = new Member()
					.setId(Long.valueOf(n))
					.setFirstName("ธีภพ" + n)
					.setLastName("ดรุณพันธ์" + n)
					.setBirthDate(java.sql.Date.valueOf("1979-01-02"))
					.setMemberGrade("A")
					.setCreateDate(new java.sql.Timestamp(System.currentTimeMillis()));
						
			lstMember.add(m1);
			
		}
		
		return lstMember;// ทดสอบผ่าน browser ระบบส่งออกเป็น json //ผ่าน 14/12/65
	}
	
	@GetMapping(value = "/findAll")
	public List<Member> findAll() {
		return memberRepos.findAll();// ทดสอบผ่าน browser ระบบส่งออกเป็น json //ผ่าน 14/12/65
	}
	
	@PostMapping(value = "/save")
	public Member save(@RequestBody Member member) {
		return memberRepos.save(member);// การ save ถ้าเจอ key เดิมจะเป็นการ update ถ้าไม่เจอจะเป็นการ insert
	}

	@DeleteMapping(value = "/deleteById/{id}")
	public void delete(@PathVariable Long id) {
		memberRepos.deleteById(id);
	}
}
