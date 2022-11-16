package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.Emp;
import com.atguigu.mybatis.pojo.EmpExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author QiZhen
 * @Description
 * @date 2022-11_11—0:44
 */
public class MBGTest {

    @Test
    public void testMBG(){
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            //查询所有数据
           /* List<Emp> list = mapper.selectByExample(null);
            list.forEach(emp -> System.out.println("emp = " + emp));*/
            EmpExample empExample = new EmpExample();
            empExample.createCriteria().andEmpNameEqualTo("张三").andAgeGreaterThan(20);
            empExample.or().andAgeGreaterThan(20);
            List<Emp> list = mapper.selectByExample(empExample);
            list.forEach(emp -> System.out.println("emp = " + emp));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
