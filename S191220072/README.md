# 第二次java作业

## 任务零

### example示例代码的运行

[![asciicast](https://asciinema.org/a/04EnSpcMinO4zLGEK2S4b13rQ.svg)](https://asciinema.org/a/04EnSpcMinO4zLGEK2S4b13rQ)

```
[![asciicast](https://asciinema.org/a/436507.svg)](https://asciinema.org/a/436507)
```

## 任务一

+ example的类图如下：

![](http://www.plantuml.com/plantuml/png/XLJ1Rjim3BtdAtYqr_eVkW9PLs1eYgJD1WGvo0xdQ9DbGvAnTK7txLD9CL6T0Ck5rAF8zqYBkV51kJ1sPjOOvJtSZdMDRjMxWEumW_YhGDi0QhEbqnMONksA26sse4h6igoYJ4d-bnhUhe6-fW8_9B0AJjiM1gDiolQSnuSMmsCCnVFhB6cwG_mZQWvHRz3DArG9Xh17zjx5kTAJn8wo9aTk53mPaOE1uU9TL0GQRV5vABv4GxwK6fJpIDAp3YM8lx4P0qh81noeGRHZ1tVzw7RStk9XJlRfwuBjv-MSaK-BvoKxzsj6LlVVsCxNyuViKxdCRpE_JlvZxtNGlOKXEOJ_PHa5LRZcP3PrUM6oTFdsPQdiJqw9JfeG-OKSGiyzduOR-TzOvGQz5C6eB2ZRXpswx4cibHcHtqHaG_TTDGZn4wZQuE6yz5cBKkeOQASw9ZJyyYA3b2UVMZLesx0l9zFSR8zaFcKCOoYIHZ1iUQ8weomOc4mh6zz7vzHBlWv9jspdX25zJ955xpIFbwTffKpIo1UYcSnO_WdETXyDTcY3B_-xnBGfVIMwPyb6IBHV-Ey1tGs90HZ9TYKBIWbckQfEDBpfyA6IV4xDXDoWtOsT-GS0)

+ 时序图如下（**这里我不太明白应该怎么画，所以绿色是根据Scene.main画的，而红色是简单的展开了部分函数调用链。虽说oop的特点就是对象互相间发消息，但还是有点梳理不出来这些个信息，把握不住详略**）

![](http://www.plantuml.com/plantuml/png/RP31QeGm48RlynG3FVi2Uv1jBxtq8FLOUiZgO0CnaScaPVlqZSEWAnO4PVp-_5_cciMnb3a0lAR28pOVJnCJnSyMttmal61N17EQ2HK0q77JOfUo-1GhqPEiiBi50h3nIcZ0_jkurX3z4Z_sl9HRJVQ9XTXe-IBCEjW2Pp5zbMKtClA2xoI58xhwCBixkc58FFeueIJqal7RXKBxqIrkxS-MgQGQ01pqchDk_QXD8qxctGKNJwcJMoKrkaejcZ3YSMy_RjdMVBZ3VBUQ_zRJ3nHfRmffKh-5VHI0AyMnpE4F)

+ example的设计理念。

  + 尽可能维持一个低耦合性，比如将排序抽离出去，通过传入权重值数组，返回排序的一个plan然后进行分割。准确来说，运用了Strategy设计模式，将算法剥离出去，只是定义了与算法相关的api，然后在程序中通过Geezer来委托从而使用算法。
    + 这样的很明显的一个好处就是可以使用委托这种弱关联关系从而很方便地整体替换算法（事实上我的任务二和三就是基于此实现了两个Sorter）
  + 明确表示了Geezer只有一个实例，运用了Singleton设计模式，将构造函数设置为private，通过getInstance返回theGeezer确保只有一个实例。
    + 本题还不明显，但是有些情况尤其是并发编程时，多个实例可能会相互影响，从而产生相互意想不到的bug。
  + 通过将Postion里指向一个linable，然后linable又可以改变其postion，配合sorter返回的plan真的是很巧妙进行了排序。（关于设计模式我还没有自学完，只觉得这种调用关系让我想起了visitor模式）

+ 部分可改进之处：

  + ```java
    public static Gourd getGourdByRank(int rank) {
    
            for (Gourd gourd : Gourd.values()) {
                if (gourd.rank() == rank) {
                    return gourd;
                }
            }
            return null;
    
        }
    ```

    注意到，sorter生成的plan是将权值进行对调，然后geezer是根据权值再进行寻找下标进行交换的。

    这种寻找下标的方式要求权重一定要是unique的，如1，5，3，5.每次寻找5都会返回下标1。大多数有重复权值的case会因此产生bug。

  + ```java
    for (String step : sortSteps) {
                this.execute(step);
                System.out.println(line.toString());
                log += line.toString();
                log += "\n[frame]\n";
            }
    ```

    ```java
     @Override
        public String toString() {
            String lineString = "\t";
            for (Position p : positions) {
                lineString += p.linable.toString();
            }
            return lineString;
        }
    ```

    注意到Geezer生成log的一段以及覆写的toString，会发现log的第一段记录，是已经进行了一次排序的结果。对于冒泡或者选择排序，这样无伤大雅。但是对于快排，可视化的结果会有些费解。

    ```java
    private void quickSort(int first, int last){
            if(first < last){
                int pivot = a[first];
                int splitPoint = partition(pivot, first, last);
                quickSort(first, splitPoint - 1);
                quickSort(splitPoint + 1, last); 
            }
        }
    ```

    以我写的代码为例，上述代码产生的log会展示quickSort(first，splitPoint - 1)的结果。不从最后开始就有点令人费解了。如何解决，我选择的是将Line的原始状态先append到字符串。

  + 注意到Gourd使用的是枚举，对于葫芦娃7兄弟肯定是够用的，但是对于64个小妖怪肯定不行的。所以在后来我将生成的64个小妖怪组织到了一个静态的MonsterArray,然后再仿照example的思路实现了getMonsterByColor。



## 任务二

类图如下

![](http://www.plantuml.com/plantuml/png/hLNDRXCn4BxdANpqqkOD42fmBL9HW4kKWxETRaqTUx5dWIegpuupdjbrTYCG4hbu_kVxPXplJK8JSJYuLUjCIce33bhSX8WGZokLVsjbFIgptP5sfLmmzpgRjZjbccAhidJ3IUc7wKzXobvJWQ_Ys62qlbEzCvwp4-UHqW7UPLVMNrQCwUDWswT_W4GHVQPbqGPF87hxFM01yc1ZOZ5NH-dxJPheoq7_czJ6coSeT8u93ThsHQsBLU4Za73kfJAVs3NEUQRHb5IYGv00xrEVUGMRinu-z_es7oHN0qn0g2joueIVq0u8uae8VSNjG_1fjgnOoLqbxrc-2ucsfNeMoFvBIccgSLtIox4N0crm8MhHC1HC4_bS-bHG8cY9OkFoolYGXnwhVNqnRW2z9FKsHlCysnlPRct2rnpqHXsAa2wia6FUFRydu9bBeT3COssECZXoor1oBCG7quBAwp9x1ySbpSKaXTJewARWYuGBtzn1ofDCG1tu3X-RsTAskx5Puen-GCqObIjdMPkjMceop3TNhP86Mo-1SSlDcdNWEoEmw4xN_cQQ5cSIHWvmG8sc6SkJitXamC41FARch_zrngl2Y7MLJyYwK5dLz-faPttvzYbxwBanGvkzsNyAahAr_mpHrNg2L5wXL_AS2CGQzGtu-_pL-0q0)

将example的enum和根据rank查找Gourd改成了根据颜色查找。同时写了两个ConcreteSorter就可以了。

[![asciicast](https://asciinema.org/a/1eMkw7C22wCD0JhCGDHYnopIL.svg)](https://asciinema.org/a/1eMkw7C22wCD0JhCGDHYnopIL)](https://asciinema.org/a/tb8eHwIBlx3GbAQVeL3P9dkiI)

~~这里选用的是SelectSort，根据颜色排序是按rgb的总和来的。但是因为terminal宽度温度没有录全。~~

这里依然选用SelectSort，**为了做出渐变的效果，重新定义了排序的value值，以及重写随机颜色生成的代码**

**这段视频是用我本机的代码直接改录，没有重新上传到github，所以github上的打M2Tag的代码没有重新定义排序的value值，其余一样。但是M3是重新组织上传了的。**

## 任务三

![](http://www.plantuml.com/plantuml/png/hLJDRXCn4BxdAURefSaR85JWsaf50IvH3kvs6aozzcBFqbPHUNQSyJZhEX48YLns_kVxPfovZAG3JODTz5R726kqsDFQ1yAmNq3whS0u0htPidO1rkixbKoRBUXER7EMwYGfFkhn40PcoGM-5yUQWd4x6ArsaXqvJvGTqaro9Vrb8PW-JQP_-3T8AnWJBKF6EmOncf-UCf1x4wA8gJgLlZzA4tK-w7-JMZlzW9dEFf8cqx_0Abk1lY4BhxrSvhEuZdDkD9uIHFuK92ZxLA-y1Pir3h-Cwbed2aymyASQOaH6NX4iJdp2VY8ihaWuLloklOlDma8bxohvLkGR7tbZC8h0zb-bbE8Qovo-p9c5HE-j3wfev3EcUG2fzA5WYU155fkK1-ry6doeTlPLsmdLAQdt8UZdPdTikpAHtahGEnYo4C-iKM8-F7zCm9bPfj2riIP5QJfoIr1IB8PxtIDS6QTlBUvFQPvCif0wEdPpy5d2mfTVWpGGBQ_1Fov1K7VD-ZRRpVROE4hkE94Il63pTmdjFqbGgDhUxhHQCe-yNeymAc-kwIUlgS0ymC7Qtyrpb4n6B04MkT4yVJb8foS8BGxeA7P_lGI25_mHwoBF-60Hvw9-SmUxwAUt4SmmIci1rzxqFmL9sThVOBfOpQ3odNgpAZKakyPzYUvk6knl)

~~类图同2，我只是修改了Line覆写的toString方法。~~

更新：将Line改成Matrix，并将postion改为二维数组，同时修改Snake接受的排序对象。



[![asciicast](https://asciinema.org/a/tJ08Wzw839IU2WFvXxEGROQot.svg)](https://asciinema.org/a/tJ08Wzw839IU2WFvXxEGROQot)](https://asciinema.org/a/9QE6XfSbhPwTtCcMByaI9mH6h)

这是快排的8x8,我稍做了修改，不再是挖去pivot然后最后空出来的位置填上，而是将pivot一直参与交换，但本质还是快排没错。