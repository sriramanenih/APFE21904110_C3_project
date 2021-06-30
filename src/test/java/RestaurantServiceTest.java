import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeAll;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE


        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");


        {
            restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
            restaurant.addToMenu("Sweet corn soup", 100);
            restaurant.addToMenu("Vegetable lasagne", 200);

        }




    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {

            //1. searching_for_existing_restaurant_should_return_expected_restaurant_object()
        Restaurant searchedRestaurant = service.findRestaurantByName("Amelie's cafe");
        assertEquals(searchedRestaurant.getName(), restaurant.getName());
    }


    @Test
    public void	 searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
            //2. searching_for_non_existing_restaurant_should_throw_exception()
        assertThrows(restaurantNotFoundException.class,()->service.findRestaurantByName("Hyderabad House"));

    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>





    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
       
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
       
       assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){
      
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>


}