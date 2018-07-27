package srallegro;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import srallegro.exception.EmptyCategoryNameException;

import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CategoryTest {
    PrintStream out;

    @Before
    public void setup() {
        this.out = mock(PrintStream.class);
    }

    @Test
    public void testPrintCategories() throws EmptyCategoryNameException {
        Category category = new Category("test");
        CategoryController.printCategories(category, 1, out);
        verify(out).println("test");
    }



}
