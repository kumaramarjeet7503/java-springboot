package com.springbootgeneratedocument.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.springbootgeneratedocument.entitiy.Order;

public class ExcelHelper {
    public static String[] HEADERS   = {
        "id",
        "amount",
        "orderId",
        "status",
        "receipt",
        "paymentId"
    } ;

    public static String FILE_NAME = "orders" ;

    public static ByteArrayInputStream getData(List<Order> orders) throws IOException
    {
        Workbook workbook = new XSSFWorkbook() ;
        ByteArrayOutputStream out = new ByteArrayOutputStream() ;  
        try {
           
         
            
            //  Create sheet for excel
            Sheet sheet =   workbook.createSheet(FILE_NAME) ;
            //  Create header row for excel sheet
            Row row =  sheet.createRow(0) ;

            for (int i = 0; i < HEADERS.length; i++) {
                Cell cell = row.createCell(i) ;
                cell.setCellValue(HEADERS[i]);
            }

            //  Create value cell for sheet
            int rowIndex = 1 ;
            for (Order order : orders) {

                Row dataRow = sheet.createRow(rowIndex);
               dataRow.createCell(0).setCellValue(order.getId()) ;
                dataRow.createCell(1).setCellValue(order.getAmount()) ;
                 dataRow.createCell(2).setCellValue(order.getOrderId()) ;
                  dataRow.createCell(3).setCellValue(order.getStatus()) ;
                   dataRow.createCell(4).setCellValue(order.getReceipt()) ;
                    dataRow.createCell(5).setCellValue(order.getPaymentId()) ;
                    rowIndex++ ;
            }
            
            workbook.write(out) ;
            return new ByteArrayInputStream(out.toByteArray())  ;

        } catch (Exception e) {
            // TODO: handle exception
            return null ;
        }finally
        {
            workbook.close() ;
            out.close();

        }
    }


}
