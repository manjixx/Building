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

- 2022/12/13
  - [x] 登陆页面开发
  - [x] 登录拦截器
  - [x] 用户信息加密与登录认证
  - [x] 用户登出与完善的访问拦截
- 2022/12/14
  - [ ] 合并登录与注册页面
  - [ ] 修改导航栏
  - [ ] 热舒适信息采集页面设计
    - [ ] 新用户弹窗，个人信息采集
    - [ ] 旧用户，实时信息采集

## 三、登陆页面开发

### 3.1 前端页面开发

> **`Login.vue`与`AppIndex.vue`开发**

- 开发登录页面组件，右键 `src\components` 文件夹，新建`Login.vue`文件

- 新建首页组件，右键 `src\components` 文件夹，新建一个 `directory`，命名为 `home`，再在 `home` 下新建一个 `Appindex.vue`

### 3.2 前端相关配置

> **设置反向代理**

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

> **配置页面路由**

修改 `src\router\index.js` 代码如下:

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

> **跨域支持**

在 `config\index.js` 中，找到 `proxyTable` 位置，修改为以下内容:

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

> **使用`history`模式**

 **修改路由模式为`history`模式**，在`config/index.js`文件下添加如下代码：

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

## 四、登录拦截器

### 4.1 后端登录拦截器

> **LoginController**

```java
@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "/api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser, HttpSession session) {
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);

        User user = userService.get(username, requestUser.getPassword());
        if (null == user) {
            return new Result(400);
        } else {
            session.setAttribute("user", user);
            return new Result(200);
        }
    }
}
```

> **LoginInterceptor**

```java
public class LoginInterceptor  implements HandlerInterceptor{

    @Override
    public boolean preHandle (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        String contextPath=session.getServletContext().getContextPath();
        String[] requireAuthPages = new String[]{
                "index",
        };

        String uri = httpServletRequest.getRequestURI();

        uri = StringUtils.remove(uri, contextPath+"/");
        String page = uri;

        if(begingWith(page, requireAuthPages)){
            User user = (User) session.getAttribute("user");
            if(user==null) {
                httpServletResponse.sendRedirect("login");
                return false;
            }
        }
        return true;
    }

    private boolean begingWith(String page, String[] requiredAuthPages) {
        boolean result = false;
        for (String requiredAuthPage : requiredAuthPages) {
            if(StringUtils.startsWith(page, requiredAuthPage)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
```

> **WebConfigurer**

```java
@SpringBootConfiguration
public class WebConfigurer implements WebMvcConfigurer {

    @Bean
    public LoginInterceptor getLoginIntercepter() {
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(getLoginIntercepter()).addPathPatterns("/**").excludePathPatterns("/index.html");
    }
}
```

### 4.2 Vuex 与前端登录拦截器

> **引入 Vuex**

- 运行 `npm install vuex --save`
- 在 `src` 目录下新建一个文件夹 `store`，并在该目录下新建 `index.js` 文件，在该文件中引入 `vue` 和 `vuex`，代码如下：

```js
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: {
      username: window.localStorage.getItem('user' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('user' || '[]')).username
    }
  },
  mutations: {
    login (state, user) {
      state.user = user
      window.localStorage.setItem('user', JSON.stringify(user))
    }
  }
})
```

> **修改路由配置**

为了区分页面是否需要拦截，我们需要修改一下 `src\router\index.js`，在需要拦截的路由中加一条元数据，设置一个 `requireAuth` 字段如下：

```js
{
  path: '/index',
  name: 'AppIndex',
  component: AppIndex,
  meta: {
    requireAuth: true
  }
}
```

> **使用钩子函数判断是否拦截**

钩子函数及在某些时机会被调用的函数。这里我们使用 `router.beforeEach()`，意思是在访问每一个路由前调用。打开 `src\main.js` 。

- 首先添加对 `store` 的引用

```js
import store from './store'
```

- 并修改 Vue 对象里的内容

```js
new Vue({
  el: '#app',
  render: h => h(App),
  router,
  // 注意这里
  store,
  components: { App },
  template: '<App/>'
})
```

- 写 `beforeEach()` 函数

```js
router.beforeEach((to, from, next) => {
    if (to.meta.requireAuth) {
      if (store.state.user.username) {
        next()
      } else {
        next({
          path: 'login',
          query: {redirect: to.fullPath}
        })
      }
    } else {
      next()
    }
  }
)
```

> **修改 Login.vue**

1. 点击登录按钮，向后端发送数据
2. 受到后端返回的成功代码时，触发 store 中的 login() 方法，把 loginForm 对象传递给 store 中的 user 对象（*这里只是简单的实现，在后端我们可以通过用户名和密码查询数据库，获得 user 表的完整信息，比如用户昵称、用户级别等，返回前端，并传递给 user 对象，以实现更复杂的功能）
3. 获取登录前页面的路径并跳转，如果该路径不存在，则跳转到首页

修改后的 `login()` 方法如下：

```js
login () {
  var _this = this
  console.log(this.$store.state)
  this.$axios
    .post('/login', {
      username: this.loginForm.username,
      password: this.loginForm.password
    })
    .then(successResponse => {
      if (successResponse.data.code === 200) {
        // var data = this.loginForm
        _this.$store.commit('login', _this.loginForm)
        var path = this.$route.query.redirect
        this.$router.replace({path: path === '/' || path === undefined ? '/index' : path})
      }
    })
    .catch(failResponse => {
    })
}
```

## 五、使用Shiro实现用户信息加密与登录认证

### 4.1 用户信息加密

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

#### 4.2.2 Shiro配置与登录验证

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

## 六、用户登出功能与完善的访问拦截

### 6.1 用户登出功能开发

#### 6.1.1 后端

> **修改`LoginController`**

```java
    @ResponseBody
    @GetMapping("api/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        String message = "成功登出";
        return ResultFactory.buildSuccessResult(message);
    }
```

> **修改WebConfigurer**

由于登出功能不需要被拦截，所以我们还需要修改配置类 `WebConfigurer` 的 `addInterceptors()` 方法，添加一条路径：

```java
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getLoginIntercepter())
                .addPathPatterns("/**")
                .excludePathPatterns("/index.html")
                .excludePathPatterns("/api/login")
                .excludePathPatterns("/api/logout");
    }
```

#### 6.1.2 前端

> **导航栏增加等处按钮**

```js
<i class="el-icon-switch-button" v-on:click="logout" style="float:right;font-size: 40px;color: #222;padding: 10px"></i>
```

调整样式：

```js
.el-icon-switch-button {
  cursor: pointer;
  outline:0;
}

```

在 methods 中编写 logout() 方法：

```js
logout () {
  var _this = this
  this.$axios.get('/logout').then(resp => {
    if (resp.data.code === 200) {
      // 前后端状态保持一致
      _this.$store.commit('logout')
      _this.$router.replace('/login')
    }
  })
}
```

> **在 store 中定义 logout 方法**

```js
    logout (state) {
      state.user = []
      window.localStorage.removeItem('user')
    }
```

### 6.2 完善的访问拦截

#### 6.2.1 后端登录拦截

> **编写一下拦截器 `LoginInterceptor`**

主要是修改 `preHandle` 方法

```java
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        // 放行 options 请求，否则无法让前端带上自定义的 header 信息，导致 sessionID 改变，shiro 验证失败
        if (HttpMethod.OPTIONS.toString().equals(httpServletRequest.getMethod())) {
            httpServletResponse.setStatus(HttpStatus.NO_CONTENT.value());
            return true;
        }

        Subject subject = SecurityUtils.getSubject();
        // 使用 shiro 验证
        if (!subject.isAuthenticated()) {
            return false;
        }
        return true;
    }
```

> **修改配置类 `WebConfigurer`**

为了允许跨域的 cookie，我们需要修改`addCorsMappings` 方法：

```java
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .allowedHeaders("*")
    }
```

#### 6.2.2 前端配置

> **修改`main.js`**

为了让前端能够带上 cookie，我们需要通过 axios 主动开启 withCredentials 功能，即在 main.js 中添加一行

```js
axios.defaults.withCredentials = true
```

> **修改`router.beforEach`**

即后端接口的拦截是实现了，但页面的拦截并没有实现，仍然可以通过伪造参数，绕过前端的路由限制，访问本来需要登录才能访问的页面。为了解决这个问题，我们可以修改 `router.beforeEach` 方法：

```js
router.beforeEach((to, from, next) => {
    if (to.meta.requireAuth) {
      if (store.state.user) {
        axios.get('/authentication').then(resp => {
          if (resp) next()
        })
      } else {
        next({
          path: 'login',
          query: {redirect: to.fullPath}
        })
      }
    } else {
      next()
    }
  }
)
```

即访问每个页面前都向后端发送一个请求，目的是**经由拦截器验证服务器端的登录状态**，防止上述情况的发生。后端这个接口可以暂时写成空的，比如：

```java
    @ResponseBody
    @GetMapping(value = "api/authentication")
    public String authentication(){
        return "身份认证成功";
    }
```

#### 6.2.3 rememberMe

文提到 cookie 的生命周期如果未特别设置则与浏览器保持一致。并没有设置存活时间，所以在关闭浏览器后，sessionId 就会消失，再次发送请求，shiro 就会认为用户已经变更。但有时我们需要保持登录状态，不然每次都要重新登录怪麻烦的，所以 shiro 提供了 rememberMe 机制。

ememberMe 机制不是单纯地设置 cookie 存活时间，而是又单独保存了一种新的状态。之所以这样设计，也是出于安全性考虑，把 “记住我” 的状态与实际登录状态做出区分，这样，就可以控制用户在访问不太敏感的页面时无需重新登录，而访问类似于购物车、订单之类的页面时必须重新登录。

> **修改`shiro`配置类**

```java
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey("EVANNIGHTLY_WAOU".getBytes());
        return cookieRememberMeManager;
    }

    @Bean
    public SimpleCookie rememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }
```

> **在登录方法中设置 `UsernamePasswordToken` 的 `rememberMe` 属性**

```java
···
UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, requestUser.getPassword());
usernamePasswordToken.setRememberMe(true);
try {
    subject.login(usernamePasswordToken);
    ···
    }

```

在拦截器中进行具体的判断逻辑，由于目前我们并没有特殊需求，所以姑且两种状态都放行：

```java
if (!subject.isAuthenticated() && !subject.isRemembered()) {
    return false;
}
```