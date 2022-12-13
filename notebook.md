# 功能与工作计划清单

## 一、系统设计

> **人员权限设计**
  
- 用户模式
  - 热舒适信息采集

- 管理者模式
  - 添加、管理、删除用户
  - 添加、管理、删除传感器信息
  - 查看房间内实时信息
  - 查看传感器实时信息
  - 查看用户热舒适数据（个人主页）

> **前端页面**

- 登录/注册页面（用户模式/管理者模式）
- 数据采集页面（用户模式）

- 房间内实时信息展示页面（管理者模式，参考榆林能源站）
  - 人员数量
  - 热舒适预测
  - 群体热舒适
  - 查看热舒适统计分析数据
- 用户管理页面（管理者模式）
- 传感器统计分析数据(管理者模式，参考传感器管理平台图片)
- 传感器管理页面(管理者模式)

> **数据库设计**

- RDBC原则

> **技术选型**

## 二、工作计划

- [ ] 登陆页面设计
- [ ] 热舒适信息采集页面设计
  - [ ] 新用户弹窗，个人信息采集
  - [ ] 旧用户，实时信息采集

## 三、登陆页面开发

### 3.1 前端开发

> **前端界面开发**

- 开发登录页面组件，右键 `src\components` 文件夹，新建`Login.vue`文件

- 新建首页组件，右键 `src\components` 文件夹，新建一个 `directory`，命名为 `home`，再在 `home` 下新建一个 `Appindex.vue`

> **前端相关配置**

- 修改 `src\main.js` 代码如下, 使用了新的模块 `axios`，所以需要进入到项目文件夹中，执行 `npm install --save axios`，以安装这个模块：

```js
// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import ElementUI from 'element-ui'

Vue.use(ElementUI)

// 设置反向代理，前端请求默认发送到 http://localhost:8443/api
Vue.prototype.$axios = axios
axios.defaults.baseURL = 'http://localhost:8443/api'
// 全局注册，之后可在其他组件中通过 this.$axios 发送数据
Vue.prototype.$axios = axios
Vue.config.productionTip = false
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
 ```

- **配置页面路由**，修改 `src\router\index.js` 代码如下:

```js
import Vue from 'vue'
import Router from 'vue-router'
// 导入刚才编写的组件
import AppIndex from '@/components/home/AppIndex'
import Login from '@/components/Login'

Vue.use(Router)

export default new Router({
  routes: [
  // 下面都是固定的写法
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/index',
      name: 'AppIndex',
      component: AppIndex
    }
  ]
})
```

- **跨域支持**，在 `config\index.js` 中，找到 `proxyTable` 位置，修改为以下内容:

```js
    proxyTable: {
      '/api': {
        target: 'http://localhost:8443',
        changeOrigin: true,
        pathRewrite: {
          '^/api': ''
        }
      }
    }
 ```

- **修改路由模式为`history`模式**，在`config/index.js`文件下添加如下代码：

```js
mode: 'history',
 ```

### 3.2 后端开发

> **项目相关配置**

- 添加依赖
- 配置数据库:注意端口、数据库名、用户名、密码要与你想使用的数据库一致。

```properities
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/white_jotter?characterEncoding=UTF-8
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.jpa.hibernate.ddl-auto = none
```

> **新建数据库与`User`表**

包括字段如下

```java
int id;
String username;
String password;
```

> **新建`entity`的包，新建`User`类**

`@Entity` 表示这是一个实体类
`@Table(name=“user”)` 表示对应的表名是 `user`

为了简化对数据库的操作，我们使用了 `Java Persistence API（JPA）`，对于 `@JsonIgnoreProperties({ “handler”,“hibernateLazyInitializer” })`，解释起来比较复杂，下面的话看不懂可以忽略：

因为是做前后端分离，而前后端数据交互用的是 `json` 格式。 那么 `User` 对象就会被转换为 `json` 数据。 而本项目使用 jpa 来做实体类的持久化，`jpa` 默认会使用 `hibernate`, 在 `jpa` 工作过程中，就会创造代理类来继承 `User` ，并添加 `handler` 和 `hibernateLazyInitializer` 这两个无须 `json` 化的属性，所以这里需要用 `JsonIgnoreProperties` 把这两个属性忽略掉。

```java
@Data
@Entity // 实体类
@Table(name = "user")   // 表名
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    String username;
    String password;
```

> **UserDAO**

由于使用了 JPA，无需手动构建 SQL 语句，而只需要按照规范提供方法的名字即可实现对数据库的增删改查。

```java
public interface UserDAO extends JpaRepository<User,Integer> {
    User findByUsername(String username);

    User getByUsernameAndPassword(String username,String password);
}
```

> **UserService**

```java
@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public boolean isExist(String username) {
        User user = getByName(username);
        return null!=user;
    }

    public User getByName(String username) {
        return userDAO.findByUsername(username);
    }

    public User get(String username, String password){
        return userDAO.getByUsernameAndPassword(username, password);
    }

    public void add(User user) {
        userDAO.save(user);
    }
}
```

实际上是对 UserDAO 进行了二次封装，一般来讲，我们在 DAO 中只定义基础的增删改查操作，而具体的操作，需要由 Service 来完成。

> **LoginController**

```java
@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "/api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser) {
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);

        User user = userService.get(username, requestUser.getPassword());
        if (null == user) {
            return new Result(400);
        } else {
            return new Result(200);
        }
    }
}
 ```

## 四、用户认证与访问拦截

### 4.1 使用Shiro实现用户信息加密

加密在注册与认证中都有体现，但考虑到认证要用到 shiro，所以先讲在注册中的实现。

> **修改User表与类**

User类与表中添加 salt字段

> **修改`result`包的内容**

- 修改Result类
- 添加ResultCode类
- 添加ResultFactory类

> **编写register方法**

```java
    @PostMapping("api/register")
    @ResponseBody
    public Result register(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);

        boolean exist = userService.isExist(username);
        if (exist) {
            String message = "用户名已被使用";
            return ResultFactory.buildFailResult(message);
        }

        // 生成盐,默认长度 16 位
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        // 设置 hash 算法迭代次数
        int times = 2;
        // 得到 hash 后的密码
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();
        // 存储用户信息，包括 salt 与 hash 后的密码
        user.setSalt(salt);
        user.setPassword(encodedPassword);
        userService.add(user);

        return ResultFactory.buildSuccessResult(user);
    }
```

> **前端实现注册页面**

```js
<template>
  <body id="paper">
  <el-form class="login-container" label-position="left"
           label-width="0px" v-loading="loading">
    <h3 class="login_title">用户注册</h3>
    <el-form-item>
      <el-input type="text" v-model="loginForm.username"
                auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item>
      <el-input type="password" v-model="loginForm.password"
                auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-button type="primary" style="width: 40%;background: #505458;border: none" v-on:click="register">注册</el-button>
    </el-form-item>
  </el-form>
  </body>
</template>
<script>
  export default{
    data () {
      return {
        checked: true,
        loginForm: {
          username: '',
          password: ''
        },
        loading: false
      }
    },
    methods: {
      register () {
        var _this = this
        this.$axios
          .post('/register', {
            username: this.loginForm.username,
            password: this.loginForm.password
          })
          .then(resp => {
            if (resp.data.code === 200) {
              this.$alert('注册成功', '提示', {
                confirmButtonText: '确定'
              })
              _this.$router.replace('/login')
            } else {
              this.$alert(resp.data.message, '提示', {
                confirmButtonText: '确定'
              })
            }
          })
          .catch(failResponse => {})
      }
    }
  }
</script>
<style>
  #paper {
    background:url("../assets/img/bg/eva1.jpg") no-repeat;
    background-position: center;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;
  }
  body{
    margin: -5px 0px;
  }
  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 90px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }
</style>

```

### 4.2 使用Shiro实现登录认证

#### 4.2.1 Shiro核心概念

关于 `Shiro`，需要理解三个核心概念：`Subject`、`SecurityManager` 和 `Realms`

- `Subject`： “现在在与软件交互的东西”，这个东西可能是你是我，可能是第三方进程。说白了就是穿了马甲的用户类，负责存储与修改当前用户的信息和状态。使用 Shiro 实现我们所设计的各种功能，实际上就是在调用 Subject 的 API
- `SecurityManager`： `Subject` 背后安全相关的操作实际上是由其管理的。只用在项目中配置一次。
- `Realm`： 是 `Shiro` 和安全相关数据（比如用户信息）的桥梁，也就是说，`Realm` 负责从数据源中获取数据并加工后传给 `SecurityManager`。我们可以通过配置使用特定的 Realm 替代 DAO，和 JPA 类似，Realm 获取数据的方法被封装了起来，但是数据库中的表名、字段等需要与源码预定义的查询保持一致，所以在我们的项目中获取数据的功能仍旧可以交给 JPA 完成，Realm 只负责加工并传递这些数据。

Shiro还具备四大功能

- Authentication（认证）
- Authorization（授权）
- Session Management（会话管理）
- Cryptography（加密）

各种安全框架解决的都是这几类问题，看名字就大概知道是什么意思了。

#### 4.2.2 Shiro配置与登录拦截

> **添加maven依赖**

为了使用 Shiro，首先要添加 maven 依赖。

> **配置顺序如下**

- 创建 `Realm` 并重写获取认证与授权信息的方法
- 创建配置类，包括创建并配置 SecurityManager 等。也可以通过 web.xml 启用 Shiro 过滤器，再通过 shiro.ini 文件进行配置，不过我们并没有 web.xml。既然用了 Spring Boot，就尽情地使用 Java 类吧

> **创建`Realm`类**

```java
public class WJRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    // 简单重写获取授权信息方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
        return s;
    }

    // 获取认证信息，即根据 token 中的用户名从数据库中获取密码、盐等并返回
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = token.getPrincipal().toString();
        User user = userService.getByUserName(userName);
        String passwordInDB = user.getPassword();
        String salt = user.getSalt();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, passwordInDB, ByteSource.Util.bytes(salt), getName());
        return authenticationInfo;
    }
}
```

> **编写ShiroConfig配置类**

```java
package com.eva.backend.config;

import com.eva.backend.realm.Realm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author：
 * Date：2022/12/1322:35
 * Desc:
 */
@Configuration
public class ShiroConfig {
    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getRealm());
        return securityManager;
    }

    @Bean
    public Realm getRealm() {
        Realm realm = new Realm();
        realm.setCredentialsMatcher(hashedCredentialsMatcher());
        return realm;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
```

### 4.2 用户认证方案与完善的访问拦截