1、StringBuffer 和 StringBuilder 的区别

    String是 final关键字修饰的 字符数组 final char value[]   java 9之后改为 final byte[] value，修改为byte数组
     共同点:1、都是继承了abstractStringBuilder类，都是可变的，没有使用final  2、都有父类的基本方法，append indexOf insert
           3、String类型进行改变，会生成新的String,然后将指针指向新的String对象 【少量发数据使用】。
              StringBuffer builder对自己对象进行操作
     不同点:1、StringBuffer 对方法进行了加锁，线程安全 。StringBuilder没有进行加同步锁，线程不安全  
2、基本数据类型及包装类

    1、char byte short int long float double boolean java的基本数据不是面向对象
    2、Character Byte Short...  方便集合操作
    3、自动装箱 Integer i =100 ========> Integer i =new Integer(100);Integer具有缓存 -128~127具有缓存，直接比较会相等
       当两个数都是100的时候==判断相等，当两个数都是200的时候判断不相等。
    4、自动拆箱 int a =new Integer(100); =======> int a= new Integer(100).intValue();
    5、float double的valueOf实现类型，但是没有缓存，因为在整个范围内的整数型数值的个数是有限的，而浮点数不是
3、==和equals区别

    1、==比较的是两个对象的地址是否是一样的，判断2个对象是否是一个对象（基本数据类型==比较的是值，引用数据类型==比较的是内存地址）
    2、equals也是判断2个对象是否一致，分为两种情况 ： 
        一、如果没有覆盖equals方法，等同于 ==
        二、覆盖了equals方法，内容相等，则返回true（认为这两个对象相等）    
    3、String对象创建的话，虚拟机会在常量池中查看是否已经存在值相等的对象，如果有则直接赋给当前引用。没有则在常量池中重新创建一个对象
4、hashCode 和 equals
    
    1、hashCode()是获取对象哈希码，将对象的内存地址转换成整数。
    2、hashset检查重复，首先对比hashCode，然后对比equal()检查值是否一样。如果两个都相同，则不让插入。所以必须重写hashCode()
    3、hashCode()默认行为是对堆上的对象产生独特的值，如果没有重写hashCode()，则class两个对象无论如何都不会相等（即使对象指向相同内容）
    4、hashCode()使用的方法是杂凑算法，可能产生hash碰撞，hashCode只是用来缩小查找成本
5、深拷贝和浅拷贝
    
    1、浅拷贝：对基本数据进行值传递，对引用数据类型进行引用传递的拷贝
    2、深拷贝：对基本数据进行值传递，对引用数据类型，创建一个新的对象，并复制其内容   
6、ArrayList 和 linkedList
    
    1、是否保证线程安全：两者都不是线程安全的
    2、数据结构： 一、arrayList 是 数组，linkedList 使用的是双向链表（1.6以前是循环链表 1.7取消了循环）
    3、插入和删除是否受元素位置影响：一 arrayList采用的是数组，插入删除受影响，如果是add方法，则是0(1)。如果是add(1,3)，则需要移位置，
       插入和0(n-i) 二、链表删除插入 近似于O(1),在指定位置i插入删除，近似于O(n)要移动到指定位置再插入
    4、是否支持快速随机访问：linkedList 不支持，arrayList可以通过get（index）来快速定位
    5、空间占用：arrayList空间浪费主要是 list列表会预留一定的容量空间。而linkedList 则体现在每一个元素消耗更多的空间来存放后续和直接前驱数据
7、arrayList扩容

    1、创建对象的时候，如果没有指定集合大小则为0，如果指定大小，则集合大小为指定的大小
    2、第一次调用add()方法时，集合长度变为10和addAll内容的之间的最大值
    3、再次调用add方法，先将集合扩大1.5倍，如果仍然不够，新长度为传入集合大小