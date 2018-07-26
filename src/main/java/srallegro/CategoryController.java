package srallegro;

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

    public static Category createCategoryTree (Map<String, Category> allcategories) {
        Category uberCat = new Category ("All");
        allcategories.put(uberCat.getName(), uberCat);
        Category cars = new Category("Samochody");
        allcategories.put(cars.getName(), cars);
        Category electr = new Category ("Elektronika");
        allcategories.put(electr.getName(), electr);
        Category toys = new Category ("Zabawki");
        allcategories.put(toys.getName(), toys);
        Category tv = new Category("Telewizory");
        allcategories.put(tv.getName(), tv);
        Category mp3 = new Category("Empetrójki");
        allcategories.put(mp3.getName(), mp3);
        Category horse = new Category("Elektroniczne konie");
        allcategories.put(horse.getName(), horse);
        Category bears = new Category("Misie pluszowe");
        allcategories.put(bears.getName(),bears);
        Category sm = new Category("Zabawki sadomaso");
        allcategories.put(sm.getName(),sm);
        Category sw = new Category("Zabawki z 'Gwiezdnych Wojen'");
        allcategories.put(sw.getName(),sw);
        Category hansolo = new Category("Zabawki z Hanem Solo");
        allcategories.put(hansolo.getName(),hansolo);
        Category stormtrooper = new Category("Stormtrooperzy");
        allcategories.put(stormtrooper.getName(),stormtrooper);
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

        allcategories = allcategories;
        return uberCat;
    }




}
