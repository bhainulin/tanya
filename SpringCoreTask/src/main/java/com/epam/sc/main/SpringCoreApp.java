package com.epam.sc.main;

import static com.epam.sc.util.RandomUtil.getRandomBoolean;
import static com.epam.sc.util.RandomUtil.getRandomInteger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.sc.beans.Auditorium;
import com.epam.sc.beans.Identifiable;
import com.epam.sc.beans.Event;
import com.epam.sc.beans.EventAuditorium;
import com.epam.sc.beans.EventRating;
import com.epam.sc.beans.Ticket;
import com.epam.sc.beans.User;
import com.epam.sc.beans.UserTicket;
import com.epam.sc.config.DataConfig;
import com.epam.sc.services.api.AuditoriumService;
import com.epam.sc.services.api.BookingService;
import com.epam.sc.services.api.CounterService;
import com.epam.sc.services.api.DiscountService;
import com.epam.sc.services.api.EventService;
import com.epam.sc.services.api.TicketService;
import com.epam.sc.services.api.UserService;

public class SpringCoreApp {
  
  @Autowired
  private UserService userService;
  
  @Autowired
  private EventService eventService;
  
  @Autowired
  private AuditoriumService auditoriumService;
  
  @Autowired
  private TicketService ticketService;
  
  @Autowired
  private DiscountService discountService;
  
  @Autowired
  private BookingService bookingService;
  
  @Autowired
  private CounterService<Identifiable> counterService;
  
  @Value("${a1.name}")
  private String redRoomName;
  
  @Value("${a2.name}")
  private String blueRoomName;
  
  @Value("${baseTicketPrice}")
  private Double basePrice;
  
  @Autowired
  private Properties props;
  
  private final static String HARRY_POTTER =  "Harry Potter";
  private final static String STAR_WARS =  "Star Wars 7";
  
 /* public void printProps() {
    for(Entry<Object, Object> e : props.entrySet()) {
        System.out.println(e);
    }
    
    props.forEach((k,v)->{
      System.out.println("k: " + k + ", v: " + v);
    });
  }*/
  
  /*
   * For Task#3 http://docs.spring.io/spring/docs/current/spring-framework-reference/html/jdbc.html
   */
  public static void main(String[] args) {
   AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
   ctx.register(DataConfig.class);
   ctx.refresh();
     
   SpringCoreApp app = (SpringCoreApp) ctx.getBean("app");

    
   //-----Initial
    print("\n\t1. We already have 3 Auditoriums in our cinema");
    app.infoRunAuditoriumService();
    print("\t2. Let's create 2 films(events)");
    app.createFilms();
    print("\t3. Let's register 2 users");
    app.registerUsers(ctx);
    print("\t4. Assign Films to Auditoriums");
    app.assignAuditorium();
    print("\t5. Let's create tickets (At that moment all of them are free)");
    app.createTickets();
    app.showTickets(false);
    //----Simulate some user actions
    /* print("\t6. Simulation#1");
    app.simulation1();
    
    //Test Aspects
    print("\t7. Simulation#Aspect# Counter");
    app.simulateForCounters();
    app.printCounterStatistic();
    print("\t8. Simulation#Aspect# Discount");
    app.printDiscountStatistic();
    print("\t9. Simulation#Aspect# Lucky Winner");
    app.printLuckyUsers();*/
    
   /* Scanner scanner = new Scanner(System.in); 
    boolean loop = true;
    while (loop) {
      printMainMenu(); 
      int chosen = getMenuPoint(scanner);
      switch (chosen) {
        case 0:
          loop = false;
          break;
      }
    }
    scanner.close();*/
    
  }
  
  private static void printMainMenu() {
    StringBuilder sb = new StringBuilder();
    sb.append("\n--------------------------\n");      
    sb.append("0.").append("exit\nPlease, select from menu(enter a number):");
    print(sb.toString());
}
  
  private static int getMenuPoint(Scanner scanner) {       
    int menuPoint = scanner.nextInt();
    scanner.nextLine();       
    return menuPoint;
  }
  
  public void printCounterStatistic(){
    print("\n***CounterStatistic:");
    Map<Identifiable, Integer> map = counterService.getAll();
    map.forEach((k,v)->{
      print(">entity: ------->" + k + ", \n\t >count: " + v);
    });
    printLine();
  }
  
  public void printDiscountStatistic(){
    print("\n***CounterStatistic:");
    Map<User, Map<String, Integer>> map = counterService.getUserDiscountMap();
    map.forEach((k,v)->{
      print(">user: ------->" + k.getName() + ", \n\t >discount_count: " + v);
    });
    printLine();
  }
  
  public void infoRunAuditoriumService(){
    print("\n***Our Auditoriums:");
    List<Auditorium> list = auditoriumService.getAuditoriums();
    for(Auditorium e : list){
      print(e);
    }
    print("***Vip seats for Red room: " + auditoriumService.getVipSeats(redRoomName));
    printLine(); 
  }
  
  public void createFilms(){
    eventService.createEvent(new Event(HARRY_POTTER, basePrice, EventRating.MID));
    eventService.createEvent(new Event(STAR_WARS, basePrice, EventRating.HIGH));
    print("\n***All Events:");
    List<Event> list = eventService.getAll();
    for(Event e : list){
      print(e);
    }
    printLine();
  }
  
  public void registerUsers(ApplicationContext ctx){
    //UserService userService = (UserService) ctx.getBean("userService"); //The second option
    userService.registerUser(new User("Ivan Ivanov", "Ivan@mail.ru", LocalDate.of(1991, Month.JANUARY, 20)));
    userService.registerUser(new User("Tatsiana Yavarovich", "Tanya@mail.ru", LocalDate.of(1991, Month.OCTOBER, 24)));
    print("\n***All registered users:");
    List<User> list = userService.getAllUsers();
    for(User e : list){
      print(e);
    }
    User u = userService.getUserByEmail("Tanya@mail.ru");
    print("***Find user by email: " + u);
  /*  u.setEmail("NEW_EM@mail.ru");
    userService.updateUser(u);*/
    printLine();
  }
  
  public void assignAuditorium(){
    Auditorium redAudit = auditoriumService.getAuditoriumByName(redRoomName);
    Auditorium blueAudit = auditoriumService.getAuditoriumByName(blueRoomName);
    Event harryPotterEvent = eventService.getEventByName(HARRY_POTTER);
    Event starWarsEvent  = eventService.getEventByName(STAR_WARS);
        
    eventService.assignAuditorium(harryPotterEvent, redAudit, LocalDateTime.of(2015, Month.OCTOBER, 24, 14, 00 ));
    eventService.assignAuditorium(harryPotterEvent, redAudit, LocalDateTime.of(2015, Month.OCTOBER, 24, 19, 00 ));
    eventService.assignAuditorium(harryPotterEvent, blueAudit, LocalDateTime.of(2015, Month.OCTOBER, 24, 14, 30 ));
    
    eventService.assignAuditorium(starWarsEvent, redAudit, LocalDateTime.of(2015, Month.OCTOBER, 24, 17, 00 ));
    eventService.assignAuditorium(starWarsEvent, blueAudit, LocalDateTime.of(2015, Month.OCTOBER, 24, 17, 45 ));
    eventService.assignAuditorium(starWarsEvent, blueAudit, LocalDateTime.of(2015, Month.OCTOBER, 24, 20, 30 ));
    
    print("\n***All events&Auditorium");
    List<EventAuditorium> eventAuditList = eventService.getAllEventAuditorium();    
    for(EventAuditorium e : eventAuditList){
      print(e);
    }
    printLine();
  }
  
  public void createTickets(){
    List<EventAuditorium> eventAuditList = eventService.getAllEventAuditorium();    
    for(EventAuditorium e : eventAuditList){
      ticketService.createTickets(e);
    }
  }
  
  private void showTickets(boolean onlyFree){ 
    List<EventAuditorium> eventAuditList = eventService.getAllEventAuditorium();   
    for(EventAuditorium e : eventAuditList){
      List<Ticket> all = bookingService.getTickets(e.getEvent(), e.getAuditorium(), e.getTime(), onlyFree);
      for(Ticket t : all){
        print(t);
      }
    }
    printLine();    
  }
  
  private void simulation1(){
    print("***Let's see all avaliable tickets for \"Harry Potter\" at Red room at 14.00 10/24/2015");
    Event harryPotterEvent = eventService.getEventByName(HARRY_POTTER);
    Auditorium redAudit = auditoriumService.getAuditoriumByName(redRoomName);
    LocalDateTime time = LocalDateTime.of(2015, Month.OCTOBER, 24, 14, 00 );
    List<Ticket> all = bookingService.getTickets(harryPotterEvent, redAudit, time, true);
    List<Integer> userWantToBuy = new ArrayList<Integer>();
    for(Ticket t : all){
      print(t);
      if(getRandomBoolean()){
        userWantToBuy.add(t.getSeat());
      }
    }
    printLine();
    print("***User wants to buy: " + userWantToBuy);
    User registerUser = userService.getUserById(2L);
    Map<Ticket, Double> priceMap = bookingService.getTicketPrice(harryPotterEvent, redAudit, time, userWantToBuy, registerUser);
   
    print("***Price Map: ");
    for (Map.Entry<Ticket, Double> entry : priceMap.entrySet()){
      print("Price "+entry.getValue() + " / " + entry.getKey());
      bookingService.bookTicket(entry.getKey(), registerUser, entry.getValue());
    }
    printLine();
    print("***So now all avaliable tickets are: ");
    List<Ticket> allAvaliable = bookingService.getTickets(harryPotterEvent, redAudit, time, true);
    for(Ticket t : allAvaliable){
      print(t);     
    }
    printLine();
    
    print("***Booked tickets for user " + registerUser + ": ");
    List<UserTicket> tickets = userService.getBookedTickets(registerUser);
    for(UserTicket t : tickets){
      print(t.getTicket() + "\n\t Final Price:" + t.getFinalPrice());     
    }
    printLine();
  }
  
  public void simulateForCounters(){
    int boundForFilms = getRandomInteger(10);
    print("boundForFilms: " + boundForFilms);
    for(int i = 0; i < boundForFilms; i++){
      if(getRandomBoolean()){
        eventService.getEventByName(HARRY_POTTER);
      } else {
        eventService.getEventByName(STAR_WARS);
      }
    }
    
    int boundForPrices = getRandomInteger(10);
    print("boundForPrices: " + boundForPrices);
    for(int i = 0; i < boundForPrices; i++){
      Event event = getRandomBoolean() ? eventService.getEventByName(HARRY_POTTER) : eventService.getEventByName(STAR_WARS);
      Auditorium audit = auditoriumService.getAuditoriumByName(redRoomName);
      LocalDateTime time = HARRY_POTTER.equals(event.getName())
          ? (getRandomBoolean() ? LocalDateTime.of(2015, Month.OCTOBER, 24, 14, 00 ) : LocalDateTime.of(2015, Month.OCTOBER, 24, 19, 00 ))
          : LocalDateTime.of(2015, Month.OCTOBER, 24, 17, 00 );
      List<Integer> userWantToBuy = new ArrayList<Integer>();
      userWantToBuy.add(1);
      userWantToBuy.add(2);
      User registerUser = userService.getUserById(2L);
      bookingService.getTicketPrice(event, audit, time, userWantToBuy, registerUser);
    }    
    printLine();
  }
  
  public void printLuckyUsers(){
    print("***All Lucky Users:");
    List<User> list = userService.getLuckyUsers();
    for(User u : list){
      print("User: " + u.getName() + ", You was lucky  <" + u.getLuckyCount() + "> times. " + u.getSystemMessage());
    }
    printLine();
  }
 
  
  private static void print(Object ob){
    System.out.println(ob);
  }
  
  private static void printLine(){
    print("---------------------------------------\n");
  }
  
}
