package me.gacl.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

import me.gacl.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Test1 {

    public static void main(String[] args) throws IOException {
        //mybatis的配置文件
        String resource = "conf.xml";
        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = Test1.class.getClassLoader().getResourceAsStream(resource);
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
        //Reader reader = Resources.getResourceAsReader(resource); 
        //构建sqlSession的工厂
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //创建能执行映射文件中sql的sqlSession
        SqlSession session = sessionFactory.openSession();
        /**
         * 映射sql的标识字符串，
         * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
         * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
        String statement = "me.gacl.mapping.userMapper.getUser";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql
        User user = session.selectOne(statement, 1);
        System.out.println(user);
        
        //执行查询全部用户数据
        String statement1 = "me.gacl.mapping.userMapper.getAllUsers";
		List<User> Users = session.selectList(statement1);
		System.out.println(Users);
		
		//执行插入新用户
//		String statement2 = "me.gacl.mapping.userMapper.addUser";
//		User user1 = new User();
//		user1.setName("风淡云轻");
//		user1.setAge(20);
//		int retResult = session.insert(statement2,user1);
//		session.commit();
//		System.out.println(retResult);
		
		//删除用户
		String statement3 = "me.gacl.mapping.userMapper.delUser";
		int retResult1 = session.delete(statement3,9);   
		session.commit();
		session.close();
		System.out.println(retResult1);
		
		
    }
}