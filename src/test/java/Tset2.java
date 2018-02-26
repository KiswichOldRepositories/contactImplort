import cn.showclear.entity.base.OrgMemberEntity;
import cn.showclear.service.CellService;
import cn.showclear.service.impl.CellServiceImpl;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Tset2 {
    @Test
    public void testSubstring() {
        String str = "-d33060";
        System.out.println(str.substring(2));
    }

    @Test
    public void testSplitSpace() {
        String str = "jk jk jss";
        System.out.println(Arrays.toString(str.split(" ")));
    }

    @Test
    public void testFor() {
        for (int i = 1; i < -1; i++) {
            System.out.println("hahaha");
        }
    }

    @Test
    public void testSpead() {
        Date date = new Date();
        OrgMemberEntity entity = new OrgMemberEntity();
        for (int i = 0; i < 1000; i++) {
            OrgMemberEntity orgMemberEntity = new OrgMemberEntity();
        }
        Date date1 = new Date();


        for (int i = 1; i < 1000; i++) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                objectOutputStream.writeObject(entity);
                ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                OrgMemberEntity o = (OrgMemberEntity) objectInputStream.readObject();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        Date date2 = new Date();

        for (int i = 0; i < 1000; i++) {
            try {
                OrgMemberEntity clone = (OrgMemberEntity) entity.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

        Date date3 = new Date();
        System.out.println("1花了" + (date1.getTime() - date.getTime()) + " ; 2花了" + (date2.getTime() - date1.getTime())
                + " ; 3花了" + (date3.getTime() - date2.getTime()));
    }

    @Test
    public void testPoi() {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("E://叙简科技内部通讯录.xlsx");
            Workbook sheets = WorkbookFactory.create(inputStream);
            Cell cell = sheets.getSheetAt(0).getRow(2).getCell(1);
            Cell cell1 = sheets.getSheetAt(0).getRow(10000).getCell(1);

            int a = 0;

        } catch (InvalidFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testArrayList(){
        ArrayList<OrgMemberEntity> orgMemberEntities = new ArrayList<>(100);
        System.out.println(orgMemberEntities.size());

    }

}
