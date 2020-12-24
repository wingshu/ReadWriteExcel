package Entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

@Data //lombok插件 作用 自动构建getter，setter
public class Student {
    @ExcelProperty("学号")
    @ColumnWidth(6)             //列宽
    private int studentId;
    @ExcelProperty("姓名")
    @ColumnWidth(8)
    private String studentName;
    @ExcelProperty("电话")
    @ColumnWidth(8)
    private String phone;
}
