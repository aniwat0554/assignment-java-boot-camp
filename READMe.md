This Spring Boot project written in Java is a RESTFul API to serve data and execute business process transaction of a whisky store.
This API handle only success case as described here
https://github.com/aniwat0554/assignment-java-boot-camp/wiki/Scenario#user-stories

Which was modeled as below
From users' perspective down to data modeling

## Design
### Success case process flow
```mermaid
    graph LR
    Search("Search for Johny Walker") --> Choose["Choose Red Label"]
    Choose -->  Select[Put in basket]
    Select --> Basket[Click see basket] 
    Basket --> Payment["Click pay"]
    Payment --> Shipping["Complete shipment info and proceed"]
    Shipping --> PaymentMethodSelect["Select payment method"]
    PaymentMethodSelect --> Confirm["Confirm Order"]
```
### Success case corresponding IOs
```mermaid
    graph LR
    Catalog[\"Show 5 whiskies"\] --> Detail[\"Show Red Label detail"\]
    Detail --> BasketPut[\"Put item in user's basket"\]
    BasketPut --> Basket[\Show basket item\] --> Payment[\"Create order"\]--> Shipping[\"Save default address to order"\]
    Shipping --> PaymentMethodSelect[\"Accept corresponding payment method require info"\]
    PaymentMethodSelect --> Confirm["Save and show order summary"]
```

### Entities for the IOs
```mermaid
    graph LR
    Catalog[\"Show 5 whiskies"\] --> |GET| Whiskies((ListOfWhisky))
    Detail[\"Show Read Label detail"\] -->|GET| Whisky(("Whisky"))
    BasketPut[\"Put item in user's basket"\] -->|POST| Basket(("Basket"))
    BasketShow[\Show basket item\] -->|GET| Basket((Basket)) 
    Payment[\"Create order"\] --> |POST| Order(("Order"))
    Shipping[\"Save default address to order"\] -->|GET| User(("User"))
    Shipping[\"Save default address to order"\] -->|PUT| Order(("Order"))
    
    PaymentMethodSelect[\"Accept corresponding payment method required info"\]  -->|PUT| Order(("Order"))
    Confirm["Save and show order summary"] -->|POST,GET| Order(("Order"))
```

### Entities composition
```mermaid
    graph LR
    Whiskies((ListOfWhisky)) -->|ComposedOf| Whisky(("Whisky"))
    Basket(("Basket")) --> |ComposedOf| Whiskies((ListOfWhisky))
    Order(("Order")) -->|ComposedOf| Whiskies((ListOfWhisky))
    Order(("Order")) --> |ComposedOf| Shipment((Shipment))
    Order(("Order")) --> |ComposedOf| Payment((Payment))
```


## Assignments for Java Boot Camp
* [Week 1 :: Design and Develop RESTful API with Spring Boot](https://github.com/up1/assignment-java-boot-camp/wiki/Week-01)


## Resources
* [Spring Boot Reference](https://spring.io/projects/spring-boot)
* https://www.baeldung.com/ 
* https://start.spring.io/
* [Git commit message](https://www.conventionalcommits.org/en/v1.0.0/)
