package cn.leemaster.flowersid.stragy;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author leemaster
 * @Title: DateTimeId
 * @Package cn.leemaster.flowersid.stragy
 * @Description:
 * just contain the timestamp and the will use the simple id to get
 *
 * the database will use the 64bits to contain the all data
 * the max year will use 4095 -1 << 12 ^ -1 12 bits
 * the max month will be 12 use 4 bits
 * the max day will be 31 use 5 bits
 * the sequence will be 11 bits
 *
 * and then the 10 bits will be the real auto_increment id
 *
 * the sequence will be that year month day sequence
 *
 * use the time pagenation e.g. will be that
 *
 * create the max id and then add to the now date time fields and then the record will between full zero and 0x7FF
 *
 * this generator will be adviced to be the article like id to sign
 *
 * because the day max can be create to 2048 and then will come to for
 *
 * @date 2018/4/12下午5:40
 */
public class DateTimeId implements Strategy {


    // the data field bits count
    private static final int YEAR_BIT = 12;

    private static final int MONTH_BIT = 4;

    private static final int DAY_BIT = 5;

    private static final int SENQUENCE_BIT = 11;



    private static final long MAX_YEAR = -1L ^ (-1L << YEAR_BIT);

    private static final long MAX_MONTH = -1L ^ (-1L << MONTH_BIT);

    private static final long MAX_DAY = -1L ^ (-1L << DAY_BIT);

    private static final long MAX_SENQUENCE = -1L ^ (-1L << SENQUENCE_BIT);


    private static final int DAY_LEFT = SENQUENCE_BIT;

    private static final int MONTH_LEFT = DAY_LEFT + DAY_BIT;

    private static final int YEAR_LEFT = MONTH_LEFT + MONTH_BIT;


    private Long senquence;

    private static DateTimeId dateTimeId = new DateTimeId();


    private DateTimeId(){
        this.senquence = 0L;
    }

    @Override
    public long nextId() {

        Calendar calendar = Calendar.getInstance();

        long year = calendar.get(Calendar.YEAR) & MAX_YEAR;
        long month = calendar.get(Calendar.MONTH) + 1 & MAX_MONTH;
        long day = calendar.get(Calendar.DAY_OF_MONTH) & MAX_DAY;

        long result;

        synchronized (senquence){

            senquence = senquence + 1 & MAX_SENQUENCE;

            result = senquence;
        }

        return    year << YEAR_LEFT
                | month << MONTH_LEFT
                | day << DAY_LEFT
                | result;
    }


    public static Strategy getInstacnce() {

        return dateTimeId;
    }


    public static void main(String[] args) {

        Strategy dateTimeId = DateTimeId.getInstacnce();

//        for(int i = 0;i < 5;i++){
//
//            (new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(Thread.currentThread().getName() + "--" +Long.toBinaryString( dateTimeId.nextId()));
//                }
//            },"Thread" + (i +1))).start();
//
//        }

        for(int i = 0;i < 2056 ;i++){
            System.out.println(Long.toBinaryString(dateTimeId.nextId()));
        }

    }


}
