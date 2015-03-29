package com.internetadvert.edu.Controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PDFController {

	@RequestMapping(value = "/report.pdf", method = RequestMethod.GET)
	public ModelAndView doProcess(Locale locale, ModelMap model) {
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		
		
		
		model.addAttribute("time", formattedDate );
		
		return new ModelAndView(new MyPdfView(),model);
	}
}
