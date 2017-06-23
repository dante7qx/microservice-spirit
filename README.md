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

- **project-common**

  只包含所有微服务公用的代码，例如：DateUtils、BigDecimalUtils。一些不通用的工具类不允许在这里编写。

- **project-common-dao**

  依赖 `project-common`，包含 DAO 操作的工具类、自定义异常类 *SpiritAPIDaoException*。

- **project-common-api**

  依赖 `project-common-dao`，所有提供 Restful API的微服务公用代码，包含公共逻辑类、自定义异常类 *SpiritAPIServiceException.java*。

- **project-common-ui**

  依赖 `project-common`，所有 UI 微服务公用代码。

- **project-eureka-server**

  服务发现组件。==第一个启动的==

- **project-config-server**

  统一配置组件。==第二个启动的==

- **project-sysmgr-dao**

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

- **project-sysmgr-api**

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

    ​

  - ​

- ​





### 2. 代码说明 
