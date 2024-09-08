# SpringSimulator
> 简单模拟spring


模拟spring的步骤简单记录：

现在让我们来模仿下日常开发一个项目，首先我们会新建一个spring项目，

然后我们会

- 新建一个controller或者service，给他放上@Controller\@Service等注解，相信我们稍微了解过的都知道这些注解其实除了名称之外和@Component没什么区别，只做区分，所以接下来我们统一用@Component来表示，标这个注解的目的是把这些类加入到spring的bean工厂里，由spring来帮我们管理，比如：依赖注入等。但只标注解不行，spring不知道哪些地方归他管理，所以还需要添加另一个注解@ComponentScan来开启扫描这些类，让spring知道哪些位置由他来处理，因此第一步，我们来模拟 **@ComponentScan**
- 