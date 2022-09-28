##GridWorld
Ubuntu22.04.1配置Java8环境
[toc]

---
###Part1
####Set1
`1. `当bug八个方向都有rock的时候无法移动到一个新位置
`2. `当前方无障碍时直行，碰到障碍向前进方向右侧45°移动，碰到边界向前进方向右侧移动
`3. `按八个方向原地旋转
`4. `flower标记
`5. `碰到边界向前进方向右侧移动
`6.	`碰到障碍向前进方向右侧45°移动
`7. `flower不会移动
`8. `flower会变颜色
`9. `好像没有
`10. `Bug碰到flower不会变化方向，碰到rock会
####Exercises
`1. `
|Degrees|Compass Direction|
|:---:|:---:|
|0|North|
|45|Northeast|
|90|East|
|135|Southeast|
|180|South|
|225|Southwest|
|270|West|
|315|Northwest|
|360|North|
`2. `可以移动到grid的任意位置并会清除掉该格的rock，若输入的位置超出grid范围则会报错
`3.	`void setColor(java.awt.Color)
`4.	`bug会消失

---
###Part2
####Set2
`1. `规定**BoxBug**移动步数，移动达到规定步数将会执行**turn()**函数
`2. `记录**BoxBug**已移动步数来与**sidelength**比较
`3. `因为**BoxBug**移动路线要求是一个方形
`4. `因为**BoxBug**继承了**Bug**类，可以使用**Bug**类中的**move()**方法
`5. `不一定，当**BoxBug**周围有障碍时，方形会改变
`6. `会改变，比如**BoxBug**碰到**actor**就会改变其路径
`7. `当**BoxBug**执行完构造函数到第一次移动之前；当**steps>sidelegth**或者无法移动时，再次执行act()方法会将steps置0
####Exercises
`1. `如果空间足够大且路径无障碍**CircleBug**路线呈正六边形
`2. `/projects/spiralBug
`3. `/projects/zBug
`4. `/projects/dancingBug
`5. `
初始化一个BoxBug对象
 `BoxBug tom = new BoxBug(5);`
使用ActorWorld中的add()方法
 `world.add(new Location(1, 2), tom);`

---
###Part3
####Set3
`1. ` `int row1 = loc1.getRow();`
`2. ` `false`
`3. ` 一个Location类，row和col分别为4和4
`4. `135
`5. `一个location对应的方格的八个方向正好是八个方格，同时这八个方格与其row值和col值相差0或1，则可以根据不同方向来计算对应方格location
####Set4
`1. `通过`getNumRow()`和`getNumCols()`函数获取grid的行数和列数，然后遍历每一个`location`，使用`get(Location loc)`函数来获取每一个location中的object，当其返回值不为空，则将返回值加入到一个ArrayList中，遍历完成后读取ArrayList的大小；grid的方格数减去物体数即为空方格数
`2. `使用`isValid(Location loc)`函数，即通过`getNumRow()`和`getNumCols()`函数获取grid的行数和列数，然后比较(10, 10)是否在范围内
`3. `因为Grid是一个interface，我们可以在AbstractGrid、BoundedGrid和UnboundedGrid中找到方法的实现
`4. `不一定，当使用数组时，可以使用[]来直接访问数组元素；而ArrayList可以动态扩充，数组不行
####Set5
`1. ` `grid location direction color`
`2. `北方和蓝色
`3. `因为Actor类需要有成员变量，而接口不能有成员变量
`4. `/projects/actorTest