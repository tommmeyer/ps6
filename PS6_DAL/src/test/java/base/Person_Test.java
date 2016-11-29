package base;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person_Test {
		
	private static PersonDomainModel person1;
	private static UUID person1UUID = UUID.randomUUID();	
	private static PersonDomainModel person2;
	private static UUID person2UUID = UUID.randomUUID();
	private static PersonDomainModel person3;
	private static UUID person3UUID = UUID.randomUUID();
	
	@BeforeClass
	public static void personInstance() throws Exception{
		
		Date person1Birth = null;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		 person1 = new PersonDomainModel();
		 
		try {
			person1Birth = dateFormat.parse("1994-11-27");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		person1.setPersonID(person1UUID);
		person1.setFirstName("Mingkun");
		person1.setMiddleName("a");
		person1.setLastName("Chen");
		person1.setBirthday(person1Birth);
		person1.setCity("Elkton");
		person1.setStreet("702 Stone Gate Blvd");
		person1.setPostalCode(21921);
		
		Date person2Birth = null;

		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		
		 person2 = new PersonDomainModel();
		 
		try {
			person2Birth = dateFormat2.parse("1994-11-27");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		person2.setPersonID(person1UUID);
		person2.setFirstName("Doctor");
		person2.setMiddleName("b");
		person2.setLastName("Evil");
		person2.setBirthday(person1Birth);
		person2.setCity("Brussels");
		person2.setStreet("702 Viacom Drive");
		person2.setPostalCode(21921);
		
		
		PersonDAL.addPerson(person1);
		PersonDAL.addPerson(person2);
		
	}
	@Test
	public void DeleteTest(){
		assertEquals(PersonDAL.getPerson(person1UUID).getPersonID(), person1UUID);
		PersonDAL.deletePerson(person1UUID);
		assertNull(PersonDAL.getPerson(person1UUID));
	}
	@Test
	public void CreateTest() throws Exception{
		assertNull(PersonDAL.getPerson(person3UUID));
		Date person3Birth = null;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		 person3 = new PersonDomainModel();
		 
		try {
			person3Birth = dateFormat.parse("1994-11-27");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		person3.setPersonID(person3UUID);
		person3.setFirstName("Austin");
		person3.setMiddleName("Danger");
		person3.setLastName("Powers");
		person3.setBirthday(person3Birth);
		person3.setCity("London");
		person3.setStreet("702 Downtown Abbey");
		person3.setPostalCode(21921);
		
		PersonDAL.addPerson(person1);
		assertEquals(PersonDAL.getPerson(person3UUID).getPersonID(), person3UUID);
	}
	@Test
	public void UpdateTest(){
		assertEquals(PersonDAL.getPerson(person2UUID).getPostalCode(), 21921);
		PersonDAL.updatePerson(person2).setPostalCode(19810);;
		assertEquals(PersonDAL.getPerson(person2UUID).getPostalCode(), 19810);
	}
	

}
