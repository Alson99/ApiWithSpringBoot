package com.filsalouna.springjdbcdemo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.filsalouna.springjdbcdemo.model.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate; 
	
	@Override
	public int save(Employee employee) {
		return jdbcTemplate.update("INSERT INTO tbl_employees (fullname, email, department) VALUES(?, ?, ?)", new Object[] {employee.getFullname(), employee.getEmail(), employee.getDepartment()});
	}

	@Override
	public int update(Employee employee, int id) {
		return jdbcTemplate.update("UPDATE tbl_employees SET fullname = ?, email = ?, department = ? WHERE id = ?", new Object[] {employee.getFullname(), employee.getEmail(), employee.getDepartment(), id});
	}

	@Override
	public int delete(int id) {
		return jdbcTemplate.update("DELETE FROM tbl_employees WHERE id = ?", id);
	}

	@Override
	public List<Employee> getAll() {
		return jdbcTemplate.query("SELECT * FROM tbl_employees", new BeanPropertyRowMapper<Employee>(Employee.class));
	}

	@Override
	public Employee getById(int id) {
		return jdbcTemplate.queryForObject("SELECT * FROM tbl_employees WHERE id=?", new BeanPropertyRowMapper<Employee>(Employee.class), id);
	}

}
