## 中山大学软件工程学院20级中级实训课程项目
##### 项目名称：GridWorld游戏开发
##### 学院：软件工程学院
##### 年级：20级
##### 指导老师：黄袁
##### 助教：林泽炜
##### 小组成员
| 姓名 | 学号 |
| ---- | ---- |
| 冯洪江 | 20331012 |
| 储震坤 | 20318068 |
| 黄能 | 20331014 |
| 宋皓 ||
| 李昱航| 20331022 |
##### [项目成果仓库](https://github.com/Joshvon/GridWorld)
下载项目
```shell
git@github.com:Joshvon/GridWorld.git
```
编译项目
```shell
ant
```
运行项目，比如运行一个A*算法自动走迷宫
```shell
cd classes
java maze.SmarterMazeRunner
```
### 项目背景
该项目基于`Java.swing`和`Java.awt`等工具包构建的GridWorld一个图形化环境用于可视化对象在二维网格中的交互。在这个案例中，我们设计和制造各种Actor的对象，将它们添加到一个网格中，并且根据一定的规则决定Actor的行为。此外还完成了N-Puzzle（华容道）和MazeBug（迷宫）等拓展任务
### 完成情况
小组成员根据开发手册的内容进行同步开发，小组成员完成所有内容。项目运行环境为`Linux`，使用`jdk1.8`并且使用`Ant`进行编译
1. 在个人电脑上安装虚拟机程序并配置好`jdk`和`Ant`环境
2. 编写`ant`文件（项目仓库下的`mybuild.xml`文件）并运行第一个程序（`project/fistProject/BugRunner`）
3. [开发记录](https://github.com/Joshvon/GridWorld/blob/main/exp.md)，即项目仓库下的exp.md
### 项目结构
`/framework` 项目主要框架，包括网格，游戏角色，gui等，**MazeBug**也在其中
`/N-Puzzle` N-Puzzle（华容道）代码
`/projects` 开发主要代码，包括各种`Actor`对象和对应的`Runner`运行类
`/resources` `MazeBug`迷宫游戏运行所需地图

### 部分代码及运行截图
`/project/Jumper/Jumper.java`
```java
package Jumper;

import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;

public class Jumper extends Bug
{
    public boolean canJump() {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return false;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if(!gr.isValid(next))
            return false;
        Location next_next = next.getAdjacentLocation(getDirection());
        if (!gr.isValid(next_next))
            return false;
        Actor neighbor2 = gr.get(next_next);
        return (neighbor2 == null) || (neighbor2 instanceof Flower);
    }
    public void jump()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location next_next = next.getAdjacentLocation(getDirection());
        if (gr.isValid(next_next))
            moveTo(next_next);
        else
            removeSelfFromGrid();
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
    }
    public void act() {
        if(canJump())
            jump();
        else
            turn();
    }
}
```

`/framework/info/gridworld/maze/SmarterMazeBug.java`
```java
package info.gridworld.maze;

import info.gridworld.grid.*;
import info.gridworld.actor.Actor;

import java.awt.Color;
import java.util.ArrayList;

public class SmarterMazeBug extends MazeBug{

    private int[] factor = {0, 0, 0, 0};

    public SmarterMazeBug() {
        setColor(Color.GREEN);
		last = new Location(0, 0);
    }

    @Override
    public boolean canMove() {
        Grid<Actor> gr = getGrid();
		if (gr == null)
			return false;
		Location loc = getLocation();
		ArrayList<Location> locs = getValid(loc);
        for(int i = locs.size() -1; i >= 0; i--) {
			Location location = locs.get(i);
			if(hasVisited.contains(location)) locs.remove(i);
		}
        if(locs.size() > 0) {
			next = getNext(locs);
			locs.add(loc);
			crossLocation.push(locs);
		}
        else {
            ArrayList<Location> l = crossLocation.pop();
            next = l.get(l.size() - 1);
            if (next.getDirectionToward(getLocation()) == Location.SOUTH) {
                // north
                factor[1]--;
            }
            if (next.getDirectionToward(getLocation()) == Location.NORTH) {
                // south
                factor[0]--;
            }
            if (next.getDirectionToward(getLocation()) == Location.WEST) {
                // east
                factor[2]--;
            }
            if (next.getDirectionToward(getLocation()) == Location.EAST) {
                // west
                factor[3]--;
            }
        }
        return true;
    }

    private Location getNext(ArrayList<Location> locs) {
        Location[] validloc = { null, null, null, null };
        for (int i = 0; i < locs.size(); i++) {
            if (locs.get(i).getDirectionToward(getLocation()) == Location.SOUTH) {
                // north
                validloc[0] = locs.get(i);
            }
            if (locs.get(i).getDirectionToward(getLocation()) == Location.NORTH) {
                // south
                validloc[1] = locs.get(i);
            }
            if (locs.get(i).getDirectionToward(getLocation()) == Location.WEST) {
                // east
                validloc[3] = locs.get(i);
            }
            if (locs.get(i).getDirectionToward(getLocation()) == Location.EAST) {
                // west
                validloc[2] = locs.get(i);
            }
        }
        int target = -1;
        for(int i = 0; i < validloc.length; i++) {
            if(validloc[i] != null) {
                if(target == -1) target = i;
                if(factor[i] > factor[target]) target = i;
            }
        }
        factor[target]++;
        return validloc[target];
    }
}
```

`迷宫游戏运行截图`
[](img/mazebug.png)