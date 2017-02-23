import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by WZES on 2017/2/18.
 */
class WorkTask extends TimerTask {
    private int taskId = 0;
    public WorkTask(int id){
        this.taskId = id;
    }

    @Override
    public void run() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("执行工作任务-"+this.taskId+" 执行时间- "+format.format(new Date()));
    }
}


public class TextThreadTimer {
    public static void main(String[] args) {
        Timer timer = new Timer();
        WorkTask workTask = new WorkTask(1);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(System.currentTimeMillis()));
        timer.schedule(workTask, new Date(System.currentTimeMillis() + 1000), 1000);


    }
}
