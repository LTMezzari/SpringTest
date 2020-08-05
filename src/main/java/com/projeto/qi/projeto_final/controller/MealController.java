package com.projeto.qi.projeto_final.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.sql.rowset.serial.SerialBlob;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.lang.Nullable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.qi.projeto_final.model.Institution;
import com.projeto.qi.projeto_final.model.Meal;
import com.projeto.qi.projeto_final.model.MealImage;
import com.projeto.qi.projeto_final.model.Person;
import com.projeto.qi.projeto_final.service.MealService;
import com.projeto.qi.projeto_final.service.PersonService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MealController {

	@Autowired
	private MealService service;

	@Autowired
	private PersonService personsService;
	
	@Autowired
    public JavaMailSender emailSender;
	
	@GetMapping("/meal")
	public List<MealImage> getAll() {
		List<MealImage> meals = service.getAll();
		for (MealImage m : meals) {
			Blob image = m.getImage();
			try {
				String base64 = Base64.getEncoder().encodeToString(m.getImage().getBytes(1, (int) image.length()));
				m.setImageBase64(base64);
				m.setImage(null);
				image.free();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return meals;
	}
	
	@PostMapping("/meal")
	public MealImage save(@RequestBody @Valid MealImage mealImage) {
		byte[] image;
		Meal meal = save(mealImage.getMeal());
		mealImage.setMeal(meal);
		
		try {
			image = Base64.getDecoder().decode(new String(mealImage.getImageBase64()).getBytes("UTF-8"));
			mealImage.setImage(new SerialBlob(image));
		} catch (Exception e) {
			return null;
		}
		
		mealImage = service.save(mealImage);
		boolean wasSuccessful = mealImage != null;
		
		if (wasSuccessful) {
			notifyUsersForMeal(image, meal.getDescription(), meal.getInstitution(), meal.getMealDate());
		}
		return mealImage;
	}
	
	private Meal save(@Valid Meal meal) {
		meal.setRegistrationDate(new Date());
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date mealDate = format.parse(meal.getDate());
			meal.setMealDate(mealDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return service.save(meal);
	}
	
	private void notifyUsersForMeal(@Nullable byte[] imageArray, String description, Institution institution, Date mealDate) {
		List<Person> persons = personsService.getByInstitution(institution);
		File image = getImageFromBlob(imageArray);
		for (Person p : persons) {
			if (p.wantToReceiveMail())
				sendEmail(image, description, p, mealDate);
		}
	}
	
	private void sendEmail(@Nullable File image, String description, Person person, Date mealDate) {
		String date = "";
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			date = format.format(mealDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		MimeMessage message = mountEmail(image, description, person.getName(), person.getEmail(), date);
		emailSender.send(message);
	}
	
	@Nullable
	private File getImageFromBlob(@Nullable byte[] image) {
		File tempFile = null;
		if (image != null) try {
			tempFile = File.createTempFile("mealImage", "jpg", null);
			FileOutputStream fos = new FileOutputStream(tempFile);
			fos.write(image);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tempFile;
	}
	
	private MimeMessage mountEmail(@Nullable File image, String description, String name, String email, String date) {
		MimeMessage message = emailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			
			helper.setTo(email);
		    helper.setSubject(String.format("Nova Refeição - %s", description));
		    helper.setText(String.format("Aviso da refeição do dia %s: %s."
		    		+ "\nVenha conferir Sr.(a) %s.", date, description, name));
		    
		    if (image != null) {
		    	helper.addAttachment(String.format("%s.jpeg", description), new FileSystemResource(image));
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}
}
