package listener;

import Entity.Student;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
public class StudentListenr extends AnalysisEventListener<Student> {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentListenr.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<Student> list = new ArrayList<Student>();

    private Student demoDAO;
    public StudentListenr() {
        // 这里是demo，所以随便new一个。实际使用如果到了spring,请使用下面的有参构造函数
        demoDAO = new Student();
    }

    public StudentListenr(Student demoDAO) {
        this.demoDAO = demoDAO;
    }

    @Override
    public void invoke(Student student, AnalysisContext analysisContext) {
        System.out.println("解析到一条数据:"+JSON.toJSONString(student));
        list.add(student);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //数据存储
        saveData();
        System.out.println(("所有数据解析完成！"));
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        System.out.println(list.size()+"条数据，可进行数据库存储！");;
        System.out.println((Arrays.toString(new List[]{list})));
        System.out.println("数据处理完毕");
    }
}