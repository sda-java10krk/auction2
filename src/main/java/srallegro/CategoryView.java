package srallegro;

import java.io.PrintStream;

public class CategoryView {


    public static void printCategories(Category category, int level, PrintStream out){
        if(category.name!=null) {
            out.print("+");
            for (int i = 0; i < level; i++) {
                out.print("-");
            }
            out.println(category.name);
        }
        for(Category subcategory: category.getSubcategories()){
            printCategories(subcategory, level + 1, out);
        }
    }
}
