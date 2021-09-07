package com.whp.SimpleLogin.color;

enum Color {
    MBLUE("§1"),//蓝色
    MGREEN("§2"),//绿色
    MBLUE_GREEN("§3"),//蓝绿色
    MRED("§4"),//红色
    MPRED("§5"),//品红色
    MGOLD("§6"),//金色
    MGREY("§7"),//灰色
    MGREY_GREEN("§8"),//灰绿色
    MSBLUE("§9"),//浅蓝色
    MYELLOW("§0"),//黄色
    MCYAN("§a"),//青色
    MPINK("§d"),//粉红色
    MWHITE("§f"),//白色
    MSIKY_BLUE("§b"),//天蓝色
    MSOLD("§l"),//加粗
    MSHADOW("§o");//阴影


    private String code;

    Color(String code) {
        this.code = code;
    }

}
