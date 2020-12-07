package com.reader.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.reader.model.ArnatiSteelLimited;
import com.reader.serviceImpl.ExcelReaderServiceImpl;

@Controller
public class ExcelReaderController {
	
	@Autowired private ExcelReaderServiceImpl excelReaderServiceImpl;
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("arnatiSteel", new ArnatiSteelLimited());
		List<ArnatiSteelLimited> listofTools=excelReaderServiceImpl.findAll();
		model.addAttribute("listofTools",listofTools);
		return "view/Display";
		}
	@PostMapping("/fileupload")
public String uploadFile(@ModelAttribute ArnatiSteelLimited arnatiSteelLimited,RedirectAttributes attributes) {
	boolean isFlag=excelReaderServiceImpl.saveDataFromUploadFile(arnatiSteelLimited.getFile());
	if(isFlag) {
		attributes.addFlashAttribute("successmessage", "File Upload Successfully");
	}else {
		attributes.addFlashAttribute("errormessage", "File Upload not Successfully...Please try again !");
	}
	return "redirect:/";
	
}
}
