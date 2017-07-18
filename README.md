## microservice-spirit

### 1. 简介

本框架基于Spring Cloud，涵盖服务发现、动态路由、负载均衡、统一配置等微服务的核心功能。在业务上已经实现一套基本的系统管理模块，包括用户管理、权限管理、角色管理、资源管理、服务模块管理。

### 2. 开发要求

- JDK8+
- Maven3.3+
- Git2.X+
- MySQL5.5+ ／ SQLSERVER2012+
- Redis3.X +
- RabbitMQ3.X +
- STS / Eclipse / IntelliJ IDEA
- Docker (可选)

### 3. 项目结构

#### **project-common**

只包含所有微服务公用的代码，例如：DateUtils、BigDecimalUtils。一些不通用的工具类不允许在这里编写。

#### project-common-dao

依赖 `project-common`，包含 DAO 操作的工具类、自定义异常类 *SpiritAPIDaoException*。

#### **project-common-api**

依赖 `project-common-dao`，所有提供 Restful API的微服务公用代码，包含公共逻辑类、自定义异常类 *SpiritAPIServiceException.java*。

#### **project-common-ui**

依赖 `project-common`，所有 UI 微服务公用代码。

#### **project-eureka-server**

服务发现组件。==第一个启动的==

#### **project-config-server**

统一配置组件。==第二个启动的==

#### project-config-repo

所有的微服务配置文件所在目录，每个配置文件的命名规则如下

**${application}-${profile}.yml**

其中：application: 每个微服务的 spring.application.name

​	   profile: 运行环境，dev、uat、prod

#### **project-sysmgr-dao**

系统管理DAO组件，不算微服务。所有直接访问数据库的操作只能写到这个组件中。

**包结构：**

- ***po***  	

  所有映射数据库表的实体类所在包。实体类命名必须遵循 `XXXPO.java` 的格式。

- ***dao*** 

  所有数据访问对象所在包。命名必须遵循 `XXXDAO.java `的格式。例：

  ```java
  public interface UserDAO extends JpaRepository<UserPO, Long>, JpaSpecificationExecutor<UserPO>{
  	...
  }
  ```

- ***dao.nativesql***

  原生SQL常量类所在包。命名必须遵循 `XXXSQL.java`，例 `UserSQL.java`。

- ***dao.specification***

  JPA查询规约类所在包。命名必须遵循 `XXXSpecification.java`，例 `UserSpecification.java`。

- ***bo***

  一些辅助的业务实体类（多表字段组合）所在包。命名必须遵循 `XXXBO.java`，例 `UserAuthBO.java`。

- ***config***

  所有配置信息所在包。命名必须遵循 `XXXConfig.java`。

#### project-sysmgr-api

系统管理Restful API微服务，Maven依赖 **project-sysmgr-dao** 组件。系统管理中所有的数据访问都由这个微服务以 RestFul 接口的形式提供。

**包结构：**

- ***constant***

  所有常量所在包。命名必须遵循 `XXXConsts.java`，例 `UserConsts.java`。

- ***service / impl***

  所有Service所在包。命名必须遵循 `XXXService.java` `XXXServiceImpl.java`。例：

  ```java
  public interface UserService extends SpiritAbstractService<UserReqDTO, UserRespDTO> {
    	...
  }

  @Service
  @Transactional(readOnly = true)
  public class UserServiceImpl extends SpiritServiceTemplate<UserReqDTO, UserRespDTO, UserPO> implements UserService {
    	...
  }
  ```

- ***dto / req / resp***

  接口请求参数 req 和返回参数 resp 所在包。命名必须遵循 `XXXReqDTO.java` `XXXRespDTO.java`。

- ***controller***

  Restful 接口所在包。命名必须遵循 `XXXController.java`，接口url必须全部小写，只可用"_"分割。例：

  ```java
  @RestController
  @RequestMapping("/user")
  public class UserController {
  	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
  	@Autowired
  	private UserService userService;
    
  	/**
  	 * 分页查询用户
  	 * 
  	 * @param pageReq
  	 * @return
  	 */
  	@PostMapping("/query_page")
  	public BaseResp<PageResp<UserRespDTO>> queryUserPage(@RequestBody PageReq pageReq) {
        	...
      }
  }
  ```

#### **project-sysmgr-ui**

系统管理UI微服务。

**包结构**：

- ***config***

  所有配置信息所在包。命名必须遵循 `XXXConfig.java`。

- ***constant***

  所有常量所在包。命名必须遵循 `XXXConsts.java`。

- ***vo***

  所有页面请求参数、调用接口返回参数都在此包下定义，**根据不同的业务建立子包**。命名必须遵循 `XXXVO.java`。

- ***client ／ fallback***

  1. 调用其他微服务的接口所在包 client。命名必须遵循 `XXXFeignCient.java`。
  2. 所有的容错类所在包 client/fallback。命名必须遵循 `XXXFeignCientFallback.java`。

- ***service / impl***

  所有Service所在包。命名必须遵循 `XXXService.java` `XXXServiceImpl.java`。

- ***controller***

  MVC中的C，控制层所在包。命名必须遵循 `XXXController.java`，接口url必须全部小写，只可用"_"分割。权限控制可在方法上使用注解 `@PreAuthorize("hasAuthority('sysmgr.user.query')")`，例：

  ```java
  // 当前用户必须拥有权限 sysmgr.user.query，才能访问此方法 queryUserPage
  @PreAuthorize("hasAuthority('sysmgr.user.query')")
  @PostMapping(value = "/query_page")
  public PageResult<UserVO> queryUserPage(PageReq pageReq) {
  	...
  }
  ```

**页面结构**：

​	本框架采用的模板语言是 [Thymeleaf](http://www.thymeleaf.org/)，前端框架采用easyui。所有页面都在 src/main/resources 下，目录结构如下：

```md
static
  easyui
  module
  ux
templates
```

- ***easyui*** 

  easyui库的代码。

- ***module***

  所有页面的 ***js*** 都从页面中分离出来，按业务建立文件夹，将 js 放到子文件夹中。命名必须遵循`XXX.js`。**全部小写**。

- ***ux***

  - util 公共的js工具库。
  - 整体的风格和样式，css、js。

- ***templates***

  所有的功能页面都放到 templates 目录下，按照业务建立相应的文件夹，**全部小写**。

#### project-getway-ui

统一网关，整个框架只将这个微服务暴漏给用户。

### 4. 使用说明 

#### 1. 下载代码

```sh
git clone https://github.com/dante7qx/microservice-spirit.git
```

#### 2. 修改项目名

将下载的框架中 **project-** 或 **PROJECT-** 都修改为真正项目的名称，例

`project-common 修改为 boss-common`

要修改的地方

- 每个微服务的 application.name


- parent的 pom.xml


- 每个组件 pom.xml，artifactId

- project-common-ui 中 SpiritSessionConfigConsts.java - REDIS_NAMESPACE

- project-sysmgr-ui 中 common.html，/project 修改为 实际项目的名称 /boss

  ```html
  <link th:href="@{/project/webjars/bootstrap/3.3.7/css/bootstrap.min.css}" rel="stylesheet"/>
  <link th:href="@{/project/webjars/bootstrap/3.3.7/css/bootstrap-theme.min.css}" rel="stylesheet"/>
  <script th:src="@{/project/webjars/jquery/1.11.3/jquery.min.js}" type="text/javascript"></script>
  ```

#### 3. 启动

a. 先启动 project-eureka-server

b. 再启动 project-config-server

c. 其他微服务启动顺序无先后

d. 最后通过 getway-ui 进行访问

#### 4. 新的微服务

参照系统管理模块，先创建 dao组件、再创建 api 组件，然后创建 ui 组件，最后在 getway-ui 中进行配置。

### 5. 框架注意点

#### 1. 原生SQL返回自定义实体

- dao，返回结果要指定为 List<Object[]> 

```java

@Query(value = AuthorityRoleSQL.FIND_AUTHORITY_ROLE_BY_ROLE_ID, nativeQuery = true)
public List<Object[]> findAuthorityRoleByRoleId(Long roleId) throws SpiritDaoException;
```

- 实际返回实体中必须定义一个构造函数，构造函数中**参数的顺序必须和SQL的字段查询顺序保持一致**

```java
/**
 * 构造函数参数的顺序必须与NativeSQL中查询字段顺序保持一致
 * 
 * @param id
 * @param name
 * @param code
 * @param authorityDesc
 * @param showOrder
 * @param pid
 * @param roleId
 */
public AuthorityRoleBO(Object id, Object name, Object code, Object authorityDesc, Object showOrder, Object pid,Object roleId) {
  this.id = id != null ? Long.parseLong(id.toString()) : null;
  this.pid = pid != null ? Long.parseLong(pid.toString()) : null;
  this.name = name != null ? name.toString() : null;
  this.code = code != null ? code.toString() : null;
  this.authorityDesc = authorityDesc != null ? authorityDesc.toString() : null;
  this.showOrder = showOrder != null ? Integer.parseInt(showOrder.toString()) : 1;
  this.roleId = roleId != null ? Long.parseLong(roleId.toString()) : null;
}
```

- 使用 JpaEntityConvertUtils 工具类

```java
List<AuthorityRoleBO> authorityRoleBOs = JpaEntityConvertUtils
					.castEntity(authorityDAO.findAuthorityRoleByRoleId(roleId), AuthorityRoleBO.class);
```

#### 2. UI层form提交，必须指定 iframe: false

```js

$('#userDetailForm').form('submit', {
  iframe: false,
  url: 'user/update_user',
  success:function(result){
    UserDetailPage.SUBMIT_FLAG = false;
    var result = eval('(' + result + ')');
    if(result['resultCode'] == COMMON_CONFIG['SUCCESS']) {
      $('#userGridlist').datagrid('reload');
      $('#userWindow').window('close');
    } else {
      $.messager.alert('错误','系统错误，请联系系统管理员', 'error');
    }
  }
});
```

#### 3. 不要使用状态性的操作，例如：使用全局Map进行缓存。



### 6.待续...



## 代码规范