//import com.hawkeye.ElInterface.ElCinemaInterface;
//import com.hawkeye.ElInterface.ElFilmmakerInterface;
//import com.hawkeye.ElInterface.ElMoviesInterface;
//import com.hawkeye.HawkeyeApplication;
//import com.hawkeye.elpojo.ElCinema;
//import com.hawkeye.elpojo.ElFilmmaker;
//import com.hawkeye.elpojo.ElMovies;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = HawkeyeApplication.class)
//public class ElTest {
//
//    @Autowired
//    ElasticsearchTemplate elasticsearchTemplate;
//
////    @Autowired
////    ElasticsearchRepository elasticsearchRepository;
//
//    @Autowired
//    ElCinemaInterface elCinemaInterface;
//
//    @Autowired
//    ElFilmmakerInterface elFilmmakerInterface;
//
//    @Autowired
//    ElMoviesInterface elMoviesInterface;
//
//    @Test
//    public void createIndex(){
//        try {
//            //ElCinema/ElFilmmaker/ELMovies
//            elasticsearchTemplate.createIndex(ElCinema.class);//创建索引
//            elasticsearchTemplate.putMapping(ElCinema.class);//创建映射
//
//            elasticsearchTemplate.createIndex(ElFilmmaker.class);//创建索引
//            elasticsearchTemplate.putMapping(ElFilmmaker.class);//创建映射
//
//            elasticsearchTemplate.createIndex(ElMovies.class);//创建索引
//            elasticsearchTemplate.putMapping(ElMovies.class);//创建映射
//            System.out.println("成功");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void test(){
//        try {
//            elCinemaInterface.save(new ElCinema(1,"中影德金影城和平店"));
//            elCinemaInterface.save(new ElCinema(2,"中影德金影院福永店"));
//            elCinemaInterface.save(new ElCinema(3,"中影德金影城松岗店"));
//            elFilmmakerInterface.save(new ElFilmmaker("",1,"刘德华"));
//            elMoviesInterface.save(new ElMovies("",1,"少年的你"));
//            elMoviesInterface.save(new ElMovies("",2,"航海王"));
//            elMoviesInterface.save(new ElMovies("",3,"我和我的祖国"));
//            System.out.println("成功");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void query(){
//
//        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
//
//        queryBuilder.withQuery(QueryBuilders.matchQuery("name", "刘德华"));
//
//        Iterable<ElCinema> elCinemas = elCinemaInterface.search(queryBuilder.build());
//        Iterable<ElFilmmaker> elFilmmakers = elFilmmakerInterface.search(queryBuilder.build());
//        Iterable<ElMovies> elMoviess = elMoviesInterface.search(queryBuilder.build());
//
//        System.out.println("<----------影院---------->");
//        for (ElCinema item:elCinemas) {
//            System.out.println(item.getId() + ":" + item.getName());
//        }
//        System.out.println("<----------影人---------->");
//        for (ElFilmmaker item:elFilmmakers) {
//            System.out.println(item.getId() + ":" + item.getName());
//        }
//        System.out.println("<----------影视剧---------->");
//        for (ElMovies item:elMoviess) {
//            System.out.println(item.getId() + ":" + item.getName());
//        }
//    }
//}
