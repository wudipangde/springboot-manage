1、Spring，SpringMVC，SpringBoot，SpringCloud有什么区别和联系？

    简单介绍
    Spring是一个轻量级的控制反转(IoC)和面向切面(AOP)的容器框架。Spring使你能够编写更干净、更可管理、并且更易于测试的代码。
    
    Spring MVC是Spring的一个模块，一个web框架。通过Dispatcher Servlet, ModelAndView 和 View Resolver，开发web应用变得很容易。主要针对的是网站应用程序或者服务开发——URL路由、Session、模板引擎、静态Web资源等等。
    
    Spring配置复杂，繁琐，所以推出了Spring boot，约定优于配置，简化了spring的配置流程。
    
    Spring Cloud构建于Spring Boot之上，是一个关注全局的服务治理框架。
    
    Spring VS SpringMVC：
    
    Spring是一个一站式的轻量级的java开发框架，核心是控制反转（IOC）和面向切面（AOP），针对于开发的WEB层(springMvc)、业务层(Ioc)、持久层(jdbcTemplate)等都提供了多种配置解决方案；
    
    SpringMVC是Spring基础之上的一个MVC框架，主要处理web开发的路径映射和视图渲染，属于Spring框架中WEB层开发的一部分；
    
    SpringMVC VS SpringBoot：
    
    SpringMVC属于一个企业WEB开发的MVC框架，涵盖面包括前端视图开发、文件配置、后台接口逻辑开发等，XML、config等配置相对比较繁琐复杂；
    
    SpringBoot框架相对于SpringMVC框架来说，更专注于开发微服务后台接口，不开发前端视图；
    
    SpringBoot和SpringCloud：
    
    SpringBoot使用了默认大于配置的理念，集成了快速开发的Spring多个插件，同时自动过滤不需要配置的多余的插件，简化了项目的开发配置流程，一定程度上取消xml配置，是一套快速配置开发的脚手架，能快速开发单个微服务；
    
    SpringCloud大部分的功能插件都是基于SpringBoot去实现的，SpringCloud关注于全局的微服务整合和管理，将多个SpringBoot单体微服务进行整合以及管理；SpringCloud依赖于SpringBoot开发，而SpringBoot可以独立开发；
    
    总结下来：
    Spring是核心，提供了基础功能；
    
    Spring MVC 是基于Spring的一个 MVC 框架 ；
    
    Spring Boot 是为简化Spring配置的快速开发整合包；
    
    Spring Cloud是构建在Spring Boot之上的服务治理框架。
    
2、你能说说Spring框架中Bean的生命周期吗？

    首先简单说一下（以下为一个回答的参考模板）
    
    1、实例化一个Bean－－也就是我们常说的new；
    
    2、按照Spring上下文对实例化的Bean进行配置－－也就是IOC注入；
    
    3、如果这个Bean已经实现了BeanNameAware接口，会调用它实现的setBeanName(String)方法，此处传递的就是Spring配置文件中Bean的id值
    
    4、如果这个Bean已经实现了BeanFactoryAware接口，会调用它实现的setBeanFactory(setBeanFactory(BeanFactory)传递的是Spring工厂自身（可以用这个方式来获取其它Bean，只需在Spring配置文件中配置一个普通的Bean就可以）；
    
    5、如果这个Bean已经实现了ApplicationContextAware接口，会调用setApplicationContext(ApplicationContext)方法，传入Spring上下文（同样这个方式也可以实现步骤4的内容，但比4更好，因为ApplicationContext是BeanFactory的子接口，有更多的实现方法）；
    
    6、如果这个Bean关联了BeanPostProcessor接口，将会调用postProcessBeforeInitialization(Object obj, String s)方法，BeanPostProcessor经常被用作是Bean内容的更改，并且由于这个是在Bean初始化结束时调用那个的方法，也可以被应用于内存或缓存技术；
    
    7、如果Bean在Spring配置文件中配置了init-method属性会自动调用其配置的初始化方法。
    
    8、如果这个Bean关联了BeanPostProcessor接口，将会调用postProcessAfterInitialization(Object obj, String s)方法、；
    
    注：以上工作完成以后就可以应用这个Bean了，那这个Bean是一个Singleton的，所以一般情况下我们调用同一个id的Bean会是在内容地址相同的实例，当然在Spring配置文件中也可以配置非Singleton，这里我们不做赘述。
    
    9、当Bean不再需要时，会经过清理阶段，如果Bean实现了DisposableBean这个接口，会调用那个其实现的destroy()方法；
    
    10、最后，如果这个Bean的Spring配置中配置了destroy-method属性，会自动调用其配置的销毁方法。
    
    结合代码理解一下
    
    1、Bean的定义
    Spring通常通过配置文件定义Bean。如：
    
    <?xml version=”1.0″ encoding=”UTF-8″?>
    
    <beans xmlns=”http://www.springframework.org/schema/beans”
    xmlns:xsi=”http://www.w3.org/2001/XMLSchema-instance”
    xsi:schemaLocation=”http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.0.xsd”>
    
    <bean id=”HelloWorld” class=”com.pqf.beans.HelloWorld”>
        <property name=”msg”>
           <value>HelloWorld</value>
        </property>
    </bean>
    
    </beans>
    这个配置文件就定义了一个标识为 HelloWorld 的Bean。在一个配置文档中可以定义多个Bean。
    
    2、Bean的初始化
    有两种方式初始化Bean。
    
    1、在配置文档中通过指定init-method 属性来完成
    在Bean的类中实现一个初始化Bean属性的方法，如init()，如：
    
    public class HelloWorld{
       public String msg=null;
       public Date date=null;
    
        public void init() {
          msg=”HelloWorld”;
          date=new Date();
        }
        …… 
    }
    然后，在配置文件中设置init-mothod属性：
    
    2、实现 org.springframwork.beans.factory.InitializingBean接口
    Bean实现InitializingBean接口，并且增加 afterPropertiesSet() 方法：
    
    public class HelloWorld implement InitializingBean {
       public String msg=null;
       public Date date=null;
    
       public void afterPropertiesSet() {
           msg="向全世界问好！";
           date=new Date();
       }
        …… 
    }
    那么，当这个Bean的所有属性被Spring的BeanFactory设置完后，会自动调用afterPropertiesSet()方法对Bean进行初始化，于是，配置文件就不用指定 init-method属性了。
    
    3、Bean的调用
    有三种方式可以得到Bean并进行调用：
    
    1、使用BeanWrapper
    HelloWorld hw=new HelloWorld();
    BeanWrapper bw=new BeanWrapperImpl(hw);
    bw.setPropertyvalue(”msg”,”HelloWorld”);
    system.out.println(bw.getPropertyCalue(”msg”));
    2、使用BeanFactory
    InputStream is=new FileInputStream(”config.xml”);
    XmlBeanFactory factory=new XmlBeanFactory(is);
    HelloWorld hw=(HelloWorld) factory.getBean(”HelloWorld”);
    system.out.println(hw.getMsg());
    3、使用ApplicationConttext
    ApplicationContext actx=new FleSystemXmlApplicationContext(”config.xml”);
    HelloWorld hw=(HelloWorld) actx.getBean(”HelloWorld”);
    System.out.println(hw.getMsg());
    4、Bean的销毁
    1、使用配置文件中的 destory-method 属性
    与初始化属性 init-methods类似，在Bean的类中实现一个撤销Bean的方法，然后在配置文件中通过 destory-method指定，那么当bean销毁时，Spring将自动调用指定的销毁方法。
    
    2、实现 org.springframwork.bean.factory.DisposebleBean接口
    如果实现了DisposebleBean接口，那么Spring将自动调用bean中的Destory方法进行销毁，所以，Bean中必须提供Destory方法。
3、SpringBean的装配
    
    .........
    
4、 如何决定使用 HashMap 还是 TreeMap？

    问：如何决定使用 HashMap 还是 TreeMap？
    
    介绍
    TreeMap<K,V>的Key值是要求实现java.lang.Comparable，所以迭代的时候TreeMap默认是按照Key值升序排序的；TreeMap的实现是基于红黑树结构。适用于按自然顺序或自定义顺序遍历键（key）。
    
    HashMap<K,V>的Key值实现散列hashCode()，分布是散列的、均匀的，不支持排序；数据结构主要是桶(数组)，链表或红黑树。适用于在Map中插入、删除和定位元素。
    
    结论
    如果你需要得到一个有序的结果时就应该使用TreeMap（因为HashMap中元素的排列顺序是不固定的）。除此之外，由于HashMap有更好的性能，所以大多不需要排序的时候我们会使用HashMap。
    
    拓展
    1、HashMap 和 TreeMap 的实现
    
    HashMap：基于哈希表实现。使用HashMap要求添加的键类明确定义了hashCode()和equals()[可以重写hashCode()和equals()]，为了优化HashMap空间的使用，您可以调优初始容量和负载因子。
    
    HashMap(): 构建一个空的哈希映像
    
    HashMap(Map m): 构建一个哈希映像，并且添加映像m的所有映射
    
    HashMap(int initialCapacity): 构建一个拥有特定容量的空的哈希映像
    
    HashMap(int initialCapacity, float loadFactor): 构建一个拥有特定容量和加载因子的空的哈希映像
    
    TreeMap：基于红黑树实现。TreeMap没有调优选项，因为该树总处于平衡状态。
    
    TreeMap()：构建一个空的映像树
    
    TreeMap(Map m): 构建一个映像树，并且添加映像m中所有元素
    
    TreeMap(Comparator c): 构建一个映像树，并且使用特定的比较器对关键字进行排序
    
    TreeMap(SortedMap s): 构建一个映像树，添加映像树s中所有映射，并且使用与有序映像s相同的比较器排序
    
    2、HashMap 和 TreeMap 都是非线程安全
    
    HashMap继承AbstractMap抽象类，TreeMap继承自SortedMap接口。
    
    AbstractMap抽象类：覆盖了equals()和hashCode()方法以确保两个相等映射返回相同的哈希码。如果两个映射大小相等、包含同样的键且每个键在这两个映射中对应的值都相同，则这两个映射相等。映射的哈希码是映射元素哈希码的总和，其中每个元素是Map.Entry接口的一个实现。因此，不论映射内部顺序如何，两个相等映射会报告相同的哈希码。
    
    SortedMap接口：它用来保持键的有序顺序。SortedMap接口为映像的视图(子集)，包括两个端点提供了访问方法。除了排序是作用于映射的键以外，处理SortedMap和处理SortedSet一样。添加到SortedMap实现类的元素必须实现Comparable接口，否则您必须给它的构造函数提供一个Comparator接口的实现。TreeMap类是它的唯一一个实现。
    
    3、TreeMap中默认是按照升序进行排序的，如何让他降序
    
    通过自定义的比较器来实现
    
    定义一个比较器类，实现Comparator接口，重写compare方法，有两个参数，这两个参数通过调用compareTo进行比较，而compareTo默认规则是：
    
    如果参数字符串等于此字符串，则返回 0 值；
    
    如果此字符串小于字符串参数，则返回一个小于 0 的值；
    
    如果此字符串大于字符串参数，则返回一个大于 0 的值。
    
    自定义比较器时，在返回时多添加了个负号，就将比较的结果以相反的形式返回，代码如下：
    
    static class MyComparator implements Comparator{
        @Override
        public int compare(Object o1, Object o2) {
            // TODO Auto-generated method stub
            String param1 = (String)o1;
            String param2 = (String)o2;
            return -param1.compareTo(param2);
        }   
    }
    之后，通过MyComparator类初始化一个比较器实例，将其作为参数传进TreeMap的构造方法中：
    
    MyComparator comparator = new MyComparator();
    
    Map<String,String> map = new TreeMap<String,String>(comparator);
    这样，我们就可以使用自定义的比较器实现降序了
    
    public class MapTest {
    
        public static void main(String[] args) {
            //初始化自定义比较器
            MyComparator comparator = new MyComparator();
            //初始化一个map集合
            Map<String,String> map = new TreeMap<String,String>(comparator);
            //存入数据
            map.put("a", "a");
            map.put("b", "b");
            map.put("f", "f");
            map.put("d", "d");
            map.put("c", "c");
            map.put("g", "g");
            //遍历输出
            Iterator iterator = map.keySet().iterator();
            while(iterator.hasNext()){
                String key = (String)iterator.next();
                System.out.println(map.get(key));
            }
        }
    
        static class MyComparator implements Comparator{
    
            @Override
            public int compare(Object o1, Object o2) {
                // TODO Auto-generated method stub
                String param1 = (String)o1;
                String param2 = (String)o2;
                return -param1.compareTo(param2);
            }
    
        }
    
    }
    
5、分库分表之后，id 主键如何处理？

    问：分库分表之后，id 主键如何处理？
    
    面试官心理分析
    其实这是分库分表之后你必然要面对的一个问题，就是 id 咋生成？因为要是分成多个表之后，每个表都是从 1 开始累加，那肯定不对啊，需要一个全局唯一的 id 来支持。所以这都是你实际生产环境中必须考虑的问题。
    
    面试题剖析
    基于数据库的实现方案
    数据库自增 id
    这个就是说你的系统里每次得到一个 id，都是往一个库的一个表里插入一条没什么业务含义的数据，然后获取一个数据库自增的一个 id。拿到这个 id 之后再往对应的分库分表里去写入。
    
    这个方案的好处就是方便简单，谁都会用；缺点就是单库生成自增 id，要是高并发的话，就会有瓶颈的；如果你硬是要改进一下，那么就专门开一个服务出来，这个服务每次就拿到当前 id 最大值，然后自己递增几个 id，一次性返回一批 id，然后再把当前最大 id 值修改成递增几个 id 之后的一个值；但是无论如何都是基于单个数据库。
    
    适合的场景：你分库分表就俩原因，要不就是单库并发太高，要不就是单库数据量太大；除非是你并发不高，但是数据量太大导致的分库分表扩容，你可以用这个方案，因为可能每秒最高并发最多就几百，那么就走单独的一个库和表生成自增主键即可。
    
    设置数据库 sequence 或者表自增字段步长
    可以通过设置数据库 sequence 或者表的自增字段步长来进行水平伸缩。
    
    比如说，现在有 8 个服务节点，每个服务节点使用一个 sequence 功能来产生 ID，每个 sequence 的起始 ID 不同，并且依次递增，步长都是 8。
    
    图片
    
    适合的场景：在用户防止产生的 ID 重复时，这种方案实现起来比较简单，也能达到性能目标。但是服务节点固定，步长也固定，将来如果还要增加服务节点，就不好搞了。
    
    UUID
    好处就是本地生成，不要基于数据库来了；不好之处就是，UUID 太长了、占用空间大，作为主键性能太差了；更重要的是，UUID 不具有有序性，会导致 B+ 树索引在写的时候有过多的随机写操作（连续的 ID 可以产生部分顺序写），还有，由于在写的时候不能产生有顺序的 append 操作，而需要进行 insert 操作，将会读取整个 B+ 树节点到内存，在插入这条记录后会将整个节点写回磁盘，这种操作在记录占用空间比较大的情况下，性能下降明显。
    
    适合的场景：如果你是要随机生成个什么文件名、编号之类的，你可以用 UUID，但是作为主键是不能用 UUID 的。
    
    UUID.randomUUID().toString().replace(“-”, “”) -> sfsdf23423rr234sfdaf
    获取系统当前时间
    这个就是获取当前时间即可，但是问题是，并发很高的时候，比如一秒并发几千，会有重复的情况，这个是肯定不合适的。基本就不用考虑了。
    
    适合的场景：一般如果用这个方案，是将当前时间跟很多其他的业务字段拼接起来，作为一个 id，如果业务上你觉得可以接受，那么也是可以的。你可以将别的业务字段值跟当前时间拼接起来，组成一个全局唯一的编号。
    
    snowflake 算法
    snowflake 算法是 twitter 开源的分布式 id 生成算法，采用 Scala 语言实现，是把一个 64 位的 long 型的 id，1 个 bit 是不用的，用其中的 41 bit 作为毫秒数，用 10 bit 作为工作机器 id，12 bit 作为序列号。
    
    1 bit：不用，为啥呢？因为二进制里第一个 bit 为如果是 1，那么都是负数，但是我们生成的 id 都是正数，所以第一个 bit 统一都是 0。
    
    41 bit：表示的是时间戳，单位是毫秒。41 bit 可以表示的数字多达 2^41 - 1，也就是可以标识 2^41 - 1 个毫秒值，换算成年就是表示69年的时间。
    
    10 bit：记录工作机器 id，代表的是这个服务最多可以部署在 2^10台机器上哪，也就是1024台机器。但是 10 bit 里 5 个 bit 代表机房 id，5 个 bit 代表机器 id。意思就是最多代表 2^5个机房（32个机房），每个机房里可以代表 2^5 个机器（32台机器）。
    
    12 bit：这个是用来记录同一个毫秒内产生的不同 id，12 bit 可以代表的最大正整数是 2^12 - 1 = 4096，也就是说可以用这个 12 bit 代表的数字来区分同一个毫秒内的 4096 个不同的 id。
    
    
    0 | 0001100 10100010 10111110 10001001 01011100 00 | 10001 | 1 1001 | 0000 00000000
    
    
    public class IdWorker {
    
        private long workerId;
        private long datacenterId;
        private long sequence;
    
        public IdWorker(long workerId, long datacenterId, long sequence) {
            // sanity check for workerId
            // 这儿不就检查了一下，要求就是你传递进来的机房id和机器id不能超过32，不能小于0
            if (workerId > maxWorkerId || workerId < 0) {
                throw new IllegalArgumentException(
                        String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
            }
            if (datacenterId > maxDatacenterId || datacenterId < 0) {
                throw new IllegalArgumentException(
                        String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
            }
            System.out.printf(
                    "worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d, sequence bits %d, workerid %d",
                    timestampLeftShift, datacenterIdBits, workerIdBits, sequenceBits, workerId);
    
            this.workerId = workerId;
            this.datacenterId = datacenterId;
            this.sequence = sequence;
        }
    
        private long twepoch = 1288834974657L;
    
        private long workerIdBits = 5L;
        private long datacenterIdBits = 5L;
    
        // 这个是二进制运算，就是 5 bit最多只能有31个数字，也就是说机器id最多只能是32以内
        private long maxWorkerId = -1L ^ (-1L << workerIdBits);
    
        // 这个是一个意思，就是 5 bit最多只能有31个数字，机房id最多只能是32以内
        private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
        private long sequenceBits = 12L;
    
        private long workerIdShift = sequenceBits;
        private long datacenterIdShift = sequenceBits + workerIdBits;
        private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
        private long sequenceMask = -1L ^ (-1L << sequenceBits);
    
        private long lastTimestamp = -1L;
    
        public long getWorkerId() {
            return workerId;
        }
    
        public long getDatacenterId() {
            return datacenterId;
        }
    
        public long getTimestamp() {
            return System.currentTimeMillis();
        }
    
        public synchronized long nextId() {
            // 这儿就是获取当前时间戳，单位是毫秒
            long timestamp = timeGen();
    
            if (timestamp < lastTimestamp) {
                System.err.printf("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp);
                throw new RuntimeException(String.format(
                        "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
            }
    
            if (lastTimestamp == timestamp) {
                // 这个意思是说一个毫秒内最多只能有4096个数字
                // 无论你传递多少进来，这个位运算保证始终就是在4096这个范围内，避免你自己传递个sequence超过了4096这个范围
                sequence = (sequence + 1) & sequenceMask;
                if (sequence == 0) {
                    timestamp = tilNextMillis(lastTimestamp);
                }
            } else {
                sequence = 0;
            }
    
            // 这儿记录一下最近一次生成id的时间戳，单位是毫秒
            lastTimestamp = timestamp;
    
            // 这儿就是将时间戳左移，放到 41 bit那儿；
            // 将机房 id左移放到 5 bit那儿；
            // 将机器id左移放到5 bit那儿；将序号放最后12 bit；
            // 最后拼接起来成一个 64 bit的二进制数字，转换成 10 进制就是个 long 型
            return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift)
                    | (workerId << workerIdShift) | sequence;
        }
    
        private long tilNextMillis(long lastTimestamp) {
            long timestamp = timeGen();
            while (timestamp <= lastTimestamp) {
                timestamp = timeGen();
            }
            return timestamp;
        }
    
        private long timeGen() {
            return System.currentTimeMillis();
        }
    
        // ---------------测试---------------
        public static void main(String[] args) {
            IdWorker worker = new IdWorker(1, 1, 1);
            for (int i = 0; i < 30; i++) {
                System.out.println(worker.nextId());
            }
        }
    
    }
    怎么说呢，大概这个意思吧，就是说 41 bit 是当前毫秒单位的一个时间戳，就这意思；然后 5 bit 是你传递进来的一个机房 id（但是最大只能是 32 以内），另外 5 bit 是你传递进来的机器 id（但是最大只能是 32 以内），剩下的那个 12 bit序列号，就是如果跟你上次生成 id 的时间还在一个毫秒内，那么会把顺序给你累加，最多在 4096 个序号以内。
    
    所以你自己利用这个工具类，自己搞一个服务，然后对每个机房的每个机器都初始化这么一个东西，刚开始这个机房的这个机器的序号就是 0。然后每次接收到一个请求，说这个机房的这个机器要生成一个 id，你就找到对应的 Worker 生成。
    
    利用这个 snowflake 算法，你可以开发自己公司的服务，甚至对于机房 id 和机器 id，反正给你预留了 5 bit + 5 bit，你换成别的有业务含义的东西也可以的。
    
    这个 snowflake 算法相对来说还是比较靠谱的，所以你要真是搞分布式 id 生成，如果是高并发啥的，那么用这个应该性能比较好，一般每秒几万并发的场景，也足够你用了。
6、消息队列中，如何保证消息的顺序性？

     问：如何保证消息的顺序性？
     
     面试官心理分析
     其实这个也是用 MQ 的时候必问的话题，第一看看你了不了解顺序这个事儿？第二看看你有没有办法保证消息是有顺序的？这是生产系统中常见的问题。
     
     面试题剖析
     我举个例子，我们以前做过一个 mysql binlog 同步的系统，压力还是非常大的，日同步数据要达到上亿，就是说数据从一个 mysql 库原封不动地同步到另一个 mysql 库里面去（mysql -> mysql）。常见的一点在于说比如大数据 team，就需要同步一个 mysql 库过来，对公司的业务系统的数据做各种复杂的操作。
     
     你在 mysql 里增删改一条数据，对应出来了增删改 3 条 binlog 日志，接着这三条 binlog 发送到 MQ 里面，再消费出来依次执行，起码得保证人家是按照顺序来的吧？不然本来是：增加、修改、删除；你楞是换了顺序给执行成删除、修改、增加，不全错了么。
     
     本来这个数据同步过来，应该最后这个数据被删除了；结果你搞错了这个顺序，最后这个数据保留下来了，数据同步就出错了。
     
     先看看顺序会错乱的俩场景：
     
     RabbitMQ：一个 queue，多个 consumer。比如，生产者向 RabbitMQ 里发送了三条数据，顺序依次是 data1/data2/data3，压入的是 RabbitMQ 的一个内存队列。有三个消费者分别从 MQ 中消费这三条数据中的一条，结果消费者2先执行完操作，把 data2 存入数据库，然后是 data1/data3。这不明显乱了。
     
     Kafka：比如说我们建了一个 topic，有三个 partition。生产者在写的时候，其实可以指定一个 key，比如说我们指定了某个订单 id 作为 key，那么这个订单相关的数据，一定会被分发到同一个 partition 中去，而且这个 partition 中的数据一定是有顺序的。
     
     
     消费者从 partition 中取出来数据的时候，也一定是有顺序的。到这里，顺序还是 ok 的，没有错乱。接着，我们在消费者里可能会搞多个线程来并发处理消息。因为如果消费者是单线程消费处理，而处理比较耗时的话，比如处理一条消息耗时几十 ms，那么 1 秒钟只能处理几十条消息，这吞吐量太低了。而多个线程并发跑的话，顺序可能就乱掉了。
     
     
     解决方案
     RabbitMQ
     拆分多个 queue，每个 queue 一个 consumer，就是多一些 queue 而已，确实是麻烦点；或者就一个 queue 但是对应一个 consumer，然后这个 consumer 内部用内存队列做排队，然后分发给底层不同的 worker 来处理。
     
     
     Kafka
     一个 topic，一个 partition，一个 consumer，内部单线程消费，单线程吞吐量太低，一般不会用这个。
     
     写 N 个内存 queue，具有相同 key 的数据都到同一个内存 queue；然后对于 N 个线程，每个线程分别消费一个内存 queue 即可，这样就能保证顺序性。
 
 7、  单例模式的写法
    
    “你知道茴香豆的‘茴’字有几种写法吗？”
    
    纠结单例模式有几种写法有用吗？有点用，面试中经常选择其中一种或几种写法作为话头，考查设计模式和coding style的同时，还很容易扩展到其他问题。
    
    这里讲解几种笔者常用的写法，但切忌生搬硬套，去记“茴香豆的写法”。编程最大的乐趣在于“know everything, control everything”。
    
    JDK版本：oracle java 1.8.0_102
    
    大体可分为4类，下面分别介绍他们的基本形式、变种及特点。
    
    饱汉模式
    饱汉是变种最多的单例模式。我们从饱汉出发，通过其变种逐渐了解实现单例模式时需要关注的问题。
    
    基础的饱汉
    饱汉，即已经吃饱，不着急再吃，饿的时候再吃。所以他就先不初始化单例，等第一次使用的时候再初始化，即“懒加载”。
    
    // 饱汉
    // UnThreadSafe
    public class Singleton1 {
      private static Singleton1 singleton = null;
      private Singleton1() {
      }
      public static Singleton1 getInstance() {
        if (singleton == null) {
          singleton = new Singleton1();
        }
        return singleton;
      }
    }
    饱汉模式的核心就是懒加载。好处是更启动速度快、节省资源，一直到实例被第一次访问，才需要初始化单例；小坏处是写起来麻烦，大坏处是线程不安全，if语句存在竞态条件。
    
    写起来麻烦不是大问题，可读性好啊。因此，单线程环境下，基础饱汉是笔者最喜欢的写法。但多线程环境下，基础饱汉就彻底不可用了。下面的几种变种都在试图解决基础饱汉线程不安全的问题。
    
    饱汉 - 变种 1
    最粗暴的犯法是用synchronized关键字修饰getInstance()方法，这样能达到绝对的线程安全。
    
    // 饱汉
    // ThreadSafe
    public class Singleton1_1 {
      private static Singleton1_1 singleton = null;
      private Singleton1_1() {
      }
      public synchronized static Singleton1_1 getInstance() {
        if (singleton == null) {
          singleton = new Singleton1_1();
        }
        return singleton;
      }
    }
    变种1的好处是写起来简单，且绝对线程安全；坏处是并发性能极差，事实上完全退化到了串行。单例只需要初始化一次，但就算初始化以后，synchronized的锁也无法避开，从而getInstance()完全变成了串行操作。性能不敏感的场景建议使用。
    
    饱汉 - 变种 2
    变种2是“臭名昭著”的DCL 1.0。
    
    针对变种1中单例初始化后锁仍然无法避开的问题，变种2在变种1的外层又套了一层check，加上synchronized内层的check，即所谓“双重检查锁”（Double Check Lock，简称DCL）。
    
    // 饱汉
    // UnThreadSafe
    public class Singleton1_2 {
      private static Singleton1_2 singleton = null;
    
      public int f1 = 1;   // 触发部分初始化问题
      public int f2 = 2;
      private Singleton1_2() {
      }
      public static Singleton1_2 getInstance() {
        // may get half object
        if (singleton == null) {
          synchronized (Singleton1_2.class) {
            if (singleton == null) {
              singleton = new Singleton1_2();
            }
          }
        }
        return singleton;
      }
    }
    变种2的核心是DCL，看起来变种2似乎已经达到了理想的效果：懒加载+线程安全。可惜的是，正如注释中所说，DCL仍然是线程不安全的，由于指令重排序，你可能会得到“半个对象”，即”部分初始化“问题。详细在看完变种3后，可参考下面这篇文章，这里不再赘述。
    
    https://monkeysayhi.github.io/2016/11/29/volatile关键字的作用、原理/
    
    饱汉 - 变种 3
    变种3专门针对变种2，可谓DCL 2.0。
    
    针对变种3的“半个对象”问题，变种3在instance上增加了volatile关键字，原理见上述参考。
    
    // 饱汉
    // ThreadSafe
    public class Singleton1_3 {
      private static volatile Singleton1_3 singleton = null;
    
      public int f1 = 1;   // 触发部分初始化问题
      public int f2 = 2;
      private Singleton1_3() {
      }
      public static Singleton1_3 getInstance() {
        if (singleton == null) {
          synchronized (Singleton1_3.class) {
            // must be a complete instance
            if (singleton == null) {
              singleton = new Singleton1_3();
            }
          }
        }
        return singleton;
      }
    }
    多线程环境下，变种3更适用于性能敏感的场景。但后面我们将了解到，就算是线程安全的，还有一些办法能破坏单例。
    
    当然，还有很多方式，能通过与volatile类似的方式防止部分初始化。读者可自行阅读内存屏障相关内容，但面试时不建议主动装逼。
    
    饿汉模式
    与饱汉相对，饿汉很饿，只想着尽早吃到。所以他就在最早的时机，即类加载时初始化单例，以后访问时直接返回即可。
    
    // 饿汉
    // ThreadSafe
    public class Singleton2 {
      private static final Singleton2 singleton = new Singleton2();
      private Singleton2() {
      }
      public static Singleton2 getInstance() {
        return singleton;
      }
    }
    饿汉的好处是天生的线程安全（得益于类加载机制），写起来超级简单，使用时没有延迟；坏处是有可能造成资源浪费（如果类加载后就一直不使用单例的话）。
    
    值得注意的时，单线程环境下，饿汉与饱汉在性能上没什么差别；但多线程环境下，由于饱汉需要加锁，饿汉的性能反而更优。
    
    Holder模式
    我们既希望利用饿汉模式中静态变量的方便和线程安全；又希望通过懒加载规避资源浪费。Holder模式满足了这两点要求：核心仍然是静态变量，足够方便和线程安全；通过静态的Holder类持有真正实例，间接实现了懒加载。
    
    // Holder模式
    // ThreadSafe
    public class Singleton3 {
      private static class SingletonHolder {
        private static final Singleton3 singleton = new Singleton3();
        private SingletonHolder() {
        }
      }
      private Singleton3() {
      }
    
      /**
      * 勘误：多写了个synchronized。。
      public synchronized static Singleton3 getInstance() {
        return SingletonHolder.singleton;
      }
      */
      public static Singleton3 getInstance() {
        return SingletonHolder.singleton;
      }
    }
    相对于饿汉模式，Holder模式仅增加了一个静态内部类的成本，与饱汉的变种3效果相当（略优），都是比较受欢迎的实现方式。同样建议考虑。
    
    枚举模式
    用枚举实现单例模式，相当好用，但可读性是不存在的。
    
    基础的枚举
    将枚举的静态成员变量作为单例的实例：
    
    // 枚举
    // ThreadSafe
    public enum Singleton4 {
      SINGLETON;
    }
    代码量比饿汉模式更少。但用户只能直接访问实例Singleton4.SINGLETON——事实上，这样的访问方式作为单例使用也是恰当的，只是牺牲了静态工厂方法的优点，如无法实现懒加载。
    
    丑陋但好用的语法糖
    Java的枚举是一个“丑陋但好用的语法糖”。
    
    枚举型单例模式的本质
    通过反编译打开语法糖，就看到了枚举类型的本质，简化如下：
    
    // 枚举
    // ThreadSafe
    public class Singleton4 extends Enum<Singleton4> {
      ...
      public static final Singleton4 SINGLETON = new Singleton4();
      ...
    }
    本质上和饿汉模式相同，区别仅在于公有的静态成员变量。
    
    用枚举实现一些trick
    这一部分与单例没什么关系，可以跳过。如果选择阅读也请认清这样的事实：虽然枚举相当灵活，但如何恰当的使用枚举有一定难度。一个足够简单的典型例子是TimeUnit类，建议有时间耐心阅读。
    
    上面已经看到，枚举型单例的本质仍然是一个普通的类。实际上，我们可以在枚举型型单例上增加任何普通类可以完成的功能。要点在于枚举实例的初始化，可以理解为实例化了一个匿名内部类。为了更明显，我们在Singleton4_1中定义一个普通的私有成员变量，一个普通的公有成员方法，和一个公有的抽象成员方法，如下：
    
    // 枚举
    // ThreadSafe
    public enum Singleton4_1 {
      SINGLETON("enum is the easiest singleton pattern, but not the most readable") {
        public void testAbsMethod() {
          print();
          System.out.println("enum is ugly, but so flexible to make lots of trick");
        }
      };
      private String comment = null;
      Singleton4_1(String comment) {
        this.comment = comment;
      }
      public void print() {
        System.out.println("comment=" + comment);
      }
      abstract public void testAbsMethod();
      public static Singleton4_1 getInstance() {
        return SINGLETON;
      }
    }
    这样，枚举类Singleton4_1中的每一个枚举实例不仅继承了父类Singleton4_1的成员方法print()，还必须实现父类Singleton4_1的抽象成员方法testAbsMethod()。
    
    总结
    上面的分析都忽略了反射和序列化的问题。通过反射或序列化，我们仍然能够访问到私有构造器，创建新的实例破坏单例模式。此时，只有枚举模式能天然防范这一问题。反射和序列化笔者还不太了解，但基本原理并不难，可以在其他模式上手动实现。
    
    下面继续忽略反射和序列化的问题，做个总结回味一下：
        
    单例模式是面试中的常考点，写起来非常简单。
8、 Redis中是如何实现分布式锁的？

       分布式锁常见的三种实现方式：
       
       数据库乐观锁；
       
       基于Redis的分布式锁；
       
       基于ZooKeeper的分布式锁。
       
       本地面试考点是，你对Redis使用熟悉吗？Redis中是如何实现分布式锁的。
       
       要点
       Redis要实现分布式锁，以下条件应该得到满足
       
       互斥性
       
       在任意时刻，只有一个客户端能持有锁。
       
       不能死锁
       
       客户端在持有锁的期间崩溃而没有主动解锁，也能保证后续其他客户端能加锁。
       
       容错性
       
       只要大部分的Redis节点正常运行，客户端就可以加锁和解锁。
       
       实现
       可以直接通过 set key value px milliseconds nx 命令实现加锁， 通过Lua脚本实现解锁。
       
       //获取锁（unique_value可以是UUID等）
       SET resource_name unique_value NX PX  30000
       
       //释放锁（lua脚本中，一定要比较value，防止误解锁）
       if redis.call("get",KEYS[1]) == ARGV[1] then
           return redis.call("del",KEYS[1])
       else
           return 0
       end
       代码解释
       
       set 命令要用 set key value px milliseconds nx，替代 setnx + expire 需要分两次执行命令的方式，保证了原子性，
       
       value 要具有唯一性，可以使用UUID.randomUUID().toString()方法生成，用来标识这把锁是属于哪个请求加的，在解锁的时候就可以有依据；
       
       释放锁时要验证 value 值，防止误解锁；
       
       通过 Lua 脚本来避免 Check And Set 模型的并发问题，因为在释放锁的时候因为涉及到多个Redis操作 （利用了eval命令执行Lua脚本的原子性）；
       
       加锁代码分析
       
       首先，set()加入了NX参数，可以保证如果已有key存在，则函数不会调用成功，也就是只有一个客户端能持有锁，满足互斥性。其次，由于我们对锁设置了过期时间，即使锁的持有者后续发生崩溃而没有解锁，锁也会因为到了过期时间而自动解锁（即key被删除），不会发生死锁。最后，因为我们将value赋值为requestId，用来标识这把锁是属于哪个请求加的，那么在客户端在解锁的时候就可以进行校验是否是同一个客户端。
       
       解锁代码分析
       
       将Lua代码传到jedis.eval()方法里，并使参数KEYS[1]赋值为lockKey，ARGV[1]赋值为requestId。在执行的时候，首先会获取锁对应的value值，检查是否与requestId相等，如果相等则解锁（删除key）。
       
       存在的风险
       
       如果存储锁对应key的那个节点挂了的话，就可能存在丢失锁的风险，导致出现多个客户端持有锁的情况，这样就不能实现资源的独享了。
       
       客户端A从master获取到锁
       
       在master将锁同步到slave之前，master宕掉了（Redis的主从同步通常是异步的）。
       主从切换，slave节点被晋级为master节点
       
       客户端B取得了同一个资源被客户端A已经获取到的另外一个锁。导致存在同一时刻存不止一个线程获取到锁的情况。
       
       redlock算法出现
       这个场景是假设有一个 redis cluster，有 5 个 redis master 实例。然后执行如下步骤获取一把锁：
       
       获取当前时间戳，单位是毫秒；
       
       跟上面类似，轮流尝试在每个 master 节点上创建锁，过期时间较短，一般就几十毫秒；
       
       尝试在大多数节点上建立一个锁，比如 5 个节点就要求是 3 个节点 n / 2 + 1；
       
       客户端计算建立好锁的时间，如果建立锁的时间小于超时时间，就算建立成功了；
       
       要是锁建立失败了，那么就依次之前建立过的锁删除；
       
       只要别人建立了一把分布式锁，你就得不断轮询去尝试获取锁。
       
       
       
       图片
       
       Redis 官方给出了以上两种基于 Redis 实现分布式锁的方法，详细说明可以查看：
       
       https://redis.io/topics/distlock 。
       
       Redisson实现
       Redisson是一个在Redis的基础上实现的Java驻内存数据网格（In-Memory Data Grid）。它不仅提供了一系列的分布式的Java常用对象，还实现了可重入锁（Reentrant Lock）、公平锁（Fair Lock、联锁（MultiLock）、 红锁（RedLock）、 读写锁（ReadWriteLock）等，还提供了许多分布式服务。
       
       Redisson提供了使用Redis的最简单和最便捷的方法。Redisson的宗旨是促进使用者对Redis的关注分离（Separation of Concern），从而让使用者能够将精力更集中地放在处理业务逻辑上。
       
       Redisson 分布式重入锁用法
       
       Redisson 支持单点模式、主从模式、哨兵模式、集群模式，这里以单点模式为例：
       
       // 1.构造redisson实现分布式锁必要的Config
       Config config = new Config();
       config.useSingleServer().setAddress("redis://127.0.0.1:5379").setPassword("123456").setDatabase(0);
       // 2.构造RedissonClient
       RedissonClient redissonClient = Redisson.create(config);
       // 3.获取锁对象实例（无法保证是按线程的顺序获取到）
       RLock rLock = redissonClient.getLock(lockKey);
       try {
           /**
            * 4.尝试获取锁
            * waitTimeout 尝试获取锁的最大等待时间，超过这个值，则认为获取锁失败
            * leaseTime   锁的持有时间,超过这个时间锁会自动失效（值应设置为大于业务处理的时间，确保在锁有效期内业务能处理完）
            */
           boolean res = rLock.tryLock((long)waitTimeout, (long)leaseTime, TimeUnit.SECONDS);
           if (res) {
               //成功获得锁，在这里处理业务
           }
       } catch (Exception e) {
           throw new RuntimeException("aquire lock fail");
       }finally{
           //无论如何, 最后都要解锁
           rLock.unlock();
       }
       加锁流程图
       
       key是否存在  
            是：value是否存在
                是：执行hincyby重入次数加1，并用pexpire设置失效时间
                    返回控制nil
                否：说明被其他线程占用，通过pttl获取key剩余时间并返回
                    返回key剩余时间      
            否：执行hset key uuid + threadId I设置键值并初始化重入次数为1，并用pexpire设置失效时间
       
       解锁流程图
       key是否存在
            是：value是否匹配
                是：执行hincrby将重入数减1
                    重入数是否大于0 
                        是：执行重新设置过期时间
                        否：删除key 
            否：广播释放消息（通知阻塞等待的线程或进程资源可用）
                    
       
       我们可以看到，RedissonLock是可重入的，并且考虑了失败重试，可以设置锁的最大等待时间， 在实现上也做了一些优化，减少了无效的锁申请，提升了资源的利用率。
       
       需要特别注意的是，RedissonLock 同样没有解决 节点挂掉的时候，存在丢失锁的风险的问题。而现实情况是有一些场景无法容忍的，所以 Redisson 提供了实现了redlock算法的 RedissonRedLock，RedissonRedLock 真正解决了单点失败的问题，代价是需要额外的为 RedissonRedLock 搭建Redis环境。
       
       所以，如果业务场景可以容忍这种小概率的错误，则推荐使用 RedissonLock， 如果无法容忍，则推荐使用 RedissonRedLock。
9、Object类方法

    具体解答
    1.Object()
    这个没什么可说的，Object类的构造方法。(非重点)
    
    2.registerNatives()
    为了使JVM发现本机功能，他们被一定的方式命名。例如，对于java.lang.Object.registerNatives，对应的C函数命名为Java_java_lang_Object_registerNatives。
    
    通过使用registerNatives（或者更确切地说，JNI函数RegisterNatives），可以命名任何你想要你的C函数。(非重点)
    
    3.clone()
    clone()函数的用途是用来另存一个当前存在的对象。只有实现了Cloneable接口才可以调用该方法，否则抛出CloneNotSupportedException异常。（注意：回答这里时可能会引出设计模式的提问）
    
    4.getClass()
    final方法，用于获得运行时的类型。该方法返回的是此Object对象的类对象/运行时类对象Class。效果与Object.class相同。（注意：回答这里时可能会引出类加载，反射等知识点的提问）
    
    5.equals()
    equals用来比较两个对象的内容是否相等。默认情况下(继承自Object类)，equals和==是一样的，除非被覆写(override)了。（注意：这里可能引出更常问的“equals与==的区别”及hashmap实现原理的提问）
    
    6.hashCode()
    该方法用来返回其所在对象的物理地址（哈希码值），常会和equals方法同时重写，确保相等的两个对象拥有相等的hashCode。（同样，可能引出hashmap实现原理的提问）
    
    7.toString()
    toString()方法返回该对象的字符串表示，这个方法没什么可说的。
    
    8.wait()
    导致当前的线程等待，直到其他线程调用此对象的 notify() 方法或 notifyAll() 方法。（引出线程通信及“wait和sleep的区别”的提问）
    
    9.wait(long timeout)
    导致当前的线程等待，直到其他线程调用此对象的 notify() 方法或 notifyAll() 方法，或者超过指定的时间量。（引出线程通信及“wait和sleep的区别”的提问）
    
    10.wait(long timeout, int nanos)
    导致当前的线程等待，直到其他线程调用此对象的 notify() 方法或 notifyAll() 方法，或者其他某个线程中断当前线程，或者已超过某个实际时间量。（引出线程通信及“wait和sleep的区别”的提问）
    
    11.notify()
    唤醒在此对象监视器上等待的单个线程。（引出线程通信的提问）
    
    12. notifyAll()
    唤醒在此对象监视器上等待的所有线程。（引出线程通信的提问）
    
    13.finalize()
    当垃圾回收器确定不存在对该对象的更多引用时，由对象的垃圾回收器调用此方法。（非重点，但小心引出垃圾回收的提问）
    
    引申常见问题
    equals() 与 == 的区别是什么？
    
    hashCode() 和 equals() 之间有什么联系？
    
    wait()方法与sleep()方法的区别
    
    为什么重写了equals就必须重写hashCode
    
    HashMap的实现原理
    
    谈谈类加载机制
    
    后续我们就这些常见问题一一做一些解答，当然，可能大部分人对这类问了八百遍的问题已经倒背如流了

10、hashcode 和 equals 关系和区别
    
    上一篇关于介绍Object类下的几种方法时面试题时，提到equals()和hashCode()方法可能引出关于“hashCode() 和 equals() 之间的关系？”的面试题，本篇来解析一下这道基础面试题。
    
    先祭一张图，可以思考一下为什么？
    
    图片
    
    介绍
    equals() 的作用是用来判断两个对象是否相等。
    
    hashCode() 的作用是获取哈希码，也称为散列码；它实际上是返回一个int整数。这个哈希码的作用是确定该对象在哈希表中的索引位置。
    
    关系
    我们以“类的用途”来将“hashCode() 和 equals()的关系”分2种情况来说明。
    
    1、不会创建“类对应的散列表”
    这里所说的“不会创建类对应的散列表”是说：我们不会在HashSet, Hashtable, HashMap等等这些本质是散列表的数据结构中，用到该类。例如，不会创建该类的HashSet集合。
    
    在这种情况下，该类的“hashCode() 和 equals() ”没有半毛钱关系的！equals() 用来比较该类的两个对象是否相等。而hashCode() 则根本没有任何作用。
    
    下面，我们通过示例查看类的两个对象相等 以及 不等时hashCode()的取值。
    
    import java.util.*;
    import java.lang.Comparable;
    
    /**
     * @desc 比较equals() 返回true 以及 返回false时， hashCode()的值。
     *
     */
    public class NormalHashCodeTest{
    
        public static void main(String[] args) {
            // 新建2个相同内容的Person对象，
            // 再用equals比较它们是否相等
            Person p1 = new Person("eee", 100);
            Person p2 = new Person("eee", 100);
            Person p3 = new Person("aaa", 200);
            System.out.printf("p1.equals(p2) : %s; p1(%d) p2(%d)\n", p1.equals(p2), p1.hashCode(), p2.hashCode());
            System.out.printf("p1.equals(p3) : %s; p1(%d) p3(%d)\n", p1.equals(p3), p1.hashCode(), p3.hashCode());
        }
    
        /**
         * @desc Person类。
         */
        private static class Person {
            int age;
            String name;
    
            public Person(String name, int age) {
                this.name = name;
                this.age = age;
            }
    
            public String toString() {
                return name + " - " +age;
            }
    
            /** 
             * @desc 覆盖equals方法 
             */  
            public boolean equals(Object obj){  
                if(obj == null){  
                    return false;  
                }  
    
                //如果是同一个对象返回true，反之返回false  
                if(this == obj){  
                    return true;  
                }  
    
                //判断是否类型相同  
                if(this.getClass() != obj.getClass()){  
                    return false;  
                }  
    
                Person person = (Person)obj;  
                return name.equals(person.name) && age==person.age;  
            } 
        }
    }
    运行结果：
    
    p1.equals(p2) : true; p1(1169863946) p2(1901116749)
    p1.equals(p3) : false; p1(1169863946) p3(2131949076)
    从结果也可以看出：p1和p2相等的情况下，hashCode()也不一定相等。
    
    2、会创建“类对应的散列表”
    这里所说的“会创建类对应的散列表”是说：我们会在HashSet, Hashtable, HashMap等等这些本质是散列表的数据结构中，用到该类。例如，会创建该类的HashSet集合。
    
    在这种情况下，该类的“hashCode() 和 equals() ”是有关系的：
    
    如果两个对象相等，那么它们的hashCode()值一定相同。这里的相等是指，通过equals()比较两个对象时返回true。
    
    如果两个对象hashCode()相等，它们并不一定相等。因为在散列表中，hashCode()相等，即两个键值对的哈希值相等。然而哈希值相等，并不一定能得出键值对相等。补充说一句：“两个不同的键值对，哈希值相等”，这就是哈希冲突。
    
    此外，在这种情况下。若要判断两个对象是否相等，除了要覆盖equals()之外，也要覆盖hashCode()函数。否则，equals()无效。
    
    举例，创建Person类的HashSet集合，必须同时覆盖Person类的equals() 和 hashCode()方法。 
    
    如果单单只是覆盖equals()方法。我们会发现，equals()方法没有达到我们想要的效果。
    
    import java.util.*;
    import java.lang.Comparable;
    
    /**
     * @desc 比较equals() 返回true 以及 返回false时， hashCode()的值。
     *
     */
    public class ConflictHashCodeTest1{
    
        public static void main(String[] args) {
            // 新建Person对象，
            Person p1 = new Person("eee", 100);
            Person p2 = new Person("eee", 100);
            Person p3 = new Person("aaa", 200);
    
            // 新建HashSet对象 
            HashSet set = new HashSet();
            set.add(p1);
            set.add(p2);
            set.add(p3);
    
            // 比较p1 和 p2， 并打印它们的hashCode()
            System.out.printf("p1.equals(p2) : %s; p1(%d) p2(%d)\n", p1.equals(p2), p1.hashCode(), p2.hashCode());
            // 打印set
            System.out.printf("set:%s\n", set);
        }
    
        /**
         * @desc Person类。
         */
        private static class Person {
            int age;
            String name;
    
            public Person(String name, int age) {
                this.name = name;
                this.age = age;
            }
    
            public String toString() {
                return "("+name + ", " +age+")";
            }
    
            /** 
             * @desc 覆盖equals方法 
             */  
            @Override
            public boolean equals(Object obj){  
                if(obj == null){  
                    return false;  
                }  
    
                //如果是同一个对象返回true，反之返回false  
                if(this == obj){  
                    return true;  
                }  
    
                //判断是否类型相同  
                if(this.getClass() != obj.getClass()){  
                    return false;  
                }  
    
                Person person = (Person)obj;  
                return name.equals(person.name) && age==person.age;  
            } 
        }
    }
    运行结果：
    
    p1.equals(p2) : true; p1(1169863946) p2(1690552137)
    set:[(eee, 100), (eee, 100), (aaa, 200)]
    结果分析：
    
    我们重写了Person的equals()。但是，很奇怪的发现：HashSet中仍然有重复元素：p1 和 p2。为什么会出现这种情况呢？
    
    这是因为虽然p1 和 p2的内容相等，但是它们的hashCode()不等；所以，HashSet在添加p1和p2的时候，认为它们不相等。
    
    那同时覆盖equals() 和 hashCode()方法呢？
    
    import java.util.*;
    import java.lang.Comparable;
    
    /**
     * @desc 比较equals() 返回true 以及 返回false时， hashCode()的值。
     *
     */
    public class ConflictHashCodeTest2{
    
        public static void main(String[] args) {
            // 新建Person对象，
            Person p1 = new Person("eee", 100);
            Person p2 = new Person("eee", 100);
            Person p3 = new Person("aaa", 200);
            Person p4 = new Person("EEE", 100);
    
            // 新建HashSet对象 
            HashSet set = new HashSet();
            set.add(p1);
            set.add(p2);
            set.add(p3);
    
            // 比较p1 和 p2， 并打印它们的hashCode()
            System.out.printf("p1.equals(p2) : %s; p1(%d) p2(%d)\n", p1.equals(p2), p1.hashCode(), p2.hashCode());
            // 比较p1 和 p4， 并打印它们的hashCode()
            System.out.printf("p1.equals(p4) : %s; p1(%d) p4(%d)\n", p1.equals(p4), p1.hashCode(), p4.hashCode());
            // 打印set
            System.out.printf("set:%s\n", set);
        }
    
        /**
         * @desc Person类。
         */
        private static class Person {
            int age;
            String name;
    
            public Person(String name, int age) {
                this.name = name;
                this.age = age;
            }
    
            public String toString() {
                return name + " - " +age;
            }
    
            /** 
             * @desc重写hashCode 
             */  
            @Override
            public int hashCode(){  
                int nameHash =  name.toUpperCase().hashCode();
                return nameHash ^ age;
            }
    
            /** 
             * @desc 覆盖equals方法 
             */  
            @Override
            public boolean equals(Object obj){  
                if(obj == null){  
                    return false;  
                }  
    
                //如果是同一个对象返回true，反之返回false  
                if(this == obj){  
                    return true;  
                }  
    
                //判断是否类型相同  
                if(this.getClass() != obj.getClass()){  
                    return false;  
                }  
    
                Person person = (Person)obj;  
                return name.equals(person.name) && age==person.age;  
            } 
        }
    }
    运行结果：
    
    p1.equals(p2) : true; p1(68545) p2(68545)
    p1.equals(p4) : false; p1(68545) p4(68545)
    set:[aaa - 200, eee - 100]
    结果分析：
    
    这下，equals()生效了，HashSet中没有重复元素。
    
    比较p1和p2，我们发现：它们的hashCode()相等，通过equals()比较它们也返回true。所以，p1和p2被视为相等。
    
    比较p1和p4，我们发现：虽然它们的hashCode()相等；但是，通过equals()比较它们返回false。所以，p1和p4被视为不相等。
    
    原则
    1.同一个对象（没有发生过修改）无论何时调用hashCode()得到的返回值必须一样。
    如果一个key对象在put的时候调用hashCode()决定了存放的位置，而在get的时候调用hashCode()得到了不一样的返回值，这个值映射到了一个和原来不一样的地方，那么肯定就找不到原来那个键值对了。
    
    2.hashCode()的返回值相等的对象不一定相等，通过hashCode()和equals()必须能唯一确定一个对象。不相等的对象的hashCode()的结果可以相等。hashCode()在注意关注碰撞问题的时候，也要关注生成速度问题，完美hash不现实。
    
    3.一旦重写了equals()函数（重写equals的时候还要注意要满足自反性、对称性、传递性、一致性），就必须重写hashCode()函数。而且hashCode()的生成哈希值的依据应该是equals()中用来比较是否相等的字段。
    
    如果两个由equals()规定相等的对象生成的hashCode不等，对于hashMap来说，他们很可能分别映射到不同位置，没有调用equals()比较是否相等的机会，两个实际上相等的对象可能被插入不同位置，出现错误。其他一些基于哈希方法的集合类可能也会有这个问题

11、redis缓存问题
    
    1. 什么是缓存雪崩？怎么解决？
    图片
    
    通常，我们会使用缓存用于缓冲对 DB 的冲击，如果缓存宕机，所有请求将直接打在 DB，造成 DB 宕机——从而导致整个系统宕机。
    
    如何解决呢？
    图片
    
    2 种策略（同时使用）：
    
    对缓存做高可用，防止缓存宕机
    
    使用断路器，如果缓存宕机，为了防止系统全部宕机，限制部分流量进入 DB，保证部分可用，其余的请求返回断路器的默认值。
    
    2. 什么是缓存穿透？怎么解决？
    解释 1：缓存查询一个没有的 key，同时数据库也没有，如果黑客大量的使用这种方式，那么就会导致 DB 宕机。
    
    解决方案：我们可以使用一个默认值来防止，例如，当访问一个不存在的 key，然后再去访问数据库，还是没有，那么就在缓存里放一个占位符，下次来的时候，检查这个占位符，如果发生时占位符，就不去数据库查询了，防止 DB 宕机。
    
    解释 2：大量请求查询一个刚刚失效的 key，导致 DB 压力倍增，可能导致宕机，但实际上，查询的都是相同的数据。
    
    解决方案：可以在这些请求代码加上双重检查锁。但是那个阶段的请求会变慢。不过总比 DB 宕机好。
    
    3. 什么是缓存并发竞争？怎么解决？
    解释：多个客户端写一个 key，如果顺序错了，数据就不对了。但是顺序我们无法控制。
    
    解决方案：使用分布式锁，例如 zk，同时加入数据的时间戳。同一时刻，只有抢到锁的客户端才能写入，同时，写入时，比较当前数据的时间戳和缓存中数据的时间戳。
    
    4.什么是缓存和数据库双写不一致？怎么解决？
    解释：连续写数据库和缓存，但是操作期间，出现并发了，数据不一致了。
    
    通常，更新缓存和数据库有以下几种顺序：
    
    先更新数据库，再更新缓存。
    
    先删缓存，再更新数据库。
    
    先更新数据库，再删除缓存。
    
    三种方式的优劣来看一下：
    
    先更新数据库，再更新缓存。
    
    这么做的问题是：当有 2 个请求同时更新数据，那么如果不使用分布式锁，将无法控制最后缓存的值到底是多少。也就是并发写的时候有问题。
    
    先删缓存，再更新数据库。
    
    这么做的问题：如果在删除缓存后，有客户端读数据，将可能读到旧数据，并有可能设置到缓存中，导致缓存中的数据一直是老数据。
    
    有 2 种解决方案：
    
    使用“双删”，即删更删，最后一步的删除作为异步操作，就是防止有客户端读取的时候设置了旧值。
    
    使用队列，当这个 key 不存在时，将其放入队列，串行执行，必须等到更新数据库完毕才能读取数据。
    
    总的来讲，比较麻烦。
    
    先更新数据库，再删除缓存
    
    这个实际是常用的方案，但是有很多人不知道，这里介绍一下，这个叫 Cache Aside Pattern，老外发明的。如果先更新数据库，再删除缓存，那么就会出现更新数据库之前有瞬间数据不是很及时。
    
    同时，如果在更新之前，缓存刚好失效了，读客户端有可能读到旧值，然后在写客户端删除结束后再次设置了旧值，非常巧合的情况。
    
    有 2 个前提条件：缓存在写之前的时候失效，同时，在写客户度删除操作结束后，放置旧数据 —— 也就是读比写慢。设置有的写操作还会锁表。
    
    所以，这个很难出现，但是如果出现了怎么办？使用双删！！！记录更新期间有没有客户端读数据库，如果有，在更新完数据库之后，执行延迟删除。
    
    还有一种可能，如果执行更新数据库，准备执行删除缓存时，服务挂了，执行删除失败怎么办？？？
    
    这就坑了！！！不过可以通过订阅数据库的 binlog 来删除。

12、  分布式系统接口，如何避免表单的重复提交？  
    
    
    关于怎么实现承载更多用户量的系统，一直是我重点关注的一个技术方向。改造架构提高承载力，通常来讲分为两个大方向，互相配合实现。
    
    硬件架构改进，主要是使用阿里云这种多组件的云环境：通过负载均衡SLB，模版克隆的云服务器ECS，云数据库RDS，共享对象存储OSS等不同职责的云产品组合实现。
    
    软件架构优化，主要是软件代码开发的规范：业务解耦合，架构微服务，单机无状态化，文件存储共享等
    
    在分布式系统的学习途中也不断见识新的知识点，今天要说的就是软件开发时候对于接口服务的“幂等性”实现！
    
    幂等性
    效果：系统对某接口的多次请求，都应该返回同样的结果！（网络访问失败的场景除外）
    
    目的：避免因为各种原因，重复请求导致的业务重复处理
    
    重复请求场景案例：
    1，客户端第一次请求后，网络异常导致收到请求执行逻辑但是没有返回给客户端，客户端的重新发起请求
    
    2，客户端迅速点击按钮提交，导致同一逻辑被多次发送到服务器
    
    简单来划分，业务逻辑无非都可以归纳为增删改查！
    
    对于查询，内部不包含其他操作，属于只读性质的那种业务必然符合幂等性要求的。
    
    对于删除，重复做删除请求至少不会造成数据杂乱，不过也有些场景更希望重复点击提示的是删除成功，而不是目标不存在的提示。
    
    对于新增和修改，这里是今天要重点关注的部分：新增，需要避免重复插入；修改，避免进行无效的重复修改；
    
    幂等性的实现方式
    实现方法：客户端做某一请求的时候带上识别参数标识，服务端对此标识进行识别，重复请求则重复返回第一次的结果即可。
    
    举个栗子：比如添加请求的表单里，在打开添加表单页面的时候，就生成一个AddId标识，这个AddId跟着表单一起提交到后台接口。
    
    后台接口根据这个AddId，服务端就可以进行缓存标记并进行过滤，缓存值可以是AddId作为缓存key，返回内容作为缓存Value，这样即使添加按钮被多次点下也可以识别出来。
    
    这个AddId什么时候更新呢？只有在保存成功并且清空表单之后，才变更这个AddId标识，从而实现新数据的表单提交
    
    个人看法： 应该由服务端返回，然后设置一个过期时间【？如果用户停留很久？】好吧，还是上面的方法

13、单点登录的问题
    
    注：单点登录原理是一个重要知识点，也常被问及，很多童鞋照葫芦画瓢搭建过单点登录，但是被问到原理时可能说不出来，下面简单介绍，抛砖引玉，希望对大家有所帮助。
    
    单点登录在现在的系统架构中广泛存在，他将多个子系统的认证体系打通，实现了一个入口多处使用，而在架构单点登录时，也会遇到一些小问题，在不同的应用环境中可以采用不同的单点登录实现方案来满足需求。
    
    我将以我所遇到的应用环境以及在其中所经历的各个阶段与大家分享，若有不足，希望各位不吝赐教。
    
    一、共享Session
    共享Session可谓是实现单点登录最直接、最简单的方式。将用户认证信息保存于Session中，即以Session内存储的值为用户凭证，这在单个站点内使用是很正常也很容易实现的，而在用户验证、用户信息管理与业务应用分离的场景下即会遇到单点登录的问题，在应用体系简单，子系统很少的情况下，可以考虑采用Session共享的方法来处理这个问题。
    
    图片
    
    这个架构我使用了基于Redis的Session共享方案。将Session存储于Redis上，然后将整个系统的全局Cookie Domain设置于顶级域名上，这样SessionID就能在各个子系统间共享。
    
    这个方案存在着严重的扩展性问题，首先，ASP.NET的Session存储必须为SessionStateItemCollection对象，而存储的结构是经过序列化后经过加密存储的。
    
    并且当用户访问应用时，他首先做的就是将存储容器里的所有内容全部取出，并且反序列化为SessionStateItemCollection对象。这就决定了他具有以下约束：
    
    Session中所涉及的类型必须是子系统中共同拥有的（即程序集、类型都需要一致），这导致Session的使用受到诸多限制；
    
    跨顶级域名的情况完全无法处理；
    
    二、基于OpenId的单点登录
    这种单点登录将用户的身份标识信息简化为OpenId存放于客户端，当用户登录某个子系统时，将OpenId传送到服务端，服务端根据OpenId构造用户验证信息，多用于C/S与B/S相结合的系统，流程如下：
    
    图片
    
    由上图可以看到，这套单点登录依赖于OpenId的传递，其验证的基础在于OpenId的存储以及发送。
    
    当用户第一次登录时，将用户名密码发送给验证服务；
    
    验证服务将用户标识OpenId返回到客户端；
    
    客户端进行存储；
    
    访问子系统时，将OpenId发送到子系统；
    
    子系统将OpenId转发到验证服务；
    
    验证服务将用户认证信息返回给子系统；
    
    子系统构建用户验证信息后将授权后的内容返回给客户端。
    
    这套单点登录验证机制的主要问题在于他基于C/S架构下将用户的OpenId存储于客户端，在子系统之间发送OpenId，而B/S模式下要做到这一点就显得较为困难。为了处理这个问题我们将引出下一种方式，这种方式将解决B/S模式下的OpenId的存储、传递问题。
    
    三、基于Cookie的OpenId存储方案
    我们知道，Cookie的作用在于充当一个信息载体在Server端和Browser端进行信息传递，而Cookie一般是以域名为分割的，例如a.xxx.com与b.xxx.com的Cookie是不能互相访问的，但是子域名是可以访问上级域名的Cookie的。即a.xxx.com和b.xxx.com是可以访问xxx.com下的Cookie的，于是就能将顶级域名的Cookie作为OpenId的载体。
    
    图片
    
    验证步骤和上第二个方法非常相似：
    
    在提供验证服务的站点里登录；
    
    将OpenId写入顶级域名Cookie里；
    
    访问子系统（Cookie里带有OpenId）
    
    子系统取出OpenId通过并向验证服务发送OpenId
    
    返回用户认证信息
    
    返回授权后的内容
    
    在以上两种方法中我们都可以看到通过OpenId解耦了Session共享方案中的类型等问题，并且构造用户验证信息将更灵活，子系统间的验证是相互独立的，但是在第三种方案里，我们基于所有子系统都是同一个顶级域名的假设，而在实际生产环境里有多个域名是很正常的事情，那么就不得不考虑跨域问题究竟如何解决。
    
    四、B/S多域名环境下的单点登录处理
    在多个顶级域名的情况下，我们将无法让各个子系统的OpenId共享。处理B/S环境下的跨域问题，我们首先就应该想到JSONP的方案。
    
    图片
    
    验证步骤如下：
    
    用户通过登录子系统进行用户登录；
    
    用户登录子系统记录了用户的登录状态、OpenId等信息；
    
    用户使用业务子系统；
    
    若用户未登录业务子系统则将用户跳转至用户登录子系统；
    
    用户子系统通过JSONP接口将用户OpenId传给业务子系统；
    
    业务子系统通过OpenId调用验证服务；
    
    验证服务返回认证信息、业务子系统构造用户登录凭证；（此时用户客户端已经与子业务系统的验证信息已经一一对应）
    
    将用户登录结果返回用户登录子系统，若成功登录则将用户跳转回业务子系统；
    
    将授权后的内容返回客户端；
    
    五、安全问题
    经过以上步骤，跨域情况下的单点登录问题已经可以得到解决。而在整个开发过程初期，我们采用用户表中记录一个OpenId字段来保存用户OpenId，而这个机制下很明显存在一些安全性、扩展性问题。这个扩展性问题主要体现在一个方面：OpenId的安全性和用户体验的矛盾。
    
    整个单点登录的机制决定了OpenId是会出现在客户端的，所以OpenId需要有过期机制，假如用户在一个终端登录的话可以选择在用户每次登录或者每次退出时刷新OpenId，而在多终端登录的情况下就会出现矛盾：当一个终端刷新了OpenId之后其他终端将无法正常授权。
    
    而最终，我采用了单用户多OpenId的解决方案。每次用户通过用户名/密码登录时，产生一个OpenId保存在Redis里，并且设定过期时间，这样多个终端登录就会有多个OpenId与之对应，不再会存在一个OpenId失效所有终端验证都失效的情况。【2个及多个token】
    
14、reds过期时间
    
    
    在日常开发中，我们使用 Redis 存储 key 时通常会设置一个过期时间，但是 Redis 是怎么删除过期的 key，而且 Redis 是单线程的，删除 key 会不会造成阻塞。要搞清楚这些，就要了解 Redis 的过期策略和内存淘汰机制。
    
    Redis采用的是定期删除 + 懒惰删除策略。
    
    定期删除策略
    Redis 会将每个设置了过期时间的 key 放入到一个独立的字典中，默认每 100ms 进行一次过期扫描：
    
    随机抽取 20 个 key
    
    删除这 20 个key中过期的key
    
    如果过期的 key 比例超过 1/4，就重复步骤 1，继续删除。
    
    为什不扫描所有的 key？
    
    Redis 是单线程，全部扫描岂不是卡死了。而且为了防止每次扫描过期的 key 比例都超过 1/4，导致不停循环卡死线程，Redis 为每次扫描添加了上限时间，默认是 25ms。
    
    如果客户端将超时时间设置的比较短，比如 10ms，那么就会出现大量的链接因为超时而关闭，业务端就会出现很多异常。而且这时你还无法从 Redis 的 slowlog 中看到慢查询记录，因为慢查询指的是逻辑处理过程慢，不包含等待时间。
    
    如果在同一时间出现大面积 key 过期，Redis 循环多次扫描过期词典，直到过期的 key 比例小于 1/4。这会导致卡顿，而且在高并发的情况下，可能会导致缓存雪崩。
    
    为什么 Redis 为每次扫描添的上限时间是 25ms，还会出现上面的情况？
    
    因为 Redis 是单线程，每个请求处理都需要排队，而且由于 Redis 每次扫描都是 25ms，也就是每个请求最多 25ms，100 个请求就是 2500ms。
    
    如果有大批量的 key 过期，要给过期时间设置一个随机范围，而不宜全部在同一时间过期，分散过期处理的压力。
    
    从库的过期策略
    从库不会进行过期扫描，从库对过期的处理是被动的。主库在 key 到期时，会在 AOF 文件里增加一条 del 指令，同步到所有的从库，从库通过执行这条 del 指令来删除过期的 key。
    
    因为指令同步是异步进行的，所以主库过期的 key 的 del 指令没有及时同步到从库的话，会出现主从数据的不一致，主库没有的数据在从库里还存在。
    
    懒惰删除策略
    Redis 为什么要懒惰删除(lazy free)？
    
    删除指令 del 会直接释放对象的内存，大部分情况下，这个指令非常快，没有明显延迟。不过如果删除的 key 是一个非常大的对象，比如一个包含了千万元素的 hash，又或者在使用 FLUSHDB 和 FLUSHALL 删除包含大量键的数据库时，那么删除操作就会导致单线程卡顿。
    
    redis 4.0 引入了 lazyfree 的机制，它可以将删除键或数据库的操作放在后台线程里执行， 从而尽可能地避免服务器阻塞。
    
    unlink
    unlink 指令，它能对删除操作进行懒处理，丢给后台线程来异步回收内存。
    
    > unlink key
    OK
    flush
    flushdb 和 flushall 指令，用来清空数据库，这也是极其缓慢的操作。Redis 4.0 同样给这两个指令也带来了异步化，在指令后面增加 async 参数就可以将整棵大树连根拔起，扔给后台线程慢慢焚烧。
    
    > flushall async
    OK
    异步队列
    主线程将对象的引用从「大树」中摘除后，会将这个 key 的内存回收操作包装成一个任务，塞进异步任务队列，后台线程会从这个异步队列中取任务。任务队列被主线程和异步线程同时操作，所以必须是一个线程安全的队列。
    
    不是所有的 unlink 操作都会延后处理，如果对应 key 所占用的内存很小，延后处理就没有必要了，这时候 Redis 会将对应的 key 内存立即回收，跟 del 指令一样。
    
    更多异步删除点
    Redis 回收内存除了 del 指令和 flush 之外，还会存在于在 key 的过期、LRU 淘汰、rename 指令以及从库全量同步时接受完 rdb 文件后会立即进行的 flush 操作。
    
    Redis4.0 为这些删除点也带来了异步删除机制，打开这些点需要额外的配置选项。
    
    slave-lazy-flush 从库接受完 rdb 文件后的 flush 操作
    
    lazyfree-lazy-eviction 内存达到 maxmemory 时进行淘汰
    
    lazyfree-lazy-expire key 过期删除
    
    lazyfree-lazy-server-del rename 指令删除 destKey
    
    内存淘汰机制
    Redis 的内存占用会越来越高。Redis 为了限制最大使用内存，提供了 redis.conf 中的
    配置参数 maxmemory。当内存超出 maxmemory，Redis 提供了几种内存淘汰机制让用户选择，配置 maxmemory-policy：
    
    noeviction：当内存超出 maxmemory，写入请求会报错，但是删除和读请求可以继续。（使用这个策略，疯了吧）
    
    allkeys-lru：当内存超出 maxmemory，在所有的 key 中，移除最少使用的key。只把 Redis 既当缓存是使用这种策略。（推荐）。
    
    allkeys-random：当内存超出 maxmemory，在所有的 key 中，随机移除某个 key。（应该没人用吧）
    
    volatile-lru：当内存超出 maxmemory，在设置了过期时间 key 的字典中，移除最少使用的 key。把 Redis 既当缓存，又做持久化的时候使用这种策略。
    
    volatile-random：当内存超出 maxmemory，在设置了过期时间 key 的字典中，随机移除某个key。
    
    volatile-ttl：当内存超出 maxmemory，在设置了过期时间 key 的字典中，优先移除 ttl 小的。
    
    LRU 算法
    实现 LRU 算法除了需要 key/value 字典外，还需要附加一个链表，链表中的元素按照一定的顺序进行排列。当空间满的时候，会踢掉链表尾部的元素。当字典的某个元素被访问时，它在链表中的位置会被移动到表头。所以链表的元素排列顺序就是元素最近被访问的时间顺序。
    
    使用 Python 的 OrderedDict(双向链表 + 字典) 来实现一个简单的 LRU 算法：
    
    from collections import OrderedDict
    
    class LRUDict(OrderedDict):
    
        def __init__(self, capacity):
            self.capacity = capacity
            self.items = OrderedDict()
    
        def __setitem__(self, key, value):
            old_value = self.items.get(key)
            if old_value is not None:
                self.items.pop(key)
                self.items[key] = value
            elif len(self.items) < self.capacity:
                self.items[key] = value
            else:
                self.items.popitem(last=True)
                self.items[key] = value
    
        def __getitem__(self, key):
            value = self.items.get(key)
            if value is not None:
                self.items.pop(key)
                self.items[key] = value
            return value
    
        def __repr__(self):
            return repr(self.items)
    
    
    d = LRUDict(10)
    
    for i in range(15):
        d[i] = i
    print d
    近似 LRU 算法
    Redis 使用的并不是完全 LRU 算法。不使用 LRU 算法，是为了节省内存，Redis 采用的是随机LRU算法，Redis 为每一个 key 增加了一个24 bit的字段，用来记录这个 key 最后一次被访问的时间戳。
    
    注意 Redis 的 LRU 淘汰策略是懒惰处理，也就是不会主动执行淘汰策略，当 Redis 执行写操作时，发现内存超出 maxmemory，就会执行 LRU 淘汰算法。这个算法就是随机采样出5(默认值)个 key，然后移除最旧的 key，如果移除后内存还是超出 maxmemory，那就继续随机采样淘汰，直到内存低于 maxmemory 为止。
    
    如何采样就是看 maxmemory-policy 的配置，如果是 allkeys 就是从所有的 key 字典中随机，如果是 volatile 就从带过期时间的 key 字典中随机。每次采样多少个 key 看的是 maxmemory_samples 的配置，默认为 5。
    
    LFU
    Redis 4.0 里引入了一个新的淘汰策略 —— LFU（Least Frequently Used） 模式，作者认为它比 LRU 更加优秀。
    
    LFU 表示按最近的访问频率进行淘汰，它比 LRU 更加精准地表示了一个 key 被访问的热度。
    
    如果一个 key 长时间不被访问，只是刚刚偶然被用户访问了一下，那么在使用 LRU 算法下它是不容易被淘汰的，因为 LRU 算法认为当前这个 key 是很热的。而 LFU 是需要追踪最近一段时间的访问频率，如果某个 key 只是偶然被访问一次是不足以变得很热的，它需要在近期一段时间内被访问很多次才有机会被认为很热。
    
    Redis 对象的热度
    
    Redis 的所有对象结构头中都有一个 24bit 的字段，这个字段用来记录对象的热度。
    
    // redis 的对象头
    typedef struct redisObject {
        unsigned type:4; // 对象类型如 zset/set/hash 等等
        unsigned encoding:4; // 对象编码如 ziplist/intset/skiplist 等等
        unsigned lru:24; // 对象的「热度」
        int refcount; // 引用计数
        void *ptr; // 对象的 body
    } robj;
    LRU 模式
    
    在 LRU 模式下，lru 字段存储的是 Redis 时钟 server.lruclock，Redis 时钟是一个 24bit 的整数，默认是 Unix 时间戳对 2^24 取模的结果，大约 97 天清零一次。当某个 key 被访问一次，它的对象头的 lru 字段值就会被更新为 server.lruclock。
    
    LFU 模式
    
    在 LFU 模式下，lru 字段 24 个 bit 用来存储两个值，分别是 ldt(last decrement time) 和 logc(logistic counter)。
    
    logc 是 8 个 bit，用来存储访问频次，因为 8 个 bit 能表示的最大整数值为 255，存储频次肯定远远不够，所以这 8 个 bit 存储的是频次的对数值，并且这个值还会随时间衰减。如果它的值比较小，那么就很容易被回收。为了确保新创建的对象不被回收，新对象的这 8 个 bit 会初始化为一个大于零的值，默认是 LFU_INIT_VAL=5。
    
    ldt 是 16 个位，用来存储上一次 logc 的更新时间，因为只有 16 位，所以精度不可能很高。它取的是分钟时间戳对 2^16 进行取模，大约每隔 45 天就会折返。
    
    同 LRU 模式一样，我们也可以使用这个逻辑计算出对象的空闲时间，只不过精度是分钟级别的。图中的 server.unixtime 是当前 redis 记录的系统时间戳，和 server.lruclock 一样，它也是每毫秒更新一次。
    
15、进程和线程的区别
    