##GridWorld
Ubuntu22.04.1配置Java8环境
[toc]
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