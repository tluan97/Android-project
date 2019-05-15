package com.example.rssfeed.helper;

import com.example.rssfeed.model.Category;

import java.util.ArrayList;

public class CategoriesSeeder {
    public static ArrayList<Category> GetListCategories(){
        ArrayList<Category> landmarks = new ArrayList<>();
        landmarks.add(new Category(1,
                "Appetizer",
                "https://www.specialtyfood.com/rss/featured-articles/category/appetizer/"
        ));
        landmarks.add(new Category(2,
                "Breakfast",
                "https://www.specialtyfood.com/rss/featured-articles/category/breakfast/"
        ));
        landmarks.add(new Category(3,
                "Dessert",
                "https://www.specialtyfood.com/rss/featured-articles/category/dessert/"
        ));
        landmarks.add(new Category(4,
                "Dinner",
                "https://www.specialtyfood.com/rss/featured-articles/category/dinner/"
        ));
        landmarks.add(new Category(5,
                "Lunch",
                "https://www.specialtyfood.com/rss/featured-articles/category/lunch/"
        ));
        landmarks.add(new Category(6,
                "Fish",
                "https://www.specialtyfood.com/rss/featured-articles/by-tag-name/fish/"
        ));
        landmarks.add(new Category(7,
                "Chicken",
                "https://www.specialtyfood.com/rss/featured-articles/by-tag-name/chicken/"
        ));
        landmarks.add(new Category(8,
                "Pasta sauce",
                "https://www.specialtyfood.com/rss/featured-articles/by-tag-name/pasta-sauce/"
        ));
        landmarks.add(new Category(9,
                "Bakery",
                "https://www.specialtyfood.com/rss/featured-articles/by-tag-name/bakery/"
        ));
        landmarks.add(new Category(10,
                "Beef",
                "https://www.specialtyfood.com/rss/featured-articles/by-tag-name/beef/"
        ));
        landmarks.add(new Category(11,
                "Beverages",
                "https://www.specialtyfood.com/rss/featured-articles/by-tag-name/beverages/"
        ));

        return landmarks;
    }
}
