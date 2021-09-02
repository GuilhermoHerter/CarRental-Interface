package application.services;

import application.entities.CarRental;
import application.entities.Invoice;

public class RentalService {
	private Double pricePerHour;
	private Double pricePerDay;
	private TaxServices taxService;

	public RentalService(Double pricePerHour, Double pricePerDay, TaxServices taxService) {

		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
		this.taxService = taxService;
	}

	public void processInvoice(CarRental car) {
		long t1 = car.getStart().getTime();
		long t2 = car.getFinish().getTime();
		double hours = (t1 - t2) / 1000 / 60 / 60;

		double basicPayment = 0;

		if (hours <= 12.0) {
			basicPayment = (double) Math.ceil(hours) * pricePerHour;
		} else {
			basicPayment = (double) Math.ceil(hours / 24) * pricePerDay;
		}
		double tax = taxService.tax(basicPayment);
		car.setInvoice(new Invoice(basicPayment, tax));
	}

}
