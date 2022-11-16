package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.Emp;
import com.atguigu.mybatis.pojo.EmpExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
 * @date 2022-11_11—1:19
 */
public class PageHelperTest {
    @Test
    public void testPageHelper(){
        /*
        * limit index,pageSize
        * index:当前页的起始索引
        * pageSize：每页显示的条数
        * pageNum:当前页的页码
        * index=(pageNum-1)*pageSize
        * 使用MyBatis的分页插件实现分页功能
        * 1,需要查询功能之前开启分页
        *  PageHelper.startPage(1,4);
        * 2,在查询功能之后获取分页相关的信息
        *    PageInfo<Emp> page = new PageInfo<>(list, 5);
        *list 表示 分页的数据 5表示当前导航分页的数量
        * */
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            PageHelper.startPage(1,4);
            //查询所有数据
            List<Emp> list = mapper.selectByExample(null);
            PageInfo<Emp> page = new PageInfo<>(list, 5);
            list.forEach(emp -> System.out.println("emp = " + emp));
        /*    EmpExample empExample = new EmpExample();
            empExample.createCriteria().andEmpNameEqualTo("张三").andAgeGreaterThan(20);
            empExample.or().andAgeGreaterThan(20);
            List<Emp> list = mapper.selectByExample(e mpExample);*/
           // list.forEach(emp -> System.out.println("emp = " + emp));
            System.out.println("page = " + page);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
