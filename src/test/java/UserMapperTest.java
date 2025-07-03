import com.example.pojo.User;
/*MyBatis核心会话类*/
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
/*资源加载工具*/
import org.apache.ibatis.io.Resources;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class UserMapperTest {
    @Test
    public void findById() throws IOException {
        //1.获取资源核心配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //2.创建SqlSessionFactory工厂对象（由SqlSessionFactoryBuilder()创建）
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        //3.创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4.执行Sql语句，需要sql语句的唯一标识：namespace.statementId
        User user = sqlSession.selectOne("com.example.pojo.User.findById",1);
        System.out.println(user.toString());
        //5.释放资源
        sqlSession.close();
    }

    @Test
    public void findTotal() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int sum_of_users = sqlSession.selectOne("com.example.pojo.User.findTotal");
        System.out.println("Sum of users is "+sum_of_users);
        sqlSession.close();
    }
}
