package com.example.demo;

import com.example.demo.ordering.basket.UsersBasketRepository;
import com.example.demo.ordering.objects.BankPayment;
import com.example.demo.ordering.objects.UsersBasket;
import com.example.demo.ordering.objects.UsersOrder;
import com.example.demo.ordering.objects.WhiskyOrder;
import com.example.demo.ordering.order.OrderRepository;
import com.example.demo.pricing.Price;
import com.example.demo.shipment.Address;
import com.example.demo.users.UserRepository;
import com.example.demo.users.UsersService;
import com.example.demo.users.objects.User;
import com.example.demo.whiskies.objects.Whisky;
import com.example.demo.whiskies.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	private WhiskyRepository whiskyRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UsersBasketRepository usersBasketRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UsersService usersService;
	@PostConstruct
	public void initWhiskiesData(){
		Price redPrice = new Price(600,"THB",0);
		Whisky redLabel = new Whisky("RedLabel Johny Walker",redPrice);
		redLabel.setDegree(40);
		redLabel.setDescription("Great blend");
		redLabel.setReview("www.google.com");
		redLabel.setTasteProfile("sweet");

		Price blackPrice = new Price(700,"THB",0);
		Whisky blackLabel = new Whisky("BlackLabel Johny Walker",blackPrice);
		blackLabel.setDegree(40);
		blackLabel.setDescription("Great blend");
		blackLabel.setReview("www.google.com");
		blackLabel.setTasteProfile("sweet");

		Price greenPrice = new Price(800,"THB",10);
		Whisky greenLabel = new Whisky("GreenLabel Johny Walker",greenPrice);
		greenLabel.setDegree(40);
		greenLabel.setDescription("Great blend");
		greenLabel.setReview("www.google.com");
		greenLabel.setTasteProfile("sweet");

		Price goldPrice = new Price(900,"THB",0);
		Whisky goldLabel = new Whisky("GoldLabel Johny Walker",goldPrice);
		goldLabel.setDegree(40);
		goldLabel.setDescription("Great blend");
		goldLabel.setReview("www.google.com");
		goldLabel.setTasteProfile("sweet");

		Price bluePrice = new Price(1000,"THB",0);
		Whisky blueLabel = new Whisky("BlueLabel Johny Walker",bluePrice);
		blueLabel.setDegree(40);
		blueLabel.setDescription("Great blend");
		blueLabel.setReview("www.google.com");
		blueLabel.setTasteProfile("sweet");

		Whisky savedRedLabel = whiskyRepository.save(redLabel);
		whiskyRepository.save(blackLabel);
		whiskyRepository.save(greenLabel);
		whiskyRepository.save(blueLabel);
		whiskyRepository.save(goldLabel);

		Address address = new Address("Rayong","Noenphra","Rayong City","21000","xxx");

		User savedUser = usersService.createUser("Aniwat","1234",address);
		ArrayList<Whisky> whiskyList = new ArrayList<Whisky>();

		UsersBasket usersBasket = new UsersBasket();
		usersBasket.setBasketOwner(savedUser);
		usersBasketRepository.save(usersBasket);


		//Remove later : This block was used to faciltate development and testing
		/*
		User shopper = usersBasket.getBasketOwner();
		WhiskyOrder order = new WhiskyOrder();

		Address updatingAddress = new Address();

		updatingAddress.setHouseNo(shopper.getAddress().getHouseNo());
		updatingAddress.setPostcode(shopper.getAddress().getPostcode());
		updatingAddress.setSubdistrict(shopper.getAddress().getSubdistrict());
		updatingAddress.setProvince(shopper.getAddress().getProvince());
		updatingAddress.setDistrict(shopper.getAddress().getDistrict());
		order.setAddress(updatingAddress);
		order.setWhiskyToPurchasedWhiskyList(whiskyList);
		order.setPaymentStatus("unpaid");
		BankPayment bankPayment = new BankPayment();
		bankPayment.setRefNo2("11234");
		bankPayment.setRefNo1("2134");
		order.setBankPayment(bankPayment);
		UsersOrder usersOrder = new UsersOrder(order,shopper);
		UsersOrder createdOrder = orderRepository.save(usersOrder);

		 */

	}
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
