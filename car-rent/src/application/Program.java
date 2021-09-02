package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import application.entities.CarRental;
import application.entities.Vehicle;
import application.services.BrasilTaxServices;
import application.services.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");

		System.out.println("Car Rental Data:");
		System.out.println("Car Model");
		String carModel = sc.nextLine();
		System.out.println("Pikup DD/MM/YYYY HH:SS");
		Date start1 = sdf.parse(sc.nextLine());
		System.out.println("Return DD/MM/YYYY HH:SS");
		Date finish1 = sdf.parse(sc.nextLine());
		
		CarRental car = new CarRental(start1, finish1, new Vehicle(carModel));
		
		System.out.println("Price per hour:");
		double priceHour = sc.nextDouble();
		System.out.println("Price per Day:");
		double priceDay = sc.nextDouble();
		
		RentalService service = new RentalService(priceHour, priceDay, new BrasilTaxServices());
		service.processInvoice(car);
		System.out.println("INVOICE:");
		System.out.println("BAIC PAYMENTt= " + car.getInvoice().getBasicPayment());
		System.out.println("TAX= " + car.getInvoice().getTax());
		System.out.println("Total= " + car.getInvoice().getTotalPayment());

	}

}
