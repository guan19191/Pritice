package sgcf.zz.com.pritice.net;

/**
 *Retrofit 工厂单例
 */
public class RetrofitFactory {
    static RetrofitFactory instance;

    private RetrofitFactory() {

    }

    public static RetrofitFactory getInstance() {
        if (instance == null) {
            synchronized (RetrofitFactory.class){
                if(instance==null){
                    instance = new RetrofitFactory();
                }
            }
        }
        return instance;
    }
}
