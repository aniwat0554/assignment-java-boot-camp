## Design
### Success case process flow
```mermaid
    graph LR
    Search("Search for whisky") --> Choose["Choose Teacher's"]
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
    Catalog[\"Show 5 whiskies"\] --> Detail[\"Show Teacher's detail"\]
    Detail --> BasketPut[\"Put item in user's basket"\]
    BasketPut --> Basket[\Show basket item\] --> Payment[\"Create order"\]--> Shipping[\"Save default address to order"\]
    Shipping --> PaymentMethodSelect[\"Accept corresponding payment method require info"\]
    PaymentMethodSelect --> Confirm["Save and show order summary"]
```

### Entities for the IOs
```mermaid
    graph LR
    Catalog[\"Show 5 whiskies"\] --> |GET| Whiskies((Whiskies))
    Detail[\"Show Teacher's detail"\] -->|GET| Whisky(("Whisky"))
    BasketPut[\"Put item in user's basket"\] -->|POST| Basket(("Basket"))
    BasketShow[\Show basket item\] -->|GET| Basket((Basket)) 
    Payment[\"Create order"\] --> |POST| Order(("Order"))
    Shipping[\"Save default address to order"\] -->|GET| User(("User"))
    Shipping[\"Save default address to order"\] -->|PUT| Order(("Order"))
    
    PaymentMethodSelect[\"Accept corresponding payment method require info"\]  -->|PUT| Order(("Order"))
    Confirm["Save and show order summary"] -->|POST,GET| Order(("Order"))
```

## Assignments for Java Boot Camp
* [Week 1 :: Design and Develop RESTful API with Spring Boot](https://github.com/up1/assignment-java-boot-camp/wiki/Week-01)


## Resources
* [Spring Boot Reference](https://spring.io/projects/spring-boot)
* https://www.baeldung.com/ 
* https://start.spring.io/
* [Git commit message](https://www.conventionalcommits.org/en/v1.0.0/)
