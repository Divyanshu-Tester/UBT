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

public class Universal extends Base_Class 
{

    // Adding Logger to the class
    private static final Logger LOGGER = Logger.getLogger(Find_Public_Link.class.getName());

    // Finding all the URLs that are visiting and then storing them
    private Set<String> visitedUrls = new HashSet<>();

    // Creating the set to visit only public URLs
    private Set<String> urlsToVisit = new HashSet<>();

    // This will list all the public URLs along with their source URLs
    private List<String[]> publicUrls = new ArrayList<>();

    // Limit for testing purposes
    private static final int URL_LIMIT = 5;

    @Test
    public void Run() {
        // The base URL from where it will start crawling
        String baseUrl = "https://www.upstatebeertourist.com/";

        // Adding the base URL to set URLs to visit so that it will start from the base URL
        urlsToVisit.add(baseUrl);

        // This will check URL which is visiting is empty or not and it will continue in loop
        while (!urlsToVisit.isEmpty() && publicUrls.size() < URL_LIMIT) {
            // Iterating the next URLs
            String url = urlsToVisit.iterator().next();

            // Removing the URLs which is visiting
            urlsToVisit.remove(url);

            // Checking the visited URLs
            if (!visitedUrls.contains(url)) {
                visitPage(url);
            }
        }

        // Print the URLs to verify
        for (String[] urlPair : publicUrls) {
            System.out.println("Public URL: " + urlPair[1] + " (Found on: " + urlPair[0] + ")");
        }

        // Saving the URLs to Excel
        saveUrlsToExcel(publicUrls, "C:\\Users\\archa\\Downloads\\URLS\\UBT.xlsx");
    }

    private void visitPage(String url) {
        try {
            // Launching the WebDriver
            driver.get(url);

            // Adding the URL to visited URLs to keep the track
            visitedUrls.add(url);

            // Listing the anchor tags using Selenium
            List<WebElement> links = driver.findElements(By.tagName("a"));

            for (WebElement link : links) {
                // Storing the value of href
                String href = link.getAttribute("href");

                // Matching the URL that it should start from the base URL
                if (href != null && href.startsWith("https://www.upstatebeertourist.com/") && !visitedUrls.contains(href)) {
                    // Logging all the URLs which contain public
                    if (href.contains("/public")) {
                        LOGGER.log(Level.INFO, url + " => Link contains /public: " + href);

                        // Adding them to the public URLs with their source URLs
                        publicUrls.add(new String[]{url, href});

                        // Stop if the limit is reached
                        if (publicUrls.size() >= URL_LIMIT) {
                            return;
                        }
                    }

                    urlsToVisit.add(href);
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error visiting page: " + url, e);
        }
    }

    @SuppressWarnings("unused")
	private void checkLink(String url) {
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

    private void saveUrlsToExcel(List<String[]> urls, String fileName) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Public URLs");

        // Create header row
        Row headerRow = sheet.createRow(0);
        Cell headerCell1 = headerRow.createCell(0);
        headerCell1.setCellValue("Source URL");
        Cell headerCell2 = headerRow.createCell(1);
        headerCell2.setCellValue("Public URL");

        // Populate rows with URLs
        int rowNum = 1;
        for (String[] urlPair : urls) {
            Row row = sheet.createRow(rowNum++);
            Cell cell1 = row.createCell(0);
            cell1.setCellValue(urlPair[0]);
            Cell cell2 = row.createCell(1);
            cell2.setCellValue(urlPair[1]);
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