package com.sqli.testauto.products.capsules;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import java.io.FileInputStream;
import java.io.IOException;


public class ReadCapsuleData {
    static final String filePath = "src/test/java/com/sqli/testauto/products/productslist.xlsx";
    @DataProvider(name = "getCapsulesAndValidQuantity")
    public Object[][] getCapsulesAndQuantityData() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheet("sheet1");
        int rowCount = sheet.getPhysicalNumberOfRows();

        int validRowCount = 0;
        for (int rowNumber = 1; rowNumber < rowCount; rowNumber++) {
            XSSFRow row = sheet.getRow(rowNumber);
            if (row.getCell(0) != null && row.getCell(1) != null) {
                validRowCount++;
            }
        }

        Object[][] data = new Object[validRowCount][2];
        int dataIndex = 0;

        for (int rowNumber = 1; rowNumber < validRowCount + 1; rowNumber++) {
            XSSFRow row = sheet.getRow(rowNumber);
            if (row.getCell(0) != null && row.getCell(1) != null) {
                data[dataIndex][0] = row.getCell(0).toString();
                System.out.println("product name at index: " + dataIndex + " is " + data[dataIndex][0]);
                data[dataIndex][1] = row.getCell(1).toString();
                System.out.println("valid quantity at index: " + dataIndex + " is " + data[dataIndex][1]);
                dataIndex++;
            }
        }
        workbook.close();
        fileInputStream.close();
        return data;
    }
//    @DataProvider(name = "getCapsulesFromAPI")
//    public Object[][] getCapsulesFromAPI(){
//        return null;
//    }
//    @Test
//    public Object[] getAllMachinesNamesUsingApi(){
//        Object<String> data = new ArrayList<>();
//        List<WebElement> webElements = driver.findElements(By.xpath("//article//a[contains(@id,'ProductListElementMachine__product-at-')]"));
//        String xpathOfProductsNames = "//h3[contains(text(),'')]//ancestor::article//button[contains(@class,'AddToBagButton')]";
//        int i = 0;
//        for(WebElement webElement:webElements){
//            i++;
//            data.add(webElement.getText());
//            System.out.println(webElement.getText());
//        }
//        System.out.println(i + webElements.toString());
//        return data;

//    }


//    @DataProvider(name = "capsulesNamesProviderAPI")
//    public Object[][] getAllCapsulesNamesUsingApi() throws URISyntaxException, IOException, InterruptedException {
//        List<String> data = new ArrayList<>();
//
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(new URI("https://www.nespresso.com/fr/en/order/capsules/original/"))
//                .method("",HttpRequest.BodyPublishers.ofString(""))
//                .build();
//
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//
//        System.out.println("Total machines: " + data.size());
//
//        Object[][] dataArray = new Object[data.size()][1];
//        for (int i = 0; i < data.size(); i++) {
//            dataArray[i][0] = data.get(i);
//        }
//
//        return dataArray;
//    }
}
