1、lambda表达式
  button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
      System.out.println("button clicked");
      }
  });
  
  button.addActionListener(event -> System.out.println("button clicked"));
  
  lambda表达式都是静态的，引入的变量 隐性变成final,引用的是值而不是变量
  
  lambda 表达式是一个匿名方法，将行为像数据一样进行传递。 ?
  Lambda 表达式的常见结构： ? BinaryOperator<Integer> add = (x, y) → x + y 。
  函数接口指仅具有单个抽象方法的接口，用来表示 Lambda 表达式的类型。
  
  内部迭代将更多控制权交给了集合类。 
  和 Iterator 类似， Stream 是一种内部迭代方式。
  将 Lambda 表达式和 ? Stream 上的方法结合起来，可以完成很多常见的集合操作