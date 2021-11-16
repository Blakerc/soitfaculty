package com.soit.soitfaculty;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.soit.soitfaculty.entity.Faculty;
import com.soit.soitfaculty.service.FacultyService;

@Controller
@RequestMapping("/Faculties")
public class FacultyController {
//
//	//upload faculty info
//	private List<Faculty> theFaculties;
//	
//	@PostConstruct
//	private void loadData() {
//		
//		
//		//create faculties
//		Faculty fac1 = new Faculty(1, "Kelly", "Miller", "kelly@uc.edu");
//		Faculty fac2 = new Faculty(2, "Robert", "Lee", "Robert@uc.edu");
//		Faculty fac3 = new Faculty(3, "laura", "West", "laura@uc.edu");
//		
//		//create list
//		theFaculties = new ArrayList<>();
//		
//		//Add or list 
//		theFaculties.add(fac1);
//		theFaculties.add(fac2);
//		theFaculties.add(fac3);
//		
//	}
	
	public FacultyService facultyService;
	
	public FacultyController(FacultyService theFacultyService) {
		facultyService = theFacultyService;
	}
	
	//mapping for /list
	@GetMapping("/List")
	public String listFaculties(Model theModel) {
		
		//Retrieve faculties from database
		List<Faculty> theFaculties = facultyService.findAll();
		
		
		//add faculties to the spring model
		theModel.addAttribute("faculties", theFaculties);
		
		return "faculties/list-faculties";
		
	}
	
	@GetMapping("/viewAddForm")
	public String viewAddForm(Model theModel) {
		
		//model attribute for data binding
		Faculty theFaculty = new Faculty();
		
		theModel.addAttribute("faculties", theFaculty);
		
		return "faculties/faculty-form";
	}
	
	@GetMapping("/viewUpdateForm")
	public String viewUpdateForm(@RequestParam("facultyId") int theId, Model theModel) {
		
		//Retrieve the faculty
		Faculty theFaculty = facultyService.findById(theId);
		
		//prepopulate the form by setting the caulty as a model attribute
		
		theModel.addAttribute("faculty", theFaculty);
		
		//redirect to form
		
		return "faculties/faculty-form";
		
	}
	
	@PostMapping("/save")
	public String saveFaculty(@ModelAttribute("faculty") Faculty theFaculty) {
		
		//register the faculty
		facultyService.save(theFaculty);
		
		//block duplicate submission for accidental page refresh
		return "redirect:/Faculties/List";
		
	}
	
	
	
	
}
