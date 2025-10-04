import adapters.Dictionary;
import domain.Category;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;


/**
 * Тестируем, что методы словаря корреткон работают
 */
public class DictionaryTest {

    @BeforeEach
    void setUp() {
        Dictionary.getInstance().clear();
    }

    /**
     * Проверяет что полученное слово, является элементом словаря
     */
    @Test
    public void returnsWordFromCategoryListTest() {
        Dictionary d = Dictionary.getInstance();
        d.addWord(Category.ITEM, "ручка");
        d.addWord(Category.ITEM, "стол");
        String w = d.getRandomWord(Category.ITEM);
        assertTrue(List.of("ручка", "стол").contains(w));
    }

    /**
     * Проверяет что слово приводится к нужному регистру и независит от ввода
     */
    @Test
    public void castWordToLowerCaseTest(){
        Dictionary d = Dictionary.getInstance();
        d.addWord(Category.ITEM, "РуЧка");
        d.addWord(Category.ITEM, "СТОЛ");
        d.addWord(Category.ITEM, "вино");
        assertTrue(Dictionary.getInstance().isWordExist(Category.ITEM,"ручка"));
        assertTrue(Dictionary.getInstance().isWordExist(Category.ITEM,"стол"));
        assertTrue(Dictionary.getInstance().isWordExist(Category.ITEM,"вино"));
    }
}
