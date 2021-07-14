package com.wyb.test.java.java8;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 查询树形菜单
 *
 * @author Marcher丶
 */
public class Java8Tree {

    @Data
    private static class TreeNode {
        private int id;
        private int pid;
        private
        String value;
        private List<TreeNode> children;

        public TreeNode() {

        }

        TreeNode(final int id, final int pid, final String value) {
            this.id = id;
            this.pid = pid;
            this.value = value;
        }
    }

    private static List<TreeNode> init() {
        return Lists.newArrayList(
                new TreeNode(1, 0, "Code"),
                new TreeNode(2, 1, "Java"),
                new TreeNode(3, 1, "JavaScript"),
                new TreeNode(4, 3, "Node. js"),
                new TreeNode(5, 3, "Vue"),
                new TreeNode(6, 3, "React"),
                new TreeNode(7, 1, "Golang"),
                new TreeNode(8, 1, "Python"),
                new TreeNode(9, 8, "Python27"),
                new TreeNode(10, 8, "Python3 8")
        );
    }

    private static List<TreeNode> getChildNode(final TreeNode root, final List<TreeNode> treeNodes) {
        return treeNodes.stream().filter(entity -> Objects.equals(entity.getPid(), root.getId())).
                peek(entity -> entity.setChildren(getChildNode(entity, treeNodes))).collect(Collectors.toList());
    }


    public static void main(final String[] args) {

        final List<TreeNode> treeNodes = init();

        final List<TreeNode> tree = treeNodes.stream().filter(entity -> entity.getPid() == 0).
                peek(entity -> entity.setChildren(getChildNode(entity, treeNodes))).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(tree));

    }


    /**
     * 查询树形菜单
     * @return
     */
    public List<ApiMenu> listWithTree() {
        //1.查出所有分类
//        List<ApiMenu> apiMenulist = mongoTemplate.findAll(ApiMenu.class);

        List<ApiMenu> apiMenulist = Lists.newArrayList();

        //2.构成父子的树形结构
        List<ApiMenu> level1Menus = apiMenulist.stream().filter(apiMenu ->
                //查询所有的一级分类
                apiMenu.getParentId() == 0
        ).map(menu -> {
            //查询所有的一级分类下的子菜单
            menu.setChildren(getChildrens(menu, apiMenulist));
            return menu;
        }).sorted((menu1, menu2) -> {
            //排序
            return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
        }).collect(Collectors.toList());

        return level1Menus;
    }

    /**
     * 递归查询所有菜单的子菜单
     *
     * @param root 一级分类菜单
     * @param all  所有菜单
     * @return
     */
    private List<ApiMenu> getChildrens(ApiMenu root, List<ApiMenu> all) {
        List<ApiMenu> collect = all.stream().filter(categoryEntity -> {
            return categoryEntity.getParentId() == root.getId();
        }).map(categoryEntity -> {
            categoryEntity.setChildren(getChildrens(categoryEntity, all));
            return categoryEntity;
        }).sorted((menu1, menu2) -> {
            return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
        }).collect(Collectors.toList());
        return collect;
    }

}
