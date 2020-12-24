import Entity.Student;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import listener.StudentListenr;

import java.util.ArrayList;
import java.util.List;

public class ReadWriteExcel {
    public static void main(String[] args) {
        /**
         * 写Excel操作
         * 需要添加Excel字段，在实体类Student中添加字段即可并添加@ExcelProperty()注解等。
         */
        writeExcel();

        /**
         * 读Excel操作
         */
        readExcel();
    }

    /*
        写操作Excel文档
     */
    public static void writeExcel(){
        Student student = new Student();
        student.setStudentId(3);
        student.setStudentName("xushumin");
        student.setPhone("123");
        List<Student> list = new ArrayList<Student>();
        list.add(student);
        // 这里需要确定是否具有D盘，或者可以自定义文件生成路径以及文件名
        ExcelWriterBuilder write = EasyExcel.write("D://xsmEasyExcelTest.xlsx", Student.class);
        write.sheet().doWrite(list);
    }

    public static void readExcel() {
        //Excel数据处理位于StudentListener的 invoke方法以及doAfterAllAnalysed方法中。一行搞定版,其他详细的读取文档操作可参考官网读Excel相关文档
        EasyExcel.read("D://xsmEasyExcelTest.xlsx", Student.class, new StudentListenr()).sheet().doRead();
    }
}
