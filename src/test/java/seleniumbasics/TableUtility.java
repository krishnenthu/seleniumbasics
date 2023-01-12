package seleniumbasics;

import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TableUtility {
    public static List<ArrayList<String>> get_Dynamic_TwoDimension_TablElemnts(List<WebElement> rowItems, List<WebElement> columnItems) {
        int rSize = rowItems.size() - 1;
        int colunmSize = columnItems.size();  /** cells count** 18**/
        String[] columnList = new String[colunmSize / rSize]; /** 18/6=3** columnList - size of the array**/
        List<ArrayList<String>> gridData = new ArrayList<ArrayList<String>>();  /** 2 dimensional array**/
        int x = 0;
        for (int i = 0; i < rSize; i++) {
            for (int j = 0; j < columnList.length; j++) {
                columnList[j] = columnItems.get(x).getText();
                x++;
            }
            gridData.add(new ArrayList<String>(Arrays.asList(columnList)));
        }
        return gridData;
    }
}
