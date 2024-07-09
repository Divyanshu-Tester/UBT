package test_Cases;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.logging.Level;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import test_Base.Base_Class;

public class Find_Public_Link extends Base_Class {

    private static final Logger LOGGER = Logger.getLogger(Find_Public_Link.class.getName());
    private Set<String> visitedUrls = new HashSet<>();
    private Set<String> urlsToVisit = new HashSet<>();
    private List<String> publicUrls = new ArrayList<>();

    @Test
    public void Run() {
        String baseUrl = "https://www.upstatebeertourist.com/";
        urlsToVisit.add(baseUrl);

        while (!urlsToVisit.isEmpty()) {
            String url = urlsToVisit.iterator().next();
            urlsToVisit.remove(url);
            if (!visitedUrls.contains(url)) {
                visitPage(url);
            }
        }

        saveUrlsToExcel(publicUrls, "PublicUrls.xlsx");
    }

    private void visitPage(String url) {
        try {
            driver.get(url);
            visitedUrls.add(url);
            List<WebElement> links = driver.findElements(By.tagName("a"));
            for (WebElement link : links) {
                String href = link.getAttribute("href");
                if (href != null && href.startsWith("https://www.upstatebeertourist.com/") && !visitedUrls.contains(href)) {
                    if (href.contains("/public")) {
                        LOGGER.log(Level.INFO,url + " => Link contains /public: " + href);
                        publicUrls.add(href);
                    }
                    urlsToVisit.add(href);
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error visiting page: " + url, e);
        }
    }

    @SuppressWarnings("unused")
	private void checkLink(String url) 
    {
        try {
            @SuppressWarnings("deprecation")
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 404) {
                LOGGER.log(Level.SEVERE, "Broken link: " + url);
            } else {
                LOGGER.log(Level.INFO, "Valid link: " + url + " (Response Code: " + responseCode + ")");
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error checking link: " + url, e);
        }
    }

    private void saveUrlsToExcel(List<String> urls, String fileName) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Public URLs");

        // Create header row
        Row headerRow = sheet.createRow(0);
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("URLs containing /public");

        // Populate rows with URLs
        int rowNum = 1;
        for (String url : urls) {
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue(url);
        }

        // Write the output to a file
        try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error writing to Excel file", e);
        }

        // Close the workbook
        try {
            workbook.close();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error closing the workbook", e);
        }
    }
}