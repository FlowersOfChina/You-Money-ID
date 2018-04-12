package cn.leemaster.flowersid.stragy;

/**
 * @author leemaster
 * @Title: Stragy
 * @Package cn.leemaster.flowersid.stragy
 * @Description:
 * @date 2018/4/12下午5:35
 */
public interface Strategy {

    public long nextId(String ... params);

}
