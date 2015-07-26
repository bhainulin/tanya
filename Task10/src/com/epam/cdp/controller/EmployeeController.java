package com.epam.cdp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.cdp.entity.Employee;
import com.epam.cdp.service.IEmployeeService;

@Controller
@RequestMapping("employee")
public class EmployeeController {

	@Autowired
	private IEmployeeService emService;

	@RequestMapping(method = RequestMethod.GET)
	public String getEmployee(Map<String, Object> map) {
		map.put("employee", new Employee());
		map.put("employeeList", emService.getEmployeeList());
		return "employee";
	}

	@RequestMapping("/")
	public String home() {
		return "redirect:/employee";
	}
	
	@RequestMapping("/create")
	public String create(Map<String, Object> map) {
		map.put("employee", new Employee());
		return "create";
	}
	
	@RequestMapping("/update/{employeeId}")
	public String redirectToUpdate(@PathVariable("employeeId") Integer employeeId,Map<String, Object> map) {
		map.put("employee", emService.getEmployee(employeeId));
		return "edit";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@ModelAttribute("employee") Employee employee, BindingResult result) {
		emService.editEmployee(employee);
		return "redirect:/employee/" + employee.getId();
	}

	@RequestMapping(value = "/{employeeId}", method = RequestMethod.DELETE)
	public String deleteEmployee(@PathVariable("employeeId") Integer employeeId) {
		emService.deleteEmployee(employeeId);
		return "redirect:/employee";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(Employee employee) {
		emService.addEmployee(employee);
		return "redirect:/employee/" + employee.getId();
	}

	@RequestMapping(value = "/{employeeId}", method = RequestMethod.GET)
	public String viewEmployee(@PathVariable("employeeId") Integer employeeId,
			Map<String, Object> map) {
		map.put("employeeView", emService.getEmployee(employeeId));
		return "view";
	}
}
