package com.mezjh.blog.play.other;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import java.io.*;
import java.util.ArrayList;

/**
 * @author ZJH
 * @date 2020/10/29 17:53
 */
class SqlToExcel {
    public static void main(String[] args) {
        String readPath ="C:\\Users\\ZJH\\Desktop\\全民自动营业1.3\\test.sql";
        String writePath="C:\\Users\\ZJH\\Desktop\\全民自动营业1.3\\0017.xls";
        ArrayList<ArrayList<String>> allDate =getData(readPath);
        writeExcel(writePath,allDate);
    }
    //读取文件
    private static ArrayList<ArrayList<String>> getData(String path){
        //用于存储所有从文件中读取的数据
        ArrayList<ArrayList<String >> allDate = new ArrayList<>();
        try {
            //字符缓冲输入流
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path))) ;
            String line;
            //存储每一行要展示的内容
            ArrayList<String> oneDate = new ArrayList<>();
            //判断是否读到行尾
            while((line = reader.readLine())!= null){
                //判断读到的是否为空行
                if(line.equals("")){
                    allDate.add(oneDate);
                    oneDate = new ArrayList<>();
                }
                //判断读到的内容是否为插入语句
                if(line.indexOf("insert")!=-1){
                    oneDate.add(line);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allDate;
    }

    private  static void writeExcel(String path, ArrayList<ArrayList<String>> allDate){
        if(path !=null && allDate !=null){
            WritableWorkbook wwb = null;
            WritableSheet ws;
            String sheetName= "zhr";
            try {
                //首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象
                wwb = Workbook.createWorkbook(new File(path));
                //创建sheet
                ws = wwb.createSheet(sheetName, 1);
                //循环遍历获取内容
                for(int i=0;i<allDate.size();i++){
                    Label labelC;
                    ArrayList<String> oneDate= allDate.get(i);
                    String mergeDate = "";
                    for (int j = 0; j < oneDate.size(); j++) {
                        String oneLabelDate = oneDate.get(j);
                        mergeDate = mergeDate + oneLabelDate + "\n";
                    }
                    labelC = new Label( 0, i, mergeDate);
                    //将生成的单元格添加到工作表中
                    ws.addCell(labelC);
                }
                //写入数据
                wwb.write();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (RowsExceededException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            } finally{
                try {
                    //关闭工作簿
                    wwb.close();
                } catch (WriteException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}