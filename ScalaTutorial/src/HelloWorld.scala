/**
 * @author gudongxian
 * @version 0.1
 *          2020/11/21
 */
object HelloWorld {
  /**
   * 这是一个 Scala 程序
   * 这是一行注释
   * 这里演示了多行注释
   */
  def main(args: Array[String]): Unit = {
    // 输出 Hello World
    // 这是一个单行注释
    println("Hello,World")
    val x = scala.Symbol("X")
    println(x)

    println("Hello\tWorld\n\n")

    //    var VariableName : DataType [=  Initial Value]
    // 变量
    var foo: String = "Foo"
    // 常量
    val bar: String = "Bar"
    printf("%s\t%s", foo, bar)


  }
}

