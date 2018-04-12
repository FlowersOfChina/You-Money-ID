package cn.leemaster.flowersid.stragy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author leemaster
 * @Title: TimeAutoId
 * @Package cn.leemaster.flowersid.stragy
 * @Description:
 *
 * this strategy will be advice to use generate the user and the order id
 * @date 2018/4/12下午8:47
 */
public class TimeAutoId implements Strategy {

    // The system start time can't be modify when the project run in distributed
    private static final Date SYS_START = new Date(2018,4,1) ;


    private static final int TIMESTAMP_BITS = 42;

    private static final int TYPE_BITS = 5;

    private static final int AUTO_BITS = 5;

    private static final int SEQUENCE_BITS = 10;



    private static final long MAX_TIMESTAMP = -1l ^ (-1L << TIMESTAMP_BITS);

    private static final long MAX_TYPE = -1L ^ (-1L << TYPE_BITS);

    private static final long MAX_AUTO = -1L ^ (-1L << AUTO_BITS);

    private static final long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BITS);


    private static final int AUTO_LEFT = SEQUENCE_BITS;

    private static final int TYPE_LEFT = AUTO_LEFT + AUTO_BITS;

    private static final int TIMESTAMP_LEFT = TYPE_LEFT + TYPE_BITS;


    private static Strategy timeAutoId = new TimeAutoId();

    private volatile long sequence ;

    private volatile long auto;

    private long lastTime ;









    private TimeAutoId(){
        this.sequence = 0;
        this.auto = 1;
        this.lastTime = System.currentTimeMillis();
    }


    public static Strategy getInstance(){
        return timeAutoId;
    }

    /**
     * wanna some params to define the type
     * this methods need only one param to use that define the type it is the type code in the business system
     *
     * when the sequence then will increment the auto field then will do more effective in concurrency
     * @param params
     * @return
     */
    @Override
    public long nextId(String ... params) {
        return 0;
    }

    public static void main(String[] args) {

        Date date = new Date();

        System.out.println(date.getTime());

        System.out.println(System.currentTimeMillis());

    }
}
