package srallegro;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

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
        Category uberCat = new Category ("All");
        Category cars = new Category("Samochody");
        Category electr = new Category ("Elektronika");
        Category toys = new Category ("Zabawki");
        Category tv = new Category("Telewizory");
        Category mp3 = new Category("Empetrójki");
        Category horse = new Category("Elektroniczne konie");
        Category bears = new Category("Misie pluszowe");
        Category sm = new Category("Zabawki sadomaso");
        Category sw = new Category("Zabawki z 'Gwiezdnych Wojen'");
        Category hansolo = new Category("Zabawki z Hanem Solo");
        Category stormtrooper = new Category("Stormtrooperzy");
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
