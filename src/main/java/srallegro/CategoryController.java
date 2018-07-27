package srallegro;

import srallegro.user.Database;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CategoryController {


    public static void printCategories(Category category, int level, PrintStream out){
        if(category.getName()!=null) {
            out.print("+");
            for (int i = 0; i < level; i++) {
                out.print("-");
            }
            out.println(category.getName());
        }
        for(Category subcategory: category.getSubcategories()){
            printCategories(subcategory, level + 1, out);
        }
    }

    public static Category createCategoryTree () {
        Database database = Database.getInstance();
        Category uberCat = new Category ("All");
        database.addCategoryToAllCategories(uberCat);
        Category cars = new Category("Samochody");
        database.addCategoryToAllCategories(cars);
        Category electr = new Category ("Elektronika");
        database.addCategoryToAllCategories(electr);
        Category toys = new Category ("Zabawki");
        database.addCategoryToAllCategories(toys);
        Category tv = new Category("Telewizory");
        database.addCategoryToAllCategories(tv);
        Category mp3 = new Category("Empetrójki");
        database.addCategoryToAllCategories(mp3);
        Category horse = new Category("Elektroniczne konie");
        database.addCategoryToAllCategories(horse);
        Category bears = new Category("Misie pluszowe");
        database.addCategoryToAllCategories(bears);
        Category sm = new Category("Zabawki sadomaso");
        database.addCategoryToAllCategories(sm);
        Category sw = new Category("Zabawki z 'Gwiezdnych Wojen'");
        database.addCategoryToAllCategories(sw);
        Category hansolo = new Category("Zabawki z Hanem Solo");
        database.addCategoryToAllCategories(hansolo);
        Category stormtrooper = new Category("Stormtrooperzy");
        database.addCategoryToAllCategories(stormtrooper);
        uberCat.addSubcategory(toys);
        uberCat.addSubcategory(electr);
        toys.addSubcategory(bears);
        toys.addSubcategory(sm);
        toys.addSubcategory(sw);
        electr.addSubcategory(tv);
        electr.addSubcategory(mp3);
        electr.addSubcategory(horse);
        sw.addSubcategory(hansolo);
        sw.addSubcategory(stormtrooper);
        uberCat.addSubcategory(cars);

        return uberCat;
    }




}
