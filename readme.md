TEST CASES

- Feature: The user can search through the products.
- - Conditionals:
The user can touch the search icon enter their search term(s) into the searchbar that appears.
The user can view a list of the search results specific to their search terms.
The user can erase the previous search and start a fresh one.
The user can touch the search results to see a detailed view.

- - User Story:
As a user, I want to search through the items using the search bar.
As a user, I want to search for items by their name, price, or description.
As a user, I want to touch one of the search results to find out more information abour it.


- Feature: The user can browse through all the products. 
- - Conditionals: 
The user can scroll through a list of products.
The user can view a name and price for each product from the list.
- - User Story:
As a user, I want to browse all the products on this app.


- Feature: The user can view details about a product.
- - Conditionals:
The user can touch an item from the main list and it will open a new activity with details on that product, including description.
- - User Story:
As a user, I want to touch an item from the list to see more info about it.


- Feature: The user can access cart from any screen.
- - Conditionals: 
The user can view a shopping cart button on every screen.
The user can touch a shopping cart button that will take them to the shopping cart activity.
The user can see the same information displayed in their cart even if they leave the shopping cart activity.
- - User Story: 
As a user, I want to go to my shopping cart from any screen.


- Feature: The user can add/remove items to/from  cart.
- - Conditionals:
The user can touch an icon on the list item to add it to the shopping cart.
The user can touch an icon on the detail activity to add that item to the shopping cart.
The user can touch an icon next to an item in the shopping cart activity to remove that item from the shopping cart.
- - User Story:
As a user, I want to add items to my cart.
As a user, I want to remove items from my cart.


- Feature: The user can change quantity of an item in the cart.
- - Conditionals:
The user can touch an icon next to an item in the shopping cart activity to increment or decrement the quantity.
The user can see the price change in their subtotal to reflect the change in quantity.
- - User Story:
As a user, I want to change the quantity of an item in my cart.



BUGS AND NOTES
- Search feature does not launch Search Results Activity when using an Emulator ... but it does work on an actual device.
- Upon launch, main recyclerview does not populate. if I stop and start again, there it is!
