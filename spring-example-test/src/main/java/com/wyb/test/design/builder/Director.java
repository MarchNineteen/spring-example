package com.wyb.test.design.builder;

/**
 * 实现实例
 *
 * @author Kunzite
 */
public class Director {

    private DefaultBuild defaultBuild;

    public Director(DefaultBuild defaultBuild) {
        this.defaultBuild = defaultBuild;
    }

    public void build() {
        defaultBuild.part1();
        defaultBuild.part2();
    }

    public DefaultBuild getResult() {
        return defaultBuild;
    }
}
